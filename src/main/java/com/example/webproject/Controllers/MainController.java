package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.User;
import com.example.webproject.repos.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private BillRepo accountRepo;

    @GetMapping(StringConstant.SLMAIN)
    public String main(@AuthenticationPrincipal User user, Model model)
    {
      Iterable<Bill> accounts =  accountRepo.findAccountsByUser(user);
      model.addAttribute("accounts",accounts);
        return StringConstant.MAIN;
    }



}
