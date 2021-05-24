package com.endgame.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private Boolean hasSubCategory;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> children = new ArrayList<Category>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasSubCategory() {
        return hasSubCategory;
    }

    public void setHasSubCategory(Boolean hasSubCategory) {
        this.hasSubCategory = hasSubCategory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
