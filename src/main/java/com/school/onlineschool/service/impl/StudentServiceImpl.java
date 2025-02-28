package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.StudentRequestDto;
import com.school.onlineschool.domain.dto.response.StudentDetailsResponseDto;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.domain.entiy.Students;
import com.school.onlineschool.repository.EnrollmentsRepository;
import com.school.onlineschool.repository.StudentsRepository;
import com.school.onlineschool.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final EnrollmentsRepository enrollmentsRepository;

    @Override
    public Long createStudent(StudentRequestDto dto) {

        if (studentsRepository.existsByPhoneNumber(dto.phoneNumber())){
            throw new RuntimeException("phone number is already excist");
        }
        Students students = new Students();
        students.setName(dto.name());
        students.setStatus(Boolean.TRUE);
        students.setPhoneNumber(dto.phoneNumber());
        return studentsRepository.save(students).getId();
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        Optional<Students> students = studentsRepository.findById(id);

        if (students.isEmpty()){
            throw new RuntimeException("there are no students in database");
        }

        List<String> courseList = enrollmentsRepository.findCourseByStudentId(students.get().getId());

        List<StudentDetailsResponseDto> studentDetails = courseList.stream()
                .map(courseName -> StudentDetailsResponseDto.builder().courseName(courseName).build())
                .toList();

        return new StudentResponseDto(
                students.get().getId(),
                students.get().getName(),
                students.get().getPhoneNumber(),
                courseList.stream()
                .map(courseName -> StudentDetailsResponseDto.builder().courseName(courseName).build())
                .toList()
        );
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<Students> students = studentsRepository.findAll();

        if (students.isEmpty()){
            throw new RuntimeException("there are no students in database");
        }
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (Students student : students){
            List<String> courseList = enrollmentsRepository.findCourseByStudentId(student.getId());

            List<StudentDetailsResponseDto> studentDetails = courseList.stream()
                    .map(courseName -> StudentDetailsResponseDto.builder().courseName(courseName).build())
                    .toList();

            StudentResponseDto studentResponse = StudentResponseDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .phoneNumber(student.getPhoneNumber())
                    .studentDetails(studentDetails)
                    .build();
            studentResponseDtos.add(studentResponse);
        }

        return studentResponseDtos;
    }
}
