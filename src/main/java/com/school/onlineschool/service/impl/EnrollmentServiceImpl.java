package com.school.onlineschool.service.impl;

import com.school.onlineschool.domain.dto.request.EnrollmentDetailsRequestDto;
import com.school.onlineschool.domain.dto.request.EnrollmentRequestDto;
import com.school.onlineschool.domain.entiy.Enrollments;
import com.school.onlineschool.repository.CourseRepository;
import com.school.onlineschool.repository.EnrollmentsRepository;
import com.school.onlineschool.repository.UserRepository;
import com.school.onlineschool.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentsRepository enrollmentsRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public void createEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        userRepository.findById(enrollmentRequestDto.studentId()).orElseThrow(
                () -> new RuntimeException("There is not such student in database")
        );
        for (EnrollmentDetailsRequestDto detailsDto : enrollmentRequestDto.courseIds()){
            courseRepository.findById(detailsDto.id()).orElseThrow(
                    () -> new RuntimeException("There is not such course in database")
            );
            Enrollments enrollments = new Enrollments();
            enrollments.setStudentId(enrollmentRequestDto.studentId());
            enrollments.setCourseId(detailsDto.id());
            enrollmentsRepository.save(enrollments);
        }
    }
}
