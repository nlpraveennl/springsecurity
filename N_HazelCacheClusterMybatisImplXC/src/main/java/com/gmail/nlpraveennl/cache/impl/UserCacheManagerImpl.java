package com.gmail.nlpraveennl.cache.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.UserCacheManager;
import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.cache.util.HazelcastCacheMapNames;
import com.gmail.nlpraveennl.cache.util.HazelcastInstanceProvider;
import com.gmail.nlpraveennl.util.ApplicationConstants;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.spi.ExecutionService;

@Service
public class UserCacheManagerImpl implements UserCacheManager
{
	Logger	OUT	= LoggerFactory.getLogger(UserCacheManagerImpl.class);
	
	@Autowired
	HazelcastInstance hazelCastInstance;

	@Override
	public Map<Integer, UserEntity> getAll()
	{
		try
		{
			return hazelCastInstance.getMap(HazelcastCacheMapNames.USER_CACHE_IMAP);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return null;
		}
	}

	@Override
	public Boolean addUserEntity(UserEntity userEntity)
	{
		try
		{
			ExecutorService es =hazelCastInstance.getExecutorService(ExecutionService.SCHEDULED_EXECUTOR);
			Future<Boolean> task = es.submit(new AddUserTask(userEntity));
			return task.get();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return false;
		}
	}

	@Override
	public Boolean updateUserEntity(UserEntity userEntity)
	{
		try
		{
			ExecutorService es =hazelCastInstance.getExecutorService(ExecutionService.SCHEDULED_EXECUTOR);
			Future<Boolean> task = es.submit(new UpdateUserTask(userEntity));
			return task.get();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return false;
		}
	}
}


class AddUserTask implements Callable<Boolean>, Serializable
{
	private static final long serialVersionUID = -3570086713190488941L;

	Logger	OUT	= LoggerFactory.getLogger(AddUserTask.class);

	private UserEntity			userEntity;
	
	AddUserTask(UserEntity userEntity)
	{
		this.userEntity = userEntity;
	}

	@Override
	public Boolean call() throws Exception
	{
		try
		{
			IMap<Integer, UserEntity> imap = HazelcastInstanceProvider.getInstance().getHazelcastInstance().getMap(HazelcastCacheMapNames.USER_CACHE_IMAP);
			imap.put(this.userEntity.getId(), this.userEntity);
			return true;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return false;
		}
	}
}

class UpdateUserTask implements Callable<Boolean>, Serializable
{
	private static final long serialVersionUID = 2908430435593225910L;

	Logger	OUT	= LoggerFactory.getLogger(UpdateUserTask.class);

	private UserEntity			userEntity;
	
	UpdateUserTask(UserEntity userEntity)
	{
		this.userEntity = userEntity;
	}

	@Override
	public Boolean call() throws Exception
	{
		IMap<Integer, UserEntity> imap = HazelcastInstanceProvider.getInstance().getHazelcastInstance().getMap(HazelcastCacheMapNames.USER_CACHE_IMAP);
		try
		{
			imap.lock(this.userEntity.getId());
			imap.replace(this.userEntity.getId(), this.userEntity);
			return true;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return false;
		}
		finally 
		{
			imap.unlock(this.userEntity.getId());
		}
	}
}
