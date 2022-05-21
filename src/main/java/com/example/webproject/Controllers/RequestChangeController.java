package com.example.webproject.Controllers;

import com.example.webproject.Constants.Bank;
import com.example.webproject.Constants.StringConstant;
import com.example.webproject.Constants.Type;
import com.example.webproject.domain.Request;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.RequestRepo;
import com.example.webproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestChangeController {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BillRepo billRepo;
    @GetMapping(StringConstant.SLREQUESTCHANGE_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {
        model.addAttribute("State",StringConstant.statuslist());
        model.addAttribute("Bank", Bank.TakeList());
        model.addAttribute("request", requestRepo.findRequestById(id));
        model.addAttribute("Type", Type.TakeTypeList());
        return StringConstant.REQUESTCHANGED;
    }
    @PostMapping (StringConstant.SLREQUESTCHANGE_ID)
    public String add(@PathVariable("id")Long id, @RequestParam(name = "money" ,required = false )Double money, @RequestParam(name = "fio" ,required = false )String  fio, @RequestParam(name = "username" ,required = false )String username, @RequestParam(name = "bank" ,required = false )String bank,@RequestParam(name = "status" ,required = false )String status)
    {
        Request request = requestRepo.findRequestById(id);
        request.setUser_username(username);
        request.setStatus(status);
        request.getBillRequest().setBank(bank);
        if(money == null){
            request.getBillRequest().setMoney(0d);}
        request.getBillRequest().setMoney(money);
        request.getBillRequest().setFio(fio);
        request.getBillRequest().setUser(userRepo.findByUsername(username));
        requestRepo.save(request);
        return StringConstant.REDREQUEST;
    }
    @DeleteMapping (StringConstant.SLREQUESTCHANGE_ID)
    public String delete(@PathVariable("id")Long id, @RequestParam(name = "time" ,required = false )String time,@RequestParam(name = "CVV" ,required = false )String CVV,@RequestParam(name = "number" ,required = false )String number,@RequestParam(name = "billid" ,required = false )Long billid,@RequestParam(name = "status" ,required = false )String status)
    {
        Request request = requestRepo.findRequestById(id);
       request.setStatus(status);
       request.getCardRequest().setBill(billRepo.findAccountsById(billid));
       request.getCardRequest().setCVV(CVV);
        request.getCardRequest().setTime(time);
        request.getCardRequest().setNumber(number);
        request.getCardRequest().setFio(billRepo.findAccountsById(billid).getFio());
        requestRepo.save(request);
        return StringConstant.REDREQUEST;
    }
}
