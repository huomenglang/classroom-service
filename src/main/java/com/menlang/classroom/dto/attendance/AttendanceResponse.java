package com.menlang.classroom.dto.attendance;
import com.menglang.common.library.page.paginate.BasePageResponse;
import com.menlang.classroom.dto.classroom.ClassRoomBaseResponse;
import com.menlang.classroom.dto.student.StudentResponse;
import com.menlang.classroom.dto.subject.SubjectResponse;
import com.menlang.classroom.dto.teacher.TeacherResponse;
import com.menlang.classroom.dto.timeslot.TimeslotResponse;
import com.menlang.classroom.model.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AttendanceResponse extends BasePageResponse implements Serializable {
    private Long id;
    private AttendanceStatus status;
    private StudentResponse student;
    private TeacherResponse teacher;
    private ClassRoomBaseResponse classroom;
    private SubjectResponse subject;
    private TimeslotResponse timeslot;
    private Long academicYearId;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
