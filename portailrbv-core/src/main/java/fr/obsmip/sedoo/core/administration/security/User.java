package fr.obsmip.sedoo.core.administration.security;

import java.util.List;
import java.util.Map;

public interface User {

	String getLogin();
	
	List<Role> getRoles();
	boolean hasPermission(String permissionId, Map context);
}
