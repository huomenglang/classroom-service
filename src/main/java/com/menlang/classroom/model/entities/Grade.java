package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "grade")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade extends AuditEntity<Long> {

    @Column(length = 20,unique=true)
    private String grade;

    @Column(length = 100)
    private String description;
}
