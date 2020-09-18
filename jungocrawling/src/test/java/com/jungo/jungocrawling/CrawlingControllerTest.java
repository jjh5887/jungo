package com.jungo.jungocrawling;

import com.jungo.jungocrawling.Account.ItemRepository;
import com.jungo.jungocrawling.Account.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DataJpaTest
class CrawlingControllerTest {

    @Autowired
    ItemRepository itemRepository;
    @Test
    public void crawling()  {
        List<Item> id;


    }
}