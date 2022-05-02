package com.example.webproject.Controllers;

import com.example.webproject.Constants.Bank;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Constants.Type;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.Card;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class AddCardController {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private BillRepo billRepo;
    @GetMapping(StringConstant.SLADDCARD_ID)
    public String show(@PathVariable("id")Long id,Model model)
    {

        model.addAttribute("Bill",billRepo.findAccountsById(id));
        model.addAttribute("type", Type.TakeTypeList());
        return StringConstant.ADDCARD;
    }
    @PostMapping(StringConstant.SLADDCARD_ID)
    public String add(@PathVariable("id")Long id, @RequestParam(name = "Type" ,required = false )String type)
    {
        Bill bill = billRepo.findAccountsById(id);
        Random random = new Random();
        String result = String.valueOf(Bank.GetValue(bill.getBank()));
        result += String.valueOf(Type.GetValue(type));

        for(int i = 0; i < 8; i++){
            result += String.valueOf(random.nextInt(10));
        }
        String CVV = "";
        for(int i = 0; i < 3; i++)
        {
            CVV += String.valueOf(random.nextInt(10));
        }
        String timenow = LocalDateTime.now().toString().substring(0 , 10);
        char[]  a = LocalDateTime.now().toString().substring(0 , 10).toCharArray();
        int b = a[3];
        b +=2;
        a[3] = (char) b;

        String str = timenow +" - "+ String.valueOf(a);
        Card card = new Card(result, bill.getFio(), CVV,str,bill);
        cardRepo.save(card);
        return StringConstant.SLREDCARDVIEW;
    }
}
