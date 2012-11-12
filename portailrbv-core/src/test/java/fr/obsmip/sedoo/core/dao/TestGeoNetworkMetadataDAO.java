package fr.obsmip.sedoo.core.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.domain.Metadata;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/config-test.xml"})
public class TestGeoNetworkMetadataDAO 
{
	
	
	
	@Test
	public void testGetSummaries() throws Exception 
	{
		GeoNetworkMetadataDAO dao = new GeoNetworkMetadataDAO();
		Metadata metadataById = dao.getMetadataById("ba11884f-3028-42b9-bdd4-4b653430c4b1");
		Assert.assertNotNull(metadataById);
	}

		
}
