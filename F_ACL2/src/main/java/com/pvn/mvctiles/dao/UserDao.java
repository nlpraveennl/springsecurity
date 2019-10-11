package com.pvn.mvctiles.dao;

import java.util.List;

import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.vo.UserVO;

public interface UserDao
{

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
	 * @return
	 */
	public List<UserVO> listUserVO();

	/**
	 * @param userId
	 * @return
	 */
	public UserDetails getUser(int userId);
	
}
