package org.launchcode.blogz.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	private String username;
	private String pwHash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	// all posts by a given user
	private List<Post> posts;
	
	// no-arg constructor, for Hibernate (req, as are setters for all db columns)
	public User() {}
	
	public User(String username, String password) {
		
		super();
		
		if (!isValidUsername(username)) {
			throw new IllegalArgumentException("Invalid username");
		}
		
		this.username = username;
		this.pwHash = hashPassword(password);
		
	}
	
	@NotNull
    @Column(name = "pwhash")
	public String getPwHash() {
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}
	
	@NotNull
    @Column(name = "username", unique = true)
	public String getUsername() {
		return username;
	}
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username) {
		this.username = username;
	}
	
	// Checks that the given password is correct for the user
	// must have user obj to call with some value to match like:
	// User.isMatchingPassword(...)
	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, pwHash);
	}
	
	// Checks that the password meets minimum standards
	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");  // can't have whitespace, must be b/w 6 and 20 char long
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}
	
	// Checks that the username meets minimum standards
	public static boolean isValidUsername(String username) {
		Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
		Matcher matcher = validUsernamePattern.matcher(username);
		return matcher.matches();
	}
	
	protected void addPost(Post post) {
		posts.add(post);
	}
	
	// how to get all posts for single user
	@OneToMany     // one user, many posts
    @JoinColumn(name = "author_uid")    // primary key to join tables
    public List<Post> getPosts() {
        return posts;
    }
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
