package com.devcortes.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "name")
    private String name;

    @Column(name = "eban")
    private String eban;

    @Column(name = "published")
    private LocalDate published;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "version")
    private LocalDateTime version;
}
