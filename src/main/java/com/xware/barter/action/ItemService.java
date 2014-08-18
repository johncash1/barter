package com.xware.barter.action;

import com.xware.barter.domain.Item;

public interface ItemService {
	
	Item editItem(Item item);
	boolean removeItem(Integer id);
	Item add(Item item);
	Item getItem(Integer id);

}
