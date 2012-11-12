package fr.obsmip.sedoo.core.dao.csw.xml;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.commons.digester3.Digester;

import fr.obsmip.sedoo.core.geonetwork.Group;

public class GroupUnmarshaller 
{
	public static List<Group> fromXML(String xml)
	{
		
		Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("response",GroupList.class);
        digester.addObjectCreate("response/record",Group.class);
        digester.addBeanPropertySetter("response/record/name","name");
        digester.addBeanPropertySetter("response/record/id","id");
        digester.addBeanPropertySetter("response/record/description","observation");
        digester.addSetNext("response/record","add");
        try {
        	GroupList result = digester.parse(new ByteArrayInputStream(xml.getBytes()));
			return result;
		} catch (Exception e) 
		{
			return new GroupList();
		}
        
	}
	
}
