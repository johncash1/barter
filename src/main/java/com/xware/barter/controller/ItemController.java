package com.xware.barter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xware.barter.action.ItemService;
import com.xware.barter.domain.Item;

@Controller
public class ItemController {
//	@Autowired
//	private ItemService itemService;


		
		@RequestMapping(value = "/additem/", method = RequestMethod.POST)
		public Item AddItem(@ModelAttribute Item item) {
		ModelAndView modelAndView = new ModelAndView("item");
 //        itemService.add(item); 
			Item i = new Item();
			return i;
		}
		@RequestMapping(value = "/edititem/", method = RequestMethod.POST)
		 ModelAndView EditItem(@PathVariable Integer id){
			ModelAndView modelAndView = new ModelAndView("edit_item");
     //       Item item= itemService.getItem(id);
     //       modelAndView.addObject("item",item);
			return modelAndView;
			
		}
		@RequestMapping(value = "/finditem/", method = RequestMethod.POST)
		Item FindItem(@PathVariable Integer id){
			
			return new Item(id);
		}		
		@RequestMapping(value = "/deleteitem/", method = RequestMethod.POST)
		void RemoveItem(int id){
			
		}


}
