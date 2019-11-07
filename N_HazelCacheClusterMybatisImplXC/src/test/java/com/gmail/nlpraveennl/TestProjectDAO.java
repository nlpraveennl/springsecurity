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

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;
import com.gmail.nlpraveennl.service.ProjectService;

@ContextConfiguration(locations = "classpath:mybatis-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectDAO 
{
     
    @Autowired
    private ProjectService projectService;
     
    @Test
    public void testGetAllKeys()
    {
        List<Integer> keys = projectService.getAllKeys();
        Assert.assertNotNull("Null Resultset", keys);
        Assert.assertFalse("Empty Resultset", keys.isEmpty());
        Assert.assertTrue("Less than 5", keys.size() >= 5);
    }
    
    @Test
    public void testGetAllObjects()
    {
        List<Integer> keys = projectService.getAllKeys();
        Assert.assertNotNull("Non Empty Resultset", keys);
        
        Map<Integer, ProjectEntity> roleMap = projectService.getAll(keys);
        assertNotNull("Empty resultset", roleMap);
        
        System.out.println("--------------");
        System.out.println(keys.size());
        System.out.println(roleMap.size());
        assertTrue("Same Size", keys.size() == roleMap.size());
    }
}