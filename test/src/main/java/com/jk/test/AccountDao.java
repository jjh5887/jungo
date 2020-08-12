package com.jk.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
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
	
	public int accountInsert(final Account account) {
		
		final String sql = "INSERT INTO member (acId, acPw, balance) values (?,?,?)";
		this.result = tem.update(sql, account.getid(), account.getpassword(), account.getbalance());
		return this.result;
	}
	
	public int setAccountBalance(Account account) {

		final String sql = "UPDATE member SET balance = ? WHERE acId = ?";
		result = tem.update(sql, account.getbalance(), account.getid());
		return result;
	}
	
	public Account findAccount(final Account account) {
		
		List<Account> buf = null;
		final String sql = "SELECT * FROM member WHERE acId = ?";
	
		buf = tem.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, account.getid());
			}
		}, new RowMapper<Account>() {
			@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
				Account ac = new Account();
				ac.setid(rs.getString("acId"));
				ac.setpassword(rs.getString("acPw"));
				ac.setbalance(rs.getInt("balance"));
				return ac;
		}});
//		result = tem.queryForObject(sql, new Object[] {account.getid(), account.getpassword()}, int.class);
		
		return buf.get(0);
	}

}