package com.menlang.classroom.dto.classroom;

import java.io.Serializable;

public record ClassroomRequest(
        String name,
        Long gradeId,
        Long academicYearId,
        Long teacherId,
        String description,
        Boolean enabled

) implements Serializable {
}
