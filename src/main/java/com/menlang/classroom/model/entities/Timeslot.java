package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "dayOfWeek"})
        }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Timeslot extends AuditEntity<Long> implements Serializable {

    @Column(nullable = false,length = 100)
    private String name; // e.g., "Period 1", "Morning Session"

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private Integer ordinal; // For sorting (1, 2, 3, ...)

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // Optional, if schedule i

}
