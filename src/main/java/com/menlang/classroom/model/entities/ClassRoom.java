package com.menlang.classroom.model.entities;


import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "classromm")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassRoom extends AuditEntity<Long> implements Serializable {

    @Column(unique = true, nullable = false,length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @Column(nullable = false)
    private Long academicYear;

    private Long teacher;

    private String description;

    private Boolean enabled;
}
