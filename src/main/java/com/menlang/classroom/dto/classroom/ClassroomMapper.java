package com.menlang.classroom.dto.classroom;

import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.model.entities.Grade;
import com.menlang.classroom.repository.GradeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class ClassroomMapper {
   protected GradeRepository gradeRepository;

    public void ClassroomMapper(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Mapping(target = "grade", expression = "java(toGrade(request.gradeId()))")
    @Mapping(target = "academicYear",source = "academicYearId")
    @Mapping(target = "teacher",source = "teacherId")
    public abstract ClassRoom toEntity(ClassroomRequest request);

    @Named("toGrade")
    protected Grade toGrade(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new NotFoundException("Grade Not Found"));
    }

    public abstract ClassroomResponse toResponse(ClassRoom classRoom);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "grade", expression = "java(toGrade(request.gradeId()))")
    @Mapping(target = "academicYear",source = "academicYearId")
    @Mapping(target = "teacher",source = "teacherId")
    public abstract void toUpdateClassroom(ClassroomRequest request, @MappingTarget ClassRoom classRoom);

}
