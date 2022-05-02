package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.History;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private BillRepo accountRepo;
    @Autowired
    private HistoryRepo historyRepo;
    @GetMapping(StringConstant.SLHISTORY)
    public String show(@PathVariable("id")Long id, Model model)
    {
        Bill account = accountRepo.findAccountsById(id);
        List<History> history = (List<History>) historyRepo.findAllByNumber(account.getId());
        model.addAttribute("history",history);
        model.addAttribute("account",account);
        return StringConstant.HISTORY;
    }
    @DeleteMapping(StringConstant.SLHISTORY)
    public String delete(@RequestParam(name = "number" , required = false )Long number,@RequestParam(name = "description" ,required = false )String description,@RequestParam(name = "id1", required = false )Long id)
    {

        History history =historyRepo.findHistoryById(id);
        if(history.isFlag() == false)
            return StringConstant.SLINVALIDHISTORY;
        List<String> check = List.of(description.split(" "));
        if(check.get(0).equals(StringConstant.REPLENISHMENT) && check.size() == 3)
        {
           Bill account = accountRepo.findAccountsById(number);
           account.setMoney(account.getMoney() - Double.parseDouble(check.get(2)));
           accountRepo.save(account);
           historyRepo.delete(history);
        }
        if (check.get(0).equals(StringConstant.REPLENISHMENT) && check.size() > 3)
        {
            Bill senaccount = accountRepo.findAccountsById(number);
            Bill account = accountRepo.findAccountsById(Long.valueOf(check.get(5)));
            double a = Double.parseDouble(check.get(2));

            if (senaccount.getMoney() == null)
                senaccount.setMoney(-a);
            else
                senaccount.setMoney(senaccount.getMoney() - a);
            if (account.getMoney() == null)
                account.setMoney(a);
            else
                account.setMoney(account.getMoney() + a);
            accountRepo.save(account);
            accountRepo.save(senaccount);
            historyRepo.delete(history);
            History history1 = historyRepo.findHistoryById(id +1);
            historyRepo.delete(history1);




        }

        if (check.get(0).equals(StringConstant.TRANSLATION))
        {
            Bill senaccount = accountRepo.findAccountsById(number);
            Bill account = accountRepo.findAccountsById(Long.valueOf(check.get(3)));
            double a = Double.parseDouble(check.get(6));
            if (senaccount.getMoney() == null)
                senaccount.setMoney(a);
            else
                senaccount.setMoney(senaccount.getMoney() + a);
            if (account.getMoney() == null)
                account.setMoney(-a);
            else
                account.setMoney(account.getMoney() - a);

            accountRepo.save(account);
            accountRepo.save(senaccount);
            historyRepo.delete(history);
            History history1 = historyRepo.findHistoryById(id -1);
            historyRepo.delete(history1);
        }

         return StringConstant.REDHISTORY;
    }

}
