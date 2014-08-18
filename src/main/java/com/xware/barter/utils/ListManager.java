package com.xware.barter.utils;

import java.util.List;

public class ListManager {
	
	public boolean isPresent(String target,List<String> tlist ){
		String targets=target.replaceAll("\\s+", "");
		
		 for (String s:tlist){
			
			 
			 if (s.replaceAll("\\s+", "").toLowerCase().equals(targets.toLowerCase()))
				 return false;
			 
		 }
		return true;
	}

}
