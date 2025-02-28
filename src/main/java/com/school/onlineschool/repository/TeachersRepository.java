package com.school.onlineschool.repository;

import com.school.onlineschool.domain.entiy.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
}
