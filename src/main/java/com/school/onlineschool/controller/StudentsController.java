package com.school.onlineschool.controller;

import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('USER_ACCESS')")
    @GetMapping()
    public ResponseDTO<StudentResponseDto> getStudent(@RequestParam Long id) {
        return ResponseDTO.ok(userService.getStudent(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ACCESS')")
    @GetMapping("/all")
    public ResponseDTO<List<StudentResponseDto>> getAlStudents() {
        return ResponseDTO.ok(userService.getAll());
    }

    @GetMapping("/name")
    public ResponseDTO<List<StudentResponseDto>> findByName(@RequestParam String name) {

        MultiValueMap<String, Object> filter = new LinkedMultiValueMap<>();

        filter.add("name", name);

        return ResponseDTO.ok(userService.getStudentByName(filter), "Students have found successfully!");
    }


}
