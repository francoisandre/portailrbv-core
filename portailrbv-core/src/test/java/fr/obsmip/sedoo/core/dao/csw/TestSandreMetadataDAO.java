package fr.obsmip.sedoo.core.dao.csw;


import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import fr.obsmip.sedoo.core.domain.Summary;

public class TestSandreMetadataDAO 
{
	
	
	
	@Test
	public void testGetSummaries() throws Exception 
	{
		SandreCSWMetadataDAO dao = new SandreCSWMetadataDAO();
		List<Summary> summaries = dao.getSummaries();
		Assert.assertTrue("La liste retournée ne doit pas être vide ", summaries.size()>0);
		
		
	}

		
}
