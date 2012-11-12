package fr.obsmip.sedoo.core.dao;


import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.domain.Observatory;

public abstract class AbstractDatabaseTest 
{
	
	protected void deleteAllObservatories() throws Exception
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
