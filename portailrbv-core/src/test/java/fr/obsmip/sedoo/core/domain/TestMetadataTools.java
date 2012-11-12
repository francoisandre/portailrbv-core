package fr.obsmip.sedoo.core.domain;


import junit.framework.Assert;

import org.junit.Test;

public class TestMetadataTools 
{
	
	
	
	@Test
	public void testDateFormatter() throws Exception 
	{
		String stringDate="2012-09-25";
		Assert.assertEquals("La date doit être reformatée à l'identique",stringDate, MetadataTools.formatDate(MetadataTools.parseString(stringDate)));
	}

		
}
