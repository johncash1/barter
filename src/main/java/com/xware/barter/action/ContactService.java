package com.xware.barter.action;

import com.xware.barter.domain.Contact;
import com.xware.barter.domain.Item;

public interface ContactService {
	Contact editItem(Contact contact);
	boolean removeContact(Integer id);
	Contact add(Contact contact);
	Contact getContact(Integer id);
	Contact editContact(Contact contact);
}
