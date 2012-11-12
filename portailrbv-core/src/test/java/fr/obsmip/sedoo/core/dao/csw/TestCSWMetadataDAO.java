package fr.obsmip.sedoo.core.dao.csw;


import junit.framework.Assert;

import org.junit.Test;

import fr.obsmip.sedoo.core.dao.MetadataDAO;
import fr.obsmip.sedoo.core.domain.Metadata;

public class TestCSWMetadataDAO 
{
	
	
	
	@Test(timeout = 4000) 
	public void testGetMetadataById() throws Exception 
	{
		MetadataDAO cswDao = new GeonetworkCSWMetadataDAO();
		Metadata metadata = null;
		String id ="e4396bed-1c68-472f-9ccf-9acbae3f9e0a";
		try
		{
			metadata = cswDao.getMetadataById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		Assert.assertNotNull(metadata);
		//Assert.assertTrue("les deux identifiants doivent correspondre",metadata.getIdentifier());
		
		
	}
	
	@Test(timeout = 80000) 
	public void testGetMetadataById2() throws Exception 
	{
		MetadataDAO cswDao = new GeonetworkCSWMetadataDAO();
		Metadata metadata = null;
		String id ="e4396bed-1c68-472f-9ccf-9acbae3f9e0a";
		try
		{
			metadata = cswDao.getMetadataById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		Assert.assertNotNull(metadata);
		//Assert.assertTrue("les deux identifiants doivent correspondre",metadata.getIdentifier());
		
		
	}

		
}
