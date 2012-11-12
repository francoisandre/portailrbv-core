package fr.obsmip.sedoo.core.dao.csw.xml;

import java.io.ByteArrayInputStream;

import org.apache.commons.digester3.Digester;

import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.MetadataTools;

public class MetadataUnmarshaller 
{
	 
	

	public static Metadata fromXML(String xml)
	{
		
		Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("gmd:MD_Metadata",Metadata.class);
        digester.addBeanPropertySetter("*/gmd:abstract/gco:CharacterString","resourceAbstract");
        digester.addBeanPropertySetter("*/gmd:title/gco:CharacterString","resourceTitle");
        
        try {
        	Metadata result = digester.parse(new ByteArrayInputStream(xml.getBytes()));
			return result;
		} catch (Exception e) 
		{
			return MetadataTools.getEmptyMetadata();
		}
	}
	
}
