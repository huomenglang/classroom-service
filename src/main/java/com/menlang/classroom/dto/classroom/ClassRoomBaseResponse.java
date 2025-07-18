package com.menlang.classroom.dto.classroom;

import com.menlang.classroom.model.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClassRoomBaseResponse {
    private Long id;
    private String name;
    private Grade grade;
}
