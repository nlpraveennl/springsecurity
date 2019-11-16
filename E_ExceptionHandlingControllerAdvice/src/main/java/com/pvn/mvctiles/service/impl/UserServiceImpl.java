package com.pvn.mvctiles.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvn.mvctiles.dao.UserDao;
import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	Logger	OUT	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao	userDao;

	@Override
	public UserDetails authenticateUser(UserDetails userDetails)
	{
		return userDao.authenticateUser(userDetails);
	}

	@Override
	public UserDetails addUser(UserDetails userDetails)
	{
		String encodedPass = new BCryptPasswordEncoder().encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		userDetails.setPassword(encodedPass);

		userDetails.setEnabled(true);

		return userDao.addUser(userDetails);
	}

	@Override
	public UserDetails modifyUser(UserDetails userDetails)
	{
		String encodedPass = new BCryptPasswordEncoder().encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		userDetails.setPassword(encodedPass);

		return userDao.modifyUser(userDetails);
	}

	@Override
	public List<UserDetails> listUser()
	{
		List<UserDetails> userList = userDao.listUser();
		OUT.info("Fetched user list of size: {}", userList.size());
		
		return userList;
	}

	@Override
	public UserDetails getUser(int userId)
	{
		return userDao.getUser(userId);
	}

}
