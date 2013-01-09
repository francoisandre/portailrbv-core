package fr.obsmip.sedoo.core.dao.csw;

import java.util.List;

import javax.naming.AuthenticationException;

import com.sun.jersey.api.client.ClientResponse;

import fr.obsmip.sedoo.core.misc.HTTPTools;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.Summary;
import fr.sedoo.commons.metadata.utils.tools.SummaryUnmarshaller;

public class SandreCSWMetadataDAO extends CSWMetadataDAO {

	private static final int DEFAULT_MAX_SUMMARIES = 30;
	private int maxSummaries;
	
	public SandreCSWMetadataDAO() {
		maxSummaries = DEFAULT_MAX_SUMMARIES;
	}
	
	public Metadata getMetadataById(String id) throws Exception {
		GeonetworkCSWMetadataDAO dao = new GeonetworkCSWMetadataDAO();
		
		// TODO Enlever l'utilisation temporaire du fakeId
		String fakeId ="e4396bed-1c68-472f-9ccf-9acbae3f9e0a";
		return dao.getMetadataById(fakeId);
	}

	
	
	public List<Summary> getSummaries() 
	{
		String url = "http://services.sandre.eaufrance.fr/geonetwork_CSW/srv/fr/csw?request=GetRecords&service=CSW&version=2.0.2&namespace=xmlns(csw=http://www.opengis.net/cat/csw)&resultType=results&outputSchema=http://www.opengis.net/cat/csw/2.0.2&outputFormat=application/xml&maxRecords="+getMaxSummaries()+"&typeNames=csw:Record&elementSetName=summary&constraintLanguage=CQL_TEXT&constraint_language_version=1.1.0&constraint=AnyText+LIKE+%27%25eau%25%27";
		ClientResponse response = HTTPTools.launchPostRequest(url);
		String rawXml = response.getEntity(String.class);
		List<Summary> resultList = SummaryUnmarshaller.fromXML(rawXml);
		return resultList;
	}

	@Override
	protected void login() throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void logout() throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}



	public int getMaxSummaries() {
		return maxSummaries;
	}



	public void setMaxSummaries(int maxSummaries) {
		this.maxSummaries = maxSummaries;
	}

	public String getPDFURL(String metadataId) 
	{
		return "http://localhost:8080/geosource/srv/fre/pdf?uuid=e4396bed-1c68-472f-9ccf-9acbae3f9e0a";
	}

}
