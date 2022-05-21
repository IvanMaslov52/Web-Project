package com.example.webproject.Controllers;

import com.example.webproject.Constants.Bank;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Constants.Type;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.Card;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestAddController {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private UserRepo  userRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private CardRepo cardRepo;

    @GetMapping(StringConstant.SLREQUESTADD_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {

        model.addAttribute("Bank", Bank.TakeList());
        model.addAttribute("request", requestRepo.findRequestById(id));
        model.addAttribute("Type", Type.TakeTypeList());
        return StringConstant.REQUESTADD;
    }
    @PostMapping(StringConstant.SLREQUESTADD_ID)
    public String add(@PathVariable("id")Long id, @RequestParam(name = "money" ,required = false )Double money,@RequestParam(name = "fio" ,required = false )String  fio,@RequestParam(name = "username" ,required = false )String username,@RequestParam(name = "bank" ,required = false )String bank)
    {
        if(money == null){
        billRepo.save(new Bill(bank ,fio,userRepo.findByUsername(username),0d));}
        else{
        billRepo.save(new Bill(bank ,fio,userRepo.findByUsername(username),money));}
        Request  request = requestRepo.findRequestById(id);
        request.setStatus("Сreated");
        request.getBillRequest().setUser(userRepo.findByUsername(username));
        request.getBillRequest().setFio(fio);
        request.getBillRequest().setBank(bank);
        if(money == null){
        request.getBillRequest().setMoney(0d);}
        else {
        request.getBillRequest().setMoney(money);}
        requestRepo.save(request);

        return StringConstant.REDREQUEST;
    }
    @DeleteMapping(StringConstant.SLREQUESTADD_ID)
    public String delete(@PathVariable("id")Long id, @RequestParam(name = "time" ,required = false )String time,@RequestParam(name = "CVV" ,required = false )String CVV,@RequestParam(name = "number" ,required = false )String number,@RequestParam(name = "billid" ,required = false )Long billid)
    {
        Request request = requestRepo.findRequestById(id);
        cardRepo.save(new Card(number,billRepo.findAccountsById(billid).getFio(),CVV,time,billRepo.findAccountsById(billid)));
        request.setStatus("Сreated");
        request.getCardRequest().setBill(billRepo.findAccountsById(billid));
        request.getCardRequest().setTime(time);
        request.getCardRequest().setCVV(CVV);
        request.getCardRequest().setNumber(number);
        requestRepo.save(request);
        return StringConstant.REDREQUEST;
    }


}
