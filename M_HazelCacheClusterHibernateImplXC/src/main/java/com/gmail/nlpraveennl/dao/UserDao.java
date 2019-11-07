package com.gmail.nlpraveennl.dao;

import java.util.Collection;
import java.util.List;

import com.gmail.nlpraveennl.vo.UserVO;

public interface UserDao
{

	/**
	 * @param keys 
	 * @return
	 */
	public List<UserVO> getAll(Collection<Integer> keys);
	
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();
	
}
