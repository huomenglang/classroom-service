package com.menlang.classroom.repository;

import com.menlang.classroom.model.entities.Attendance;
import com.menlang.classroom.model.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTable,Long>, JpaSpecificationExecutor<TimeTable> {

    @Query(value = """
    SELECT a.classroom_id, a.academic_year_id, a.subject_id,a.teacher_id,a.day_of_week,a.start_time,a.end_time
    FROM timetables a
    WHERE a.academic_year_id = :academicYearId
      AND a.classroom_id = :classroomId
     
""",nativeQuery = true)
    List<TimeTable> findTimeTablesByClassroom(
            @Param("classroomId") Long classroomId,
            @Param("academicYearId") Long academicYearId
    );

    @Query(value = """
    SELECT a.classroom_id, a.academic_year_id, a.subject_id,a.teacher_id,a.day_of_week,a.start_time,a.end_time
    FROM timetables a
    WHERE a.academic_year_id = :academicYearId
      AND a.teacher_id = :teacherId
    """,nativeQuery = true)
    List<TimeTable> findTimetablesByTeacherId(
            @Param("teacherId") Long teacherId,
            @Param("academicYearId") Long academicYearId
    );

}
