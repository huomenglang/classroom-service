package com.menlang.classroom.dto.attendance;

import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menlang.classroom.dto.classroom.ClassRoomBaseResponse;
import com.menlang.classroom.dto.student.StudentResponse;
import com.menlang.classroom.dto.teacher.TeacherResponse;
import com.menlang.classroom.model.entities.Attendance;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.model.entities.Subject;
import com.menlang.classroom.model.entities.Timeslot;
import com.menlang.classroom.model.enums.Gender;
import com.menlang.classroom.repository.ClassroomRepository;
import com.menlang.classroom.repository.SubjectRepository;
import com.menlang.classroom.repository.TimeslotRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AttendanceMapper {
    @Autowired
    protected ClassroomRepository classroomRepository;

    @Autowired
    protected SubjectRepository subjectRepository;

    @Autowired
    protected TimeslotRepository timeslotRepository;

    public void AttendanceMapper(ClassroomRepository classroomRepository, SubjectRepository subjectRepository, TimeslotRepository timeslotRepository) {
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
        this.timeslotRepository = timeslotRepository;
    }

    @Mapping(target = "classroom", expression = "java(toClassroom(dto.classRoomId()))")
    @Mapping(target = "subject", expression = "java(toSubject(dto.subjectId()))")
    @Mapping(target = "timeslot", expression = "java(toTimeslot(dto.timeSlotId()))")
    public abstract Attendance toEntity(AttendanceRequest dto);


    @Mapping(target = "student",expression = "java(toStudentRes( entity.getStudentId()))")
    @Mapping(target = "teacher",expression = "java(toTeacherRes(entity.getTeacherId()))")
    @Mapping(target = "classroom",expression = "java(toClassroomRes(entity.getClassroom()))")
//    @Mapping(target = "timeslot",expression = "java(toTimeslotRes(entity.getTimeslot()))")
    public abstract AttendanceResponse toResponse(Attendance entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "classroom", expression = "java(toClassroom(dto.classRoomId()))")
    @Mapping(target = "subject", expression = "java(toSubject(dto.subjectId()))")
    @Mapping(target = "timeslot", expression = "java(toTimeslot(dto.timeSlotId()))")
    public abstract void updatedAttendance(AttendanceRequest dto, @MappingTarget Attendance attendance);

    @Named("toClassroom")
    protected ClassRoom toClassroom(Long classroomId){
        return classroomRepository.findById(classroomId).orElseThrow(()->new NotFoundException("Classroom Not Found!"));
    }

    @Named("toSubject")
    protected Subject toSubject(Long subjectId){
        return subjectRepository.findById(subjectId).orElseThrow(()->new NotFoundException("Subject Not Found!"));
    }

    @Named("toStudentRes")
    protected StudentResponse toStudentRes(Long studentId){
        return new StudentResponse(studentId,"Menglang","Huo", Gender.MALE,"012 0022554458");
    }

    @Named("toTeacherRes")
    protected TeacherResponse toTeacherRes(Long teacherId){
        return new TeacherResponse(teacherId,"MengLout");
    }

    @Named("toClassroomRes")
    protected ClassRoomBaseResponse toClassroomRes(ClassRoom classroom){
        return new ClassRoomBaseResponse(classroom.getId(),classroom.getName(),classroom.getGrade());
    }

    @Named("toTimeslot")
    protected Timeslot toTimeslot(Long timeslotId){
        return timeslotRepository.findById(timeslotId).orElseThrow(()->new NotFoundException("Timeslot Not Found!"));
    }

}
