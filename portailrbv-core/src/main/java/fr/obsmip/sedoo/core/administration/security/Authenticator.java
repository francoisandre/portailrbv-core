package fr.obsmip.sedoo.core.administration.security;

public interface Authenticator {
	
	boolean login(String login, String password);

}
