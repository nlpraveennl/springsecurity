package com.pvn.mvctiles.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.pvn.mvctiles.model.DbUserDetails;

public interface UserDao
{

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public List<GrantedAuthority> authenticate(String username, String password);

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
