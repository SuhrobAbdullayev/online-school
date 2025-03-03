package com.school.onlineschool.controller;


import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.EnrollmentRequestDto;
import com.school.onlineschool.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentsController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseDTO<?> createEnrollment(@RequestBody EnrollmentRequestDto dto){
        enrollmentService.createEnrollment(dto);
        return ResponseDTO.ok("The enrollment is created");
    }
}
