package com.jungo.jungocrawling.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemRepository itemRepository;
    @Test
    public void di() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }
    }

  //  @Test
//    public void itemTest() throws SQLException {
//        Item account = new Item();
//        account.set
//        account.setPassword("pass");
//
//        Account newAccount = accountRepository.save(account);
//
//        assertThat(newAccount).isNotNull();
//
//        Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
//        assertThat(existingAccount).isNotNull();
//
//        Account nonExistingAccount = accountRepository.findByUsername("superman");
//        assertThat(nonExistingAccount).isNull();
//    }
}
