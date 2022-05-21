package com.example.webproject.repos;

import com.example.webproject.domain.BillRequest;
import org.springframework.data.repository.CrudRepository;

public interface BillRequestRepo extends CrudRepository<BillRequest,Long>
{
    BillRequest findBillRequestById(Long id);
}
