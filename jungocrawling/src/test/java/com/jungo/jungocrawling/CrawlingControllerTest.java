package com.jungo.jungocrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@Component
class CrawlingControllerTest {


    @Test
    public void crawling() throws IOException, RserveException, REXPMismatchException {
        RConnection[] rConnection = new RConnection[20];

        rConnection[0] = new RConnection(null, 6311);
        rConnection[1] = new RConnection(null, 6312);
        rConnection[0].eval("i<-13");
        rConnection[0].eval("library(RSelenium)");
        rConnection[0].eval("setwd('C:\\\\R')");
        rConnection[0].eval("source(\"login.R\")");
        rConnection[1].eval("i<-14");
        rConnection[1].eval("library(RSelenium)");
        rConnection[1].eval("setwd('C:\\\\R')");
        rConnection[1].eval("source(\"login.R\")");


    }
}