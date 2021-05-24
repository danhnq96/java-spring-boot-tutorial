package com.csf.whoami.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MENUS")
@Where(clause = "deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "MENU_NAME")
    private String menuName = null;
    @Column(name = "LINK_SCREEN")
    private String linkScreen = null;
    @Column(name = "LOCALE")
    private String locale = null;
    @Column(name = "PARENT_MENU")
    private Long parentMenu = null;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MENU", insertable = false, updatable = false)
    private TbMenu parent = null;
}
