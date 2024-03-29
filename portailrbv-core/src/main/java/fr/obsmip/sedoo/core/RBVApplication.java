package fr.obsmip.sedoo.core;

import fr.obsmip.sedoo.core.administration.security.Authenticator;
import fr.obsmip.sedoo.core.config.GeoNetworkConfig;
import fr.obsmip.sedoo.core.dao.DrainageBasinDAO;
import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryContactDAO;
import fr.obsmip.sedoo.core.dao.ObservatoryDAO;
import fr.obsmip.sedoo.core.dao.PersonDAO;
import fr.obsmip.sedoo.core.dao.SiteDAO;
import fr.obsmip.sedoo.core.dao.UserDAO;
import fr.obsmip.sedoo.core.spring.SpringBeanFactory;

public class RBVApplication  
{
	private BeanFactory beanFactory;
	
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



	public void start() {
		
	}



	public void stop() {
		
	}
	
	
	
	
	public BeanFactory getBeanFactory() 
	{
		if (beanFactory == null)
		{
			beanFactory = new SpringBeanFactory();
		}
		return beanFactory;
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
	
public SiteDAO getSiteDAO() {
		
		return (SiteDAO) getBeanFactory().getBeanByName(BeanFactory.SITE_DAO_BEAN_NAME); 
		
	}

public PersonDAO getPersonDAO() {
	
	return (PersonDAO) getBeanFactory().getBeanByName(BeanFactory.PERSON_DAO_BEAN_NAME); 
	
}

public ObservatoryContactDAO getObservatoryContactDAO() {
	
	return (ObservatoryContactDAO) getBeanFactory().getBeanByName(BeanFactory.OBSERVATORY_CONTACT_DAO_BEAN_NAME); 
	
}
	
	
	
}
