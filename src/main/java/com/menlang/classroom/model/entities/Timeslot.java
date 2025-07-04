package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Timeslot extends AuditEntity<Long> implements Serializable {

    private String name; // e.g., "Period 1", "Morning Session"

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer ordinal; // For sorting (1, 2, 3, ...)

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // Optional, if schedule i

}
