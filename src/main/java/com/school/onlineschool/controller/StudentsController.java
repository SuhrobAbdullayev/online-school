package com.school.onlineschool.controller;

import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.StudentRequestDto;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.service.StudentsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentsService studentsService;

    @PostMapping
    public ResponseDTO<Long> createStudent(@RequestBody StudentRequestDto studentRequestDto){
        return ResponseDTO.ok(studentsService.createStudent(studentRequestDto));
    }

    @GetMapping()
    public ResponseDTO<StudentResponseDto> getStudent(@RequestParam Long id){
        return ResponseDTO.ok(studentsService.getStudent(id));
    }

    @GetMapping("/all")
    public ResponseDTO<List<StudentResponseDto>> getAlStudents(){
        return ResponseDTO.ok(studentsService.getAll());
    }




}
