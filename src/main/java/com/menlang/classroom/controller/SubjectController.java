package com.menlang.classroom.controller;
import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.subject.SubjectRequest;
import com.menlang.classroom.dto.subject.SubjectResponse;
import com.menlang.classroom.model.entities.Subject;
import com.menlang.classroom.service.subject.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private static final Logger log = LoggerFactory.getLogger(SubjectController.class);
    private final SubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getById(@PathVariable Long id){
        return PageResponseHandler.success(subjectService.getById(id),null,"Get Successful");
    }

    @PostMapping
    public ResponseEntity<PageResponse> create(@Valid @RequestBody SubjectRequest dto){
        log.info("invalid data {}",dto.name());
        return PageResponseHandler.success(subjectService.create(dto),null,"Create Successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> update(@PathVariable Long id,@Valid @RequestBody SubjectRequest dto){
        return PageResponseHandler.success(subjectService.update(id,dto),null,"Update Successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse> delete(@PathVariable Long id){
        return PageResponseHandler.success(subjectService.delete(id),null,"Delete Successful");
    }

    @GetMapping("/get-all")
    public ResponseEntity<PageResponse> getAll(@RequestParam Map<String,String> params){
        Page<Subject> subjectPage=subjectService.getAll(params);
        List<SubjectResponse> subjectResponseList=subjectService.getPageContent(subjectPage);
        return PageResponseHandler.success(subjectResponseList,subjectPage,"Get Successful");
    }
}
