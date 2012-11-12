package fr.obsmip.sedoo.core;

import fr.carnavello.administration.commons.Application;
import fr.obsmip.sedoo.core.administration.security.Authenticator;
import fr.obsmip.sedoo.core.config.GeoNetworkConfig;
import fr.obsmip.sedoo.core.dao.DrainageBasinDAO;
import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryDAO;
import fr.obsmip.sedoo.core.dao.UserDAO;

public class RBVApplication extends Application 
{
	protected static RBVApplication instance;
	private final static String AUTHENTICATOR_BEAN_NAME="authenticator";
	
	protected RBVApplication()
	{
		super();
	}
	
	
	public static RBVApplication getInstance() {
		if (instance == null)
		{
			instance = new RBVApplication();
			
		}
		return instance;	}



	@Override
	public void start() {
		
	}



	@Override
	public void stop() {
		
	}

	public UserDAO getUserDAO() 
	{
		return (UserDAO) getBeanFactory().getBeanByName(BeanFactory.USER_DAO_BEAN_NAME);
	}

	public Authenticator getAuthenticator() 
	{
		return (Authenticator) getBeanFactory().getBeanByName(AUTHENTICATOR_BEAN_NAME);
	}
	
	public MetadataDAO getMetadataDAO() 
	{
		return (MetadataDAO) getBeanFactory().getBeanByName(BeanFactory.METADATA_DAO_BEAN_NAME);
	}


	public GeoNetworkConfig getGeoNetworkConfig() 
	{
		return (GeoNetworkConfig) getBeanFactory().getBeanByName(BeanFactory.GEONETWORK_CONFIG_BEAN_NAME);
		
	}


	public ObservatoryDAO getObservatoryDAO() {
		
		return (ObservatoryDAO) getBeanFactory().getBeanByName(BeanFactory.OBSERVATORY_DAO_BEAN_NAME); 
		
	}
	
public DrainageBasinDAO getDrainageBasinDAO() {
		
		return (DrainageBasinDAO) getBeanFactory().getBeanByName(BeanFactory.DRAINAGE_BASIN_DAO_BEAN_NAME); 
		
	}
	
	
	
}
