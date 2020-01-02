package com.jk.test;

import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class AccountDao {
	private HashMap<String, Account> db;
	
	public AccountDao() {
		db = new HashMap<String, Account>();
	}
	
	public void accountInsert(String id, int balance, int password) {
		Account account = new Account();
		account.setid(id);
		account.setbalance(balance);
		account.setpassword(password);
		db.put(id, account);
	}
	
	public void setAccount(String id, int password, int balance) {
		if (db.get(id).getpassword() == password)
			db.get(id).setbalance(balance);
	}

}