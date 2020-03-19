package com.jungo.jungocrawling.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Item, Long> {
}
