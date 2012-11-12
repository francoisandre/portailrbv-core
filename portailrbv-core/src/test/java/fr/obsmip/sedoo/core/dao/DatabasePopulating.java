package fr.obsmip.sedoo.core.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/config-test.xml"})
public class DatabasePopulating extends AbstractDatabaseTest
{
	
	
	@Test
	public void populate() throws Exception 
	{
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		deleteAllObservatories();
		List<Observatory> observatories = getObservatories();
		Iterator<Observatory> iterator = observatories.iterator();
		while (iterator.hasNext()) {
			Observatory current = iterator.next();
			observatoryDAO.save(current);
		}
		
		List<Observatory> allObservatories = observatoryDAO.findAll();
		Assert.assertEquals("Tous les élements doivent avoir été intégrés", allObservatories.size(), observatories.size());
		
		Assert.assertEquals("",3, observatoryDAO.getObservatoryByShortLabel("ObsErA").getDrainageBasins().size());
	}
	
	
	private List<Observatory> getObservatories()
	{
		List<Observatory> observatories = new ArrayList<Observatory>();
		Observatory obsera = new Observatory();
		obsera.setLongLabel("Observatoire de l’Erosion aux Antilles");
		obsera.setShortLabel("ObsErA");
		obsera.setDescription("L'Observatoire de l’Erosion aux Antilles (ObsErA) est un service d'observation de l’INSU-CNRS et de l’Institut de Physique du Globe de Paris consacré à l'étude de l’altération et de l’érosion aux Antilles. Membre du Réseau de Bassins Versants de Recherche et du Critical Zone Observatory Network, il implique des équipes de l'Institut de Physique du Globe de Paris, de l'Observatoire Volcanologique et Sismologique de Guadeloupe, de l'Université Antilles-Guyane, de l'Institut Universitaire Européen de la Mer (UBO) et du Laboratoire de Sciences de la Terre de l'Université Claude Bernard (Lyon I). L’objectif d’ObsErA est de permettre la constitution d’une base de données des flux d’eau (précipitations et débit des rivières), des flux de sédiments et de matière organique (en particulier de carbone) dans les rivières et sur les versants ainsi que de la composition chimique des rivières et des sols dans le contexte particulier de l’île de Basse-Terre en Guadeloupe, marquée par un climat tropical et un volcanisme actif. Cette base de données permettra à la communauté scientifique d'étudier et de quantifier les modes de d’érosion chimique et physique, ainsi que leur impact sur l'environnement (composition des sols, chimie des rivières, flux de carbones, etc...). A ce jour, ObsErA suit l'évolution de 3 bassins versants localisés dans l'enceinte du Parc National de Guadeloupe : Capesterre, Bras-David et Vieux-Habitants.");
		
		DrainageBasin capesterre = new DrainageBasin();
		capesterre.setName("Capesterre");
		
		DrainageBasin brasDavid = new DrainageBasin();
		brasDavid.setName("Bras-David");
		
		DrainageBasin vieuxHabitants = new DrainageBasin();
		vieuxHabitants.setName("Vieux-Habitants");
		
		obsera.addDrainageBasin(capesterre);
		obsera.addDrainageBasin(brasDavid);
		obsera.addDrainageBasin(vieuxHabitants);
		
		observatories.add(obsera);
		
		Observatory bvet = new Observatory();
		bvet.setDescription("L'Observatoire de recherche en environnement BVET vise à améliorer nos connaissances sur le cycle de l'eau et les cycles biogéochimiques et à préciser la dynamique des processus d'altération et d'érosion en milieu tropical. Le projet a également pour but d'étudier l'impact des activités humaines sur le fonctionnement du milieu naturel. Dans le cadre de ce projet, plusieurs écosystèmes tropicaux au Cameroun et en Inde font l'objet d'une approche intégrée à deux échelles spatiales complémentaires. La première, locale, concerne des bassins versants expérimentaux (BVE) de quelques kilomètres carrés de superficie. La seconde, régionale, porte sur des bassins dont les superficies sont de l'ordre de 104 km2. Les BVE fournissent les données nécessaires pour comprendre, quantifier et modéliser le fonctionnement des écosystèmes et leur dynamique à moyen et long terme. Les suivis sur les bassins versants de taille plus importante permettent d'appréhender les variations des signatures biogéochimiques des eaux et les transferts de matières en fonction des changements d'échelle.");
		bvet.setLongLabel("Bassins versants expérimentaux tropicaux");
		bvet.setShortLabel("BVET");
		observatories.add(bvet);
		
		return observatories;
	}

		
}
