package com.menlang.classroom.dto.student;

import com.menlang.classroom.model.enums.Gender;
import lombok.Getter;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        String phoneNumber

) {
}
