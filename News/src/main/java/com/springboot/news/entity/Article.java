package com.springboot.news.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name ="article", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Article {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "section", nullable = false)
    private String section;
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
