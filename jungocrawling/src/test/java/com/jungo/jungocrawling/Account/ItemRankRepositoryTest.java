package com.jungo.jungocrawling.Account;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class ItemRankRepositoryTest {

    @Autowired
    ItemRankRepository itemRankRepository;

    ItemRank itemRank = new ItemRank();

    @Test
    void findByRank() {
        ItemRank itemRank = new ItemRank();
        itemRank.setCount(0);
        itemRank.setTitle("갤럭시");
        itemRankRepository.save(itemRank);
        itemRank = new ItemRank();
        itemRank.setCount(2);
        itemRank.setTitle("아이폰");
        itemRankRepository.save(itemRank);
        itemRank = new ItemRank();
        itemRank.setCount(1);
        itemRank.setTitle("LG");
        itemRankRepository.save(itemRank);

        Page<ItemRank> rank = itemRankRepository.findAll(PageRequest.of(0,10, Sort.by("count").descending()));
        assertThat(rank.getContent().size()).isEqualTo(3);
        assertThat(rank.getContent().get(0).getTitle()).isEqualTo("아이폰");

        Optional<ItemRank> title = itemRankRepository.findByTitle("아이폰");

        if (title.isEmpty()){
            itemRank = new ItemRank();
            itemRank.setTitle("아이폰");
            itemRank.setCount(0);
            itemRankRepository.save(itemRank);
        } else {
            title.get().setCount(title.get().getCount() + 1);
            itemRankRepository.save(title.get());
        }
        assertThat(itemRankRepository.findByTitle("아이폰").get().getCount()).isEqualTo(3);
    }

}