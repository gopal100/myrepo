package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfflineMeetingDetails implements MeetingLocation{
    private String meetingId;
    private String roomDetails;

    @Override
    public String getMeetingLocation() {
        return getRoomDetails();
    }
}
