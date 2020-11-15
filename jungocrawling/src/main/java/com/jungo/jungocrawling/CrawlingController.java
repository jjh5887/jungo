package com.jungo.jungocrawling;

import com.jungo.jungocrawling.Account.*;
import com.jungo.jungocrawling.utils.Criteria;
import com.jungo.jungocrawling.utils.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class CrawlingController {

    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemRankRepository itemRankRepository;

    @Autowired
    AccountRepository accountRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        long id = -1;
        if(session.getAttribute("Id") != null){
            id = (long)session.getAttribute("Id");
        }
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            model.addAttribute("login", true);
            String name = account.get().getName() + " 님";
            model.addAttribute("name", name);
            Optional<Account> account_now = accountRepository.findByEmail(account.get().getEmail());
            List<Item> favorite = new ArrayList<>(account_now.get().getItems());
            List<Item> recent = new ArrayList<>(account_now.get().getRecently_item());

            favorite.forEach(item -> {
                item.setPrice_html(decimalFormat.format(item.getPrice()));
            });

            recent.forEach(item -> {
                item.setPrice_html(decimalFormat.format(item.getPrice()));
            });
            int size = 10;
            if (recent.size() > 5) {
                if (recent.size() < 10)
                    size = recent.size();
                model.addAttribute("recent_list1", recent.subList(0 ,5));
                model.addAttribute("recent_list2", recent.subList(5 ,size));
                model.addAttribute("recent", true);
            } else if (recent.size() == 0) {
                model.addAttribute("recent", false);
            } else {
                model.addAttribute("recent_list1", recent.subList(0 ,recent.size()));
                model.addAttribute("recent_list2", recent.subList(0 ,recent.size()));
                model.addAttribute("recent", true);
            }
            size = 10;
            if (favorite.size() > 5){
                if (favorite.size() < 10)
                    size = favorite.size();
                model.addAttribute("favorite_list1",favorite.subList(0 ,5));
                model.addAttribute("favorite_list2",favorite.subList(5 ,size));
                model.addAttribute("favorite", true);
            } else if (favorite.size() == 0){
                model.addAttribute("favorite", false);
            } else {
                model.addAttribute("favorite_list1",favorite.subList(0 ,favorite.size()));
                model.addAttribute("favorite_list2",favorite.subList(0 ,favorite.size()));
                model.addAttribute("favorite", true);
            }
        } else {
            model.addAttribute("login", false);
        }

        List<Item> list1 = itemRepository.findByHomeone();
        List<Item> list2 = itemRepository.findByHometwo();
        list1.forEach(item -> {
            item.setPrice_html(decimalFormat.format(item.getPrice()));
        });
        list2.forEach(item -> {
            item.setPrice_html(decimalFormat.format(item.getPrice()));
        });
        model.addAttribute("list1",list1);
        model.addAttribute("list2",list2);

        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password ){
        Optional<Account> Account = accountRepository.findByEmail(email);
        HttpSession session = request.getSession();
        if (Account.isPresent() && Account.get().getPassword().equals(password)) {
            session.setAttribute("Id", Account.get().getId());
            return "redirect:/";
        }
        model.addAttribute("fail", true);
        return "login.html";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(Model model, HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("phone") String phone){
        if (email.equals("") || password.equals("") || name.equals("") || phone.equals("")){
            model.addAttribute("error", "빈칸을 모두 채워주세요");
            return "regist";
        }
        if (accountRepository.existsAccountByEmail(email)){
            model.addAttribute("error", "이미 존재하는 이메일 입니다.");
            return "regist";
        } else {

            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);
            account.setName(name);
            account.setPhone(phone);
            Account save = accountRepository.save(account);
            HttpSession session = request.getSession();
            session.setAttribute("Id", account.getId());
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("Id", null);
        session.setAttribute("login", false);
        return "redirect:/";
    }

    @RequestMapping(value = "/as", method = RequestMethod.GET)
    public String jungocrawling(Model model, HttpServletRequest request,
                                @RequestParam("keyword") String keyword,
                                @ModelAttribute("cri") Criteria cri,
                                @RequestParam(value = "sort", defaultValue = "1") String sort,
                                @RequestParam(value = "min", defaultValue = "none") String min,
                                @RequestParam(value = "max", defaultValue = "none") String max){
        int count = 0;
        int page = 1;
        List<Item> byTitleContainsOrderByIdDesc;
        List<Item> item = new ArrayList<>();
        PageMaker pageMaker = new PageMaker();

        if (min.equals("none")) {
            while (true) {
                byTitleContainsOrderByIdDesc = itemRepository.findByTitleContainsOrderByIdDesc(keyword, PageRequest.of(0, 100 * page));
                if (byTitleContainsOrderByIdDesc.size() == 100) {
                    page++;
                } else {
                    count = page * 100 + byTitleContainsOrderByIdDesc.size();
                    break;
                }
            }
            if (count == 0)
                return "noItem";
            pageMaker.setCri(cri);
            pageMaker.setTotalCount(count);
            switch (sort) {
                case "1":
                    item = itemRepository.findByTitleContainsOrderByIdDesc(keyword, PageRequest.of(cri.getPage() - 1, 12));
                    break;
                case "2":
                    item = itemRepository.findByTitleContainsOrderByPriceDesc(keyword, PageRequest.of(cri.getPage() - 1, 12));
                    break;
                case "3":
                    item = itemRepository.findByTitleContainsOrderByPriceAsc(keyword, PageRequest.of(cri.getPage() - 1, 12));
                    break;
            }
        } else {
            int min_p = Integer.parseInt(min);
            int max_p =  Integer.parseInt(max);
           count = itemRepository.countByMin(keyword, min_p, max_p);
            if (count == 0)
                return "noItem";
            pageMaker.setCri(cri);
            pageMaker.setTotalCount(count);
            switch (sort) {
                case "1":
                    item = itemRepository.findByMin(keyword, min_p, max_p, (cri.getPage()*12) - 12);
                    break;
                case "2":
                    item = itemRepository.findByMinDesc(keyword, min_p, max_p, (cri.getPage()*12) - 12);
                    break;
                case "3":
                    item = itemRepository.findByMinAsc(keyword, min_p, max_p, (cri.getPage()*12) - 12);
                    break;
            }
        }
        for (int i = 0; i < item.size(); i++){
            item.get(i).setPrice_html(decimalFormat.format(item.get(i).getPrice()));
        }

        Optional<ItemRank> title = itemRankRepository.findByTitle(keyword);
        if (!keyword.equals("")) {
            if (title.isEmpty()) {
                ItemRank itemRank = new ItemRank();
                itemRank.setCount(0);
                itemRank.setTitle(keyword);
                itemRankRepository.save(itemRank);
            } else {
                title.get().setCount(title.get().getCount() + 1);
                itemRankRepository.save(title.get());
            }
        }

        HttpSession session = request.getSession();
        long id = -1;
        if(session.getAttribute("Id") != null){
            id = (long)session.getAttribute("Id");
        }

        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Set<Item> items = account.get().getRecently_item();
            items.add(item.get(0));
            account.get().setRecently_item(items);
            accountRepository.save(account.get());
            String name = account.get().getName() + " 님";
            model.addAttribute("login", true);
            model.addAttribute("name", name);
        }
        else
            model.addAttribute("login", false);

        model.addAttribute("items", item);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("keyword", keyword);
        return "item";


    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    public String favoirte(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, @RequestParam("id") long iid){
        HttpSession session = request.getSession();
        long id = -1;
        if(session.getAttribute("Id") != null){
            id = (long)session.getAttribute("Id");
        }
        Optional<Account> account = accountRepository.findById(id);
        System.out.println(id);
        String referer = request.getHeader("Referer");
        if (account.isPresent()) {
            model.addAttribute("login", true);
            String name = account.get().getName() + " 님";
            model.addAttribute("name", name);
            Set<Item> items = account.get().getItems();
            items.add(itemRepository.findById(iid).get());
            account.get().setItems(items);
            accountRepository.save(account.get());
            System.out.println("즐찾 추가 성공!");
        }
        else
            model.addAttribute("login", false);

        return "redirect:" + referer;

    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String my(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        long id = -1;
        if(session.getAttribute("Id") != null){
            id = (long)session.getAttribute("Id");
        } else {
            return "redirect:/";
        }
        Optional<Account> account = accountRepository.findById(id);
        ArrayList<Item> items = new ArrayList<>(account.get().getItems());
        items.forEach(item -> {
            item.setPrice_html(decimalFormat.format(item.getPrice()));
        });
        String name = account.get().getName() + " 님";
        model.addAttribute("name", name);
        model.addAttribute("items", items);
        return "my";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Model model, HttpServletRequest request, @RequestParam("id") long iid){
        HttpSession session = request.getSession();
        long id = -1;
        if(session.getAttribute("Id") != null){
            id = (long)session.getAttribute("Id");
        } else {
            return "redirect:/";
        }
        Optional<Account> account = accountRepository.findById(id);
        Set<Item> items_delete = account.get().getItems();
        items_delete.remove(itemRepository.findById(iid).get());
        account.get().setItems(items_delete);
        accountRepository.save(account.get());
        ArrayList<Item> items = new ArrayList<>(account.get().getItems());
        items.forEach(item -> {
            item.setPrice_html(decimalFormat.format(item.getPrice()));
        });
        String name = account.get().getName() + " 님";
        model.addAttribute("name", name);
        model.addAttribute("items", items);
        return "my";
    }




    @GetMapping("/as/asc")
    public String ASC(Model model, @RequestParam("keyword") String keyword){

        List<Item> item = itemRepository.findByASC(keyword);
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

        List<Item> item = itemRepository.findByDESC(keyword);
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


}
