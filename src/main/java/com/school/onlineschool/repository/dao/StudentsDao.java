package com.school.onlineschool.repository.dao;

import com.school.onlineschool.domain.dto.response.StudentNamesResponseDto;

import java.util.List;

public interface StudentsDao {

    List<StudentNamesResponseDto> findNameByTeacherId(Long id);

}
