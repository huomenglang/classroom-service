package com.menlang.classroom.controller.attendance;
import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.attendance.AttendanceRequest;
import com.menlang.classroom.service.attendant.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<PageResponse> create(@RequestBody AttendanceRequest request){
        return PageResponseHandler.success(attendanceService.create(request),null,"Create Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getById(@PathVariable Long id){
        return PageResponseHandler.success(attendanceService.getById(id),null,"Get Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> update(@PathVariable Long id, @RequestBody AttendanceRequest request){
        return PageResponseHandler.success(attendanceService.update(id,request),null,"Update Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse> delete(@PathVariable Long id){
        return PageResponseHandler.success(attendanceService.delete(id),null,"Delete Success");
    }

    @GetMapping("get-all")
    public ResponseEntity<PageResponse> getAll(Map<String,String> params){
        return PageResponseHandler.success(attendanceService.getAll(params),null,"Get Success");
    }
}
