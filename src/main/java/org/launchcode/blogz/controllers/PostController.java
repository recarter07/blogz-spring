package org.launchcode.blogz.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.PostDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String newPost(HttpServletRequest request, Model model) {
		
		// TODO - implement newPost
	    // RC Code //
		
//		// STEPS:
	
		// get request parameters
		String newTitle = request.getParameter("title");
		String newBody = request.getParameter("body");
		
		HttpSession thisSession = request.getSession();
		
		User user = getUserFromSession(thisSession);
		
		
		// validate parameters
		if (newTitle == null || newTitle == "") {
			model.addAttribute("subNewError", "Post title and body cannot be blank");
			return "newpost";
		}
		
		if (newBody == null || newBody == "") {
			model.addAttribute("subNewError", "Post title and body cannot be blank");
			return "newpost";
		}
//		else {
//			newPost = "Cobra";
//			// if not valid, send back to the form, with error message
//			String error = "Posts cannot be blank";
//			return "redirect:newpost" + error; // + error message?  //////	
//		}
		
		Post newPost = new Post(newTitle, newBody, user);
		
//		postDao.save(newPost);    // postDao interacts with db
		int uid = postDao.save(newPost).getUid();
		
//		above statement is condensed version of below two statements without yeti variable:
//		Post yeti = postDao.save(newPost);
//		int uid = yeti.getUid();
		
		
//		model.addAttribute("title", newTitle);
//		model.addAttribute("body", newBody);
	
//		List<Post> allPosts = postDao.findAll();
		
		
//		changed redirect below:
//		return "redirect:index"; // TODO - this redirect should go to the new post's page  		
		return "redirect:/blog/" + user.getUsername() + "/" + uid;
	}
	
	// handles requests like /blog/sally/5
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
		
		// TODO - implement singlePost
	    // RC Code //
		
//		// STEPS:
		
		// get the given post
//		String singlePost = Post.getBody();
		Post singlePost = postDao.findByUid(uid);
//		String singlePost = findByUsername();
		Date created = singlePost.getCreated();
		
		// pass the post into the template
		model.addAttribute("subDate", created);
		model.addAttribute("post", singlePost);
		return "post";  // return the name of the template without .html ext
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String userPosts(@PathVariable String username, Model model) {
		
		// TODO - implement userPosts
	    // RC Code //
		
//		// STEPS:
		
		// get all of the user's posts
//		List<Post> = PostDao.findByAuthor(int authorId);    // hint on Piazza - findByAuthor not necessary
		User user = userDao.findByUsername(username);   // find by username first to define user obj
		List<Post> posts = user.getPosts();            
//		List<Post> = PostDao.findAll();
		
		// pass the posts into the template
		// (ex) model.addAttribute("name", someObj);
		// (ex) model.addAttribute("name", listOfStuff);
		
		String heading = username + "'s Posts!";
		
		model.addAttribute("subheading", heading);
		model.addAttribute("subPosts", posts);
		return "blog";    // return template name without .html ext
	}
	
}



//source: Intro to Thymeleaf vid


// ORIGINAL CODE BELOW
//package org.launchcode.blogz.controllers;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.launchcode.blogz.models.Post;
//import org.launchcode.blogz.models.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class PostController extends AbstractController {
//
//	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
//	public String newPostForm() {
//		return "newpost";
//	}
//	
//	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
//	public String newPost(HttpServletRequest request, Model model) {
//		
//		// TODO - implement newPost
//	    // RC Code //
//		
////		// STEPS:
//		
//		// get request parameters
//		
//		// validate parameters
//		
//		// if valid, create new Post (using Post model class)
//		
//		// if not valid, send back to the form, with error message
//		
//	    // RC Code //
//		return "redirect:index"; // TODO - this redirect should go to the new post's page  		
//	}
//	
//	// handles requests like /blog/sally/5
//	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
//	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
//		
//		// TODO - implement singlePost
//	    // RC Code //
//		
////		// STEPS:
//		
//		// get the given post
//		
//		// pass the post into the template
//		
//		
//		return "post";  // return the name of the template
//	}
//	
//	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
//	public String userPosts(@PathVariable String username, Model model) {
//		
//		// TODO - implement userPosts
//	    // RC Code //
//		
////		// STEPS:
//		
//		// get all of the user's posts
//		
//		
//		// pass the posts into the template
//		// (ex) model.addAttribute("name", someObj);
//		// (ex) model.addAttribute("name", listOfStuff);
//		
//		
//		return "blog";
//	}
//	
//}


