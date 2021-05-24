/**
 *
 */
package com.csf.whoami.backend.entity.mw;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

/**
 * @author Tuan Dang
 *
 */
@Entity
@Table(name = "MW_INFOMATION_TYPE_INFO")
@Where(clause = "DELFLG = 0")
public class MWInformationTypeInfo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "INFORMATION_ID")
    private MWInformationEntity informationEntity;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "TYPE_INFO_ID")
    private MWTypeInfoEntity typeInfoEntity;

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
     * @return the informationEntity
     */
    public MWInformationEntity getInformationEntity() {
        return informationEntity;
    }

    /**
     * @param informationEntity the informationEntity to set
     */
    public void setInformationEntity(MWInformationEntity informationEntity) {
        this.informationEntity = informationEntity;
    }

    /**
     * @return the typeInfoEntity
     */
    public MWTypeInfoEntity getTypeInfoEntity() {
        return typeInfoEntity;
    }

    /**
     * @param typeInfoEntity the typeInfoEntity to set
     */
    public void setTypeInfoEntity(MWTypeInfoEntity typeInfoEntity) {
        this.typeInfoEntity = typeInfoEntity;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
