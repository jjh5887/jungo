package com.jungo.jungocrawling.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY id DESC offset :page*18 - 18 LIMIT 18")
//    List<Item> findByKeyword(String keyword, Integer page);

    @Query(nativeQuery = true, value = "select * from item order by id desc limit 5;")
    List<Item> findByHomeone();

    @Query(nativeQuery = true, value = "select * from item order by id desc limit 5 offset 5;")
    List<Item> findByHometwo();
    //select * from item ORDER BY id DESC LIMIT 10 offset 10; -> 11번째분터 10개 LIMIT가 갯수 offset이 시작점

    List<Item> findByTitleContainsOrderByPriceDesc(String title, Pageable pageable);

    List<Item> findByTitleContainsOrderByPriceAsc(String title, Pageable pageable);

    List<Item> findByTitleContainsOrderByIdDesc(String title, Pageable pageable);

    long countByTitleContains(String title);

    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY price")
    List<Item> findByASC(String keyword);
    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY  price DESC ")
    List<Item> findByDESC(String keyword);

    @Query(nativeQuery = true, value = "select count(*) from item where title LIKE CONCAT('%', :keyword, '%')")
    int getCount(String keyword);

    @Modifying
    @Query(nativeQuery = true, value = "delete from item where id <= :oldId")
    void deleteByoldId(Long oldId);
}
