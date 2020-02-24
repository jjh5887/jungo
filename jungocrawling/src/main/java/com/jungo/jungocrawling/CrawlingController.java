package com.jungo.jungocrawling;

import org.jsoup.Jsoup;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.Document;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class CrawlingController {

    @Autowired
    Rconnect rconnect;

    @GetMapping("/jungocrawling")
    public String jungocrawling(){
        return "jungocrawling";
    }

    @PostMapping("/as")
    public String Rcrawling(Model model, @RequestParam("keyword") String keyword) {
        String[] address;
        String[] img;
        String[] title;
        int[] price;
        int port = 0;
        port = rconnect.getPort();

        try {
            rconnect.connection.eval("library(RSelenium)");
            rconnect.connection.eval("rD <- rsDriver(browser=\"fire\",port=as.integer(" + port + "))");
        } catch (RserveException e) {
            e.printStackTrace();
            rconnect.retrunPort(port);
        }
        try{
            rconnect.connection.eval("setwd('C:\\\\R')");
            rconnect.connection.eval("source(\"login.R\")");
            rconnect.connection.eval("keyword<-'" + keyword + "'");
            rconnect.connection.eval("page_i<-" + 1);
            rconnect.connection.eval("source(\"crawling.R\")");
            rconnect.retrunPort(port);
            address = rconnect.connection.eval("final_address").asStrings();
            title = rconnect.connection.eval("final_title").asStrings();
            price = rconnect.connection.eval("final_price").asIntegers();
            img = rconnect.connection.eval("final_img").asStrings();
            model.addAttribute("link",address);
            model.addAttribute("title",title);
            model.addAttribute("price",price);
            model.addAttribute("img",img);
            for(int i = 0; i < title.length; i++)
                System.out.println(img[i]);
        } catch (RserveException | REXPMismatchException e) {
            e.printStackTrace();
            return "crawlingOk";
        }
        return "crawlingOk";
    }
    @PostMapping("/jungocrawling/asssss")
    public String crawling(Model model,@RequestParam("keyword") String keyword, @RequestParam("page") int page) throws IOException {
        String url=null;
        String encodedString = URLEncoder.encode(keyword, "UTF-8");
        Document document = null;

        for(int i=1; i<=page; i++) {
            url = "https://m.cafe.naver.com/ArticleSearchListAjax.nhn?search.query=" + keyword + "&search.menuid=0&search.searchBy=0&search.sortBy=date&search.clubid=10050146&search.option=0&search.defaultValue=&search.page=" + i;
            document = (Document) Jsoup.connect(url).get();

        }

        return "crawlingOk";
    }
}
