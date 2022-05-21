package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RequestController {
    @Autowired
    private RequestRepo requestRepo;
    @GetMapping(StringConstant.SLREQUEST)
    public String show(Model model){
        Iterable<Request> requests = requestRepo.findAll();
        model.addAttribute("requests",requests);
        return StringConstant.REQUEST;
    }
}
