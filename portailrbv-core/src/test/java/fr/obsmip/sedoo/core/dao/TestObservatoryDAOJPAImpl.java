package fr.obsmip.sedoo.core.dao;


import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.domain.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/jpaTestConfig.xml"})
public class TestObservatoryDAOJPAImpl extends AbstractDatabaseTest
{
	@Test
	public void testDefaultPopulating() throws Exception 
	{
		deleteAllObservatories();
		populateDefaultBase();
	}


	private void populateDefaultBase() throws Exception
	{
		List<Observatory> observatories = SampleDatabaseProvider.getSampleModel();
		
		
		Iterator<Observatory> iterator = observatories.iterator();
		while (iterator.hasNext()) 
		{
			Observatory observatory = iterator.next();
			observatoryDAO.save(observatory);
		}

		iterator = observatories.iterator();
		while (iterator.hasNext()) 
		{
			Observatory observatory = iterator.next();
			Observatory observatoryByShortLabel = observatoryDAO.getObservatoryByShortLabel(observatory.getShortLabel(),true);
			assertEqual(observatory, observatoryByShortLabel);
		}
	}

	private void assertEqual(Observatory observatory,
			Observatory observatoryByShortLabel) throws Exception 
			{
		assertEqual(observatory.getDescription(),observatoryByShortLabel.getDescription(),"Descriptions de l'observatoire différentes");
		assertEqual(observatory.getShortLabel(),observatoryByShortLabel.getShortLabel(),"Libellés courts de l'observatoire différents");
		assertEqual(observatory.getLongLabel(),observatoryByShortLabel.getLongLabel(),"Libellés longs de l'observatoire différents");
		assertEqualDBs(observatory.getDrainageBasins(),observatoryByShortLabel.getDrainageBasins(),"Liste des bassins versants de l'observatoire différents");
			}

	private void assertEqualDBs(List<DrainageBasin> col1, List<DrainageBasin> col2, String message) throws Exception
	{
		try
		{
			assertEqualSize(col1, col2);
		}
		catch (Exception e)
		{
			throw new Exception(message);
		}
		
		if (col1 != null && col2 != null)
		{
		
		for (int i=0; i< col1.size();i++)
		{
			assertEqual(col1.get(i), col2.get(i));
		}
		}
	}
	
	private void assertEqualSites(List<Site> col1, List<Site> col2, String message) throws Exception
	{
		try
		{
			assertEqualSize(col1, col2);
		}
		catch (Exception e)
		{
			throw new Exception(message);
		}
		if (col1 != null && col2 != null)
		{
		for (int i=0; i< col1.size();i++)
		{
			assertEqual(col1.get(i), col2.get(i));
		}
		}
	}

	private void assertEqual(Site site1, Site site2) throws Exception {
	
		assertEqual(site1.getLabel(),site2.getLabel(),"Noms de sites différents");
	}

	private void assertEqualSize(List col1,
			List col2) throws Exception {

		if ((col1 == null) && (col2 == null))
		{
			return;
		}
		if ((col2 == null) && (col1 != null))
		{
			if (col1.size() != 0)
			{
				throw new Exception();
			}
			return;
		}
		if ((col2 != null) && (col1 == null))
		{
			if (col2.size() != 0)
			{
				throw new Exception();
			}
			return;
		}
		if ((col1.size() != col2.size()))
		{
			throw new Exception();
		}

	}

	private void assertEqual(DrainageBasin drainageBasin1, DrainageBasin drainageBasin2) throws Exception 
	{
		assertEqual(drainageBasin1.getName(),drainageBasin2.getName(),"Noms des bassins différents");
		assertEqualSites(drainageBasin1.getSites(), drainageBasin2.getSites(), "Listes des sites différentes");
		//		assertEqual(observatory.getShortLabel(),observatoryByShortLabel.getShortLabel(),"Libellés courts de l'observatoire différents");		
	}

	private void assertEqual(String str1, String str2, String message) throws Exception {
		if ((str1 == null) && (str2 == null))
		{
			return;
		}
		if ((str2 == null) && (str1 != null))
		{
			throw new Exception(message);
		}
		if ((str2 != null) && (str1 == null))
		{
			throw new Exception(message);
		}
		else
		{
			if (str2.compareTo(str1) != 0)
			{
				throw new Exception(message);
			}
		}
	}



}
