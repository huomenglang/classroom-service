package com.menlang.classroom.dto.classroom;

import com.menglang.common.library.page.paginate.BasePageResponse;
import com.menlang.classroom.model.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClassroomResponse extends BasePageResponse {
    private String id;
    private String name;
    private Grade grade;
    private Long academicYear;
    private Long teacher;
    private String description;
    private Boolean enabled;
}
