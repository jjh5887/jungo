package com.jungo.jungocrawling;

import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

@Service
public class SampleListner implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    Rconnect rconnect;


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("앱 꺼짐?");
        try {
            for(int i=0; i<3; i++) {
                rconnect.rConnection[i].eval("i<-" + i + 1);
                rconnect.rConnection[i].eval("setwd('C:\\\\R')");
                rconnect.rConnection[i].eval("source(\"kill_selenium.R\")");
            }
        } catch (RserveException e) {
            e.printStackTrace();
        }
    }
}
