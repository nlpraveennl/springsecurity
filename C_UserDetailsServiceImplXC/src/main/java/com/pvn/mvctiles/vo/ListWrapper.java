package com.pvn.mvctiles.vo;

import java.util.List;

import com.pvn.mvctiles.model.DbUserDetails;

public class ListWrapper
{
	private List<DbUserDetails> list;
	
	public List<DbUserDetails> getList()
	{
		return list;
	}

	public void setList(List<DbUserDetails> list)
	{
		this.list = list;
	}
}
