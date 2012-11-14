package fr.obsmip.sedoo.core.administration.security;

import java.util.List;
import java.util.Map;

public interface Role {

	boolean hasPermission(String permissionId, Map context);
	List<Permission> getPermissions();
	String getId();

}
