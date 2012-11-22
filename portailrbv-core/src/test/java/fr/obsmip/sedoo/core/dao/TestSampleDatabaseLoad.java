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
			throw new Exception("Impossible de supprimer les observatoires présents en base");
		}
	}


	private void populateDefaultBase() throws Exception
	{
		List<Observatory> observatories = new ArrayList<Observatory>();
		Observatory obsera = new Observatory();
		obsera.setLongLabel("Observatoire de l�Erosion aux Antilles");
		obsera.setShortLabel("ObsErA");
		obsera.setDescription("L'Observatoire de l�Erosion aux Antilles (ObsErA) est un service d'observation de l�INSU-CNRS et de l�Institut de Physique du Globe de Paris consacr� � l'�tude de l�alt�ration et de l��rosion aux Antilles. Membre du R�seau de Bassins Versants de Recherche et du Critical Zone Observatory Network, il implique des �quipes de l'Institut de Physique du Globe de Paris, de l'Observatoire Volcanologique et Sismologique de Guadeloupe, de l'Universit� Antilles-Guyane, de l'Institut Universitaire Europ�en de la Mer (UBO) et du Laboratoire de Sciences de la Terre de l'Universit� Claude Bernard (Lyon I). L�objectif d�ObsErA est de permettre la constitution d�une base de donn�es des flux d�eau (pr�cipitations et d�bit des rivi�res), des flux de s�diments et de mati�re organique (en particulier de carbone) dans les rivi�res et sur les versants ainsi que de la composition chimique des rivi�res et des sols dans le contexte particulier de l��le de Basse-Terre en Guadeloupe, marqu�e par un climat tropical et un volcanisme actif. Cette base de donn�es permettra � la communaut� scientifique d'�tudier et de quantifier les modes de d��rosion chimique et physique, ainsi que leur impact sur l'environnement (composition des sols, chimie des rivi�res, flux de carbones, etc...). A ce jour, ObsErA suit l'�volution de 3 bassins versants localis�s dans l'enceinte du Parc National de Guadeloupe : Capesterre, Bras-David et Vieux-Habitants.");

		
		ObservatoryContact contact1 = new ObservatoryContact();
		contact1.setOrganisationName("IRD");
		contact1.setPersonName("Jacques C�l�re");
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
		bvet.setDescription("L'Observatoire de recherche en environnement BVET vise � am�liorer nos connaissances sur le cycle de l'eau et les cycles biog�ochimiques et � pr�ciser la dynamique des processus d'alt�ration et d'�rosion en milieu tropical. Le projet a �galement pour but d'�tudier l'impact des activit�s humaines sur le fonctionnement du milieu naturel. Dans le cadre de ce projet, plusieurs �cosyst�mes tropicaux au Cameroun et en Inde font l'objet d'une approche int�gr�e � deux �chelles spatiales compl�mentaires. La premi�re, locale, concerne des bassins versants exp�rimentaux (BVE) de quelques kilom�tres carr�s de superficie. La seconde, r�gionale, porte sur des bassins dont les superficies sont de l'ordre de 104 km2. Les BVE fournissent les donn�es n�cessaires pour comprendre, quantifier et mod�liser le fonctionnement des �cosyst�mes et leur dynamique � moyen et long terme. Les suivis sur les bassins versants de taille plus importante permettent d'appr�hender les variations des signatures biog�ochimiques des eaux et les transferts de mati�res en fonction des changements d'�chelle.");
		bvet.setLongLabel("Bassins versants exp�rimentaux tropicaux");
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
		assertEqual(observatory.getDescription(),observatoryByShortLabel.getDescription(),"Descriptions de l'observatoire diff�rentes");
		assertEqual(observatory.getShortLabel(),observatoryByShortLabel.getShortLabel(),"Libell�s courts de l'observatoire diff�rents");
		assertEqual(observatory.getLongLabel(),observatoryByShortLabel.getLongLabel(),"Libell�s longs de l'observatoire diff�rents");
		assertEqualDBs(observatory.getDrainageBasins(),observatoryByShortLabel.getDrainageBasins(),"Liste des bassins versants de l'observatoire diff�rents");
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
	
		assertEqual(site1.getLabel(),site2.getLabel(),"Noms de sites diff�rents");
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
		assertEqual(drainageBasin1.getName(),drainageBasin2.getName(),"Noms des bassins diff�rents");
		assertEqualSites(drainageBasin1.getSites(), drainageBasin2.getSites(), "Listes des sites diff�rentes");
		//		assertEqual(observatory.getShortLabel(),observatoryByShortLabel.getShortLabel(),"Libell�s courts de l'observatoire diff�rents");		
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
