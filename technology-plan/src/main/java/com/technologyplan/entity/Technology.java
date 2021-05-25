package com.technologyplan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_TECHNOLOGYS")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Technology extends BasicEntity {

    @Column(name = "TECHNOLOGY_NAME", columnDefinition = "VARCHAR(255)")
    private String technologyName;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL)
    private List<TechnologyPlanContent> technologyPlanContentList;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList;

/*    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID", columnDefinition = "BIGINT", insertable = false, updatable = false)
    private Project project;*/

}
