package com.menlang.classroom.controller.classroom;
import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.classroom.ClassroomRequest;
import com.menlang.classroom.dto.classroom.ClassroomResponse;
import com.menlang.classroom.model.entities.ClassRoom;
import com.menlang.classroom.service.classroom.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {
    private final ClassroomService classroomService;

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getClassroom(@PathVariable Long id) {
        return PageResponseHandler.success(classroomService.getById(id),null,"Get Successful.");
    }

    @PostMapping
    public ResponseEntity<PageResponse> createClassroom(@Valid @RequestBody ClassroomRequest request){
        return PageResponseHandler.success(classroomService.create(request),null,"Create Successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> updateClassroom(@PathVariable Long id,@Valid @RequestBody ClassroomRequest request){
        return PageResponseHandler.success(classroomService.update(id,request),null,"Update Successful");
    }

    @GetMapping("/get-all")
    public ResponseEntity<PageResponse> getAllClassroom(@RequestParam Map<String,String> params){
        Page<ClassRoom> classRooms=this.classroomService.getAll(params);
        List<ClassroomResponse> classroomResponseList=this.classroomService.getPageContent(classRooms);

        return PageResponseHandler.success(classroomResponseList,classRooms,"Get Successful");
    }
}
