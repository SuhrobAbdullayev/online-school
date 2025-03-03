package com.school.onlineschool.repository;

import com.school.onlineschool.domain.entiy.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

  @Query(value = "select c.name from enrollment e inner join course c on e.course_id = c.id where student_id=:studentId", nativeQuery = true)
  List<String> findCourseByStudentId(@Param("studentId") Long studentId);

}