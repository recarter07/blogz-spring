package org.launchcode.blogz.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String index(HttpServletRequest request, Model model){
		
		// TODO - fetch users and pass to template
	    // RC Code //
		
		// fetch users
//		String username = request.getParameter("username"); - gets param from URL, like ?name=jim
//		String username = "Bob, Jim, Randy";
		List<User> users = userDao.findAll();
//		String username = User.getUsername();

		
//		String authorURL = "/blog/" + username;
//		for (User u : users) {	
//		}
		
		// add users to template
//		model.addAttribute("subAuthorURL", authorURL);
		model.addAttribute("subUsers", users);
//		model.addAttribute("subUsername", username);
		return "index";
	}
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String blogIndex(HttpServletRequest request, Model model) {
		
		// TODO - fetch posts and pass to template
	    // RC Code //
		
		// fetch posts
//		String post = request.getParameter("post"); - gets param from URL, like ?name=jim
//		String post = "OMG, how'd we get here?!";
//		String post = Post.getBody();
		List<Post> allPosts = postDao.findAll();
		
		
		// add posts to template
		model.addAttribute("subheading", "All Blog Posts");
		model.addAttribute("subPosts", allPosts);
		return "blog";
	}
	
}


// source: Intro to Thymeleaf vid


// ORIGINAL CODE BELOW
//package org.launchcode.blogz.controllers;
//
//import java.util.List;
//
//import org.launchcode.blogz.models.Post;
//import org.launchcode.blogz.models.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class BlogController extends AbstractController {
//
//	@RequestMapping(value = "/")
//	public String index(Model model){
//		
//		// TODO - fetch users and pass to template
//	    // RC Code //
//		
//		return "index";
//	}
//	
//	@RequestMapping(value = "/blog")
//	public String blogIndex(Model model) {
//		
//		// TODO - fetch posts and pass to template
//	    // RC Code //
//		
//		return "blog";
//	}
//	
//}