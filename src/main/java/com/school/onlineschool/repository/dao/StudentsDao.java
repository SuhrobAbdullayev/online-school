package com.school.onlineschool.repository.dao;

import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;
import com.school.onlineschool.domain.entiy.Students;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StudentsDao {

    List<Students> findStudentsbyName(MultiValueMap<String, Object> filter);

}
