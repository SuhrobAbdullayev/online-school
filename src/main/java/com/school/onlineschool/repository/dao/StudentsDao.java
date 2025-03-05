package com.school.onlineschool.repository.dao;

import com.school.onlineschool.domain.entiy.User;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StudentsDao {

    List<User> findStudentsbyName(MultiValueMap<String, Object> filter);

}
