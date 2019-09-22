package com.gmail.nlpraveennl.dao;

import java.util.List;

import com.gmail.nlpraveennl.model.UserDetails;

public interface UserDao
{

	/**
	 * Method not used in this project
	 * @param userDetails
	 * @return
	 */
	public UserDetails authenticateUser(UserDetails userDetails);

	/**
	 * @param userDetails
	 * @return
	 */
	public UserDetails addUser(UserDetails userDetails);

	/**
	 * @param userDetails
	 * @return
	 */
	public UserDetails modifyUser(UserDetails userDetails);

	/**
	 * @return
	 */
	public List<UserDetails> listUser();

	/**
	 * @param userId
	 * @return
	 */
	public UserDetails getUser(int userId);
}
