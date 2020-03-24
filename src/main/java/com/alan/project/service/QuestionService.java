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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;


    @Autowired
    private HandleHotissue handleHotissue;


    public boolean createQuestion(Question question){
        return questionMapper.createQuestion(question);
    }

    public void updateQuestionById(Question question) {
         questionMapper.updateQuestionById(question);
    }

    public QuestionListDTO getQuestionList(Integer currentPage, Integer pageSize, String search) {
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

    public void increaseViewById(Integer id) {
        questionMapper.increaseViewById(id);
    }

    public List<Question> getRelatedQuestions(Question question) {
        if (question.getTag() == null || "".equals(question.getTag())){
              return new ArrayList<>();
        }
        String tag = question.getTag();
        String filterTag = tag.substring(1,tag.length()-1).replace("\"","");//去除[]和""
        String[] tags = filterTag.split(",");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        List<Question> questionList = questionMapper.selectRelatedByTag(question.getId(), regexTag);
        List<Question> filterQuestionList = new ArrayList<>();
        if (questionList.size() <= 5){
            return questionList;
        }else {
            for (Question q : questionList){//当查询的数量超过5个时，只取前五个
                filterQuestionList.add(q);
                if (filterQuestionList.size() == 5){
                    break;
                }
            }
            return filterQuestionList;
        }
    }
}
