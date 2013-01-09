package fr.obsmip.sedoo.core.geonetwork;


import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.domain.User;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.Summary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/config-test.xml","classpath:/META-INF/spring/contacts.xml"})
public class TestGeonetworkRequests 
{
	@Test
	public void testBasicLoginLogout() throws Exception 
	{
		AuthenticatedRequest authenticator = new LoginRequest("admin","admin");
		boolean result = authenticator.login();
		Assert.assertTrue("La connexion doit se faire", result);
		authenticator = new LoginRequest("admdin","admin");
		result = authenticator.login();
		Assert.assertFalse("La connexion doit se faire", result);
		authenticator = new LoginRequest("admin","admin");
		result = authenticator.login();
		Assert.assertTrue("La connexion doit se faire", result);
	}

	//@Test(timeout=5000)
	@Test
	public void testMetadataById() throws Exception 
	{
		String id = "2293e079-d025-42df-a933-0ac6fdbca561";
		MetadataByIdRequest metadataByIdRequest = new MetadataByIdRequest(id,"admin", "admin");
		Metadata metadata = metadataByIdRequest.getMetadata();
		String title = StringUtils.defaultString(metadata.getResourceTitle());
		Assert.assertTrue("Le titre doit être présent", title.trim().length()>0);
		Assert.assertTrue("Le texte IMPETUS doit être présent", title.indexOf("IMPETUS")>=0);
		metadataByIdRequest = new MetadataByIdRequest(id,"admin", "admin");
		metadata = metadataByIdRequest.getMetadata();
		title = StringUtils.defaultString(metadata.getResourceTitle());
		Assert.assertTrue("Le texte IMPETUS doit être présent", title.indexOf("IMPETUS")>=0);
		Assert.assertTrue("Le titre doit être présent", title.trim().length()>0);
		metadataByIdRequest = new MetadataByIdRequest(id,"admin", "admin");
		metadata = metadataByIdRequest.getMetadata();
		title = StringUtils.defaultString(metadata.getResourceTitle());
		Assert.assertTrue("Le titre doit être présent", title.trim().length()>0);
		Assert.assertTrue("Le texte IMPETUS doit être présent", title.indexOf("IMPETUS")>=0);
	}

	@Test
	public void testMySummaries() throws Exception 
	{
		MySummaryRequest request = new MySummaryRequest("admin", "admin");
		List<Summary> summaries = request.getSummaries();
		Assert.assertTrue("La liste ne doit pas être vide", summaries.size()>0);
	}
	
	@Test
	public void testUserList() throws Exception 
	{
		UserRequest request = new UserRequest("admin", "admin");
		List<User> users = request.execute();
		Assert.assertTrue("La liste ne doit pas être vide", users.size()>0);
	}
	
	@Test
	public void testGroupList() throws Exception 
	{
		GroupRequest request = new GroupRequest("admin", "admin");
		List<Group> groups = request.execute();
		Assert.assertTrue("La liste ne doit pas être vide", groups.size()>0);
	}


}
