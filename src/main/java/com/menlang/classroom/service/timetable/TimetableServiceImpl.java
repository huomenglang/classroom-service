package com.menlang.classroom.service.timetable;
import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.timetable.TimetableMapper;
import com.menlang.classroom.dto.timetable.TimetableRequest;
import com.menlang.classroom.dto.timetable.TimetableResponse;
import com.menlang.classroom.model.entities.TimeTable;
import com.menlang.classroom.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService{
    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;

    @Override
    public TimetableResponse create(TimetableRequest dto) {
        log.info("received data create: {}",dto.classroomId());
        TimeTable timeTable=timetableMapper.toEntity(dto);
        log.info("classroom create: {}",timeTable.getClassroom().getId());
        try{
            return timetableMapper.toResponse(timetableRepository.save(timeTable));
        }catch (BadRequestException e){
            log.warn(e.getMessage());
            throw new BadRequestException("Unsuccess to Create Timetable.");
        }catch (RuntimeException e){
            log.warn("Runtime "+e.getMessage());
            throw new BadRequestException("Unsuccess to Create Timetable.");
        }

    }

    @Override
    public TimetableResponse update(Long id, TimetableRequest dto) {
        TimeTable timeTable=this.findTimetableById(id);
        timetableMapper.updateTimetable(dto,timeTable);
        try{
            return timetableMapper.toResponse(timetableRepository.save(timeTable));
        }catch (BadRequestException e){
            log.warn(e.getMessage());
            throw new BadRequestException("UnSuccess to Update Timetable.");
        }catch (RuntimeException e){
            log.warn("Runtime "+e.getMessage());
            throw new BadRequestException("UnSuccess to Update Timetable.");
        }

    }

    @Override
    public TimetableResponse delete(Long id) {
        TimeTable timeTable=this.findTimetableById(id);
        try{
            timetableRepository.deleteById(id);
            return timetableMapper.toResponse(timeTable);
        }catch (BadRequestException e){
            log.warn(e.getMessage());
            throw new BadRequestException("UnSuccess to Delete Timetable.");
        }catch (RuntimeException e){
            log.warn("Runtime "+e.getMessage());
            throw new BadRequestException("UnSuccess to Delete Timetable.");
        }
    }

    @Override
    public TimetableResponse getById(Long id) {
        TimeTable timeTable=this.findTimetableById(id);
        return timetableMapper.toResponse(timeTable);
    }

    @Override
    public List<TimetableResponse> getPageContent(Page<TimeTable> data) {
        return data.getContent().stream().map(timetableMapper::toResponse).toList();
    }

    @Override
    public Page<TimeTable> getAll(Map<String, String> params) {
        Pageable pageable= PageableParser.from(params);
        List<FilterBy> filter= QueryParamParser.parse(params);
        Specification<TimeTable> specification=new BaseSpecification<>(filter);
        return timetableRepository.findAll(specification,pageable);
    }

    private TimeTable findTimetableById(Long id ){
        return timetableRepository.findById(id).orElseThrow(()->new NotFoundException("Timetable Not found!"));
    }

    @Override
    public List<TimetableResponse> findTimetableByClassroom(Long classroomId, Long academicYearId) {
        List<TimeTable> classroomTimetables=timetableRepository.findTimeTablesByClassroom(classroomId,academicYearId);
        return classroomTimetables.stream().map(timetableMapper::toResponse).toList();
    }

    @Override
    public List<TimetableResponse> findTimetableByTeacher(Long teacherId, Long academicYearId) {
        List<TimeTable> timetablesOfTeacher=timetableRepository.findTimeTablesByClassroom(teacherId,academicYearId);
        return timetablesOfTeacher.stream().map(timetableMapper::toResponse).toList();
    }
}
