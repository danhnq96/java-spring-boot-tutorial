/**
 *
 */
package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tuan
 *
 */
@Entity
@Table(name = "H06DT_QUESTION")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class QuestionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "QUESTION_TYPE")
    private String questionType;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ANSWERS_OPTIONAL")
    private String answersOptional;

    @Column(name = "CORRECT_ANSWER")
    private String correctAnswer;

    @Column(name = "OWNER")
    private String ownerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER", insertable = false, updatable = false)
//	@OneToOne(optional = true)
//	@JoinColumn(name = "OWNER", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H06"))
    private Oauth2UserEntity user;

    @Column(name = "SUPER_SIZE")
    @Length(max = 1)
    private String isSuperSize = "0";

    @Column(name = "RANDOM_ANSWER")
    @Length(max = 1)
    private String randomAnswer = "0";

    @Column(name = "LOCK_STATUS")
    @Length(max = 1)
    private String isLock = "0";

    @Column(name = "PUBLISH_STATUS")
    @Length(max = 1)
    private String isPublish = "0";

    @Column(name = "PRIVATE_STATUS")
    @Length(max = 1)
    private String isPrivate = "1";

    @Column(name = "MULTIPLE_CHOICE")
    @Length(max = 1)
    private String isMultipleChoice = "0";
}
