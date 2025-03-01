package com.school.onlineschool.repository;

import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;
import com.school.onlineschool.domain.entiy.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    boolean existsByPhoneNumber(String phoneNumber);


    @Query(value = "select s.name from course c inner join enrollment e on c.id = e.course_id inner join students s on e.student_id = s.id where c.id = :id", nativeQuery = true)
    List<StudentNamesResponseDto> findStudentNameByCourseId(@Param("id") Long id);


}