package fr.obsmip.sedoo.core.dao.csw.xml;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.commons.digester3.Digester;

import fr.obsmip.sedoo.core.domain.User;

public class UserUnmarshaller 
{
	public static List<User> fromXML(String xml)
	{
		
		Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("response",UserList.class);
        digester.addObjectCreate("response/record",User.class);
        digester.addBeanPropertySetter("response/record/username","login");
        digester.addBeanPropertySetter("response/record/surname","firstName");
        digester.addBeanPropertySetter("response/record/name","lastName");
        digester.addBeanPropertySetter("response/record/email","email");
        digester.addCallMethod("response/record/profile","setAdministrator",1);
        digester.addCallParam("response/record/profile",0);
        digester.addSetNext("response/record","add");
        try {
        	UserList result = digester.parse(new ByteArrayInputStream(xml.getBytes()));
			return result;
		} catch (Exception e) 
		{
			return new UserList();// TODO Auto-generated catch block
		}
        
//        ogin;
//    	private String firstName;
//    	private String lastName;
//    	private String email
        
	}
	
}
