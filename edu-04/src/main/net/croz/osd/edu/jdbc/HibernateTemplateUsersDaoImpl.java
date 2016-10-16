package net.croz.osd.edu.jdbc;

import java.util.Collection;
import java.util.List;

import net.croz.osd.edu.domain.User;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateTemplateUsersDaoImpl implements UsersDao {
	@Autowired private HibernateTemplate hibernateTemplate;

	@Override
	public List<User> loadUsers() {
		return hibernateTemplate.loadAll(User.class);
	}

	@Override
	@Transactional
	public void saveUsers(Collection<User> users) {
		for (User usr : users) 
			hibernateTemplate.saveOrUpdate(usr);
	}
}
