package com.menlang.classroom.controller.reports;

import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.attendance.studentAttendance.StudentAttendance;
import com.menlang.classroom.service.reports.AttendanceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendance-reports")
@RequiredArgsConstructor
public class StudentAttendanceController {
    private final AttendanceReportService attendanceReport;

    @GetMapping
    public ResponseEntity<PageResponse> getStudentAttendance(
            @RequestParam Long classroomId,
            @RequestParam Long academicYearId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate
            ){
        List<StudentAttendance> studentAttendance=attendanceReport.getStudentAttendance(classroomId,academicYearId,startDate,endDate);
        if (!studentAttendance.isEmpty()){
            return PageResponseHandler.success(studentAttendance,null,"Get Student Attendance success");
        }
         return PageResponseHandler.success(Collections.emptyList(),null,"No Attendance");
    }
}
