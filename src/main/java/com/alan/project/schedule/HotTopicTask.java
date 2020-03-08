package com.alan.project.schedule;

import com.alan.project.dao.Question;
import com.alan.project.dto.HotTopicDTO;
import com.alan.project.mapper.QuestionMapper;
import com.alan.project.utils.HandleHotissue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HotTopicTask {

    @Resource
    private QuestionMapper questionMapper;

    @Autowired
    private HandleHotissue handleHotissue;

    @Scheduled(fixedRate = 1000 * 30)
    public void hotissueSchedule(){
        int offset = 0;
        int limit = 100;
        List<Question> questionList = new ArrayList<>();
        Map<Integer,Integer> priorities = new HashMap<>(); //保存问题id和计算问题的热度
        Map<Integer,HotTopicDTO> questionMap = new HashMap<>();

        while (offset ==  0 || questionList.size() == limit){
            System.out.println("offset:"+offset);
                questionList = questionMapper.getQuestions(offset,limit);
                for (Question question : questionList){
                    HotTopicDTO hotTopicDTO = new HotTopicDTO();
                    Integer priority = question.getLikeCount() +  question.getCommentCount() + 1000; //计算热度
                    priorities.put(question.getId(),priority); //保存问题id，计算问题的热度
                    BeanUtils.copyProperties(question,hotTopicDTO);
                    hotTopicDTO.setHeat(priority);
                    questionMap.put(question.getId(),hotTopicDTO); //保存问题id和问题本身及问题的热度
                }
                offset += limit;
        }
        if (questionList.size() < limit){
            offset = 0;
        }
         handleHotissue.filterHotTopic(questionMap,priorities);
    }
}
