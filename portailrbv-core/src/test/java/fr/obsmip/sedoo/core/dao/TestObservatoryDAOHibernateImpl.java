package fr.obsmip.sedoo.core.dao;


import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.domain.Observatory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/config-test.xml"})
public class TestObservatoryDAOHibernateImpl 
{
	
	
	
	@Test
	public void testSave() throws Exception 
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		deleteAllObservatories();
		Observatory observatory = new Observatory();
		observatory.setDescription("description");
		observatory.setShortLabel("short label");
		observatory.setLongLabel("long label");
		observatoryDAO.save(observatory);
		List<Observatory> allObservatories = observatoryDAO.findAll();
		Assert.assertEquals("Il doit y avoir un élément", 1, allObservatories.size());
	}
	
	private void deleteAllObservatories() throws Exception
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		List<Observatory> allObservatories = observatoryDAO.findAll();
		Iterator<Observatory> iterator = allObservatories.iterator();
		while (iterator.hasNext()) {
			Observatory observatory = iterator.next();
			observatoryDAO.delete(observatory.getId());
		}
		allObservatories = observatoryDAO.findAll();
		if (allObservatories.size()!=0)
		{
			throw new Exception("Impossible de supprimer les observatoires présents en base");
		}
	}

		
}
