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
import java.util.Date;
import java.util.UUID;

@Controller
public class ReplenishmentController {
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
    @GetMapping(StringConstant.SLREPLENISHMENT_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {

        Bill accounts =  accountRepo.findAccountsById(id);
        model.addAttribute("Accounts",accounts);
        return StringConstant.SLREPLENISHMENT;
    }
    @PostMapping(StringConstant.SLREPLENISHMENT_ID)
    public String save(@PathVariable("id")Long id,@RequestParam(name = "money" ,required = false )Double money) throws SchedulerException {
        History history = new History(accountRepo.findAccountsById(id).getId() , "replenishment on " + money,true);
        Bill account = accountRepo.findAccountsById(id);
        if(account.getMoney() == null)
            account.setMoney(money);
        else
        account.setMoney(account.getMoney() + money);

        accountRepo.save(account);
        historyRepo.save(history);
        LocalDateTime local  = LocalDateTime.now().plusSeconds(60);
        Instant instant = local.atZone(ZoneId.systemDefault()).toInstant();
        scheduler.start();
        scheduler.scheduleJob(jobScheduleCreator.createJob(CreateJob.class, true, context, String.valueOf(historyRepo.save(history).getId()), "Creator"),jobScheduleCreator.createSimpleTrigger(UUID.randomUUID().toString(),"Creation", Date.from(instant) ));

        return StringConstant.REDMAIN;
    }
}

