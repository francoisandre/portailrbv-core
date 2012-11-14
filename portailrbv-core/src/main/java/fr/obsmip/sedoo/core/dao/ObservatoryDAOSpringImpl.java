package fr.obsmip.sedoo.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;
import fr.obsmip.sedoo.core.misc.StringTools;

public class ObservatoryDAOSpringImpl implements ObservatoryDAO
{
	private Map<String, Observatory> observatories;
	
	
	public Observatory getObservatoryById(Long id) 
	{
		return getObservatories().get(StringTools.protectIdentifier(""+id));
	}


	


	public Map<String, Observatory> getObservatories() {
		return observatories;
	}


	public void setObservatories(Map<String, Observatory> observatories) {
		this.observatories = observatories;
	}





	public List<Observatory> findAll() {
		List<Observatory> result = new ArrayList<Observatory>();
		result.addAll(observatories.values());
		return result;
	}


	public void delete(Long id) {
		observatories.remove(StringTools.protectIdentifier(""+id));
		
	}



	public void save(Observatory observatory) throws Exception {
		// TODO Auto-generated method stub
		
	}





	public Observatory getObservatoryByShortLabel(String shortLabel) {
		// TODO Auto-generated method stub
		return null;
	}


	public Observatory create() {
		return new Observatory();
	}





	public Observatory getObservatoryById(Long id, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}





	public Observatory getObservatoryByShortLabel(String shortLabel,
			boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	


	

}
