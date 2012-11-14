package fr.obsmip.sedoo.core.administration.security;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/permissions.xml","classpath:/META-INF/spring/roles.xml","classpath:/META-INF/spring/applicationContext.xml","classpath:/META-INF/spring/applicationContext-test.xml"})
public class TestDefaultUser {
	
//	@Test
//	public void testDefaultRole() throws SecuritySystemException 
//	{
//		User defaultUser = Application.getInstance().getDefaultUser();
//		Assert.assertTrue("le user "+defaultUser.getLogin()+" doit avoir la permission searchMetadata", defaultUser.hasPermission(PermissionConstant.SEARCH_METADATA, null));
//		Assert.assertTrue("le user "+defaultUser.getLogin()+" doit avoir la permission login", defaultUser.hasPermission(PermissionConstant.LOGIN, null));
//		Assert.assertTrue("le user "+defaultUser.getLogin()+" doit avoir la permission readMetaDada", defaultUser.hasPermission(PermissionConstant.READ_METADATA, null));
//		Assert.assertTrue("le user "+defaultUser.getLogin()+" doit avoir la permission askAccount", defaultUser.hasPermission(PermissionConstant.ASK_ACCOUNT, null));
//		
//	}
}
