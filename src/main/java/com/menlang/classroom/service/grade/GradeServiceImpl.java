package com.menlang.classroom.service.grade;

import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.ConflictException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.grade.GradeMapper;
import com.menlang.classroom.dto.grade.GradeRequest;
import com.menlang.classroom.dto.grade.GradeResponse;
import com.menlang.classroom.model.entities.Grade;
import com.menlang.classroom.repository.GradeRepository;
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
public class GradeServiceImpl implements GradeService {
    private static final Logger log = LoggerFactory.getLogger(GradeServiceImpl.class);
    private final GradeRepository repository;
    private final GradeMapper mapper;

    @Override
    public GradeResponse create(GradeRequest dto) {
        Grade subject = mapper.toEntity(dto);
        if (this.findGradeByName(dto.grade(),0L)) throw new ConflictException("Grade Already Exist!");
        try {
            return mapper.toResponse(repository.save(subject));
        } catch (BadRequestException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }

    }

    @Override
    public GradeResponse update(Long id, GradeRequest dto) {
        Grade grade = this.findGradById(id);
        if (this.findGradeByName(dto.grade(),id)) throw new ConflictException("Grade Already Exist!");
        mapper.updateEntity(dto, grade);
        try {
            return mapper.toResponse(repository.save(grade));
        } catch (BadRequestException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }

    }

    @Override
    public GradeResponse delete(Long id) {
        Grade subject = this.findGradById(id);

        try {
            repository.deleteById(id);
            return mapper.toResponse(subject);
        } catch (BadRequestException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Grade Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }
    }

    @Override
    public GradeResponse getById(Long id) {
        Grade subject = this.findGradById(id);
        return mapper.toResponse(subject);

    }


    @Override
    public Page<Grade> getAll(Map<String, String> params) {
        Pageable pageable = PageableParser.from(params);
        List<FilterBy> filters = QueryParamParser.parse(params);
        Specification<Grade> spec = new BaseSpecification<>(filters);
        return repository.findAll(spec, pageable);
    }

    private Grade findGradById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Grade Not Found."));
    }

    @Override
    public List<GradeResponse> getPageContent(Page<Grade> pageData) {
        return pageData.getContent().stream().map(mapper::toResponse).toList();
    }

    private boolean findGradeByName(String name,Long id) {
        return repository.findGradeByName(name,id);
    }

}
