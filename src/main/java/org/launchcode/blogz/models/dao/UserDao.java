package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {    // <what to look up, data type of primary key>

	// look up user by uid
    User findByUid(int uid);
    
    // find all users and return in list
    List<User> findAll();
    
    // TODO - add method signatures as needed
    // RC Code //
    
    // look up user by username
//     User findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
     
    // look up user by username
     User findByUsername(String username);
    
}
