package com.menlang.classroom.service.seeding;
import com.menlang.classroom.dto.classroom.ClassroomMapper;
import com.menlang.classroom.dto.classroom.ClassroomRequest;
import com.menlang.classroom.dto.grade.GradeMapper;
import com.menlang.classroom.dto.grade.GradeRequest;
import com.menlang.classroom.dto.subject.SubjectMapper;
import com.menlang.classroom.dto.subject.SubjectRequest;
import com.menlang.classroom.dto.timetable.TimetableMapper;
import com.menlang.classroom.dto.timetable.TimetableRequest;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.model.entities.Grade;
import com.menlang.classroom.model.entities.Subject;
import com.menlang.classroom.model.entities.TimeTable;
import com.menlang.classroom.model.enums.DayOfWeek;
import com.menlang.classroom.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import static java.util.Map.entry;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedData {
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;
    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;
    private final GradeMapper gradeMapper;
    private final SubjectMapper subjectMapper;
    private final ClassroomMapper classroomMapper;


    @PostConstruct
    private void seeding(){
        seedingGrade();
        seedingSubject();
        seedingClassroom();
        seedTimetable();
    }

    private void seedingGrade(){
        log.info("Seeding Grade data .................");
        List<GradeRequest> gradeRequestList=new ArrayList<>();
        for (long i=1; i<=6;i++){
            GradeRequest gradeRequest=new GradeRequest("Grade "+i,"General Grade "+i);
            gradeRequestList.add(gradeRequest);
        }
        List<Grade> grades=gradeRequestList.stream().map(gradeMapper::toEntity).toList();
        try{
            gradeRepository.saveAll(grades);
        }catch (RuntimeException e){
            log.error("Error Seeding Grade: {}",e.getMessage());
        }
    }

    private void seedingClassroom(){
        List<ClassroomRequest> classRoomList=new ArrayList<>();


        Map<Long, String> grades = Map.ofEntries(
                entry(1L, "Grade 1A"),  entry(2L, "Grade 1B"),  entry(3L, "Grade 1C"),
                entry(4L, "Grade 2A"), entry(5L, "Grade 2B"), entry(6L, "Grade 2C"),
                entry(7L, "Grade 3A"), entry(8L, "Grade 3B"), entry(9L, "Grade 3C"),
                entry(10L, "Grade 4A"), entry(11L, "Grade 4B"), entry(12L, "Grade 4C"),
                entry(13L, "Grade 5A"), entry(14L, "Grade 5B"), entry(15L, "Grade 5C"),
                entry(16L, "Grade 6A"), entry(17L, "Grade 6B"), entry(18L, "Grade 6C")
        );


        for (long i=1;i<=18;i++){
            String classroomName=grades.get(i);
            Long gradeId=Math.ceilDiv(i,3);
            ClassroomRequest classroomRequest=new ClassroomRequest(classroomName,gradeId,1L,gradeId,"Classroom Sample",true);
            classRoomList.add(classroomRequest);
        }
        List<ClassRoom> classRooms=classRoomList.stream().map(classroomMapper::toEntity).toList();
        try {
            classroomRepository.saveAll(classRooms);
        }catch (RuntimeException e){
            log.error("Error seeding classroom {}",e.getMessage());
        }
    }

    private void seedingSubject(){
        log.info("Seeding Subject data .................");
        List<String> subjects=List.of("Math","Khmer","Physical","Chemistry","History","Computer");
        List<SubjectRequest> subjectRequest=new ArrayList<>();
        for (String subject: subjects){
            SubjectRequest subjectReq=new SubjectRequest(subject,"Subject "+subject);
            subjectRequest.add(subjectReq);
        }
        List<Subject> subjectsEntities=subjectRequest.stream().map(subjectMapper::toEntity).toList();
        try{
            subjectRepository.saveAll(subjectsEntities);
        }catch (RuntimeException e){
            log.error("Error Seeding Subject: {}",e.getMessage());
        }
    }

    private void seedTimetable() {
        log.info("Seeding Timetable .................");
        List<DayOfWeek> daysOfWeek = List.of(DayOfWeek.values());
        long totalClassrooms = 2L;

        for (long classroomId = 1; classroomId <= totalClassrooms; classroomId++) {
            Long academicYearId = 1L;
            List<TimetableRequest> timetableRequestList = new ArrayList<>();
            for (DayOfWeek day : daysOfWeek) {
                int time = 7;  // Reset time for each day
                int subjectId = 1;
                log.info("             Seeding on Day ........................{}",day);
                for (int j = 0; j < 3; j++) {
                    LocalTime startTime = LocalTime.of(time, 0);
                    LocalTime endTime = LocalTime.of(time + 2, 0);
                    Long teacherId = (long) subjectId;
                    String session = time < 12 ? "Morning Session" : "Evening Session";
                    TimetableRequest request = new TimetableRequest(
                            classroomId,
                            academicYearId,
                            (long) subjectId,
                            teacherId,
                            day,
                            startTime,
                            endTime,
                            session
                    );

                    timetableRequestList.add(request);
                    subjectId++;
                    if(day.equals(DayOfWeek.SATURDAY)|| day.equals(DayOfWeek.THURSDAY)|| day.equals(DayOfWeek.TUESDAY)){
                        time = j == 1 ? time + 4 : time + 2;
                    }else  time = j == 1 ? time + 6 : time + 2;

                }
            }

            List<TimeTable> entities = timetableRequestList.stream()
                    .map(timetableMapper::toEntity)
                    .toList();

          try{
              timetableRepository.saveAll(entities);
          }catch (RuntimeException e){
              log.warn("Error seeding timetable {}",e.getMessage());
          }
        }
    }

}
