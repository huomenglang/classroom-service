package com.menlang.classroom.dto.timeslot;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public record TimeslotRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @JsonFormat(pattern = "H:mm")
        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @JsonFormat(pattern = "H:mm")
        @NotNull(message = "End time is required")
        LocalTime endTime,

        @NotNull(message = "Ordinal is required")
        @Min(value = 1, message = "Ordinal must be at least 1")
        Integer ordinal,

        @NotNull(message = "Day of week is required")
        DayOfWeek dayOfWeek

) implements Serializable {
}
