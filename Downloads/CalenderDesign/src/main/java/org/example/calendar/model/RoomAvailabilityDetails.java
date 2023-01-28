package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomAvailabilityDetails {
    private Map<Boolean, Rooms> availableRooms;
    private Map<Boolean, Rooms> blockedRooms;
}
