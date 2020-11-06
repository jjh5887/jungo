package com.jungo.jungocrawling;



import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SampleListner implements ApplicationListener<ContextClosedEvent> {


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        int[][] v = {};
        Map<Integer, Integer> x = new HashMap<>();
        x.put(19, 20);
        Set<Integer> a = x.keySet();
        for(Map.Entry<Integer, Integer> entry : x.entrySet()){
            entry.getKey();
            entry.getValue();
        }

        x.forEach((key, value) -> System.out.println(key));

        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        System.out.println("앱 꺼짐?");
    }
}
