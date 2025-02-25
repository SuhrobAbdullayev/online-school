package com.school.onlineschool.repository;

import com.school.onlineschool.domain.entiy.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

  @Query(value = "select c.name from enrollment e inner join course c on e.course_id = c.id where student_id=:studentId", nativeQuery = true)
  List<String> findCourseByStudentId(Long studentId);
}