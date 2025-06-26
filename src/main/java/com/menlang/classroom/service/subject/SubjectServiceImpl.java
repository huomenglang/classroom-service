package com.menlang.classroom.service.subject;

import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.paginate.BasePageResponse;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.SubjectMapper;
import com.menlang.classroom.dto.SubjectRequest;
import com.menlang.classroom.dto.SubjectResponse;
import com.menlang.classroom.model.entities.Subject;
import com.menlang.classroom.repository.SubjectRepository;
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
public class SubjectServiceImpl implements SubjectService {
    private static final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);
    private final SubjectRepository subjectRepository;
    private final SubjectMapper mapper;

    @Override
    public SubjectResponse create(SubjectRequest dto) {
        Subject subject = mapper.toEntity(dto);
        try {
            return mapper.toResponse(subjectRepository.save(subject));
        } catch (BadRequestException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }

    }

    @Override
    public SubjectResponse update(Long id, SubjectRequest dto) {
        Subject subject=this.findSubjectById(id);
        mapper.updateEntity(dto,subject);
        try{
            return mapper.toResponse(subjectRepository.save(subject));
        }catch (BadRequestException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }

    }

    @Override
    public SubjectResponse delete(Long id) {
        Subject subject=this.findSubjectById(id);
        try{
            subjectRepository.deleteById(id);
            return mapper.toResponse(subject);
        }catch (BadRequestException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        } catch (RuntimeException e) {
            log.info("Subject Created failed: {}" + e.getMessage());
            throw new BadRequestException("Unable to create subject");
        }
    }

    @Override
    public SubjectResponse getById(Long id) {
        Subject subject=this.findSubjectById(id);
        return mapper.toResponse(subject);

    }



    @Override
    public Page<Subject> getAll(Map<String, String> params) {
        Pageable pageable= PageableParser.from(params);
        List<FilterBy> filters= QueryParamParser.parse(params);
        Specification<Subject> spec=new BaseSpecification<>(filters);
        return subjectRepository.findAll(spec,pageable);
    }

    private Subject findSubjectById(Long id){
        return subjectRepository.findById(id).orElseThrow(()->new NotFoundException("Subject Not Found."));
    }

    @Override
    public List<SubjectResponse> getPageContent(Page<Subject> pageData){
        return pageData.getContent().stream().map(mapper::toResponse).toList();
    }
}
