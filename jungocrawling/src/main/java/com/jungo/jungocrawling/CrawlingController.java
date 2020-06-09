package com.jungo.jungocrawling;

import com.jungo.jungocrawling.Account.AccountRepository;
import com.jungo.jungocrawling.Account.Item;
import com.jungo.jungocrawling.utils.Criteria;
import com.jungo.jungocrawling.utils.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CrawlingController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/as", method = RequestMethod.GET)
    public String jungocrawling(Model model, @RequestParam("keyword") String keyword, @ModelAttribute("cri") Criteria cri){
        int count;
        count = accountRepository.getCount(keyword);
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(count);
        List<Item> item = accountRepository.findByKeyword(keyword,cri.getPage());
        model.addAttribute("list", item);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("keyword", keyword);
        return "Item";


//        String[] address = new String[18];
//        String[] title = new String[18];
//        String[] img = new String[18];
//        int[] price = new int[18];
//
//        for(int i = 0; i <  18; i++) {
//            address[i] = item.get(i).getAddress();
//            System.out.println(address[i]);
//            img[i] = item.get(i).getImg();
//            System.out.println(img[i]);
//            title[i] = item.get(i).getTitle();
//            System.out.println(title[i]);
//            price[i] = item.get(i).getPrice();
//            System.out.println(price[i]);
//        }
//
//        model.addAttribute("title", title);
//        model.addAttribute("address", address);
//        model.addAttribute("img", img);
//        model.addAttribute("price", price);

    }
    @GetMapping("/as/asc")
    public String ASC(Model model, @RequestParam("keyword") String keyword){

        List<Item> item = accountRepository.findByASC(keyword);
        String[] address = new String[item.size()];
        String[] title = new String[item.size()];
        String[] img = new String[item.size()];
        int[] price = new int[item.size()];

        for(int i = 0; i< item.size(); i++) {
            address[i] = item.get(i).getAddress();
            img[i] = item.get(i).getImg();
            title[i] = item.get(i).getTitle();
            price[i] = item.get(i).getPrice();
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("title", title);
        model.addAttribute("address", address);
        model.addAttribute("img", img);
        model.addAttribute("price", price);

        return "crawlingOk";
    }
    @GetMapping("/as/desc")
    public String DESC(Model model, @RequestParam("keyword") String keyword){

        List<Item> item = accountRepository.findByDESC(keyword);
        String[] address = new String[item.size()];
        String[] title = new String[item.size()];
        String[] img = new String[item.size()];
        int[] price = new int[item.size()];

        for(int i = 0; i< item.size(); i++) {
            address[i] = item.get(i).getAddress();
            System.out.println(address[i]);
            img[i] = item.get(i).getImg();
            System.out.println(img[i]);
            title[i] = item.get(i).getTitle();
            System.out.println(title[i]);
            price[i] = item.get(i).getPrice();
            System.out.println(price[i]);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("title", title);
        model.addAttribute("address", address);
        model.addAttribute("img", img);
        model.addAttribute("price", price);

        return "crawlingOk";
    }

//    @PostMapping("/as/Ascending")
//    public String Ascending(Model model, @RequestParam("keyword") String keyword){
//        Item[] item = accountRepository.findByitemname(keyword);
//        Item buffer;
//        String[] address;
//        String[] img;
//        String[] title;
//        int[] price;
//
//        for(int i = 0; i < item.length; i++){
//            for(int j = 1; i < item.length; i++){
//                if(item[i].getPrice() < item[j].getPrice()){
//                    buffer = item[i];
//                    item[i] = item[j];
//                    item[j] = buffer;
//                }
//            }
//        }
//    return "priceAscending";
//    }

//    @PostMapping("/as/Descending")
//    public String Descending(Model model, @RequestParam("keyword") String keyword){
//        Item[] item = accountRepository.findByitemname(keyword);
//        return "priceDescending";
//    }

    @GetMapping("/asss")
    public String Rcrawling(Model model, @RequestParam("keyword") String keyword) {
        List<Item> item;
        item = accountRepository.findByKeyword(keyword, 10);
        String[] address = new String[item.size()];
        String[] title = new String[item.size()];
        String[] img = new String[item.size()];
        int[] price = new int[item.size()];
        for (int i = 0; i < item.size(); i++){
            price[i] = item.get(i).getPrice();
            address[i] = item.get(i).getAddress();
            img[i] = item.get(i).getImg();
            title[i] = item.get(i).getTitle();
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("title", title);
        model.addAttribute("address", address);
        model.addAttribute("img", img);
        model.addAttribute("price", price);

        return "crawlingOk";
    }

}
