package org.example.calendar.repository;

import org.example.calendar.model.*;

import java.time.LocalDateTime;
import java.util.*;

public class MeetingRepository {
    private List<Meeting> meetings = new ArrayList<>();
    private Map<String, List<MeetingParticipants>> meetingParticipantsDetails = new HashMap<>();
    private Map<String, MeetingLocation> meetingLocationMap =new HashMap<>();
    public void addMeetingParticipant(Participant participantId, boolean isMandatory, ParticipantResponse participantResponse, String meetingId) {
        meetingParticipantsDetails.putIfAbsent(meetingId, new ArrayList<>());
        meetingParticipantsDetails.get(meetingId).add(new MeetingParticipants(participantId, isMandatory, participantResponse));
    }

    public String createMeeting(String desc, LocalDateTime startTime, LocalDateTime endTIme,
                                Boolean isOnline, LocalDateTime reminderTime, Participant organizerId){
        String meetingId = UUID.randomUUID().toString();
        addMeetingParticipant(organizerId, true, ParticipantResponse.ACCEPTED, meetingId);
        Meeting meeting = new Meeting(meetingId, desc, startTime, endTIme, isOnline, MeetingStatus.CREATED,
                LocalDateTime.now(), reminderTime, organizerId);
        meetings.add(meeting);

        return meeting.getMeetingId();
    }

    public void addMeetingLocation(String meetingId, MeetingLocation meetingLocation){
        Optional<Meeting> meeting = meetings.stream().filter(e -> e.getMeetingId().equalsIgnoreCase(meetingId))
                .findFirst();

        if(meeting.isEmpty())
            throw new RuntimeException("Meeting id is not valid");
        meetingLocationMap.putIfAbsent(meetingId, meetingLocation);
    }

    public void deleteMeeting(String meetingId, String userId){
        Optional<Meeting> meeting = meetings.stream().filter(e -> e.getMeetingId().equalsIgnoreCase(meetingId))
                .findFirst();

        if(meeting.isEmpty())
            throw new RuntimeException("Meeting id is not valid");
        if(!meeting.get().getOrganizer().getParticipantId().equalsIgnoreCase(userId)){
            throw new RuntimeException("Meeting can be deleted by Organizer only");
        }
        meeting.get().setMeetingStatus(MeetingStatus.DELETED);
    }

    public void updateMeeting(String meetingId, String desc, LocalDateTime startTime,
                              LocalDateTime endTIme, String userId){
        Optional<Meeting> meeting = meetings.stream().filter(e -> e.getMeetingId().equalsIgnoreCase(meetingId))
                .findFirst();

        if(meeting.isEmpty())
            throw new RuntimeException("Meeting id is not valid");
        if(!meeting.get().getOrganizer().getParticipantId().equalsIgnoreCase(userId)){
            throw new RuntimeException("Meeting can be updated by Organizer only");
        }
        Meeting meetingData = meeting.get();
        meetingData.setMeetingStatus(MeetingStatus.UPDATED);
        meetingData.setStartTime(startTime);
        meetingData.setEndTIme(endTIme);
        meetingData.setDescription(desc);

    }

    public void createReminders(String meetingId){
        Optional<Meeting> meeting = meetings.stream().filter(e -> e.getMeetingId().equalsIgnoreCase(meetingId))
                .findFirst();

        if(meeting.isEmpty())
            throw new RuntimeException("Meeting id is not valid");
        List<Participant> acceptedParticipants = meetingParticipantsDetails.get(meetingId)
                .stream().filter(data ->
                        data.getParticipantResponse().compareTo(ParticipantResponse.ACCEPTED) == 0
                        || data.getParticipantResponse().compareTo(ParticipantResponse.TENTATIVE) == 0)
                .map(MeetingParticipants::getParticipant).toList();

        Reminders.getReminders().putIfAbsent(meetingId, acceptedParticipants);
    }

    public void changeParticipantStatus(String meetingId, String participantId, ParticipantResponse participantResponse){
        List<MeetingParticipants> meetingParticipants = meetingParticipantsDetails.get(meetingId);
        Optional<MeetingParticipants> meetingParticipant = meetingParticipants.stream().filter(data ->
                        data.getParticipant().getParticipantId().equalsIgnoreCase(participantId))
                .findFirst();
        meetingParticipant.orElseThrow().setParticipantResponse(participantResponse);
    }

}
