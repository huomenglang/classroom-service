package com.menlang.classroom.controller.timeslot;

import com.menglang.common.library.page.PageResponse;
import com.menglang.common.library.page.PageResponseHandler;
import com.menlang.classroom.dto.timeslot.TimeslotRequest;
import com.menlang.classroom.dto.timeslot.TimeslotResponse;
import com.menlang.classroom.model.entities.Timeslot;
import com.menlang.classroom.service.timeslot.TimeslotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/timeslots")
@RequiredArgsConstructor
public class TimeslotController {
    private final TimeslotService timeslotService;

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getTimeslot(@PathVariable Long id) {
        return PageResponseHandler.success(timeslotService.getById(id), null, "Get Successful.");
    }

    @PostMapping
    public ResponseEntity<PageResponse> createTimeslot(@Valid @RequestBody TimeslotRequest request) {
        return PageResponseHandler.success(timeslotService.create(request), null, "Create Successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponse> updateTimeslot(@PathVariable Long id, @Valid @RequestBody TimeslotRequest request) {
        return PageResponseHandler.success(timeslotService.update(id, request), null, "Update Successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse> deleteTimeslot(@PathVariable Long id) {
        return PageResponseHandler.success(timeslotService.delete(id), null, "Update Successful");
    }

    @GetMapping("/get-all")
    public ResponseEntity<PageResponse> getAllTimeslot(@RequestParam Map<String, String> params) {
        Page<Timeslot> Timeslots = this.timeslotService.getAll(params);
        List<TimeslotResponse> TimeslotResponseList = this.timeslotService.getPageContent(Timeslots);
        return PageResponseHandler.success(TimeslotResponseList, Timeslots, "Get Successful");
    }
}
