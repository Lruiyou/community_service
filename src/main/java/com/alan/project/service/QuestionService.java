package com.alan.project.service;

import com.alan.project.dao.Query;
import com.alan.project.dao.Question;
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

//    @Scheduled
//    public List<HotTopicDTO> hotissueSchedule(){
//        int offset = 0;
//        int limit = 100;
//        List<Question> questionList = new ArrayList<>();
//        Map<Integer,Integer> priorities = new HashMap<>(); //保存问题id和计算问题的热度
//        Map<Integer,HotTopicDTO> questionMap = new HashMap<>();
//
//        while (offset ==  0 || questionList.size() == limit){
//            questionList = questionMapper.getQuestions(offset,limit);
//            if (questionList.size() > 0){
//                for (Question question : questionList){
//                    HotTopicDTO hotTopicDTO = new HotTopicDTO();
//                    Integer priority = question.getLikeCount() +  question.getCommentCount() + 1000; //计算热度
//                    priorities.put(question.getId(),priority); //保存问题id，计算问题的热度
//                    BeanUtils.copyProperties(question,hotTopicDTO);
//                    hotTopicDTO.setHeat(priority);
//                    questionMap.put(question.getId(),hotTopicDTO); //保存问题id和问题本身及问题的热度
//                }
//                offset += limit;
//            }
//        }
//        return handleHotissue.filterHotTopic(questionMap,priorities);
//        }
}
