package com.school.onlineschool.repository.dao.impl;


import com.school.onlineschool.domain.entiy.Students;
import com.school.onlineschool.repository.dao.StudentsDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentsDaoImpl implements StudentsDao {

    private final EntityManager entityManager;


    @Override
    public List<Students> findStudentsbyName(MultiValueMap<String, Object> filter) {
        StringBuilder where = new StringBuilder();

        if (filter.containsKey("name") && filter.getFirst("name") != null) {
            where.append(" WHERE name ILIKE :name ");
        }

        String sql = """
                 SELECT * 
                 FROM students
                 """ + where;

        Query query = entityManager.createNativeQuery(sql, Students.class);

        if (filter.containsKey("name") && filter.getFirst("name") != null) {
            query.setParameter("name", "%" + filter.getFirst("name").toString() + "%");
        }

        return query.getResultList();
    }

}
