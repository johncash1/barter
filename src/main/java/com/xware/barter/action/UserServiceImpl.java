package com.xware.barter.action;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xware.barter.domain.User;


@Transactional
public class UserServiceImpl implements UserService {
	  @Autowired
	  private SessionFactory sessionFactory;

	public User updateUser(User u) {
		try{
		sessionFactory.getCurrentSession().update(u);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return u;
	}

	public boolean removeUser(Integer id) {
		try{
		User u =getUser(id);
	//	sessionFactory.getCurrentSession().delete(u);
		
	//	Query q = sessionFactory.getCurrentSession().createQuery("delete from User where id =:id");
	//	 q.setParameter("id", id);
	//	 q.executeUpdate();
		 sessionFactory.getCurrentSession().delete(u);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public User add(User u) {
	/*	Integer i =u.getId();
			if (i==null)
				i=(Integer) sessionFactory.getCurrentSession().save(u);
			else
			*/
		        sessionFactory.getCurrentSession().saveOrUpdate(u);
			Integer i =u.getId();
		if (i != null)
		return u;
		else
			return null;
		// TODO Auto-generated method stub

	}

	public User getUser(Integer id) {
		//User u = 
			Query q = sessionFactory.getCurrentSession().createQuery("from User where id =:id");
			 q.setParameter("id", id);
		return (User)	q.list().get(0);
	}

}
