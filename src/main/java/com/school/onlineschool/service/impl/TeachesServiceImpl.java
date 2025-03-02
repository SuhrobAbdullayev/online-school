package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.TeacherRequestDto;

import com.school.onlineschool.domain.dto.response.CourseResponseDto;
import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;
import com.school.onlineschool.domain.dto.response.TeacherResponseDto;

import com.school.onlineschool.domain.entiy.Courses;
import com.school.onlineschool.domain.entiy.Teachers;
import com.school.onlineschool.exeption.EntityNotFound;
import com.school.onlineschool.repository.CourseRepository;
import com.school.onlineschool.repository.StudentsRepository;
import com.school.onlineschool.repository.TeachersRepository;
import com.school.onlineschool.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachesServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;
    private final CourseRepository courseRepository;

    @Override
    public Long createTeacher(TeacherRequestDto dto) {

        Teachers teachers = new Teachers();
        teachers.setName(dto.name());
        return teachersRepository.save(teachers).getId();

    }

    @Override
    public TeacherResponseDto getTeacher(Long id) {
        Teachers teachers = teachersRepository.findById(id).orElseThrow(
                () ->  new EntityNotFound("Teacher with the id is not exsist")
        );
        List<Courses> courses = courseRepository.findCoursesByTeacherId(teachers.getId());
        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();
        for (Courses course : courses){
            CourseResponseDto courseResponseDto = new CourseResponseDto();
            List<StudentNamesResponseDto> studentNames = studentsRepository.findStudentNameByCourseId(course.getId());
            courseResponseDto.setName(course.getName());
            courseResponseDto.setPrice(course.getPrice());
            courseResponseDto.setStudents(studentNames);
            courseResponseDtos.add(courseResponseDto);
        }


        TeacherResponseDto teacherResponse = TeacherResponseDto.builder()
                .id(teachers.getId())
                .name(teachers.getName())
                .courses(courseResponseDtos)
                .build();

        return teacherResponse;
    }
}
