package com.menlang.classroom.service.attendant;

import com.menlang.classroom.dto.attendance.AttendanceRequest;
import com.menlang.classroom.dto.attendance.AttendanceResponse;
import com.menlang.classroom.model.entities.Attendance;
import com.menlang.classroom.service.BaseService;

public interface AttendanceService extends BaseService<AttendanceRequest, AttendanceResponse, Attendance> {
}
