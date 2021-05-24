package com.csf.whoami.backend.entity.mw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "MW_TYPE_INFO")
@Where(clause = "DELFLG = 0")
public class MWTypeInfoEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TYPE_INFO_NAME")
    private String typeInfoName;
    @Column(name = "TYPE_INFO_CODE")
    private String typeInfoCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_ACTIVE")
    @Type(type = "numeric_boolean")
    private boolean isActive;
    @Column(name = "CREATE_DATE")
    private Date createdDate;
    @Column(name = "CREATE_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updatedDate;
    @Column(name = "UPDATE_BY")
    private String updatedBy;
    @Column(name = "DELFLG")
    @Type(type = "numeric_boolean")
    private boolean isDeleted = false;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the typeInfoName
     */
    public String getTypeInfoName() {
        return typeInfoName;
    }

    /**
     * @param typeInfoName the typeInfoName to set
     */
    public void setTypeInfoName(String typeInfoName) {
        this.typeInfoName = typeInfoName;
    }

    /**
     * @return the typeInfoCode
     */
    public String getTypeInfoCode() {
        return typeInfoCode;
    }

    /**
     * @param typeInfoCode the typeInfoCode to set
     */
    public void setTypeInfoCode(String typeInfoCode) {
        this.typeInfoCode = typeInfoCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the isActive
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the isDeleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
