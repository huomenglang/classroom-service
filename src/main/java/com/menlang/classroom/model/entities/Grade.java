package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "grades")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade extends AuditEntity<Long> implements Serializable {

    @Column(length = 20,unique=true)
    private String grade;

    @Column(length = 100)
    private String description;
}
