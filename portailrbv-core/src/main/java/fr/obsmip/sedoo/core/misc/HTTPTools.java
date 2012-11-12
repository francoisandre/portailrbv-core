package fr.obsmip.sedoo.core.misc;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;

public class HTTPTools 
{
	private HTTPTools()
	{
		
	}
	
	public static ClientResponse launchPostRequest(String url)
	{
		Client client = createSessionAwareClient();
		WebResource service = client.resource(url);
		return service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
	}
	
	public static ClientResponse launchPostRequest(Client client, String url)
	{
		WebResource service = client.resource(url);
		return service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
	}
	
	
	public static Client createSessionAwareClient()
	{
		DefaultApacheHttpClientConfig config = new   DefaultApacheHttpClientConfig(); 
		config.getProperties().put(DefaultApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, Boolean.TRUE);
		return ApacheHttpClient.create(config);
	}
}
