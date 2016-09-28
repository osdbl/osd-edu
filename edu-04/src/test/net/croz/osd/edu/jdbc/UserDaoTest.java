package net.croz.osd.edu.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import net.croz.osd.edu.conf.AppConfig;
import net.croz.osd.edu.conf.JdbcConfig;
import net.croz.osd.edu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class UserDaoTest {

	@Autowired
	@Qualifier("basicUsersDaoImpl")
	UsersDao basicUsersDao;

	@Autowired
	@Qualifier("springTemplatesUsersDaoImpl")
	UsersDao springTemplatesUsersDao;
	

	@Test
	public void testabc() {

		Assert.isInstanceOf(BasicUsersDaoImpl.class, basicUsersDao);

		Assert.isInstanceOf(SpringTemplatesUsersDaoImpl.class, springTemplatesUsersDao);

	}

	@Test
	public void testbaze() {
		/*List<User> users = basicUsersDao.getUsers();
		Assert.isTrue(users.size() == 3);
		for (int i = 0; i < users.size(); i++)
			System.out.println(users.get(i).getUsername() + "," + users.get(i).getRoles().size());*/
		
		List<User> users = springTemplatesUsersDao.getUsers();
		Assert.isTrue(users.size() == 3);
		for (int i = 0; i < users.size(); i++)
			System.out.println(users.get(i).getUsername() + "," + users.get(i).getRoles().size());
	}
	
	@Test
	public void testActiveUsers(){
		
		List<User> users=basicUsersDao.getUsersByStatus(false);
		Assert.isTrue(users.size() == 1);
		for (int i = 0; i < users.size(); i++)
			System.out.println(users.get(i).getUsername() );
		
	}
	
	@Test
	public void testFindUser(){
		
		User user = springTemplatesUsersDao.findUserByUsername("jsajlovic");
		System.out.println(user);
	}
	
	

}
