/**
 *
 */
package com.csf.whoami.backend.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mba0051
 *
 */
@Entity
@Table(name = "H09DT_USER_QUIZ")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class UserQuizTestEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USER_ID")
    private String userId;
    //	@OneToOne(optional = false)
//	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H08"))
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private Oauth2UserEntity user;

    @Column(name = "QUIZ_ID")
    private String quizTestId;
    @JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_H07DT_QUIZ_TEST_H09"))
    private QuizTestEntity quizTest;

    @Column(name = "TIME_START")
    private Date timeStart;

    @Column(name = "TIME_END")
    private Date timeEnd;

    @Column(name = "FINISH_STATUS")
    private String isFinish;
}
