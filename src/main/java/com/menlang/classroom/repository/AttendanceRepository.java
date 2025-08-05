package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>, JpaSpecificationExecutor<Attendance> {
    @Query("""
    SELECT a.studentId, a.datetime, a.status
    FROM Attendance a
    WHERE a.academicYearId = :academicYearId
      AND a.classroom.id = :classroomId
      AND a.datetime BETWEEN :startDate AND :endDate
""")
    List<Attendance> findAttendanceByClassroom(
            @Param("classroomId") Long classroomId,
            @Param("academicYearId") Long academicYearId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


}
