package com.example.webproject.repos;

import com.example.webproject.domain.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepo extends CrudRepository<History,Long> {
    Iterable<History> findAllByNumber(Long number);
    History findHistoryById(Long id);

}
