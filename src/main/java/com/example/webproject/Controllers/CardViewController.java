package com.example.webproject.Controllers;

import com.example.webproject.Constants.StringConstant;
import com.example.webproject.domain.Bill;
import com.example.webproject.domain.Card;
import com.example.webproject.repos.BillRepo;
import com.example.webproject.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CardViewController {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private CardRepo cardrepo;
    @GetMapping(StringConstant.SLCARDVIEW_ID)
    public String show(@PathVariable("id")Long id, Model model)
    {
        Bill bill = billRepo.findAccountsById(id);
        Iterable<Card> cards = cardrepo.findAllByBill(bill);
        model.addAttribute("Bill",bill);
        model.addAttribute("Cards", cards);
        return StringConstant.CARDVIEW;
    }


}
