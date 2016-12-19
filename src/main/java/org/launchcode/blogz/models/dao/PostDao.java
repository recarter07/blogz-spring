package org.launchcode.blogz.models.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
    List<Post> findByAuthor(int authorId);
    
    // TODO - add method signatures as needed
    // RC Code //
    
    // find all posts and return in list
    List<Post> findAll();
    
    // find all posts by created
    List<Post> findByCreated(Date created);
    
    // find all posts by modified
    List<Post> findByModified(Date modified);
    
    // find post by title
    List<Post> findByTitle(String title);
 
    // find post by uid
    Post findByUid(int uid);
}
