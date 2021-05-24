/**
 *
 */
package com.csf.whoami.backend.service;

import com.csf.whoami.common.domain.quiz.QuizAnswersDomain;
import com.csf.whoami.common.domain.quiz.QuizRequestDomain;
import com.csf.whoami.common.domain.quiz.ResultResponseDomain;

/**
 * @author mba0051
 *
 */
public interface QuizTestService {

    /**
     * Generate random quiztest with number question and time particular.
     * @author mba0051
     * @param domain
     * @return
     */
    String generateRandomQuiz(QuizRequestDomain domain);

    /**
     * Calculate quiztest result.
     * @author mba0051
     * @param domain
     * @return
     */
    ResultResponseDomain calculateExaminationResult(QuizAnswersDomain domain);

}
