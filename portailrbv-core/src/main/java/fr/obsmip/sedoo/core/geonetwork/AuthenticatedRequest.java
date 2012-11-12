package fr.obsmip.sedoo.core.geonetwork;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class AuthenticatedRequest extends AbstractGeoNetworkClient
{
	
	protected String login;
	protected String password;
	
	
	public AuthenticatedRequest(String login, String password) 
	{
		this.login = login;
		this.password = password;
	}

	protected boolean login() {

		
		    Element request = new Element("request")
		    .addContent(new Element("username").setText(login))
		    .addContent(new Element("password").setText(password));
		    
		    PostMethod post = new PostMethod(config.getLoginURL());

		    try {
		      String postData = getString(new Document(request));

		      post.setRequestEntity(new StringRequestEntity(postData,
		      "application/xml", "UTF8"));

		      int result = httpclient.executeMethod(post);
		      return (result == HttpStatus.SC_OK);
		    }
		    catch (Exception e)
		    {	
		    	e.printStackTrace();
		    	return false;
		    }
		    finally {
		        post.releaseConnection();
		      }
	}
		    
    protected String getString(Document data)
    {
    		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    		return outputter.outputString(data);
    }

	
	
	

}
