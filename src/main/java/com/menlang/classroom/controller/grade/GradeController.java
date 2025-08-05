package com.menlang.classroom.controller.grade;

import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.grade.GradeRequest;
import com.menlang.classroom.dto.grade.GradeResponse;
import com.menlang.classroom.model.entities.Grade;
import com.menlang.classroom.service.grade.GradeService;
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
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class    GradeController {
    private static final Logger log = LoggerFactory.getLogger(GradeController.class);
    private final GradeService gradeService;

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getById(@PathVariable Long id){
        return PageResponseHandler.success(gradeService.getById(id),null,"Get Successful");
    }

    @PostMapping
    public ResponseEntity<PageResponse> create(@Valid @RequestBody GradeRequest dto){
        log.info("invalid data {}",dto.grade());
        return PageResponseHandler.success(gradeService.create(dto),null,"Create Successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> update(@PathVariable Long id,@Valid @RequestBody GradeRequest dto){
        return PageResponseHandler.success(gradeService.update(id,dto),null,"Update Successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse> delete(@PathVariable Long id){
        return PageResponseHandler.success(gradeService.delete(id),null,"Delete Successful");
    }

    @GetMapping("/get-all")
    public ResponseEntity<PageResponse> getAll(@RequestParam Map<String,String> params){
        Page<Grade> gradePage=gradeService.getAll(params);
        List<GradeResponse> gradeResponseList=gradeService.getPageContent(gradePage);
        return PageResponseHandler.success(gradeResponseList,gradePage,"Get Successful");
    }
}
