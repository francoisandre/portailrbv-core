package fr.obsmip.sedoo.core.dao.csw;

import java.util.List;

import javax.naming.AuthenticationException;

import fr.obsmip.sedoo.core.domain.Summary;
import fr.obsmip.sedoo.core.misc.HTTPTools;

public class GeonetworkCSWMetadataDAO extends CSWMetadataDAO 
{
	private String login = "admin";
	private String password = "admin";

	private String geonetworkURL ="http://localhost:8080/geosource/srv/";
	private String logoutServiceURL="xml.user.logout";
	private String loginServiceURL="xml.user.login";
	private final String USERNAME_PARAM="username";
	private final String PASSWORD_PARAM="password";

	//static final Logger logger =LoggerFactory.getLogger(GeonetworkCSWMetadataDAO.class);

	@Override
	protected void login() throws AuthenticationException {
		//logger.debug("Tentative de connexion au serveur Geonetwork");
		logout();
		//http://localhost:8080/geosource/srv/xml.user.login?username=admin&password=admin
		String loginURL = geonetworkURL+loginServiceURL+"?"+USERNAME_PARAM+"="+login+"&"+PASSWORD_PARAM+"="+password;
		System.out.println(loginURL);
		if (HTTPTools.launchPostRequest(client, loginURL).getStatus() != 200)
		{
			throw new AuthenticationException("Impossible de se connecter au serveur Geonetwork");
		}
		//logger.debug("Connexion au serveur Geonetwork réussie");
	}

	protected void logout() throws AuthenticationException
	{
		//logger.debug("Tentative de déconnexion du serveur Geonetwork");
		String logoutURL = geonetworkURL+logoutServiceURL;
		System.out.println(logoutURL);
		if (HTTPTools.launchPostRequest(client,logoutURL).getStatus() != 200)
		{
			throw new AuthenticationException("Impossible de se déconnecter du serveur Geonetwork");
		}
		//logger.debug("Déconnexion du serveur Geonetwork réussie");
	}

	public List<Summary> getSummaries() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPDFURL(String metadataId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
