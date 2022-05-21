package com.example.webproject.Controllers;

import com.example.webproject.Component.JobScheduleCreator;
import com.example.webproject.Constants.Bank;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Constants.Type;
import com.example.webproject.Jobs.TimeOutJob;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.CardRequest;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.CardRepo;
import com.example.webproject.repos.CardRequestRepo;
import com.example.webproject.repos.RequestRepo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Controller
public class AddCardController {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private CardRequestRepo cardRequestRepo;
    @Autowired
    private JobScheduleCreator jobScheduleCreator;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ApplicationContext context;
    @GetMapping(StringConstant.SLADDCARD_ID)
    public String show(@PathVariable("id")Long id,Model model)
    {

        model.addAttribute("Bill",billRepo.findAccountsById(id));
        model.addAttribute("type", Type.TakeTypeList());
        return StringConstant.ADDCARD;
    }
    @PostMapping(StringConstant.SLADDCARD_ID)
    public String add(@PathVariable("id")Long id, @RequestParam(name = "Type" ,required = false )String type) throws SchedulerException {
        Bill bill = billRepo.findAccountsById(id);
        Random random = new Random();
        String result = String.valueOf(Bank.GetValue(bill.getBank()));
        result += String.valueOf(Type.GetValue(type));

        for(int i = 0; i < 8; i++){
            result += String.valueOf(random.nextInt(10));
        }
        String CVV = "";
        for(int i = 0; i < 3; i++)
        {
            CVV += String.valueOf(random.nextInt(10));
        }
        String timenow = LocalDateTime.now().toString().substring(0 , 10);
        char[]  a = LocalDateTime.now().toString().substring(0 , 10).toCharArray();
        int b = a[3];
        b +=2;
        a[3] = (char) b;

        String str = timenow +" - "+ String.valueOf(a);
        CardRequest card = new CardRequest(result, bill.getFio(), CVV,str,bill);

       CardRequest card1 = cardRequestRepo.save(card);
       // cardRequestRepo.save(card);
        Request request =  requestRepo.save(new Request(card.getBill().getUser().getUsername(),"In progress",LocalDateTime.now(),card1));

        LocalDateTime local  = LocalDateTime.now().plusSeconds(60);
        Instant instant = local.atZone(ZoneId.systemDefault()).toInstant();
        scheduler.start();
        scheduler.scheduleJob(jobScheduleCreator.createJob(TimeOutJob.class, true, context, String.valueOf(requestRepo.save(request).getId()), "TimeOut"),jobScheduleCreator.createSimpleTrigger(UUID.randomUUID().toString(),"Creation", Date.from(instant) ));

        return StringConstant.SLREDCARDVIEW;
    }
}
