package com.jungo.jungocrawling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    Rconnect rconnect;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rconnect.connection.eval("library(RSelenium)");
        rconnect.connection.eval("rD <- rsDriver(browser=\"fire\",port=1118L)");
        rconnect.connection.eval("setwd('C:\\\\R')");
        rconnect.connection.eval("remDr <- rD[[\"client\"]]");
        rconnect.connection.eval("remDr$navigate(\"https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com\")");
        System.out.println("커넥션 성공!");
    }
}
