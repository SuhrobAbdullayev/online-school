package com.school.onlineschool.repository.dao;

import java.util.List;

public interface StudentsDao {

    List findNameByTeacherId(Long id);

}
