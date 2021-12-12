package com.myproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="movies_schema")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String genre;

    @Column(length = 10, nullable = false)
    private int releaseYear;

}
