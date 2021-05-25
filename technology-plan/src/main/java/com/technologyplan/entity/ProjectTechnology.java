package com.technologyplan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PROJECT_TECHNOLOGYS")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTechnology extends BasicEntity {

    @Column(name = "PROJECT_ID", columnDefinition = "BIGINT")
    private Long projectId;

    @Column(name = "TECHNOLOGY_ID", columnDefinition = "BIGINT")
    private Long technologyId;

}
