package com.jungo.jungocrawling.Account;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void indextest(){
        Item item = new Item();
        item.setId(1L);
        item.setAddress("address one");
        item.setImg("img one");
        item.setPrice(500);
        item.setTitle("item one");
        itemRepository.save(item);
        item.setId(2L);
        item.setAddress("address two");
        item.setImg("img two");
        item.setPrice(1000);
        item.setTitle("item two");
        itemRepository.save(item);
        item.setId(3L);
        item.setAddress("address three");
        item.setImg("img three");
        item.setPrice(800);
        item.setTitle("item three");
        itemRepository.save(item);
        item.setId(4L);
        item.setAddress("address four");
        item.setImg("img four");
        item.setPrice(2000);
        item.setTitle("tem four");
        itemRepository.save(item);

//        List<Item> items = itemRepository.findByKeyword();
//        for (int i = 0; i < items.size(); i++){
//            System.out.println(items.get(i).getTitle());
//            System.out.println(items.get(i).getPrice());
//        }
    }



    void test() {
        Item item = new Item();
        item.setId(1L);
        item.setAddress("address one");
        item.setImg("img one");
        item.setPrice(500);
        item.setTitle("item one");
        itemRepository.save(item);
        item.setId(2L);
        item.setAddress("address two");
        item.setImg("img two");
        item.setPrice(1000);
        item.setTitle("item two");
        itemRepository.save(item);
        item.setId(3L);
        item.setAddress("address three");
        item.setImg("img three");
        item.setPrice(800);
        item.setTitle("item three");
        itemRepository.save(item);
        item.setId(4L);
        item.setAddress("address four");
        item.setImg("img four");
        item.setPrice(2000);
        item.setTitle("tem four");
        itemRepository.save(item);

        List<Item> items = itemRepository.findByTitleContainsOrderByPriceDesc("item", PageRequest.of(0, 10));
        for (int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).getTitle());
            System.out.println(items.get(i).getPrice());
        }

        List<Item> itemsasc = itemRepository.findByTitleContainsOrderByPriceAsc("item", PageRequest.of(0, 10));
        for (int i = 0; i < items.size(); i++){
            System.out.println(itemsasc.get(i).getTitle());
            System.out.println(itemsasc.get(i).getPrice());
        }

        List<Item> itemid = itemRepository.findByTitleContainsOrderByIdDesc("item", PageRequest.of(0,10));
        for (int i = 0; i < items.size(); i++){
            System.out.println(itemid.get(i).getTitle());
            System.out.println(itemid.get(i).getPrice());
        }


    }
}