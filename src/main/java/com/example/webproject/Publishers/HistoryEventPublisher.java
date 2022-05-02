package com.example.webproject.Publishers;

import com.example.webproject.Events.HistoryEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HistoryEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public void publishEvent(final String message)
    {
        HistoryEvent historyEvent = new HistoryEvent(this, message);
        applicationEventPublisher.publishEvent(historyEvent);
    }

}
