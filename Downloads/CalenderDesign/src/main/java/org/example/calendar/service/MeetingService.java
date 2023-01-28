package org.example.calendar.service;

import org.example.calendar.model.MeetingLocation;
import org.example.calendar.model.Participant;
import org.example.calendar.model.ParticipantResponse;
import org.example.calendar.repository.MeetingRepository;
import org.example.calendar.repository.ParticipantRepository;

import java.time.LocalDateTime;

public class MeetingService {

    private MeetingRepository meetingRepository = new MeetingRepository();
    private ParticipantRepository participantRepository = new ParticipantRepository();
    public String createMeeting(String desc, LocalDateTime startTime, Integer timeDuration,
                                Boolean isOnline, Integer reminderTimeDuration, Participant organizerId){
        LocalDateTime endTime = startTime.plusMinutes(timeDuration);
        LocalDateTime reminderTime = endTime.minusMinutes(reminderTimeDuration);
        return meetingRepository.createMeeting(desc, startTime, endTime, isOnline, reminderTime, organizerId);
    }

    public boolean addMeetingParticipant(String participantId, boolean isMandatory, ParticipantResponse participantResponse, String meetingId){
        Participant participant = participantRepository.getParticipant(participantId);
        meetingRepository.addMeetingParticipant(participant, isMandatory, participantResponse, meetingId);
        return true;
    }

    public void updateMeetingDetails(String meetingId, String desc, LocalDateTime startTime,
                                        Integer timeDuration, String userId){
        LocalDateTime endTime = startTime.plusMinutes(timeDuration);
        LocalDateTime reminderTime = endTime.minusMinutes(timeDuration);
        meetingRepository.updateMeeting(meetingId, desc, startTime, endTime, userId);
    }

    public void deleteMeeting(String meetingId, String userId){
        meetingRepository.deleteMeeting(meetingId, userId);
    }

    public void updateParticipantResponse(String meetingId, String participantId){
        meetingRepository.changeParticipantStatus(meetingId, participantId, ParticipantResponse.ACCEPTED);
    }

    public void addLocation(String meetingId, MeetingLocation meetingLocation){
        meetingRepository.addMeetingLocation(meetingId, meetingLocation);
    }
}
