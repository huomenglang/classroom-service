package com.menlang.classroom.service.reports;

import com.menlang.classroom.dto.attendance.studentAttendance.StudentAttendance;
import com.menlang.classroom.model.entities.Attendance;
import com.menlang.classroom.model.enums.AttendanceStatus;
import com.menlang.classroom.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceReportServiceImpl implements AttendanceReportService {
    private final AttendanceRepository attendanceRepository;

    @Override
    public List<StudentAttendance> getStudentAttendance(Long classroomId, Long academicYearId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances=attendanceRepository.findAttendanceByClassroom(classroomId,academicYearId,startDate,endDate);

        //group student by studentId->group by date
        Map<Long,Map<LocalDate,List<AttendanceStatus>>> grouped=attendances.stream()
                .collect(Collectors.groupingBy(Attendance::getStudentId,
                        Collectors.groupingBy(Attendance::getDatetime,
                                Collectors.mapping(Attendance::getStatus,
                                        Collectors.toList()))));

        List<StudentAttendance> results = new ArrayList<>();
        for (Map.Entry<Long,Map<LocalDate,List<AttendanceStatus>>> studentEntry: grouped.entrySet()){
            Long studentId=studentEntry.getKey();
            Map<LocalDate,List<AttendanceStatus>> dailyMap=studentEntry.getValue();

            double total=0;
            for (List<AttendanceStatus> statuses: dailyMap.values()){
                long  pCount=statuses.stream().filter(s->s==AttendanceStatus.P).count();
                if(pCount>=2){
                    total+=0.5;
                }else if(pCount==0){
                    total+=1;
                }
            }
            results.add(new StudentAttendance(studentId,total));
        }
        return results;
    }
}
