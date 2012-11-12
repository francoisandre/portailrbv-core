package fr.obsmip.sedoo.core.geonetwork;

import org.apache.commons.httpclient.HttpClient;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.config.GeoNetworkConfig;

public abstract class AbstractGeoNetworkClient 
{
	protected HttpClient httpclient= new HttpClient();
	GeoNetworkConfig config;
	
	public AbstractGeoNetworkClient() 
	{
		config = RBVApplication.getInstance().getGeoNetworkConfig();
	}
}
