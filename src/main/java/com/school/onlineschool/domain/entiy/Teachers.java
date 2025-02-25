package com.school.onlineschool.domain.entiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import javax.naming.Name;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "teachers")
public class Teachers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "created_date")
    @CurrentTimestamp
    private LocalDateTime createdDate;

}
