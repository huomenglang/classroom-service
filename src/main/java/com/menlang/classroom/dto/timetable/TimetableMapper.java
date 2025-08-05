package com.menlang.classroom.dto.timetable;


import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menlang.classroom.dto.academicYear.AcademicYearResponse;
import com.menlang.classroom.dto.teacher.TeacherResponse;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.model.entities.Subject;
import com.menlang.classroom.model.entities.TimeTable;
import com.menlang.classroom.repository.ClassroomRepository;
import com.menlang.classroom.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class TimetableMapper {

    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public void TimetableMapper(ClassroomRepository classroomRepository, SubjectRepository subjectRepository) {
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
    }

    @Mapping(target = "classroom",expression = "java(toClassroom(dto.classroomId()))")
    @Mapping(target = "subject",expression = "java(toSubject(dto.subjectId()))")
    public abstract TimeTable toEntity(TimetableRequest dto);

    @Mapping(target = "academicYear",expression = "java(toAcademicYear(res.getAcademicYearId()))")
    @Mapping(target = "teacher",expression = "java(toTeacher(res.getTeacherId()))")
    public abstract TimetableResponse toResponse(TimeTable res);


    @Mapping(target = "classroom",expression = "java(toClassroom(req.classroomId()))")
    @Mapping(target = "subject",expression = "java(toSubject(req.subjectId()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    public abstract void updateTimetable(TimetableRequest req, @MappingTarget TimeTable timeTable);

    @Named("toClassroom")
    protected ClassRoom toClassroom(Long classroomId){
        log.info("data classroomId: {}",classroomId);
        return classroomRepository.findById(classroomId).orElseThrow(()->new NotFoundException("Classroom Not Found"));
    }

    @Named("toSubject")
    protected Subject toSubject(Long subjectId){
        log.info("data subject: {}",subjectId);
        return subjectRepository.findById(subjectId).orElseThrow(()->new NotFoundException("Subject Not Found."));
    }

    @Named("toAcademicYear")
    protected AcademicYearResponse toAcademicYear(Long academicYearId){
        return new AcademicYearResponse(academicYearId,"2025-2026");
    }
    @Named("toTeacher")
    protected TeacherResponse toTeacher(Long teacherId){
        return new TeacherResponse(teacherId,"Menglang");
    }
}
