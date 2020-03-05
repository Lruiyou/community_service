package com.alan.project.service;

import com.alan.project.dao.Query;
import com.alan.project.dao.Question;
import com.alan.project.dto.QuestionListDTO;
import com.alan.project.entity.Page;
import com.alan.project.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;


    public boolean createQuestion(Question question){
        return questionMapper.createQuestion(question);
    }

    public Boolean updateQuestionById(Question question) {
        return questionMapper.updateQuestionById(question);
    }

    public QuestionListDTO getQuestionList(Integer currentPage, Integer pageSize, String search) {
        Integer totalPage;
        Query query = new Query();
        query.setSearch(search);
        Integer totalCount = questionMapper.questionCounts(query);
        if (totalCount % pageSize == 0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount / pageSize + 1;
        }
        //偏移量
        Integer offset = pageSize * (currentPage - 1);
        query.setPage(offset);
        query.setSize(pageSize);
        List<Question> questions = questionMapper.getQuestionList(query);
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(totalPage);
        QuestionListDTO questionListDTO = new QuestionListDTO();
        questionListDTO.setQuestions(questions);
        questionListDTO.setPage(page);
        return questionListDTO;
    }
}
