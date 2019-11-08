package com.gmail.nlpraveennl.cacheservice;

import com.gmail.nlpraveennl.model.UserDetails;

public interface UserCacheUpdateSevice
{
	/**
	 * @param userDetails
	 * @return
	 */
	public boolean addUserEntity(UserDetails userDetails);
	
	/**
	 * @param userDetails
	 * @return
	 */
	public boolean updateUserEntity(UserDetails userDetails);
}
