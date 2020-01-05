package com.jk.test;

import java.beans.PropertyVetoException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBconfig {

	@Bean
	public DriverManagerDataSource datasource() throws PropertyVetoException
	{
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@Jung-Pc:1521:xe");
		datasource.setUsername("c##scott");
		datasource.setPassword("tiger");
		return datasource;
	}

}
