/**
 *
 */
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

/**
 * @author Tuan Dang
 *
 */
@Entity
@Table(name = "MW_INFORMATION")
@Where(clause = "DELFLG = 0")
public class MWInformationEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "ISAPPROVED")
    private String isApproved;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the isApproved
     */
    public String getIsApproved() {
        return isApproved;
    }

    /**
     * @param isApproved the isApproved to set
     */
    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
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
