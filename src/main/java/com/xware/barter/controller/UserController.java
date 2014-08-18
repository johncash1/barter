package com.xware.barter.controller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xware.barter.action.UserService;
import com.xware.barter.domain.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
//	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/adduser/", method = RequestMethod.POST)
	public User AddUser(@ModelAttribute User user) {
	ModelAndView modelAndView = new ModelAndView("new_user");
     userService.add(user); 
		User i = new User();
		return i;
	}
	@RequestMapping(value = "/edituser/", method = RequestMethod.GET)
	 ModelAndView EditUserForm(@PathVariable Integer id){
		ModelAndView modelAndView = new ModelAndView("edit_user");
   //    User user= userService.getUser(id);
   //     modelAndView.addObject("user",user);
		return modelAndView;
		
	}
	@RequestMapping(value = "/edituser/", method = RequestMethod.POST)
	 ModelAndView EditUser(@PathVariable User user){
		ModelAndView modelAndView = new ModelAndView("home");
  //    userService.updateUser(user);
       modelAndView.addObject("user",user);
       String message = "Team was successfully edited.";
      
               modelAndView.addObject("message", message);

		return modelAndView;
		
	}
	@RequestMapping(value = "/finduser/", method = RequestMethod.POST)
	User FindUser(@PathVariable Integer id){
		
		return new User(id);
	}		
	@RequestMapping(value = "/deleteuser/", method = RequestMethod.POST)
	void RemoveUser(int id){
		
	}

}
