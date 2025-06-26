package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>, JpaSpecificationExecutor<Subject> {
}
