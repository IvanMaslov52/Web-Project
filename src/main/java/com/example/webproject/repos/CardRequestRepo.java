package com.example.webproject.repos;

import com.example.webproject.domain.CardRequest;
import org.springframework.data.repository.CrudRepository;

public interface CardRequestRepo extends CrudRepository<CardRequest,Long> {
    CardRequest findCardRequestById(Long id);
}
