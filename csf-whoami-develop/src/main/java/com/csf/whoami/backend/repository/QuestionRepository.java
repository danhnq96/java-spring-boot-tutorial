/**
 *
 */
package com.csf.whoami.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csf.whoami.backend.entity.QuestionEntity;

/**
 * @author mba0051
 *
 */
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {


    /**
     * Refer to : The best solution seems the following with WHERE ... RAND(),
     * https://stackoverflow.com/questions/18943417/how-to-quickly-select-3-random-records-from-a-30k-mysql-table-with-a-where-filte
     *
     * @param questionNumber
     * @param groupId
     * @return
     */
    @Query(value = "select question from QuestionEntity question "
            + "inner join QuestionGroupEntity questionGroup on question.questionId = questionGroup.question.questionId "
            + "where (questionGroup.group.id = :groupId AND RAND() < 16 * 3/30000) LIMIT :questNumber", nativeQuery = true)
    List<QuestionEntity> getQuestionsByGroupId(@Param("questNumber") Integer questionNumber, @Param("groupId") String groupId);
}
