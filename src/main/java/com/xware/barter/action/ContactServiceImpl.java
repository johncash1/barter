package com.xware.barter.action;


	import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xware.barter.domain.Contact;

	@Transactional
	public class ContactServiceImpl implements ContactService {
		  @Autowired
		  private SessionFactory sessionFactory;
		
		
		@Override
		public Contact editContact(Contact contact) {
			try{
			sessionFactory.getCurrentSession().update(contact);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			return contact;
			
		}

		@Override
		public boolean removeContact(Integer id) {
			Contact contact= getContact(id);
			try{
			sessionFactory.getCurrentSession().delete(contact);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
			return true;
		}

		@Override
		public Contact add(Contact contact) {
			try{
				Integer id =  (Integer) sessionFactory.getCurrentSession().save(contact);
			contact.setId(id); 
			}
			catch(Exception e){
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
			return contact;
		}

		@Override
		public Contact getContact(Integer id) {
			Query q = sessionFactory.getCurrentSession().createQuery("from Contact where id =:id");
			 q.setParameter("id", id);
			List l= q.list();
			if (l.size() >0)
		           return (Contact)q.list().get(0);
			else return null;
			
		}

		@Override
		public Contact editItem(Contact contact) {
			// TODO Auto-generated method stub
			return null;
		}

	}

