package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "subjects")
public class Subject extends AuditEntity<Long> implements Serializable {

    @Column(length = 50, nullable = false,unique = true)
    private String name;

    @Column(length = 100)
    private String description;

}
