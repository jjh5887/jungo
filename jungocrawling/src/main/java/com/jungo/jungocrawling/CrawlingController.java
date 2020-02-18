package com.jungo.jungocrawling;

import org.jsoup.Jsoup;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
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
import java.util.Vector;

@Controller
public class CrawlingController {

    @Autowired
    Rconnect rconnect;

    @GetMapping("/jungocrawling")
    public String jungocrawling(){
        return "jungocrawling";
    }
    @PostMapping("/jungocrawling/as")
    public String Rcrawling(Model model,@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
        REXP rList;
        int len;
        String[] address;
        String[] title;
        int[] price;
        try{
            rconnect.connection.eval("keyword<-'" + keyword + "'");
            rconnect.connection.eval("page_i<-" + page);
            rconnect.connection.eval("source(\"crawling.R\")");
            address = rconnect.connection.eval("final_address").asStrings();
            title = rconnect.connection.eval("final_title").asStrings();
            price = rconnect.connection.eval("final_price").asIntegers();

            model.addAttribute("address",address);
            model.addAttribute("title",title);
            model.addAttribute("price",price);

        } catch (RserveException | REXPMismatchException e) {
            e.printStackTrace();
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
