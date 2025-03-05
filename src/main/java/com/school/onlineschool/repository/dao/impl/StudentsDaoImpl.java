package com.school.onlineschool.repository.dao.impl;


import com.school.onlineschool.domain.entiy.User;
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
    public List<User> findStudentsbyName(MultiValueMap<String, Object> filter) {
        StringBuilder where = new StringBuilder();

        if (filter.containsKey("name") && filter.getFirst("name") != null) {
            where.append(" WHERE name ILIKE :name ");
        }

        String sql = """
                 SELECT * 
                 FROM users
                 """ + where;

        Query query = entityManager.createNativeQuery(sql, User.class);

        if (filter.containsKey("name") && filter.getFirst("name") != null) {
            query.setParameter("name", "%" + filter.getFirst("name").toString() + "%");
        }

        return query.getResultList();
    }

}
