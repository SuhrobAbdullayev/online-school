package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.response.CourseResponseDto;
import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;
import com.school.onlineschool.domain.entiy.Courses;
import com.school.onlineschool.repository.CourseRepository;
import com.school.onlineschool.repository.UserRepository;
import com.school.onlineschool.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();
        for (Courses course : courseRepository.findAll()){

            List<StudentNamesResponseDto> studentNames = userRepository.findStudentNameByCourseId(course.getId());

            CourseResponseDto dto = CourseResponseDto.builder()
                    .name(course.getName())
                    .price(course.getPrice())
                    .students(studentNames)
                    .build();
            courseResponseDtos.add(dto);
        }

        return courseResponseDtos;
    }
}
