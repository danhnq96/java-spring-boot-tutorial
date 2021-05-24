/**
 *
 */
package com.csf.whoami.backend.service;

import com.csf.whoami.common.domain.quiz.QuizResponseDomain;

/**
 * @author mba0051
 *
 */
public interface ExaminationsService {

    QuizResponseDomain startExamination(String quizId);
}
