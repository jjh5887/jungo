package com.jungo.jungocrawling;

import com.jungo.jungocrawling.Account.Item;
import com.jungo.jungocrawling.Account.ItemRank;
import com.jungo.jungocrawling.Account.ItemRankRepository;
import com.jungo.jungocrawling.Account.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ApiController {

    @Autowired
    ItemRankRepository itemRankRepository;

    @GetMapping(value = "/rank")
    public List<ItemRank> rank(){
        Page<ItemRank> count = itemRankRepository.findAll(PageRequest.of(0, 10, Sort.by("count").descending()));
        return count.getContent();
    }
}
