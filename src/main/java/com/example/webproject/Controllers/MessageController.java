package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Notification;
import com.example.webproject.repos.NotificationRepo;
import com.example.webproject.repos.RequestRepo;
import com.example.webproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RequestRepo requestRepo;
    @GetMapping(StringConstant.SLMESSAGE_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {
        model.addAttribute("username",requestRepo.findRequestById(id).getUser_username());
        model.addAttribute("id",id);
        return StringConstant.SLMESSAGE;
    }
    @PostMapping(StringConstant.SLMESSAGE_ID)
    public String add(@PathVariable("id")Long id, @RequestParam(name = "message" ,required = false )String message)
    {
        notificationRepo.save(new Notification(userRepo.findByUsername(requestRepo.findRequestById(id).getUser_username()),message,requestRepo.findRequestById(id)));
        return StringConstant.REDREQUEST;
    }

}
