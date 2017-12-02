package com.intellect.assessment.SpringBoot.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.assessment.SpringBoot.rest.entities.User;

@RestController

public class UserService {
	
	final List<User> userList=new ArrayList<User>();
	
	
	@RequestMapping(value="/users",method=RequestMethod.POST,produces="text/plain",consumes="application/json")
	public String createUser(@RequestBody User user)
	{
		String email=user.getEmail();
		boolean existingUser=existingUser(email);
		if(existingUser)
		{
			return "User Already Exists";
		}
	
		else 
		{
			user.setId(Math.random()+"%&"+Math.random());
			userList.add(user);
			return "User created with UserId:"+user.getId();
		}
		
		
		
	}
	
	
	
	
	public boolean existingUser(String email)
	
	{
		
		boolean existingUser=false;
		for(User y:userList)
		{
			if (y.getEmail().equals(email))
			{
				existingUser=true;
				break;
			}
		}
		
		return existingUser;
	}
	
	@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT,consumes="application/json")
	public String updateUser(@RequestBody User u,@PathVariable(name="userId") String userId)
	{
		int pinCode=u.getPinCode();
		for (User t:userList)
		{
			if(t.getId().equals(userId))
			{
				t.setPinCode(pinCode);
			}
		}
		
		return "User Details Updated successfully";
	}
	
	
	@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable(name="userId") String userId)
	{
		for (User t:userList)
		{
			if(t.getId().equals(userId))
			{
				userList.remove(t);
			}
		}
		
		return "User Details deleted successfully";
		
	}
	
	
	
	

}

