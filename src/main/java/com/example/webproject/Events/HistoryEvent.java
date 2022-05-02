package com.example.webproject.Events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class HistoryEvent extends ApplicationEvent {

    private  String message;

    public HistoryEvent(Object source, String  message) {
        super(source);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }


}
