package fr.obsmip.sedoo.core.administration.security;

import java.util.List;

public interface RoleDAO {
	
	List<Role> findRolesByUser(User user);

}
