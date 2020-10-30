package com.jungo.jungocrawling.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRankRepository extends JpaRepository<ItemRank, Long> {

    Optional<ItemRank> findByTitle(String title);
}
