package fr.obsmip.sedoo.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.domain.User;
import fr.obsmip.sedoo.core.geonetwork.Group;
import fr.obsmip.sedoo.core.geonetwork.GroupRequest;
import fr.obsmip.sedoo.core.geonetwork.UserRequest;

public class GeoNetworkUserDAO implements UserDAO{

	private Map<String,User> users;
	private Map<String,Observatory> observatories;
	
	private String login;
	private String password;
	
	public List<User> findAll() {
	
		if (users == null)
		{
			initUsers();
		}
		return new ArrayList<User>(users.values());
	}

	public User findUserByLogin(String login) {
		
		if (users == null)
		{
			initUsers();
		}
		return users.get(login);
	}

	private void initUsers() 
	{
		users = new HashMap<String, User>();
		UserRequest request = new UserRequest(login, password);
		List<User> aux = request.execute();
		Iterator<User> iterator = aux.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			users.put(user.getLogin(), user);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Observatory> findAllObservatories() 
	{
		if (observatories == null)
		{
			initObservatories();
		}
		return new ArrayList<Observatory>(observatories.values());
	}

	private void initObservatories() 
	{
		observatories = new HashMap<String, Observatory>();
		GroupRequest request = new GroupRequest(login, password);
		List<Group> aux = request.execute();
		Iterator<Group> iterator = aux.iterator();
		while (iterator.hasNext()) {
			Group group = iterator.next();
			Observatory observatory = new Observatory();
			//observatory.setCode(group.getName());
			//observatory.setId(group.getId());
			observatory.setDescription(group.getObservation());
			observatories.put(""+observatory.getId(), observatory);
		}
		
	}

}
