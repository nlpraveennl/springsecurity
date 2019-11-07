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

import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.service.RoleService;

@ContextConfiguration(locations = "classpath:mybatis-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRoleDAO 
{
     
    @Autowired
    private RoleService roleService;
     
    @Test
    public void testGetAllKeys()
    {
        List<Integer> keys = roleService.getAllKeys();
        Assert.assertNotNull("Null Resultset", keys);
        Assert.assertFalse("Empty Resultset", keys.isEmpty());
        Assert.assertTrue("Less than 3", keys.size() >= 3);
    }
    
    @Test
    public void testGetAllObjects()
    {
        List<Integer> keys = roleService.getAllKeys();
        Assert.assertNotNull("Non Empty Resultset", keys);
        
        Map<Integer, RoleEntity> roleMap = roleService.getAll(keys);
        assertNotNull("Empty resultset", roleMap);
        
        System.out.println("--------------");
        System.out.println(keys.size());
        System.out.println(roleMap.size());
        assertTrue("Same Size", keys.size() == roleMap.size());
    }
}