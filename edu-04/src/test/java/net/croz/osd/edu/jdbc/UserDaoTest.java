package net.croz.osd.edu.jdbc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.croz.osd.edu.conf.JdbcConfig;
import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class UserDaoTest {
	@Autowired private Map<String, UsersDao> usersDaoMap;
	@Autowired Environment env;
	
	UsersDao usersDao;
	
	@Before
	public void selectDao() {
		usersDao = usersDaoMap.get(env.getProperty("userDao.impl"));
	}
	
	@Test
	public void loadAndSaveUsers() {
		List<User> existingUsers = usersDao.loadUsers();
		
		// change status of user 'admin'
		User adminOld = null;
		for (User usr : existingUsers) {
			System.out.println(usr);
			if ("admin".equals(usr.getUsername())) 
				adminOld = usr;
		}
		Assert.assertNotNull(adminOld);
		boolean adminOldStatus = adminOld.isEnabled();
		adminOld.setEnabled(!adminOldStatus);
		
		
		// add new user and save all
		User newUser = createUserWithRoles();
		existingUsers.add(newUser);
		usersDao.saveUsers(existingUsers);
		
		
		// test update and insert operations
		User adminUpdated = null;
		User savedNewUser = null;
		
		for (User usr : usersDao.loadUsers()) {
			System.out.println(usr);
			if ("admin".equals(usr.getUsername()))
				adminUpdated = usr;
			else if (newUser.getUsername().equals(usr.getUsername()))
				savedNewUser = usr;
		}
		
		Assert.assertNotNull(adminUpdated);
		Assert.assertNotSame(adminUpdated.isEnabled(), adminOldStatus);
		Assert.assertNotNull(savedNewUser);
		Assert.assertEquals(2, savedNewUser.getRoles().size());
	}
	
	
	private User createUserWithRoles() {
		String username = "user_" + Math.random();
		String password = "xxxxxxxxxx";
		
		UserRole test01 = new UserRole(username, "TEST_01");
		UserRole test02 = new UserRole(username, "TEST_02");
		Set<UserRole> roles = new HashSet<UserRole>(Arrays.asList(test01, test02));
		
		return new User(username, password, false, roles);
	}
}
