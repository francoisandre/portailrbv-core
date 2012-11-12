package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;

public class ObservatoryDAOHibernateImpl extends CustomHibernateDaoSupport implements ObservatoryDAO{

	public Observatory getObservatoryById(Long id) 
	{
		List result = getHibernateTemplate().find("from Observatory observatory where observatory.id = ?",id);
		if (result.isEmpty())
		{
			return null;
		}
		else
		{
			return (Observatory) result.iterator().next();
		}
	}

	public List<Observatory> findAll() {
		
		return getHibernateTemplate().find("from Observatory");
	}
	
	public void save(Observatory observatory) 
	{
		if (observatory.getId() == null)
		{
			getHibernateTemplate().save(observatory);
		}
		else
		{
			getHibernateTemplate().update(observatory);
		}
	}

	
	public void delete(Long id) throws Exception {
		
		List result = getHibernateTemplate().find("from Observatory observatory where observatory.id = ?",id);
		if (result.isEmpty() == false)
		{
			getHibernateTemplate().delete(result.iterator().next());
		}
		
	}


	public Observatory getObservatoryByShortLabel(String shortLabel) {
		List result = getHibernateTemplate().find("from Observatory observatory where observatory.shortLabel = ?",shortLabel);
		if (result.isEmpty())
		{
			return null;
		}
		else
		{
			return (Observatory) result.iterator().next();
		}
	}

	public Observatory create() {
		return new Observatory();
	}

	

}
