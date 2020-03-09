package com.alan.project.service;

import com.alan.project.dao.Query;
import com.alan.project.dao.Question;
import com.alan.project.dao.Thumbup;
import com.alan.project.dto.QuestionListDTO;
import com.alan.project.entity.Page;
import com.alan.project.mapper.QuestionMapper;
import com.alan.project.utils.HandleHotissue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;


    @Autowired
    private HandleHotissue handleHotissue;


    public boolean createQuestion(Question question){
        return questionMapper.createQuestion(question);
    }

    public Boolean updateQuestionById(Question question) {
        return questionMapper.updateQuestionById(question);
    }

    public QuestionListDTO getQuestionList(Integer currentPage, Integer pageSize, String search) {
        Integer totalPage;
        Query query = new Query();
        if (search != null){
            query.setSearch(search.trim());
        }else {
            query.setSearch(search);
        }
        Integer totalCount = questionMapper.questionCounts(query);
        //偏移量
        Integer offset = pageSize * (currentPage - 1);
        query.setPage(offset);
        query.setSize(pageSize);
        List<Question> questions = questionMapper.getQuestionList(query);
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        QuestionListDTO questionListDTO = new QuestionListDTO();
        questionListDTO.setQuestions(questions);
        questionListDTO.setPage(page);
        return questionListDTO;
    }

    public Thumbup findLikeByQidandUid(Integer questionId, Integer userId) {
       return questionMapper.findLikeByQidandUid(questionId,userId);
    }

    public void createLike(Thumbup thumbup) {
        questionMapper.createLike(thumbup);
    }

    public void updateLike(Thumbup record) {
        questionMapper.updateLike(record);
    }

    public void increaseLikeById(Integer questionId) {
        questionMapper.increaseLikeById(questionId);
    }

    public void decreaseLikeById(Integer questionId) {
        questionMapper.decreaseLikeById(questionId);
    }

    public Question getQuestionById(Integer id) {
        return questionMapper.getQuestionById(id);
    }
}
