package com.school.onlineschool.domain.entiy;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "marks")
public class Marks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long stuentId;

    @Column(name = "course_id")
    private Long courseId;

    private Long mark;

    private Boolean status;

    @Column(name = "created_date")
    @CurrentTimestamp
    private LocalDateTime createdDate;
}
