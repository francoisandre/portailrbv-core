package fr.obsmip.sedoo.core.geonetwork;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.domain.User;

public class LoginRequest extends AuthenticatedRequest
{

	public LoginRequest(String login, String password) 
	{
		super(login, password);
	}
	
	public User execute() throws Exception
	{
		boolean connectionResult = login();
		if (connectionResult == false)
		{
			throw new Exception("Couple Utilisateur/Mot de passe non reconnu");
		}
		else
		{
			return RBVApplication.getInstance().getUserDAO().findUserByLogin(login);
		}
	}

}
