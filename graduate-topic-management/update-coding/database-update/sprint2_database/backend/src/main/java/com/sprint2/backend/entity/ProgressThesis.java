package com.sprint2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "progress_thesis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name_detail", columnDefinition = "VARCHAR(50)")
    private String nameDetail;

    @Column(name = "file", columnDefinition = "VARCHAR(250)")
    private String file;

    @Column(name = "mark", columnDefinition = "BIGINT")
    private Long mark;

    @Column(name = "status", columnDefinition = "BIGINT")
    private Long status;

    @Column(name = "submit_date", columnDefinition = "DATETIME")
    private LocalDateTime submitDate;

    @Column(name = "progress", columnDefinition = "BIGINT")
    private Long progress;

    @Column(name = "comment", columnDefinition = "VARCHAR(250)")
    private String comment;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_thesis_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private CheckThesis checkThesis;

//    @OneToOne
//    @JoinColumn(name = "check_thesis_id", referencedColumnName = "id", columnDefinition = "BIGINT")
//    private CheckThesis checkThesis;
}
