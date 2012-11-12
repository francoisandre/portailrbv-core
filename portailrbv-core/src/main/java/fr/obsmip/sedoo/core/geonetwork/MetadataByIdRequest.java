package fr.obsmip.sedoo.core.geonetwork;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import fr.obsmip.sedoo.core.dao.csw.xml.MetadataUnmarshaller;
import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.MetadataTools;

public class MetadataByIdRequest extends AuthenticatedRequest
{

	private String id;

	public MetadataByIdRequest(String id, String login, String password) 
	{
		super(login, password);
		this.id=id;
	}

	public Metadata getMetadata()
	{
		boolean connectionResult = login();
		if (connectionResult == false)
		{
			return MetadataTools.getEmptyMetadata();
		}
		else
		{
			Namespace csw = Namespace.getNamespace("csw", "http://www.opengis.net/cat/csw/2.0.2");
			Element request = new Element("GetRecordById",csw).setAttribute("outputSchema","csw:IsoRecord").setAttribute("service", "CSW").setAttribute("version", "2.0.2").addContent(new Element("Id",csw).setText(id));

			String cswURL = config.getCSWURL();
			PostMethod post = new PostMethod(cswURL);

			try {
				String postData = getString(new Document(request));
				post.setRequestEntity(new StringRequestEntity(postData,"application/xml", "UTF8"));

				int result = httpclient.executeMethod(post);
				if (result == HttpStatus.SC_OK)
				{
					InputStream responseBodyAsStream = post.getResponseBodyAsStream();
					StringWriter writer = new StringWriter();
					IOUtils.copy(responseBodyAsStream, writer, "UTF8");
					String rawXml = writer.toString();
					String metadataXml = removeCSWTags(rawXml);
					System.out.println(metadataXml);
//					return MetadataTools.fromXML(metadataXml);
					return MetadataUnmarshaller.fromXML(metadataXml);
				}
			}
			catch (Exception e)
			{	
				return MetadataTools.getEmptyMetadata();
			}
			finally {
				post.releaseConnection();
			}
		}
		return MetadataTools.getEmptyMetadata();
	}

	private String removeCSWTags(String rawXml) {
		return rawXml.replaceAll("<(/)?csw([^>]*)>", "");
	}

}
