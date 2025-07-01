package com.menlang.classroom.service.classroom;

import com.menglang.common.library.exceptions.common.BadRequestException;
import com.menglang.common.library.exceptions.common.ConflictException;
import com.menglang.common.library.exceptions.common.NotFoundException;
import com.menglang.common.library.page.filter.FilterBy;
import com.menglang.common.library.page.parser.BaseSpecification;
import com.menglang.common.library.page.parser.PageableParser;
import com.menglang.common.library.page.parser.QueryParamParser;
import com.menlang.classroom.dto.classroom.ClassroomMapper;
import com.menlang.classroom.dto.classroom.ClassroomRequest;
import com.menlang.classroom.dto.classroom.ClassroomResponse;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.repository.ClassroomRepository;
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
public class ClassroomServiceImpl implements ClassroomService{

    private static final Logger log = LoggerFactory.getLogger(ClassroomServiceImpl.class);
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Override
    public ClassroomResponse create(ClassroomRequest dto) {
        if(isClassroomExist(dto.name(),0L)) throw new ConflictException("Classroom Already Exist!");

        ClassRoom classRoom=classroomMapper.toEntity(dto);
        try{
            return classroomMapper.toResponse(classroomRepository.save(classRoom));
        }catch (BadRequestException e){
            log.info("Unable to create classroom {} ",e.getMessage());
            throw new BadRequestException(e.getMessage());
        }catch (RuntimeException e){
            log.info("Unable to create runtime classroom {} ",e.getMessage());
        }
        return null;
    }

    @Override
    public ClassroomResponse update(Long id, ClassroomRequest dto) {
        if(isClassroomExist(dto.name(),id)) throw new ConflictException("Classroom Already Exist!");
        ClassRoom classRoom=this.findClassroomById(id);
        classroomMapper.toUpdateClassroom(dto,classRoom);
        try{
            return classroomMapper.toResponse(classroomRepository.save(classRoom));
        }catch (BadRequestException e){
            log.info("Unable to update classroom {} ",e.getMessage());
            throw new BadRequestException(e.getMessage());
        }catch (RuntimeException e){
            log.info("Unable to update runtime classroom {} ",e.getMessage());
        }
        return null;
    }

    @Override
    public ClassroomResponse delete(Long id) {
        ClassRoom classRoom=this.findClassroomById(id);
        try{
            return classroomMapper.toResponse(classRoom);
        }catch (BadRequestException e){
            log.info("Unable to delete classroom {} ",e.getMessage());
            throw new BadRequestException(e.getMessage());
        }catch (RuntimeException e){
            log.info("Unable to delete runtime classroom {} ",e.getMessage());
        }
        return null;
    }

    @Override
    public ClassroomResponse getById(Long id) {
        ClassRoom classRoom=this.findClassroomById(id);
        return classroomMapper.toResponse(classRoom);

    }

    @Override
    public List<ClassroomResponse> getPageContent(Page<ClassRoom> data) {
        return data.getContent().stream().map(classroomMapper::toResponse).toList();
    }

    @Override
    public Page<ClassRoom> getAll(Map<String, String> params) {
        Pageable pageable= PageableParser.from(params);
        List<FilterBy> filters= QueryParamParser.parse(params);
        Specification<ClassRoom> spec=new BaseSpecification<>(filters);
        return classroomRepository.findAll(spec,pageable);
    }

    private ClassRoom findClassroomById(Long id){
        return classroomRepository.findById(id).orElseThrow(()-> new NotFoundException("Classroom Not Found"));
    }

    private boolean isClassroomExist(String name,Long id){
        return this.classroomRepository.findClassroomByNameAndIdNotIn(name,id);
    }
}
