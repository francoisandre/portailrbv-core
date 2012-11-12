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

import fr.obsmip.sedoo.core.dao.csw.xml.UserUnmarshaller;
import fr.obsmip.sedoo.core.domain.User;

public class UserRequest extends AuthenticatedRequest
{

	public UserRequest(String login, String password) 
	{
		super(login, password);
	}
	
	public List<User> execute()
	{
		boolean connectionResult = login();
		if (connectionResult == false)
		{
			return new ArrayList<User>();
		}
		else
		{
			Element request = new Element("request");
			String userListURL = config.getUserListURL();
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
					
					System.out.println(rawXml);
					return UserUnmarshaller.fromXML(rawXml);
				}
			}
			catch (Exception e)
			{	
				return new ArrayList<User>();
			}
			finally {
				post.releaseConnection();
			}
		}
		return new ArrayList<User>();
	}

}
