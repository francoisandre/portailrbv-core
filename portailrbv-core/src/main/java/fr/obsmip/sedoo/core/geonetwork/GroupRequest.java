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

import fr.obsmip.sedoo.core.dao.csw.xml.GroupUnmarshaller;

public class GroupRequest extends AuthenticatedRequest
{

	public GroupRequest(String login, String password) 
	{
		super(login, password);
	}
	
	public List<Group> execute()
	{
		boolean connectionResult = login();
		if (connectionResult == false)
		{
			return new ArrayList<Group>();
		}
		else
		{
			Element request = new Element("request");
			String userListURL = config.getGroupListURL();
			PostMethod post = new PostMethod(userListURL);

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
					return GroupUnmarshaller.fromXML(rawXml);
				}
			}
			catch (Exception e)
			{	
				return new ArrayList<Group>();
			}
			finally {
				post.releaseConnection();
			}
		}
		return new ArrayList<Group>();
	}

}
