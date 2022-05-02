package com.example.webproject.repos;

import com.example.webproject.domain.Bill;
import com.example.webproject.domain.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<Card,Long> {
    Iterable<Card>  findAllByBill(Bill bill);
}
