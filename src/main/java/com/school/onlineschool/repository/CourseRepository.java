package com.school.onlineschool.repository;

import com.school.onlineschool.domain.dto.response.CourseResponseDto;
import com.school.onlineschool.domain.entiy.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

  @Query(value = "select c.id, c.name from teachers t inner join course c on t.id = c.teacher_id where t.id=:id", nativeQuery = true)
  CourseResponseDto findCourseByTeacherId(@Param("id") Long teacherId);

  }