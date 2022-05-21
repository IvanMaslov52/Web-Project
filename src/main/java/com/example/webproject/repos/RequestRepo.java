package com.example.webproject.repos;

import com.example.webproject.domain.History;
import com.example.webproject.domain.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepo extends CrudRepository<Request,Long> {
 Request findRequestById(Long id);
}
