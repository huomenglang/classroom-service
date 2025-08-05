package com.menlang.classroom.dto.timetable;
import com.menglang.common.library.page.paginate.BasePageResponse;
import com.menlang.classroom.dto.academicYear.AcademicYearResponse;
import com.menlang.classroom.dto.classroom.ClassRoomBaseResponse;
import com.menlang.classroom.dto.classroom.ClassroomResponse;
import com.menlang.classroom.dto.subject.SubjectResponse;
import com.menlang.classroom.dto.teacher.TeacherResponse;
import com.menlang.classroom.model.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimetableResponse extends BasePageResponse {
    private Long id;
    private ClassRoomBaseResponse classroom;
    private AcademicYearResponse academicYear;
    private SubjectResponse subject;
    private TeacherResponse teacher;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
