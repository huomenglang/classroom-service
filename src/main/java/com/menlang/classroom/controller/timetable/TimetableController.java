package com.menlang.classroom.controller.timetable;

import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.timetable.TimetableRequest;
import com.menlang.classroom.dto.timetable.TimetableResponse;
import com.menlang.classroom.model.entities.TimeTable;
import com.menlang.classroom.service.timetable.TimetableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/timetables")
@RequiredArgsConstructor
public class TimetableController {
    private final TimetableService timetableService;

    @PostMapping
    public ResponseEntity<PageResponse> create(@Valid @RequestBody TimetableRequest request){
        return PageResponseHandler.success(timetableService.create(request),null,"Create Time table Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getTimetableById(@PathVariable Long id){
        return PageResponseHandler.success(timetableService.getById(id),null,"Get Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> update(@PathVariable Long id,@Valid @RequestBody TimetableRequest request){
        return PageResponseHandler.success(timetableService.update(id,request),null,"Update Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse> delete(@PathVariable Long id){
        return PageResponseHandler.success(timetableService.delete(id),null,"Delete Success");
    }

    @GetMapping("/get-all")
    public ResponseEntity<PageResponse> getAll(Map<String,String> params){
        Page<TimeTable> timeTablesPage=timetableService.getAll(params);
        List<TimetableResponse> timetableResponses=timetableService.getPageContent(timeTablesPage);
        return PageResponseHandler.success(timetableResponses,timeTablesPage,"Get Success");
    }

}
