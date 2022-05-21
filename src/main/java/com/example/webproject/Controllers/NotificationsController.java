package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.User;
import com.example.webproject.repos.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NotificationsController {
    @Autowired
    private NotificationRepo notificationRepo;

    @GetMapping(StringConstant.SLNOTIFICATION)
    public String show(@AuthenticationPrincipal User user, Model model)
    {
        model.addAttribute("Notifications",notificationRepo.findAllByUser(user));
        return StringConstant.NOTIFICATION;
    }
}
