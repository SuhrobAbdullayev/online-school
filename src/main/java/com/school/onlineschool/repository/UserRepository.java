package com.school.onlineschool.repository;

import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;
import com.school.onlineschool.domain.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    boolean existsByUsername(String userName);

    @Query(value = "SELECT u FROM User u where u.refreshToken = :refreshToken")
    User findByToken(@Param("refreshToken") String refreshToken);

    @Query(value = "select s.first_name from course c inner join enrollment e on c.id = e.course_id inner join users s on e.user_id = s.id where c.id = :id", nativeQuery = true)
    List<StudentNamesResponseDto> findStudentNameByCourseId(@Param("id") Long id);


}
