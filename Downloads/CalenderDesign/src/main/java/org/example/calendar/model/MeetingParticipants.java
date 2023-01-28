package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeetingParticipants {
    private Participant participant;
    private Boolean isOptionalAttendee;
    private ParticipantResponse participantResponse;
}
