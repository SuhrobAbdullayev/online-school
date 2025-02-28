package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.TeacherRequestDto;
import com.school.onlineschool.domain.dto.response.CourseResponceDto;
import com.school.onlineschool.domain.dto.response.StudentNamesResponceDto;
import com.school.onlineschool.domain.dto.response.TeacherResponseDto;
import com.school.onlineschool.domain.entiy.Courses;
import com.school.onlineschool.domain.entiy.Teachers;
import com.school.onlineschool.repository.CouseRepository;
import com.school.onlineschool.repository.StudentsRepository;
import com.school.onlineschool.repository.TeachersRepository;
import com.school.onlineschool.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeachesServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;
    private final CouseRepository couseRepository;

    @Override
    public Long createTeacher(TeacherRequestDto dto) {

        Teachers teachers = new Teachers();
        teachers.setName(dto.name());
        return teachersRepository.save(teachers).getId();

    }

    @Override
    public TeacherResponseDto getTeacher(Long id) {
        Optional<Teachers> teachers = teachersRepository.findById(id);
        CourseResponceDto course = couseRepository.findCourseByTeacherId(teachers.get().getId());

        List<StudentNamesResponceDto> studentNames = studentsRepository.findStudentNameByCourseId(course.id());

        TeacherResponseDto teacherResponse = TeacherResponseDto.builder()
                .id(teachers.get().getId())
                .name(teachers.get().getName())
                .courseName(course.name())
                .studentNamesResponceDtos(studentNames)
                .build();

        return teacherResponse;
    }
}
