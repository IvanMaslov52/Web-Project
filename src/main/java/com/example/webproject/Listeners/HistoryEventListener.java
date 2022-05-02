package com.example.webproject.Listeners;

import com.example.webproject.Events.HistoryEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HistoryEventListener implements ApplicationListener<HistoryEvent> {
    @Override
    public void onApplicationEvent(HistoryEvent event) {
        System.out.println("Recieved "+event.getMessage());

    }


}
