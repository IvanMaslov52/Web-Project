package com.example.webproject.repos;

import com.example.webproject.domain.Bill;
import com.example.webproject.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepo extends CrudRepository<Bill,Long> {
    public List<Bill> findAccountsByUser(User user);
    public Bill findAccountsById(Long id);




}
