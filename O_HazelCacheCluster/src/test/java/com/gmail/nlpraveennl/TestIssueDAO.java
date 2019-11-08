package com.gmail.nlpraveennl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.service.IssueService;

@ContextConfiguration(locations = "classpath:mybatis-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestIssueDAO 
{
     
    @Autowired
    private IssueService issueService;
     
    @Test
    public void testGetAllKeys()
    {
        List<Integer> keys = issueService.getAllKeys();
        Assert.assertNotNull("Null Resultset", keys);
        Assert.assertFalse("Empty Resultset", keys.isEmpty());
        Assert.assertTrue("Less than 5", keys.size() >= 5);
    }
    
    @Test
    public void testGetAllObjects()
    {
        List<Integer> keys = issueService.getAllKeys();
        Assert.assertNotNull("Non Empty Resultset", keys);
        
        Map<Integer, IssueEntity> roleMap = issueService.getAll(keys);
        assertNotNull("Empty resultset", roleMap);
        
        System.out.println("--------------");
        System.out.println(keys.size());
        System.out.println(roleMap.size());
        assertTrue("Same Size", keys.size() == roleMap.size());
    }
}