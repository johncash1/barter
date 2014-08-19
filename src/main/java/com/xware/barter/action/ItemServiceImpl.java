package com.xware.barter.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xware.barter.domain.Item;

@Transactional
public class ItemServiceImpl implements ItemService{
	  @Autowired
	  private SessionFactory sessionFactory;
	
	
	@Override
	public Item editItem(Item item) {
		try{
		sessionFactory.getCurrentSession().update(item);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return item;
		
	}

	@Override
	public boolean removeItem(Integer id) {
		Item item= getItem(id);
		try{
		sessionFactory.getCurrentSession().delete(item);
	}
	catch(Exception e){
		System.out.println(e.getMessage());
		return false;
	}
		return true;
	}

	@Override
	public Item add(Item item) {
		
		try {
		 sessionFactory.getCurrentSession().saveOrUpdate(item);
		 Integer id =item.getId(); 
		 if (id != null)
		    return item;
		 

		}
		catch(HibernateException he){
			
			System.out.println( "HIBERNATE ERROR!!! "+  he.getMessage());
		}
		return null;
	}

	@Override
	public Item getItem(Integer id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Item where id =:id");
		 q.setParameter("id", id);
		 List<Item> i = q.list();
		 if (i.size()>0)
	         return i.get(0);
		 else return null;
		
	}

}
