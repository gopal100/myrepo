package org.example.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private String participantId;
    private String name;
    private String email;
    private String mobileNo;
}
