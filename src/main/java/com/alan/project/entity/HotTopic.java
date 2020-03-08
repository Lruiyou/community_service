package com.alan.project.entity;

import lombok.Data;

@Data
public class HotTopic implements Comparable{
    private Integer Id;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTopic)o).getPriority();
    }
}
