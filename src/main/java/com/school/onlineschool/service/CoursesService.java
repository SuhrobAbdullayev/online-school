package com.school.onlineschool.service;

import com.school.onlineschool.domain.dto.response.CourseResponseDto;

import java.util.List;

public interface CoursesService {

    List<CourseResponseDto> getAllCourses();
}
