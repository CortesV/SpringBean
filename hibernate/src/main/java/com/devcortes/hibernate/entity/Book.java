package com.devcortes.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "id")
    private Integer id;

    @Column(name = "author_id")
    @JsonProperty(value = "author_id")
    private Integer authorId;

    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "eban")
    @JsonProperty(value = "eban")
    private String eban;

    @Column(name = "published")
    @JsonProperty(value = "published")
    private LocalDate published;

    @Column(name = "created_date")
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "version")
    @JsonProperty(value = "version")
    private LocalDateTime version;
}
