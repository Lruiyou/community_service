package com.alan.project.mapper;

import com.alan.project.dao.Query;
import com.alan.project.dao.Question;
import com.alan.project.dao.Thumbup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    boolean createQuestion(Question question);

    Question getQuestionByCreator(@Param("creator") String creator);

    boolean updateQuestionById(Question question);

    Integer questionCounts(Query query);

    List<Question> getQuestionList(Query query);

    List<Question> getQuestions(@Param("offset") int offset,@Param("limit") int limit);

    Thumbup findLikeByQidandUid(@Param("questionId") Integer questionId, @Param("userId") Integer userId);

    void createLike(Thumbup thumbup);

    void updateLike(Thumbup record);

    void increaseLikeById(@Param("questionId") Integer questionId);//点赞数加一

    void decreaseLikeById(@Param("questionId") Integer questionId);//点赞数减一

    Question getQuestionById(@Param("id") Integer id);//根据问题id获取问题详情

    void increaseViewById(@Param("id") Integer id); //浏览数加一
}
