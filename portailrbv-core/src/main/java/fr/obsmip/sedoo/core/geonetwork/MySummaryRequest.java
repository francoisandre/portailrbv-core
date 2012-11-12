package fr.obsmip.sedoo.core.geonetwork;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import fr.obsmip.sedoo.core.dao.csw.xml.SummaryUnmarshaller;
import fr.obsmip.sedoo.core.domain.Summary;

public class MySummaryRequest extends AuthenticatedRequest
{

	private static final int MAX_RETURNED_ROW_NUMBER = 300;

	public MySummaryRequest(String login, String password) 
	{
		super(login, password);
	}

	public List<Summary> getSummaries()
	{
	
		boolean connectionResult = login();
		if (connectionResult == false)
		{
			return new ArrayList<Summary>();
		}
		else
		{
			
			Namespace csw = Namespace.getNamespace("csw", "http://www.opengis.net/cat/csw/2.0.2");
			Namespace ogc = Namespace.getNamespace("csw", "http://www.opengis.net/ogc");
			Namespace ogcWithoutPrefix = Namespace.getNamespace("http://www.opengis.net/ogc");
			
			Element getRecords = new Element("GetRecords",csw).setAttribute("service", "CSW").setAttribute("version", "2.0.2").setAttribute("resultType", "results").setAttribute("maxRecords",""+MAX_RETURNED_ROW_NUMBER);
			Element query = new Element("Query", csw).setAttribute("typeNames","csw:Record");
			
			Element elementSetName = new Element("ElementSetName", csw).addContent("summary");
			
			Element constraint = new Element("Constraint", csw).setAttribute("version", "1.1.0");
			
			Element filter = new Element("Filter", ogcWithoutPrefix);
			Element sortBy = new Element("SortBy", ogc);
			Element sortProperty = new Element("SortProperty", ogc);
			Element propertyName = new Element("PropertyName", ogc).addContent("date");
			Element sortOrder = new Element("SortOrder", ogc).addContent("ASC");
			
			
			getRecords.addContent(query);
			query.addContent(elementSetName);
			query.addContent(constraint);
			constraint.addContent(filter);
			query.addContent(sortBy);
			sortBy.addContent(sortProperty);
			sortProperty.addContent(propertyName);
			sortProperty.addContent(sortOrder);
			
			String cswURL = config.getCSWURL();
			PostMethod post = new PostMethod(cswURL);
			

			try {
				String postData = getString(new Document(getRecords));
				System.out.println(postData);
				post.setRequestEntity(new StringRequestEntity(postData,"application/xml", "UTF8"));

				int result = httpclient.executeMethod(post);
				if (result == HttpStatus.SC_OK)
				{
					InputStream responseBodyAsStream = post.getResponseBodyAsStream();
					StringWriter writer = new StringWriter();
					IOUtils.copy(responseBodyAsStream, writer, "UTF8");
					String rawXml = writer.toString();
					return SummaryUnmarshaller.fromXML(rawXml);
				}
			}
			catch (Exception e)
			{	
				return new ArrayList<Summary>();
			}
			finally {
				post.releaseConnection();
			}
		}
		return new ArrayList<Summary>();
	}
	
}
