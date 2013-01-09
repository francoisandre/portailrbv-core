package fr.obsmip.sedoo.core.dao.csw;

import javax.naming.AuthenticationException;

import org.geotoolkit.csw.GetRecordByIdRequest;
import org.geotoolkit.csw.xml.ElementSetType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.core.misc.HTTPTools;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.MetadataTools;

public abstract class CSWMetadataDAO implements MetadataDAO{

	private boolean authenticathed = false;
	
	private String serverURL = "http://localhost:8080/geosource/srv/fre/csw";
	protected Client client;
	//static final Logger logger =LoggerFactory.getLogger(CSWMetadataDAO.class);
	
	
	public CSWMetadataDAO()
	{
		client = HTTPTools.createSessionAwareClient();
	}
	
	public Metadata getMetadataById(String id) throws Exception
	{
		if ((id == null) || (id.trim().length()==0))
		{
			return new Metadata();
		}
		
		if (authenticathed == false)
		{
			client = HTTPTools.createSessionAwareClient();
			login();
			//authenticathed = true;
		}
		
		
		GetRecordByIdRequest request = new RBVGetRecordById(serverURL, null);
	    request.setElementSetName(ElementSetType.BRIEF);
	    request.setIds(new String[] {id });
	    request.setOutputSchema("http://www.isotc211.org/2005/gmd");
	    System.out.println(request.getURL().toString());
	    ClientResponse response = HTTPTools.launchPostRequest(client, request.getURL().toString());
	    String rawXml = response.getEntity(String.class);
	    System.out.println(rawXml);
	    String metadataXml = removeCSWTags(rawXml);
	  //  logger.debug(metadataXml);
	    Metadata aux = MetadataTools.fromXML(metadataXml);
		return aux;
	}


	private String removeCSWTags(String rawXml) {
		return rawXml.replaceAll("<(/)?csw([^>]*)>", "");
	}

	
	
	protected abstract void login() throws AuthenticationException;
	
	protected abstract void logout() throws AuthenticationException;

}
