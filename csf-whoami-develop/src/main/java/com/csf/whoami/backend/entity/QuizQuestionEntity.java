/**
 *
 */
package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "H11DT_QUIZ_QUESTION")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class QuizQuestionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "QUIZ_ID")
    private String quizId;
    //	@ManyToOne(optional = false)
//	@JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_H07DT_QUIZ_TEST_H11"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_ID", insertable = false, updatable = false)
    private QuizTestEntity quiz;

    @Column(name = "QUESTION_ID")
    private String questionId;
    //	@ManyToOne(optional = false)
//	@JoinColumn(name = "QUESTION_ID", foreignKey = @ForeignKey(name = "FK_H06DT_QUESTION_H11"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", insertable = false, updatable = false)
    private QuestionEntity question;
}
