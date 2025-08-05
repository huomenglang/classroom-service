package com.menlang.classroom.dto.timetable;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import com.menlang.classroom.model.enums.DayOfWeek;

import java.io.Serializable;
import java.time.LocalTime;

public record TimetableRequest(

        @NotNull(message = "Classroom ID is required")
        Long classroomId,

        @NotNull(message = "Academic Year ID is required")
        Long academicYearId,

        @NotNull(message = "Subject ID is required")
        Long subjectId,

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotNull(message = "Day of Week is required")
        DayOfWeek dayOfWeek,

        @JsonFormat(pattern = "H:mm")
        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @JsonFormat(pattern = "H:mm")
        @NotNull(message = "End time is required")
//        @Future(message = "End time must be in the future")
        LocalTime endTime,

        @Size(max = 255, message = "Description must not exceed 255 characters")
        String description

) implements Serializable {
}
