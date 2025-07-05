package com.menlang.classroom.dto.timeslot;

import com.menglang.common.library.page.paginate.BasePageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimeslotResponse extends BasePageResponse {
    Long id;
    String name;
    LocalTime startTime;
    LocalTime endTime;
    Integer ordinal;
    DayOfWeek dayOfWeek;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String createdBy;
    String updatedBy;
}
