package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import com.menlang.classroom.model.audit.BaseEntity;
import com.menlang.classroom.model.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendances",
        indexes = {@Index(name = "idx_student_id", columnList = "student_id"),},
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_id", "attendance_date", "time_slot_id"}))

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance extends AuditEntity<Long> implements Serializable {
    private Long studentId;
    private Long academicYearId;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classroom;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private Timeslot timeslot;

    @CreatedDate
    @Column(name = "attendance_date")
    private LocalDateTime datetime;
}
