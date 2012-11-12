package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.User;


public interface UserDAO {
	
	List<User> findAll();
	User findUserByLogin(String login);

}
