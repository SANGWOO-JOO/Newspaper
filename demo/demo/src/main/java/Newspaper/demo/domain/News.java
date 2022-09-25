package Newspaper.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private User user;
}
