package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "thesis_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThesisDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "progress_file", columnDefinition = "VARCHAR(250)")
    private String progressFile;

    @Column(name = "description", columnDefinition = "VARCHAR(250)")
    private String description;

    @Column(name = "percent", columnDefinition = "DOUBLE")
    private Double percent;

    @Column(name = "submit_date", columnDefinition = "DATETIME")
    private LocalDateTime submitDate;

    @Column(name = "comment", columnDefinition = "VARCHAR(250)")
    private String firstComment;

    @Column(name = "report", columnDefinition = "VARCHAR(250)")
    private String firstReport;

    // relationship

    @OneToOne
    @JoinColumn(name = "check_thesis_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private CheckThesis checkThesis;
}
