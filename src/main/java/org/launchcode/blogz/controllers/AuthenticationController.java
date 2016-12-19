package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String signup(HttpServletRequest request, Model model) {
		
		// TODO - implement signup
	    // RC Code //
		
//		// STEPS:
		// get parameters from request
		
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String verify = request.getParameter("verify"); 
		
		// validate params (username, password, verify)
		
//		ref User class methods:
//		username and password must both be valid
//		both passwords must match  //// ?????
		
		boolean validU = User.isValidUsername(username);
		boolean validP = User.isValidPassword(password);
//		boolean passMatch = User.isMatchingPassword("password");
		boolean passMatch = password.equals(verify);
		
		// if they validate, create a new user, and put them in the session
		//   (use setUserInSession called method from AbstractController class)
//		if (validU == true) {
//			if (validP == true) {
//				if (passMatch == true) {
//					// create a new user
//					User newUser = new User(username, password);
//					userDao.save(newUser);
//					HttpSession thisSession = request.getSession();
//					setUserInSession(thisSession, newUser);
//				}
//			}
//		}
//		String error = "Error";
		String uError = "Usernames must have 4-11 characters with only letters, numbers or underscores";   // letters, numbers, or underscore
		String pError = "Passwords must have 6-12 characters";
		String vError = "Passwords must match";
		
		if (validU == true) {
			if (validP == true) {
				if (passMatch == true) {
					// create a new user
					User newUser = new User(username, password);
					userDao.save(newUser);
					HttpSession thisSession = request.getSession();
					setUserInSession(thisSession, newUser);
				} 
				else {
					model.addAttribute("verify_error", vError);
					return "signup";
				} 
			} 
			else {
				model.addAttribute("password_error", pError);
				return "signup";
			}
		} 
		else {
			model.addAttribute("username_error", uError);
			return "signup";
		}
		
//User newUser = new User();
//userDao.save(newUser);
//HttpSession thisSession = request.getSession();
//		(Hibernate)
//		Session thisSession = request.getSession();
		
		return "redirect:blog/newpost";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
	    // RC Code //

//		// STEPS:
		// get parameters from request
		
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
//		String verify = request.getParameter("verify");	
		
		// get user by their username - make user obj
		
		User user = userDao.findByUsername(username);
		
		if (user == null) {
			model.addAttribute("subError", "Invalid Username or Password");
			return "login";
		}
		
		// check whether username matches from last time logged in
		// Slack Dan about this... ///////////// - really??
		
		// check password is correct - matches from last time logged in
//		use isMatchingPassword method of User class for this
//		call method on user obj, NOT on User class:
		
		boolean passwordGood = user.isMatchingPassword(password);
		
		if (passwordGood == true) {
			HttpSession thisSession = request.getSession();
			setUserInSession(thisSession, user);
		}
		else {
			model.addAttribute("subError", "Invalid Username or Password");
			return "login";
		}
		
		
		
		// if password is correct, log them in (ie setting the user in the session)
		//    as above:  (Hibernate)
//		Session thisSession = request.getSession();
		
		
		return "redirect:blog/newpost";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	@ResponseBody  // keep this to return a String, get rid of this to use template
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}


//source: on Hibernate and persistence:
// https://www.youtube.com/watch?v=kEwiIYUFolA&spfreload=1#t=586.954709