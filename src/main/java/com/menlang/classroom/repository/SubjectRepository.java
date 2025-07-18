package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>, JpaSpecificationExecutor<Subject> {

    @Query("""
            SELECT CASE WHEN COUNT(s)>0 THEN true ELSE false END FROM Subject s WHERE s.name=?1
            """)
    Boolean findSubjectByName(String subject);
}
