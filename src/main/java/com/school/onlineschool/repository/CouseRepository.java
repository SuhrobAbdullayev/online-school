package com.school.onlineschool.repository;

import com.fasterxml.jackson.databind.node.LongNode;
import com.school.onlineschool.domain.dto.response.CourseResponceDto;
import com.school.onlineschool.domain.entiy.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouseRepository extends JpaRepository<Courses, Long> {

    CourseResponceDto findCourseByTeacherId(Long teacherId);

}
