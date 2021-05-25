package com.technologyplan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TB_TECHNOLOGY_PLAN_CONTENTS")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyPlanContent extends BasicEntity {

    @Column(name = "PLAN_CONTENT", columnDefinition = "VARCHAR(255)")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "TECHNOLOGY_ID", columnDefinition = "BIGINT", referencedColumnName = "ID")
    private Technology technology;

//    @Column(name = "TECHNOLOGY_ID", columnDefinition = "BIGINT")
//    private Long technologyId;

}
