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

import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.service.UserService;

@ContextConfiguration(locations = "classpath:hibernate-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDAO 
{
     
    @Autowired
    private UserService userService;
     
    @Test
    public void testGetAllKeys()
    {
        List<Integer> keys = userService.getAllKeys();
        Assert.assertNotNull("Null Resultset", keys);
        Assert.assertFalse("Empty Resultset", keys.isEmpty());
        Assert.assertTrue("Less than 5", keys.size() >= 5);
        Assert.assertTrue("Less than 10", keys.size() >= 10);
    }
    
    @Test
    public void testGetAllObjects()
    {
        List<Integer> keys = userService.getAllKeys();
        Assert.assertNotNull("Non Empty Resultset", keys);
        
        Map<Integer, UserEntity> usersMap = userService.getAll(keys);
        assertNotNull("Empty resultset", usersMap);
        
        System.out.println("--------------");
        System.out.println(keys.size());
        System.out.println(usersMap.size());
        assertTrue("Same Size", keys.size() == usersMap.size());
    }
}