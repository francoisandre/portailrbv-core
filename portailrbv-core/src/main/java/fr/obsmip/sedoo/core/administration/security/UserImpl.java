package fr.obsmip.sedoo.core.administration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserImpl implements User {

	protected String login;
	
	protected List<Role> roles;
	
	protected RoleDAO roleDAO;
	
	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public List<Role> getRoles() 
	{
		if (roles == null)
		{
			List<Role> foundRoles = roleDAO.findRolesByUser(this);
			if (foundRoles == null)
			{
				foundRoles = new ArrayList<Role>();
			}
			this.roles= foundRoles; 
		}
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles=roles;

	}

	@Override
	public boolean hasPermission(String permissionId, Map context) 
	{
		Iterator<Role> iterator = getRoles().iterator();
		while (iterator.hasNext()) {
			Role role = (Role) iterator.next();
			if (role.hasPermission(permissionId, context))
			{
				return true;
			}
		}
		return false;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
