package com.menlang.classroom.service.timeslot;

import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.ConflictException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.timeslot.TimeslotMapper;
import com.menlang.classroom.dto.timeslot.TimeslotRequest;
import com.menlang.classroom.dto.timeslot.TimeslotResponse;
import com.menlang.classroom.model.entities.Timeslot;
import com.menlang.classroom.repository.TimeslotRepository;
import com.menlang.classroom.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TimeslotServiceImpl implements TimeslotService {
    private static final Logger log = LoggerFactory.getLogger(TimeslotServiceImpl.class);
    private final TimeslotMapper mapper;
   private final TimeslotRepository repository;

    @Override
    public TimeslotResponse create(TimeslotRequest dto) {
        if(findDuplicateTimeslot(dto.name(),dto.dayOfWeek(),0L)) throw new ConflictException("Timeslot already Exist!");
        Timeslot timeslot=mapper.toEntity(dto);
        try{
            return mapper.toResponse(repository.save(timeslot));
        }catch (BadRequestException e){
            log.info("timeslot error: {}",e.getMessage());
           throw new BadRequestException("Cannot Create Timeslot");
        }catch (RuntimeException e){
            log.info("timeslot error: {}",e.getMessage());
            throw new BadRequestException("Cannot Create Timeslot");
        }

    }

    @Override
    public TimeslotResponse update(Long id, TimeslotRequest dto) {
        Timeslot timeslot=this.findTimeslotById(id);
        mapper.updateTimeSlotDto(dto,timeslot);
        try{
            return mapper.toResponse(repository.save(timeslot));
        }catch (BadRequestException e){
            log.info("timeslot error: {}",e.getMessage());
            throw new BadRequestException("Cannot Create Timeslot");
        }catch (RuntimeException e){
            log.info("timeslot error: {}",e.getMessage());
            throw new BadRequestException("Cannot Create Timeslot");
        }

    }

    @Override
    public TimeslotResponse delete(Long id) {
        Timeslot timeslot=this.findTimeslotById(id);
        try{
            repository.deleteById(id);
            return mapper.toResponse(timeslot);
        }catch (BadRequestException e){
            log.info("timeslot error: {}",e.getMessage());
            throw new BadRequestException("Cannot Create Timeslot");
        }catch (RuntimeException e){
            log.info("timeslot error: {}",e.getMessage());
            throw new BadRequestException("Cannot Create Timeslot");
        }
    }

    @Override
    public TimeslotResponse getById(Long id) {
        Timeslot timeslot=this.findTimeslotById(id);
        return mapper.toResponse(timeslot);
    }

    @Override
    public List<TimeslotResponse> getPageContent(Page<Timeslot> data) {
        return data.getContent().stream().map(mapper::toResponse).toList();
    }

    @Override
    public Page<Timeslot> getAll(Map<String, String> params) {
        Pageable pageable= PageableParser.from(params);
        List<FilterBy> filters= QueryParamParser.parse(params);
        Specification<Timeslot> spec=new BaseSpecification<>(filters);
        return repository.findAll(spec,pageable);
    }

    private Timeslot findTimeslotById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Timeslot Not Found!"));
    }

    private boolean findDuplicateTimeslot(String name, DayOfWeek dayOfWeek,Long id){
        log.info("day of week: {}",dayOfWeek.name());
        return repository.findDuplicateNameAndDayOfWeek(name, dayOfWeek, id);
    }
}
