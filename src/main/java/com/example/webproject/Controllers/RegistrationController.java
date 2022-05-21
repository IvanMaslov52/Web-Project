package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Role;
import com.example.webproject.domain.User;
import com.example.webproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(StringConstant.SLREGISTRATION)
    public String show(){
        return StringConstant.REGISTRATION;
    }

    @PostMapping(StringConstant.SLREGISTRATION)
    public String addUser(User user , Map<String ,Object> model)//разбить на классы!
    {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.put("message","User exists!");
            return StringConstant.REGISTRATION;
        }
        user.setActive(true);
        //user.setRoles(Collections.singleton(Role.ADMIN));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return StringConstant.REDLOGIN;
    }
}
