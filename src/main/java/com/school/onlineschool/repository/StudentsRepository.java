package com.school.onlineschool.repository;

import com.school.onlineschool.domain.entiy.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}