package com.pvn.mvctiles.service;

import java.util.List;

import com.pvn.mvctiles.model.DbUserDetails;

public interface UserService
{

	/**
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
	 * @param userDetails
	 * @return
	 */
	public List<DbUserDetails> listUser();
	
	/**
	 * @param userId
	 * @return
	 */
	public DbUserDetails getUser(int userId);

}
