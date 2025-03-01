package com.school.onlineschool.repository;

import com.school.onlineschool.domain.entiy.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

  Courses findCoursesByTeacherId(Long teacherId);

  }