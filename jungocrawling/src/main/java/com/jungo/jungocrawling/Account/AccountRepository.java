package com.jungo.jungocrawling.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Item, Long> {

//    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY id DESC offset :page*18 - 18 LIMIT 18")
//    List<Item> findByKeyword(String keyword, Integer page);

    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%')ORDER BY id DESC offset :page*18 - 18 LIMIT 18")
    List<Item> findByKeyword(String keyword, int page);
    //select * from item ORDER BY id DESC LIMIT 10 offset 10; -> 11번째분터 10개 LIMIT가 갯수 offset이 시작점

    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY price")
    List<Item> findByASC(String keyword);
    @Query(nativeQuery = true, value = "select * from item where title LIKE CONCAT('%', :keyword, '%') ORDER BY  price DESC ")
    List<Item> findByDESC(String keyword);

    @Query(nativeQuery = true, value = "select count(*) from item where title LIKE CONCAT('%', :keyword, '%')")
    int getCount(String keyword);
}
