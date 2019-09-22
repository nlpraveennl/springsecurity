package com.gmail.nlpraveennl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.model.RoleMaster;
import com.gmail.nlpraveennl.model.UserDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DemoDataLoader implements CommandLineRunner 
{
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception 
    {
    	RoleMaster userRole = new RoleMaster();
    	userRole.setName("ROLE_USER");
    	
    	RoleMaster adminRole = new RoleMaster();
    	adminRole.setName("ROLE_ADMIN");
    	
    	System.out.println(userRole);
    	System.out.println(adminRole);
    	entityManager.persist(userRole);
    	entityManager.persist(adminRole);
        entityManager.flush();
    	
        UserDetails  user = new UserDetails();
        user.setFirstName("user fn");
        user.setLastName("user ln");
        user.setUserName("user");
        user.setPassword(passwordEncoder.encode("user@123#"));
        user.setEmail("user@gmail.com");
        user.setGender("MALE");
        user.setEnabled(true);
        user.setRoleId(1);
        
    	UserDetails  admin = new UserDetails();
        admin.setFirstName("admin fn");
        admin.setLastName("admin ln");
        admin.setUserName("admin");
        admin.setPassword(passwordEncoder.encode("admin@123#"));
        admin.setEmail("admin@gmail.com");
        admin.setGender("MALE");
        admin.setEnabled(true);
        admin.setRoleId(2);
        
        System.out.println(admin);
        System.out.println(user);
        
        entityManager.persist(user);
        entityManager.persist(admin);
        
        entityManager.flush();
    }
    
	@Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
