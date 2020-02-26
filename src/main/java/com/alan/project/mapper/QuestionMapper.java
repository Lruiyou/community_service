package com.alan.project.mapper;

import com.alan.project.dao.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionMapper {

    boolean createQuestion(Question question);

    Question getQuestionByCreator(@Param("creator") String creator);

    boolean updateQuestionById(Question question);
}
