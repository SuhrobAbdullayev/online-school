package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.StudentRequestDto;
import com.school.onlineschool.domain.dto.response.StudentDetailsResponseDto;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.domain.entiy.Students;
import com.school.onlineschool.repository.EnrollmentsRepository;
import com.school.onlineschool.repository.StudentsRepository;
import com.school.onlineschool.repository.dao.StudentsDao;
import com.school.onlineschool.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final EnrollmentsRepository enrollmentsRepository;
    private final StudentsDao studentsDao;

    @Override
    public Long createStudent(StudentRequestDto dto) {

        if (studentsRepository.existsByPhoneNumber(dto.phoneNumber())) {
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

        Students students = studentsRepository.findById(id).orElseThrow(
                () -> new RuntimeException("there is not such students in database")
        );

        List<StudentDetailsResponseDto> studentDetails = enrollmentsRepository.findCourseByStudentId(id);

        return new StudentResponseDto(
                students.getId(),
                students.getName(),
                students.getPhoneNumber(),
                studentDetails
        );
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<Students> students = studentsRepository.findAll();

        if (students.isEmpty()) {
            throw new RuntimeException("there are no students in database");
        }
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for (Students student : students) {
            List<StudentDetailsResponseDto> courseList = enrollmentsRepository.findCourseByStudentId(student.getId());

            StudentResponseDto studentResponse = StudentResponseDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .phoneNumber(student.getPhoneNumber())
                    .studentDetails(courseList)
                    .build();
            studentResponseDtos.add(studentResponse);
        }

        return studentResponseDtos;
    }

    @Override
    public List<StudentResponseDto> getStudentByName(MultiValueMap<String, Object> filter) {

        List<Students> students = studentsDao.findStudentsbyName(filter);
        if (students.isEmpty()) {
            throw new RuntimeException("There is no student with the name:" + filter.get("name"));
        }

        List<StudentResponseDto> dtos = new ArrayList<>();

        for (Students student : students) {
            List<StudentDetailsResponseDto> studentDetails = enrollmentsRepository.findCourseByStudentId(student.getId());
            StudentResponseDto dto = StudentResponseDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .phoneNumber(student.getPhoneNumber())
                    .studentDetails(studentDetails)
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }


}
