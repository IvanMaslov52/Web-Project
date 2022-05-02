package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.User;
import com.example.webproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping(StringConstant.SLPROFILE)
    public String main(@AuthenticationPrincipal User user, Model model)
    {
        model.addAttribute("username",user.getUsername());
        return StringConstant.PROFILE;
    }
    @PostMapping(StringConstant.SLPROFILE)
    public String change(@AuthenticationPrincipal User user,@RequestParam(name = "password" , required = false )String  password )
    {
        User authuser = userRepo.findByUsername(user.getUsername());
        authuser.setPassword(password);
        userRepo.save(authuser);
        return StringConstant.REDMAIN;
    }
}
