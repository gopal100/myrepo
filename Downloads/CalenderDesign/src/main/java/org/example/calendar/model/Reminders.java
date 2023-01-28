package org.example.calendar.model;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reminders {

    private Reminders(){}

    private static class CreateReminders{
        static final Map<String, List<Participant>> meetingReminders = new HashMap<>();
    }

    public static Map<String, List<Participant>> getReminders(){
        return CreateReminders.meetingReminders;
    }

}
