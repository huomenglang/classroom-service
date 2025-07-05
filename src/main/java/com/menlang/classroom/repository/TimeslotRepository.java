package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot,Long>, JpaSpecificationExecutor<Timeslot> {

    @Query("""
            SELECT CASE WHEN COUNT(t)>0 THEN true ELSE false END FROM Timeslot t WHERE t.name=?1 and t.dayOfWeek=?2 and t.id!=?3
            """)
    Boolean findDuplicateNameAndDayOfWeek(String name, DayOfWeek dayOfWeek, Long id);
}
