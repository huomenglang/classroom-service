package com.menlang.classroom.service.reports;

import com.menlang.classroom.dto.attendance.studentAttendance.StudentAttendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceReportService {
    List<StudentAttendance> getStudentAttendance(
            Long classroomId,
            Long academicYearId,
            LocalDate startDate,
            LocalDate endDate
    );
}
