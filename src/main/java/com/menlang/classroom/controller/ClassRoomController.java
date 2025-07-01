package com.menlang.classroom.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @GetMapping()
    public String classroom() {
        return "Greeting Classroom.....";
    }
}
