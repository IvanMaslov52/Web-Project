package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.NotificationRepo;
import com.example.webproject.repos.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationAnswerController {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private NotificationRepo notificationRepo;
    @GetMapping(StringConstant.SLNOTIFICATIONANSWER_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {
        model.addAttribute("Notification",notificationRepo.findNotificationById(id));
        return StringConstant.SLNOTIFICATIONANSWER;
    }
    @PostMapping(StringConstant.SLNOTIFICATIONANSWER_ID)
    public String add(@PathVariable("id")Long id)
    {
        Request request = requestRepo.findRequestById(notificationRepo.findNotificationById(id).getRequest().getId());
        if(request.getStatus().equals("Сreated")||request.getStatus().equals("TimeOut")){
        request.setStatus("Agreed");
        requestRepo.save(request);
        notificationRepo.delete(notificationRepo.findNotificationById(id));}

        return StringConstant.REDNOTIFICATION;
    }
    @DeleteMapping(StringConstant.SLNOTIFICATIONANSWER_ID)
    public String delete(@PathVariable("id")Long id)
    {
        Request request = requestRepo.findRequestById(notificationRepo.findNotificationById(id).getRequest().getId());
        if(request.getStatus().equals("Сreated")||request.getStatus().equals("TimeOut")){
        request.setStatus("Denied");
        requestRepo.save(request);
        notificationRepo.delete(notificationRepo.findNotificationById(id));}
        return StringConstant.REDNOTIFICATION;
    }
}
