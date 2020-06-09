package com.jungo.jungocrawling;

import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

@Service
public class SampleListner implements ApplicationListener<ContextClosedEvent> {


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("앱 꺼짐?");
    }
}
