package com.xware.barter.action;

import com.xware.barter.domain.User;

public interface UserService {
	User updateUser(User user);
	boolean removeUser(Integer id);
	User add(User user);
	User getUser(Integer id);

}
