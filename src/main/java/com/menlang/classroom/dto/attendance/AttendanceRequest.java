package com.menlang.classroom.dto.attendance;
import com.menlang.classroom.model.enums.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record AttendanceRequest(

        @NotBlank(message = "Student Must be be Blank!")
        Long studentId,
        @NotBlank(message = "Academic Year Must be be Blank!")
        Long academicYearId,
        @NotBlank(message = "Classroom Must be be Blank!")
        Long classRoomId,
        @NotBlank(message = "Subject Must be be Blank!")
        Long subjectId,
        @NotBlank(message = "Teacher Must be be Blank!")
        Long teacherId,
        @NotBlank(message = "Status Must be be Blank!")
        AttendanceStatus status,
        @NotBlank(message = "Timeslot Must be be Blank!")
        Long timeslotId
//        LocalDateTime datetime
) implements Serializable {
}
