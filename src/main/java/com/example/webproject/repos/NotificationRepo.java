package com.example.webproject.repos;

import com.example.webproject.domain.Notification;
import com.example.webproject.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepo extends CrudRepository<Notification,Long> {
    Iterable<Notification> findAllByUser(User user);
    Notification findNotificationById(Long id);
}
