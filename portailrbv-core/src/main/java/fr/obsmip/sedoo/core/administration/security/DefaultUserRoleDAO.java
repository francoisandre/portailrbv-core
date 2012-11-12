package fr.obsmip.sedoo.core.administration.security;

import java.util.List;

import fr.carnavello.administration.commons.security.api.Role;
import fr.carnavello.administration.commons.security.api.User;
import fr.carnavello.administration.commons.security.api.dao.RoleDAO;

/**
 * @author andre
 * This class correspond to the default unauthentified user with a visitorRole
 *
 */
public class DefaultUserRoleDAO implements RoleDAO 
{

	private List<Role> roles;
	
	public List<Role> findRolesByUser(User user) 
	{
		return roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
