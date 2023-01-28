package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OnlineMeetingDetails implements MeetingLocation{
    private String meetingId;
    private String meetingUrl;
    private String meetingJoiningDetails;

    @Override
    public String getMeetingLocation() {
        return getMeetingJoiningDetails();
    }
}
