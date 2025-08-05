package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTable,Long>, JpaSpecificationExecutor<TimeTable> {
}
