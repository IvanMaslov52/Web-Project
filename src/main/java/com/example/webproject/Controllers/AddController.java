package com.example.webproject.Controllers;

import com.example.webproject.Component.JobScheduleCreator;
import com.example.webproject.Constants.Bank;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Jobs.TimeOutJob;
import com.example.webproject.domain.Bill;

import com.example.webproject.domain.BillRequest;
import com.example.webproject.domain.Request;
import com.example.webproject.domain.User;
import com.example.webproject.repos.BillRepo;

import com.example.webproject.repos.BillRequestRepo;
import com.example.webproject.repos.RequestRepo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AddController {
    @Autowired
    private BillRepo accountRepo;
   @Autowired
   private RequestRepo requestRepo;
    @Autowired
    private BillRequestRepo billRequestRepo;
    @Autowired
    private JobScheduleCreator jobScheduleCreator;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ApplicationContext context;

    @GetMapping(StringConstant.SLADD)
    public String show(Model model)
    {
        model.addAttribute("Bank", Bank.TakeList());
        model.addAttribute("Account" , new Bill());
        return StringConstant.SLADD;
    }
    @PostMapping(StringConstant.SLADD)
    public String AddAccount(@AuthenticationPrincipal User user, @ModelAttribute("Account") @Valid Bill account , BindingResult bindingResult) throws SchedulerException {
        account.setUser(user);
        if(bindingResult.hasErrors())
            return StringConstant.SLADD;
        billControl(user,account);
        return StringConstant.REDMAIN;
    }

    private void billControl(User user, Bill account) throws SchedulerException {
        List<Bill> list = (List<Bill>) accountRepo.findAll();
        int i = 0;
        for(Bill el : list)
        {
            if(el.getUser().getUsername().equals(user.getUsername())){
                i++;}
        }
        if (i < 6)
        {
            accountRepo.save(account);
        }
        else {
            BillRequest billRequest =new BillRequest(account.getBank(),account.getFio(),account.getUser(),account.getMoney());
            BillRequest billRequest1 = billRequestRepo.save(billRequest);
           Request request =  requestRepo.save(new Request(account.getUser().getUsername(),"In progress",LocalDateTime.now(),billRequest1));

            LocalDateTime local  = LocalDateTime.now().plusSeconds(60);
            Instant instant = local.atZone(ZoneId.systemDefault()).toInstant();
            scheduler.start();
            scheduler.scheduleJob(jobScheduleCreator.createJob(TimeOutJob.class, true, context, String.valueOf(requestRepo.save(request).getId()), "TimeOut"),jobScheduleCreator.createSimpleTrigger(UUID.randomUUID().toString(),"Creation", Date.from(instant) ));

        }
    }

}
