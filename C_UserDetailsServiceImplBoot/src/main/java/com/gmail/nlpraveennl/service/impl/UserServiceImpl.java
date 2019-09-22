package com.gmail.nlpraveennl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.model.DbUserDetails;
import com.gmail.nlpraveennl.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	Logger	OUT	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao	userDao;

	@Override
	public DbUserDetails authenticateUser(DbUserDetails userDetails)
	{
		return userDao.authenticateUser(userDetails);
	}

	@Override
	public DbUserDetails addUser(DbUserDetails userDetails)
	{
		String encodedPass = new BCryptPasswordEncoder().encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		userDetails.setPassword(encodedPass);

		userDetails.setEnabled(true);

		return userDao.addUser(userDetails);
	}

	@Override
	public DbUserDetails modifyUser(DbUserDetails userDetails)
	{
		String encodedPass = new BCryptPasswordEncoder().encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		userDetails.setPassword(encodedPass);

		return userDao.modifyUser(userDetails);
	}

	@Override
	public List<DbUserDetails> listUser()
	{
		List<DbUserDetails> userList = userDao.listUser();
		OUT.info("Fetched user list of size: {}", userList.size());
		
		return userList;
	}

	@Override
	public DbUserDetails getUser(int userId)
	{
		return userDao.getUser(userId);
	}

}
