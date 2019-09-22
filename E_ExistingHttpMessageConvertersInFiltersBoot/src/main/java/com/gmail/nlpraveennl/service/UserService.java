package com.gmail.nlpraveennl.service;

import java.util.List;

import com.gmail.nlpraveennl.model.UserDetails;

public interface UserService
{

	/**
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
	 * @param userDetails
	 * @return
	 */
	public List<UserDetails> listUser();
	
	/**
	 * @param userId
	 * @return
	 */
	public UserDetails getUser(int userId);

}
