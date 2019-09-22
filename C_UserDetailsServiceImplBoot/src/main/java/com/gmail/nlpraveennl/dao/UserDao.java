package com.gmail.nlpraveennl.dao;

import java.util.List;

import com.gmail.nlpraveennl.model.DbUserDetails;

public interface UserDao
{

	/**
	 * Method not used in this project
	 * @param userDetails
	 * @return
	 */
	public DbUserDetails authenticateUser(DbUserDetails userDetails);
	

	/**
	 * @param userDetails
	 * @return
	 */
	public DbUserDetails addUser(DbUserDetails userDetails);

	/**
	 * @param userDetails
	 * @return
	 */
	public DbUserDetails modifyUser(DbUserDetails userDetails);

	/**
	 * @return
	 */
	public List<DbUserDetails> listUser();

	/**
	 * @param userId
	 * @return
	 */
	public DbUserDetails getUser(int userId);
}
