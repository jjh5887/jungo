package com.jungo.jungocrawling.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

    @Query(nativeQuery = true, value = "select * from date where date = :deleteDate ORDER BY id DESC")
    List<Date> findByDate(Long deleteDate);

    @Query(nativeQuery = true, value = "select count(*)>0 from date where date = :deleteDate")
    Boolean existsByDate(Long deleteDate);

    @Query(nativeQuery = true, value = "delete from date where date <= :deleteDate")
    void deleteByDate(Long deleteDate);
}
