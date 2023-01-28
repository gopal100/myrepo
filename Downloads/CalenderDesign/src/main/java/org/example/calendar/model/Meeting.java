package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Meeting {
    private String meetingId;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTIme;
    private Boolean isOnline;
    private MeetingStatus meetingStatus;
    private LocalDateTime creationTime;
    private LocalDateTime reminderTime;
    private Participant organizer;
}
