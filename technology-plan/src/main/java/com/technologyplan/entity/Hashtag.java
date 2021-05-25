package com.technologyplan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TB_HASHTAGS")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hashtag extends BasicEntity {

    @Column(name = "HASTAG_NAME", columnDefinition = "VARCHAR(255)")
    private String hashtagName;

    @Column(name = "URL_LINK", columnDefinition = "VARCHAR(255)")
    private String urlLink;

//    @Column(name = "TECHNOLOGY_ID", columnDefinition = "BIGINT")
//    private Long technologyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "TECHNOLOGY_ID", columnDefinition = "BIGINT", referencedColumnName = "ID")
    private Technology technology;

}
