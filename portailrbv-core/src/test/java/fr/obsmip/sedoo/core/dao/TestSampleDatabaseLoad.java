package fr.obsmip.sedoo.core.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.domain.ObservatoryContact;
import fr.obsmip.sedoo.core.domain.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/jpaTestConfig.xml"})
public class TestSampleDatabaseLoad 
{
	@Autowired
	private ObservatoryDAO observatoryDAO;



	@Test
	public void testDefaultPopulating() throws Exception 
	{
		deleteAllObservatories();
		populateDefaultBase();
	}

	private void deleteAllObservatories() throws Exception
	{
		List<Observatory> allObservatories = observatoryDAO.findAll();
		Iterator<Observatory> iterator = allObservatories.iterator();
		while (iterator.hasNext()) {
			Observatory observatory = iterator.next();
			observatoryDAO.delete(observatory.getId());
		}
		allObservatories = observatoryDAO.findAll();
		if (allObservatories.size()!=0)
		{
			throw new Exception("Impossible de supprimer les observatoires prÃ©sents en base");
		}
	}


	private void populateDefaultBase() throws Exception
	{
		List<Observatory> observatories = new ArrayList<Observatory>();
		Observatory obsera = new Observatory();
		obsera.setLongLabel("Observatoire de l’Erosion aux Antilles");
		obsera.setShortLabel("ObsErA");
		obsera.setDescription("L'Observatoire de l’Erosion aux Antilles (ObsErA) est un service d'observation de l’INSU-CNRS et de l’Institut de Physique du Globe de Paris consacré à l'étude de l’altération et de l’érosion aux Antilles. Membre du Réseau de Bassins Versants de Recherche et du Critical Zone Observatory Network, il implique des équipes de l'Institut de Physique du Globe de Paris, de l'Observatoire Volcanologique et Sismologique de Guadeloupe, de l'Université Antilles-Guyane, de l'Institut Universitaire Européen de la Mer (UBO) et du Laboratoire de Sciences de la Terre de l'Université Claude Bernard (Lyon I). L’objectif d’ObsErA est de permettre la constitution d’une base de données des flux d’eau (précipitations et débit des rivières), des flux de sédiments et de matière organique (en particulier de carbone) dans les rivières et sur les versants ainsi que de la composition chimique des rivières et des sols dans le contexte particulier de l’île de Basse-Terre en Guadeloupe, marquée par un climat tropical et un volcanisme actif. Cette base de données permettra à la communauté scientifique d'étudier et de quantifier les modes de d’érosion chimique et physique, ainsi que leur impact sur l'environnement (composition des sols, chimie des rivières, flux de carbones, etc...). A ce jour, ObsErA suit l'évolution de 3 bassins versants localisés dans l'enceinte du Parc National de Guadeloupe : Capesterre, Bras-David et Vieux-Habitants.");

		
		ObservatoryContact contact1 = new ObservatoryContact();
		contact1.setOrganisationName("IRD");
		contact1.setPersonName("Jacques Célère");
		contact1.setOrganisationName("IRD");
		contact1.setEmail("jacques@ird.fr");
		
		obsera.addContact(contact1);
		
		DrainageBasin capesterre = new DrainageBasin();
		capesterre.setName("Capesterre");

		DrainageBasin brasDavid = new DrainageBasin();
		brasDavid.setName("Bras-David");

		DrainageBasin vieuxHabitants = new DrainageBasin();
		vieuxHabitants.setName("Vieux-Habitants");

		Site fakeSite = new Site();
		fakeSite.setAltitude(25D);
		fakeSite.setLabel("Fake label");

		vieuxHabitants.addSite(fakeSite);

		obsera.addDrainageBasin(capesterre);
		obsera.addDrainageBasin(brasDavid);
		obsera.addDrainageBasin(vieuxHabitants);

		observatories.add(obsera);

		Observatory bvet = new Observatory();
		bvet.setDescription("L'Observatoire de recherche en environnement BVET vise à améliorer nos connaissances sur le cycle de l'eau et les cycles biogéochimiques et à préciser la dynamique des processus d'altération et d'érosion en milieu tropical. Le projet a également pour but d'étudier l'impact des activités humaines sur le fonctionnement du milieu naturel. Dans le cadre de ce projet, plusieurs écosystèmes tropicaux au Cameroun et en Inde font l'objet d'une approche intégrée à deux échelles spatiales complémentaires. La première, locale, concerne des bassins versants expérimentaux (BVE) de quelques kilomètres carrés de superficie. La seconde, régionale, porte sur des bassins dont les superficies sont de l'ordre de 104 km2. Les BVE fournissent les données nécessaires pour comprendre, quantifier et modéliser le fonctionnement des écosystèmes et leur dynamique à moyen et long terme. Les suivis sur les bassins versants de taille plus importante permettent d'appréhender les variations des signatures biogéochimiques des eaux et les transferts de matières en fonction des changements d'échelle.");
		bvet.setLongLabel("Bassins versants expérimentaux tropicaux");
		bvet.setShortLabel("BVET");
		observatories.add(bvet);		
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
