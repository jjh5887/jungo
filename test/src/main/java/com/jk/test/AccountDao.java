package com.jk.test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

@Service
public class AccountDao {
	
	private JdbcTemplate tem; 
	private int result;
	
	@Autowired
	public AccountDao(DriverManagerDataSource datasource) {
		tem = new JdbcTemplate(datasource);
	}
	
	public void accountInsert(final Account account) {
		final String sql = "INSERT INTO member (acId, acPw, balance) values (?,?,?)";
		result = tem.update(sql, account.getid(), account.getpassword(), account.getbalance());
		System.out.println(result);
		
	}
	
	public void setAccountBalance(String id, int balance) {
		//	db.get(id).setbalance(balance);
	}
	public Account findAccount(String id) {
//		if(!db.containsKey(id))
//			return null;
//		else 
//			return db.get(id);
		return null;
	}

}