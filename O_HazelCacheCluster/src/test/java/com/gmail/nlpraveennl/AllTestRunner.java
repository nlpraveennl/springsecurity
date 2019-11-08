package com.gmail.nlpraveennl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value =  
{
		TestRoleDAO.class,
		TestUserDAO.class,
		TestProjectDAO.class,
		TestIssueDAO.class
})
public class AllTestRunner
{

}
