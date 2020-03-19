package com.jungo.jungocrawling;

import com.jungo.jungocrawling.Account.AccountRepository;
import com.jungo.jungocrawling.Account.Item;
import org.jsoup.Jsoup;
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
    AccountRepository accountRepository;

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
        System.out.println(port);

        try{
            rconnect.rConnection[port].eval("keyword<-\"" + keyword + "\"");
            rconnect.rConnection[port].eval("page_i<-" + 1);
            rconnect.rConnection[port].eval("final_address<-c()");
            rconnect.rConnection[port].eval("final_title<-c()");
            rconnect.rConnection[port].eval("final_price<-c()");
            rconnect.rConnection[port].eval("final_data<-c()");
            rconnect.rConnection[port].eval("final_img<-c()");
            rconnect.rConnection[port].eval("price<-NULL");
            rconnect.rConnection[port].eval("img_list<-c()");
            rconnect.rConnection[port].eval("keyword4<-\"\"");
            rconnect.rConnection[port].eval("img_list11<-c()");
            rconnect.rConnection[port].eval("img<-c()");
            rconnect.rConnection[port].eval("final_img11<-c()");
            rconnect.rConnection[port].eval(
                    "if(str_detect(keyword,\" \")){\n" +
                    "  keyword1<-str_split(keyword,\" \")\n" +
                    "  keyword2<-unlist(keyword1)\n" +
                    "  \n" +
                    "  for(ii in 1:length(keyword2)){\n" +
                    "    keyword3<-URLencode(keyword2[[ii]])\n" +
                    "    keyword4<-paste0(keyword4,keyword3,\"+\")\n" +
                    "    \n" +
                    "  }\n" +
                    "  keyword_final<-str_sub(keyword4,0,-2)\n" +
                    "  \n" +
                    "}else{\n" +
                    "  keyword<-iconv(keyword,from=\"CP949\",to=\"UTF-8\")\n" +
                    "  keyword_final<-URLencode(keyword)\n" +
                    "}\n" +
                    "\n" +
                    "for(page in 1:page_i){\n" +
                    "  c_url<-\"https://m.cafe.naver.com/ArticleSearchListAjax.nhn?search.query=\"\n" +
                    "  c_url1<-paste0(c_url,URLencode(keyword_final))\n" +
                    "  c_url2<-paste0(c_url1,\"&search.menuid=0&search.searchBy=0&search.sortBy=date&search.clubid=10050146&search.option=3&search.defaultValue=&search.page=\")\n" +
                    "  c_url_final<-paste0(c_url2,page)\n" +
                    "  b<-readLines(c_url_final,encoding = \"UTF-8\")\n" +
                    "  \n" +
                    "  img<-b[which(str_detect(b,\"item\")) + 3]\n" +
                    "  img_list<-str_extract(img,(\"(?<=img src=\\\").*(?=\\\" width)\"))\n" +
                    "  final_img<-c(final_img,img_list)\n" +
                    "  \n" +
                    "  url<-b[which(str_detect(b,\"ArticleRead\"))]\n" +
                    "  url1<-str_extract(url,(\"(?<=href=).*(?=onclick)\"))\n" +
                    "  url_2<-str_sub(url1,3)\n" +
                    "  clubid<-str_extract(url_2,(\"(?<=clubid=).*(?=&me)\"))\n" +
                    "  menuid<-str_extract(url_2,(\"(?<=menuid=).*(?=&articleid)\"))\n" +
                    "  articleid<-str_extract(url_2,(\"(?<=articleid=).*(?=&query)\"))\n" +
                    "  query<-str_extract(url_2,(\"(?<=query=).*(?=&art)\"))\n" +
                    "  art<-str_sub(str_extract(url_2,(\"(?<=art=).*(?=)\")),,-3)\n" +
                    "  final_link<-paste0(\"https://cafe.naver.com/joonggonara/\",articleid)\n" +
                    "  final_address<-c(final_address,final_link)\n" +
                    "  \n" +
                    "  \n" +
                    "  title<-b[which(str_detect(b,\"\\t\\t\\t<h3\"))]\n" +
                    "  title1<-gsub(\"<.*?>\",\"\",title) \n" +
                    "  title2<-gsub(\"\\t\",\"\",title1)\n" +
                    "  title_final1<-gsub(\"&quot;\",\"\",title2) \n" +
                    "  title_final<-gsub(\"title_final1\",\"\",title_final1) \n" +
                    "  final_title<-c(final_title,title_final)\n" +
                    "  \n" +
                    "  product_url<-paste0(\"https://apis.naver.com/cafe-web/cafe-articleapi/cafes/\",clubid, \"/articles/\", articleid, \"?menuid=\", menuid, \"&query=\", query, \"&art=\", art)" +
                    "  \n" +
                    "  for(i3 in 1:length(title_final)){\n" +
                    "    remDr$navigate(product_url[i3])\n" +
                    "    Sys.sleep(0.1)\n" +
                    "    s_ss<-(remDr$getPageSource()[1])\n" +
                            "img11<-str_extract(s_ss,(\"(?<=f740_740).*(?=f740_740)\"))\n" +
                            "    if(is.na(img11)){\n" +
                            "      img11<-str_extract(s_ss,(\"(?<=cafe_wa420).*(?=cafe_wa420)\"))\n" +
                            "      img12<-str_split(img11,\"url\")\n" +
                            "      img13<-str_extract(img12[[1]][2],(\"(?<=).*(?=service)\"))\n" +
                            "      img14<-str_sub(img13,4,-4)\n" +
                            "      img[i3]<-paste0(img14,\"&type=cafe_wa420\")\n" +
                            "    }\n" +
                            "    else{\n" +
                            "      img12<-str_extract(img11,(\"(?<=href=).*(?=target)\"))\n" +
                            "      img[i3]<-str_sub(img12,2,-3)\n" +
                            "    }" +
                    "    \n" +
                    "    test_p1<-str_extract(s_ss,(\"(?<=price<).*(?=주의하세요)\"))\n" +
                    "    test_p2<-str_extract(s_ss,(\"(?<=price).*(?=주의하세요)\"))\n" +
                    "    \n" +
                    "    if(!(is.na(test_p1))){\n" +
                    "      p1<-str_extract(s_ss,(\"(?<=price<).*(?=주의하세요)\"))\n" +
                    "      p2<-str_extract(p1,(\"(?<=objectBox-number).*(?=거래전)\"))\n" +
                    "      p3<-str_sub(p2,0,56)\n" +
                    "      p4<-str_extract(p3,(\"(?<=>).*(?=</span></sp)\"))\n" +
                    "      p5<-gsub(\",\",\"\",p4)\n" +
                    "      p6<-gsub(\" \",\"\",p5)\n" +
                    "      price[i3]<-as.double(p6)\n" +
                    "    } else if(!(is.na(test_p2))){\n" +
                    "      p1<-str_extract(s_ss,(\"(?<=price).*(?=주의하세요)\"))\n" +
                    "      p2<-str_extract(p1,(\"(?<=:).*(?=topBanner)\"))\n" +
                    "      p3<-str_extract(p2,(\"(?<=).*(?=,)\"))\n" +
                    "      price[i3]<-as.double(p3)\n" +
                    "    }else{\n" +
                    "      p1<-str_extract(s_ss,(\"(?<=가격).*(?= 원&lt)\"))\n" +
                    "      p2<-str_extract(p1,(\"(?<=color).*(?=)\"))\n" +
                    "      p3<-str_extract(p2,(\"(?<=size).*(?=)\"))\n" +
                    "      p4<-str_extract(p3,(\"(?<=gt;).*(?=)\"))\n" +
                    "      p5<-gsub(\",\",\"\",p4)\n" +
                    "      p6<-gsub(\" \",\"\",p5)\n" +
                    "      price[i3]<-as.double(p6)\n" +
                    "    }\n" +
                            "if(is.na(price[i3])){\n" +
                            "      p1<-str_extract(s_ss,(\"(?<=price).*(?= )\"))\n" +
                            "      p2<-str_sub(p1,0,13)\n" +
                            "      p3<-str_extract(p2,(\"(?<=:).*(?=,)\"))\n" +
                            "      price[i3]<-as.double(p3)\n" +
                            "}" +
                    "  }\n" +
                            "final_img11<-c(final_img11, img)\n" +
                            "img<-NULL" + "\n" +
                    "  final_price<-c(final_price, price)\n" +
                    "  price<-NULL\n" +
                    "}\n");
            System.out.println();
            address = rconnect.rConnection[port].eval("final_address").asStrings();
            title = rconnect.rConnection[port].eval("final_title").asStrings();
            price = rconnect.rConnection[port].eval("final_price").asIntegers();
            img = rconnect.rConnection[port].eval("final_img11").asStrings();

//            for (int i = 0; i < title.length; i++){
//                Item item = new Item();
//                item.setAddress(address[i]);
//                item.setImg(img[i]);
//                item.setItemname(keyword);
//                item.setPrice(price[i]);
//                item.setTitle(title[i]);
//                accountRepository.save(item);
//            }

            model.addAttribute("link",address);
            model.addAttribute("title",title);
            model.addAttribute("price",price);
            model.addAttribute("img",img);
            rconnect.retrunPort(port);
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
