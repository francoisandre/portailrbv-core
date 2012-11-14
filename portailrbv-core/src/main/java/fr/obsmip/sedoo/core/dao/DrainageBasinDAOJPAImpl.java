package fr.obsmip.sedoo.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;

@Repository
public class DrainageBasinDAOJPAImpl implements DrainageBasinDAO{

	protected EntityManager em;
    public EntityManager getEntityManager() {
	        return em;
	    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	    }

    @Transactional
	public DrainageBasin getDrainageBasinById(Long id, boolean full) 
	{
    	DrainageBasin drainageBasin = getEntityManager().find(DrainageBasin.class, id); 
    	if (full == true)
    	{
    		//Full loading of childrens
    		drainageBasin.ensureFullyLoaded();
    	}
    	return drainageBasin;
	}

	@Transactional
	public List<DrainageBasin> findAll() {
		
		List<DrainageBasin> resultList = em.createQuery("select drainageBasin from DrainageBasin drainageBasin").getResultList();
		return resultList;
	}
	
	@Transactional
	public void save(DrainageBasin drainageBasin) 
	{
		if (drainageBasin.getId() == null)
		{
			getEntityManager().persist(drainageBasin);
		}
		else
		{
			getEntityManager().merge(drainageBasin);
		}
	}

	@Transactional
	public void delete(Long id) throws Exception {
		
		DrainageBasin drainageBasin = getEntityManager().find(DrainageBasin.class, id);
		if (drainageBasin != null)
		{
			getEntityManager().remove(drainageBasin);
		}
	}


//	@Transactional
//	public Observatory getObservatoryByShortLabel(String shortLabel, boolean full) {
//		Query query = em.createQuery("select observatory from Observatory observatory where observatory.shortLabel=:shortLabel"); 
//		query.setParameter("shortLabel", shortLabel);
//		Observatory aux =(Observatory) query.getSingleResult();
//		if (full==true)
//		{
//			aux.ensureFullyLoaded();
//		}
//		return aux; 
//	}

	public DrainageBasin create() {
		return new DrainageBasin();
	}

	@Override
	public List<DrainageBasin> getDrainageBasinByObservatoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
