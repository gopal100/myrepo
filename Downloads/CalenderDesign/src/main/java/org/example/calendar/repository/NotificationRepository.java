package org.example.calendar.repository;

import org.example.calendar.model.Participant;

import java.util.List;
import java.util.Map;

public class NotificationRepository {
    public void sendReminders(Map<String, List<Participant>> remiders){
        remiders.forEach((key, value) ->
                value.forEach(p -> System.out.println("Email sent to " + p.getEmail())));
    }
}
