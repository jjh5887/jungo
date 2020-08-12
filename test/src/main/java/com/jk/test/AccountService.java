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
		if((dao.accountInsert(account)) == 0)
			return "registerfail";
		else
			return "AccountRegisterOk";
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.POST)
	public String deposit(Account account) {
		Account buf = dao.findAccount(account);
		if(null == buf || !(buf.getpassword().equals(account.getpassword())))
			return "loginfail";
		else {
			account.setbalance(buf.getbalance() - account.getbalance());
			if(dao.setAccountBalance(account) == 0)
				return "redirect:/";
			else return "deposit";
		}
	}

}
