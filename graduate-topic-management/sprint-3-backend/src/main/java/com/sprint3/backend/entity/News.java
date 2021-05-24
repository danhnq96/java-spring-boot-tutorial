package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(250)")
    private String title;

    @Column(name = "content", columnDefinition = "VARCHAR(250)")
    private String content;

    @Column(name = "news_url", columnDefinition = "TEXT")
    private String newsURL;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    private LocalDateTime createDate;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Teacher teacher;
}
