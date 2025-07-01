package com.menlang.classroom.dto.grade;

import com.menlang.classroom.model.entities.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    Grade toEntity(GradeRequest request);

    GradeResponse toResponse(Grade dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateEntity(GradeRequest request, @MappingTarget Grade grade);
}
