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
@Table(name = "TB_PROJECTS")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BasicEntity {

    @Column(name = "NAME_PROJECT", columnDefinition = "VARCHAR(255)")
    private String nameProject;

    @Column(name = "CONTENT", columnDefinition = "VARCHAR(255)")
    private String content;

    @Column(name = "MANAGER", columnDefinition = "VARCHAR(255)")
    private String manager;

    @Column(name = "STRUCTURE", columnDefinition = "VARCHAR(255)")
    private String structure;

    @Column(name = "IMAGE", columnDefinition = "VARCHAR(255)")
    private String image;

/*    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Technology> technologyList;*/

}
