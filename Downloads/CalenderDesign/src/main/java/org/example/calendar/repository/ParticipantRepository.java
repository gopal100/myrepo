package org.example.calendar.repository;

import org.example.calendar.model.Participant;

import java.util.*;

public class ParticipantRepository {
    Map<String, Participant> participants = new HashMap<>(10);
    public void createParticipant(){
        for(int i =0; i< 10; i++){
            String id = UUID.randomUUID().toString();
            Participant participant = new Participant(id, "participant-"+i, "participant-"+i+"gmail.com", "20146");
            participants.putIfAbsent(id, participant);
        }
    }


    public Participant getParticipant(String participantId) {
        return participants.get(participantId);
    }

}