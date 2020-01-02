package com.jk.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountService {
	
	@Autowired
	AccountDao dao;
	
	@RequestMapping(value="/as", method=RequestMethod.POST)
	public String registerAccount(Account account) {
		dao.accountInsert(account.getid(), account.getpassword(), account.getbalance());
		return "AccountRegisterOk";
	}

}
