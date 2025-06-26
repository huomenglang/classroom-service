package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import com.menlang.classroom.model.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "class_schedule")
public class TimeTable extends AuditEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classroom;

    @Column(name = "academic_year_id")
    private Long academicYeaId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Enumerated(EnumType.STRING)
    @Column(length = 15,name = "day_of_week")
    private DayOfWeek DayOfWeek;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

}