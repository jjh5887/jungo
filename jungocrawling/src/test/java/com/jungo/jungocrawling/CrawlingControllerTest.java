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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CrawlingControllerTest {

    @Test
    public void crawling() throws IOException {
        boolean[] data = new boolean[100];

        Arrays.fill(data,false);
        if(!(data[1])){
            System.out.println("성공!");
        }


//        String keyword = "갤럭시 폴드";
//        String url=null;
//        String encodedString = URLEncoder.encode(keyword, "UTF-8");
//        Elements element= null;
//
//        System.out.println(encodedString);
//        Document document = null;
//
//        for(int i=1; i<=1; i++) {
//            url = "https://m.cafe.naver.com/ArticleSearchListAjax.nhn?search.query=" + encodedString + "&search.menuid=0&search.searchBy=0&search.sortBy=date&search.clubid=10050146&search.option=0&search.defaultValue=&search.page=" + i;
//            System.out.println(url);
//            document = Jsoup.connect(url).get();
//            element = document.select("h3");
//            String s= element.toString();
//
//
//        }
//        String s = "ABC[ This is to extract ]";
//        Pattern p = Pattern.compile(".*\\[ *(.*) *\\].*");
//        Matcher m = p.matcher(s);
//        m.find();
//        String text = m.group(1);
//        System.out.println(text);

//        String str = "Welcome to <b>Tutorialspoint<b>";
//        //Creating a pattern object
//        Pattern pattern = Pattern.compile("<b>(\\S+)</b>");
//        //Matching the compiled pattern in the String
//        Matcher matcher = pattern.matcher(str);
//        if (matcher.find()) {
//            String result = matcher.group(1);
//            System.out.println(result);
//        }


    }
}