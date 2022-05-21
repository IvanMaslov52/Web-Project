package com.example.webproject.Jobs;

import com.example.webproject.domain.History;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.RequestRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TimeOutJob implements Job {
    @Autowired
    private RequestRepo requestRepo;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<String> str = List.of(jobExecutionContext.getJobDetail().getKey().toString().split("[.]"));
        Request request = requestRepo.findRequestById(Long.valueOf(str.get(1)));
        if(request.getStatus().equals("In progress")||request.getStatus().equals("Denied")|| request.getStatus().equals("Agreed"))
        {
        request.setStatus("TimeOut");
        requestRepo.save(request);
        }
    }
}
