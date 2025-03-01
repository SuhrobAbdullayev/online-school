package com.school.onlineschool.controller;

import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.TeacherRequestDto;
import com.school.onlineschool.domain.dto.response.TeacherResponseDto;
import com.school.onlineschool.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersService teachersService;

    @PostMapping()
    public ResponseDTO<Long> createTeacher(@RequestBody TeacherRequestDto teacherRequestDto){
        return ResponseDTO.ok(teachersService.createTeacher(teacherRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseDTO<TeacherResponseDto> getTeacher(@PathVariable Long id){
        return ResponseDTO.ok(teachersService.getTeacher(id));
    }
}
