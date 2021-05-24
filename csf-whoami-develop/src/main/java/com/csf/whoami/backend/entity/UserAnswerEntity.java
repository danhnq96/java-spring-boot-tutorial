/**
 *
 */
package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "H08DT_USER_ANSWER")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class UserAnswerEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "USER_ID")
    private String userId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//	@OneToOne(optional = false)
//	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H08"))
    private Oauth2UserEntity user;

    @Column(name = "QUESTION_ID")
    private String questionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "QUESTION_ID", foreignKey = @ForeignKey(name = "FK_H06DT_QUESTION_H11"))
    private QuestionEntity question;

    @Column(name = "USER_ANSWER")
    private String userAnswer;

    @Column(name = "TIME_FINISH")
    private int timeToFinish;
}
