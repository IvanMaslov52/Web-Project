package com.example.webproject.Jobs;

import com.example.webproject.Publishers.HistoryEventPublisher;
import com.example.webproject.domain.History;
import com.example.webproject.repos.HistoryRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreateJob implements Job {

    @Autowired
    private HistoryEventPublisher historyEventPublisher;
    @Autowired
    private HistoryRepo historyRepo;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //System.out.println(jobExecutionContext.getJobDetail().getKey());//достаем группы и имя работы
        historyEventPublisher.publishEvent("Joba запущена");
       List<String> str = List.of(jobExecutionContext.getJobDetail().getKey().toString().split("[.]"));
        History history = historyRepo.findHistoryById(Long.valueOf(str.get(1)));
        if(history != null) {
            history.setFlag(false);
            historyRepo.save(history);
        }
        System.out.println(str.get(1));

    }
}
