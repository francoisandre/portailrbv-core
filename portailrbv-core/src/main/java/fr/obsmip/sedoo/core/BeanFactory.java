package fr.obsmip.sedoo.core;

public interface BeanFactory 
{
	
	public static final String USER_DAO_BEAN_NAME = "userDAO";
	public static final String VERSION_DAO_BEAN_NAME = "versionDAO";
	public static final String PARAMETER_PROVIDER_LIST_BEAN_NAME = "parameterProviderList";
	public static final String CONTACT_DAO_BEAN_NAME = "contactDAO";
	public static final String METADATA_DAO_BEAN_NAME = "metadataDAO";
	public static final String OBSERVATORY_DAO_BEAN_NAME = "observatoryDAO";
	public static final String DRAINAGE_BASIN_DAO_BEAN_NAME = "drainageBasinDAO";
	public static final String SITE_DAO_BEAN_NAME = "siteDAO";
	public static final String GEONETWORK_CONFIG_BEAN_NAME = "geoNetworkConfig";
	public static final String PERSON_DAO_BEAN_NAME = "personDAO";
	public static final String OBSERVATORY_CONTACT_DAO_BEAN_NAME = "observatoryContactDAO";

	Object getBeanByName(String beanName);
}
