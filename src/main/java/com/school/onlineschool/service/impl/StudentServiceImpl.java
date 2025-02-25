package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.StudentRequestDto;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentsService {


    @Override
    public Long createStudent(StudentRequestDto dto) {
        return 0L;
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        return null;
    }

    @Override
    public List<StudentResponseDto> getAll(MultiValueMap<String, Object> filter) {
        return List.of();
    }
}
