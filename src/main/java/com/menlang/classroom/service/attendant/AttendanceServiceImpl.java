package com.menlang.classroom.service.attendant;
import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.attendance.AttendanceMapper;
import com.menlang.classroom.dto.attendance.AttendanceRequest;
import com.menlang.classroom.dto.attendance.AttendanceResponse;
import com.menlang.classroom.model.entities.Attendance;
import com.menlang.classroom.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{
    private static final Logger log = LoggerFactory.getLogger(AttendanceServiceImpl.class);
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponse create(AttendanceRequest dto) {
        Attendance attendance=attendanceMapper.toEntity(dto);
        try{
            return attendanceMapper.toResponse(attendanceRepository.save(attendance));
        }catch (BadRequestException e){
            log.error(" error create attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }catch (RuntimeException e){
            log.error(" error create attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }

    }

    @Override
    public AttendanceResponse update(Long id, AttendanceRequest dto) {
        Attendance attendance=this.findAttendanceById(id);
        attendanceMapper.updatedAttendance(dto,attendance);
        try{
            return attendanceMapper.toResponse(attendanceRepository.save(attendance));
        }catch (BadRequestException e){
            log.error(" error update attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }catch (RuntimeException e){
            log.error(" error update attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }
    }

    @Override
    public AttendanceResponse delete(Long id) {
        Attendance attendance=this.findAttendanceById(id);
        try{
            attendanceRepository.deleteById(id);
            return attendanceMapper.toResponse(attendance);
        }catch (BadRequestException e){
            log.error(" error delete attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }catch (RuntimeException e){
            log.error(" error delete attendance: {}",e.getMessage());
            throw new BadRequestException("Error Create attendance");
        }
    }

    @Override
    public AttendanceResponse getById(Long id) {
       Attendance attendance=this.findAttendanceById(id);
            return attendanceMapper.toResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getPageContent(Page<Attendance> data) {
        return data.getContent().stream().map(attendanceMapper::toResponse).toList();
    }

    @Override
    public Page<Attendance> getAll(Map<String, String> params) {
        Pageable pageable= PageableParser.from(params);
        List<FilterBy> filterByList=QueryParamParser.parse(params);
        Specification<Attendance> specification= new BaseSpecification<>(filterByList);
        return attendanceRepository.findAll(specification,pageable);
    }

    private Attendance findAttendanceById(Long id){
        return attendanceRepository.findById(id).orElseThrow(()->new NotFoundException("Attendance Not Found!"));
    }
}
