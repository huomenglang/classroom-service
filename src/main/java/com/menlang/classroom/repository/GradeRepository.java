package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> , JpaSpecificationExecutor<Grade> {
    @Query("""
            SELECT CASE WHEN COUNT(s)>0 THEN true ELSE false END FROM Grade s WHERE s.grade=?1 and s.id!=?2
            """)
    Boolean findGradeByName(String subject,Long id);
}
