package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;

public class DrainageBasinDAOHibernateImpl extends CustomHibernateDaoSupport implements DrainageBasinDAO{

	public DrainageBasin getDrainageBasinById(Long id) 
	{
		List result = getHibernateTemplate().find("from DrainageBasin drainageBasin where drainageBasin.id = ?",id);
		if (result.isEmpty())
		{
			return null;
		}
		else
		{
			return (DrainageBasin) result.iterator().next();
		}
	}

	public void save(DrainageBasin drainageBasin) 
	{
		getHibernateTemplate().save(drainageBasin);
	}

	
	public void delete(Long id) throws Exception {
		
		List result = getHibernateTemplate().find("from DrainageBasin drainageBasin where drainageBasin.id = ?",id);
		if (result.isEmpty() == false)
		{
			getHibernateTemplate().delete(result.iterator().next());
		}
		
	}

	public List<DrainageBasin> getDrainageBasinByObservatoryId(Long id) {
		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
		Observatory observatory = observatoryDAO.getObservatoryById(id);
		return observatory.getDrainageBasins();
	}

}
