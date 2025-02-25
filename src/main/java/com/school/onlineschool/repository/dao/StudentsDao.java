package com.school.onlineschool.repository.dao;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StudentsDao {

    List findById(MultiValueMap<String, Object> map);

}
