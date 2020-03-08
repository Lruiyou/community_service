package com.alan.project.utils;

import com.alan.project.dto.HotTopicDTO;
import com.alan.project.entity.HotTopic;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
@Data
public class HandleHotissue {

    private List<HotTopicDTO> hots = new ArrayList<>();

    public void filterHotTopic(Map<Integer, HotTopicDTO> questionMap, Map<Integer, Integer> priorities){
        int max = 5;
        PriorityQueue<HotTopic> priorityQueue = new PriorityQueue<>(max);
        priorities.forEach((id,priority) -> {
            HotTopic hotTopic = new HotTopic();
            hotTopic.setId(id);
            hotTopic.setPriority(priority);
            if (priorityQueue.size() < max){
                priorityQueue.add(hotTopic);
            }else {
                HotTopic minHot = priorityQueue.peek();
                if (hotTopic.compareTo(minHot) > 0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTopic);
                }
            }
        });

        List<HotTopicDTO> sortedQuestions = new ArrayList<>();
        HotTopic hotTopic = priorityQueue.poll();
        while (hotTopic != null ){
            sortedQuestions.add(0,questionMap.get(hotTopic.getId()));
            hotTopic = priorityQueue.poll();
        }
        this.hots = sortedQuestions;
    }
}
