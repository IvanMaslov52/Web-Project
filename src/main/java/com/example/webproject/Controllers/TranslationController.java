package com.example.webproject.Controllers;

import com.example.webproject.Component.JobScheduleCreator;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Jobs.CreateJob;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.History;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.HistoryRepo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
public class TranslationController {
    @Autowired
    private BillRepo accountRepo;
    @Autowired
    private HistoryRepo historyRepo;
    @Autowired
    private JobScheduleCreator jobScheduleCreator;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ApplicationContext context;
    @GetMapping(StringConstant.SLTRANSLATION_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {
        Bill accounts =  accountRepo.findAccountsById(id);
        Iterable<Bill> it= accountRepo.findAll();
        List<Long> Id  = new ArrayList<>();
        for(Bill acc : it)
        {
            Id.add(acc.getId());
        }
        model.addAttribute("numbers",Id);
        model.addAttribute("Accounts",accounts);
        return StringConstant.SLTRANSLATION;
    }
    @PostMapping(StringConstant.SLTRANSLATION_ID)
    public String save(@PathVariable("id")Long id, @RequestParam(name = "money" ,required = false )Double money,@RequestParam(value = "number" ) Long number) throws SchedulerException {
        History senderhistory = new History(number , "replenishment on " + money +" from check: " + id,true);
        History receiverhistory = new History(id,"translation on id "+ number + " money send: " + money,true);
        Bill receiveraccount = accountRepo.findAccountsById(number);
        Bill senderaccount = accountRepo.findAccountsById(id);
        if(receiveraccount.getMoney() == null)
            receiveraccount.setMoney(money);
        receiveraccount.setMoney(receiveraccount.getMoney() + money);
        if (senderaccount.getMoney()==null)
            senderaccount.setMoney(-money);
        senderaccount.setMoney(senderaccount.getMoney() - money);
        accountRepo.save(senderaccount);
        accountRepo.save(receiveraccount);
        historyRepo.save(senderhistory);
        historyRepo.save(receiverhistory);
        LocalDateTime local  = LocalDateTime.now().plusSeconds(60);
        Instant instant = local.atZone(ZoneId.systemDefault()).toInstant();
        scheduler.start();
        scheduler.scheduleJob(jobScheduleCreator.createJob(CreateJob.class, true, context, String.valueOf(historyRepo.save(senderhistory).getId()), "Creator"),jobScheduleCreator.createSimpleTrigger(UUID.randomUUID().toString(),"Creation", Date.from(instant) ));
        scheduler.scheduleJob(jobScheduleCreator.createJob(CreateJob.class, true, context, String.valueOf(historyRepo.save(receiverhistory).getId()), "Creator"),jobScheduleCreator.createSimpleTrigger(UUID.randomUUID().toString(),"Creation", Date.from(instant) ));

        return StringConstant.REDMAIN;
    }

}
