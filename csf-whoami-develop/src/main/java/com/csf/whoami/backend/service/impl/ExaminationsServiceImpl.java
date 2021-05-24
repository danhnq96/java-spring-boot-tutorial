/**
 *
 */
package com.csf.whoami.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csf.whoami.backend.entity.QuizQuestionEntity;
import com.csf.whoami.backend.entity.QuizTestEntity;
import com.csf.whoami.backend.entity.UserQuizTestEntity;
import com.csf.whoami.backend.exception.CustomException;
import com.csf.whoami.backend.exception.ErrorException;
import com.csf.whoami.backend.repository.QuizQuestionRepository;
import com.csf.whoami.backend.repository.QuizTestRepository;
import com.csf.whoami.backend.repository.UserQuizTestRepository;
import com.csf.whoami.backend.service.ExaminationsService;
import com.csf.whoami.common.constant.DateFormatConstant;
import com.csf.whoami.common.domain.question.QuestionDomain;
import com.csf.whoami.common.domain.quiz.QuizResponseDomain;
import com.csf.whoami.common.utilities.AuthenticationUtils;
import com.csf.whoami.common.utilities.DateTimeUtils;

/**
 * @author mba0051
 *
 */
@Service
public class ExaminationsServiceImpl implements ExaminationsService {

    @Autowired
    private QuizTestRepository quizTestRepository;
    @Autowired
    private UserQuizTestRepository userQuizTestRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    /**
     * Start quiz test.
     * @author mba0051
     */
    @Override
    @Transactional
    public QuizResponseDomain startExamination(String quizId) {

        QuizTestEntity quizTest = quizTestRepository.findById(quizId).orElse(null);
        if (quizTest == null) {
            throw new CustomException(ErrorException.INVALID_QUIZ.getMessage(),
                    ErrorException.INVALID_QUIZ.getCode(), HttpStatus.BAD_REQUEST);
        }

        String userId = AuthenticationUtils.getCurrentUserId();
        UserQuizTestEntity userQuiz = new UserQuizTestEntity();
        userQuiz.setUserId(userId);
        userQuiz.setQuizTest(quizTest);
        userQuiz = userQuizTestRepository.save(userQuiz);
        if (userQuiz == null) {
            throw new CustomException(ErrorException.CANT_MAKE_EXAM.getMessage(),
                    ErrorException.CANT_MAKE_EXAM.getCode(), HttpStatus.BAD_REQUEST);
        }

        return startQuiztest(quizId);
    }


    /**
     * Start quiz test.
     *
     * @author mba0051
     * @param quizId
     * @return
     */
    @Transactional
    private QuizResponseDomain startQuiztest(String quizId) {

        QuizTestEntity quiz = quizTestRepository.findById(quizId).orElse(null);
        if (quiz == null) {
            throw new CustomException(ErrorException.NOT_EXIST_QUIZ.getMessage(),
                    ErrorException.NOT_EXIST_QUIZ.getCode(), HttpStatus.BAD_REQUEST);
        }
        // Get quiz questions.
        List<QuizQuestionEntity> quizQuests = quizQuestionRepository.findAllByQuizId(quiz.getId());
        if (CollectionUtils.isEmpty(quizQuests)) {
            throw new CustomException(ErrorException.INVALID_QUIZ.getMessage(), ErrorException.INVALID_QUIZ.getCode(),
                    HttpStatus.BAD_REQUEST);
        }
        return convertToQuizDomain(quizId, quizQuests, quiz.getGroupId(), quiz.getQuestionTime());
    }

    /**
     * Convert data to response domain.
     *
     * @author mba0051
     * @param quizId
     * @param quizQuests
     * @param groupId
     * @param questionTime
     * @return
     */
    private QuizResponseDomain convertToQuizDomain(String quizId, List<QuizQuestionEntity> quizQuests,
                                                   String groupId, int questionTime) {
        QuizResponseDomain responseDomain = new QuizResponseDomain();
        responseDomain.setQuizId(quizId);
//		responseDomain.setDuration(StringUtils.convertObjectToString(questionTime));
        try {
            responseDomain.setTimeStart(DateTimeUtils.getSysDateTime(DateFormatConstant.YYYYMMDDhhmm));
        } catch (Exception e) {
            throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
                    ErrorException.INVALID_FORMAT.getCode(),
                    HttpStatus.BAD_REQUEST);
        }

        List<QuestionDomain> domain = quizQuests.stream().map(item -> new QuestionDomain(item.getId(),
                item.getQuestion().getQuestionType(),
                item.getQuestion().getContent(),
                groupId)).collect(Collectors.toList());
        responseDomain.setQuestions(domain);
        return responseDomain;
    }
}
