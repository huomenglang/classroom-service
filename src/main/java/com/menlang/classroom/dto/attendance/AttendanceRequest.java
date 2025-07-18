package com.menlang.classroom.dto.attendance;
import com.menlang.classroom.model.enums.AttendanceStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AttendanceRequest(
        Long studentId,
        Long academicYearId,
        Long classRoomId,
        Long subjectId,
        Long teacherId,
        AttendanceStatus status,
        Long timeSlotId,
        LocalDateTime datetime
) implements Serializable {
}
