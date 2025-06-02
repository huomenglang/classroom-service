package com.menlang.classroom.model.entities;

import com.menlang.classroom.model.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "subject")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject extends AuditEntity<Long> {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

}
