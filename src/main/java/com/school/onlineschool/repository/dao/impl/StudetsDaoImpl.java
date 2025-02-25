package com.school.onlineschool.repository.dao.impl;

import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.repository.dao.StudentsDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudetsDaoImpl implements StudentsDao {

    private final EntityManager entityManager;

    @Override
    public List findById(MultiValueMap<String, Object> filter) {

        StringBuilder where = new StringBuilder();

        if (filter.containsKey("id") && filter.getFirst("id") != null) {
            where.append("where s.id = :id");
        }

        String sql = """
                             select s.id s.name, s.phone_number, c.name from students s
                             inner join enrollment e on s.id = e.student_id
                             inner join course c on e.course_id = c.id""" + where;

        Query query = entityManager.createNativeQuery(sql, StudentResponseDto.class);

        return query.getResultList();

    }
}
