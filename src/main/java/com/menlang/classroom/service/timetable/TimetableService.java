package com.menlang.classroom.service.timetable;

import com.menlang.classroom.dto.timetable.TimetableRequest;
import com.menlang.classroom.dto.timetable.TimetableResponse;
import com.menlang.classroom.model.entities.TimeTable;
import com.menlang.classroom.service.BaseService;

import java.util.List;

public interface TimetableService extends BaseService<TimetableRequest, TimetableResponse, TimeTable> {
    List<TimetableResponse> findTimetableByClassroom(Long classroomId,Long academicYearId);
    List<TimetableResponse> findTimetableByTeacher(Long teacherId,Long academicYearId);
}
