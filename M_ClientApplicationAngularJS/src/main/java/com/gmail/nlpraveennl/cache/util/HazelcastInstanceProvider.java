package com.gmail.nlpraveennl.cache.util;

import java.io.Serializable;

import com.hazelcast.core.HazelcastInstance;

public class HazelcastInstanceProvider implements Serializable
{
	private static final long serialVersionUID = 4526303890254026698L;

	private static final HazelcastInstanceProvider INSTANCE = new HazelcastInstanceProvider();
	
	private HazelcastInstance hazelcastInstance;
	
	private HazelcastInstanceProvider() {}
	
	public static HazelcastInstanceProvider getInstance()
	{
		return INSTANCE;
	}
	
	public HazelcastInstance getHazelcastInstance()
	{
		return hazelcastInstance;
	}
	
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance)
	{
		this.hazelcastInstance = hazelcastInstance;
	}
}
