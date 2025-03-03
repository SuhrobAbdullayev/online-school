package com.school.onlineschool.controller;


import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.response.CourseResponseDto;
import com.school.onlineschool.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;

    @GetMapping
    public ResponseDTO<List<CourseResponseDto>> getAllCourses(){
        return ResponseDTO.ok(coursesService.getAllCourses());
    }
}
