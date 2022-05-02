package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InvalidSessionControllers {
    @GetMapping(StringConstant.SLINVALIDSESSION)
    public String show()
    {
        return StringConstant.INVALIDSESSION;
    }
}
