package com.alan.project.mapper;

import com.alan.project.dao.Pagination;
import com.alan.project.dao.Query;
import com.alan.project.dao.Question;
import com.alan.project.dao.Thumbup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    boolean createQuestion(Question question);

    void updateQuestionById(Question question);

    Integer questionCounts(Query query);

    List<Question> getQuestionList(Query query);

    List<Question> getQuestions(@Param("offset") int offset,@Param("limit") int limit);

    Thumbup findLikeByQidandUid(@Param("questionId") Integer questionId, @Param("userId") String userId);

    void createLike(Thumbup thumbup);

    void updateLike(Thumbup record);

    void increaseLikeById(@Param("questionId") Integer questionId);//点赞数加一

    void decreaseLikeById(@Param("questionId") Integer questionId);//点赞数减一

    Question getQuestionById(@Param("id") Integer id);//根据问题id获取问题详情

    void increaseViewById(@Param("id") Integer id); //浏览数加一

    List<Question> selectRelatedByTag(@Param("id") Integer id, @Param("tag") String regexTag);//查询标签相关联的问题

    void increaseCommentCountById(@Param("questionId") Integer topicId);//评论数加一

    List<Question> getQuestionListByUid(Pagination pagination);
}
