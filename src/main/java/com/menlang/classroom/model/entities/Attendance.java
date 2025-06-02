package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.BaseEntity;
import com.menlang.classroom.model.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance extends BaseEntity<Long> {
    private Long studentId;
    private Long academicYearId;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    private Long teacherId;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @CreatedDate
    @Column(name = "attendance_date")
    private LocalDate date;
}
