package fr.obsmip.sedoo.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.obsmip.sedoo.core.domain.Observatory;

@Repository
public class ObservatoryDAOJPAImpl implements ObservatoryDAO{

	protected EntityManager em;
    public EntityManager getEntityManager() {
	        return em;
	    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	    }

    @Transactional
	public Observatory getObservatoryById(Long id, boolean full) 
	{
    	Observatory observatory = getEntityManager().find(Observatory.class, id); 
    	if (full == true)
    	{
    		//Full loading of childrens
    		observatory.ensureFullyLoaded();
    	}
    	return observatory;
	}

	@Transactional
	public List<Observatory> findAll() {
		
		List<Observatory> resultList = em.createQuery("select observatory from Observatory observatory").getResultList();
		return resultList;
	}
	
	@Transactional
	public void save(Observatory observatory) 
	{
		if (observatory.getId() == null)
		{
			getEntityManager().persist(observatory);
		}
		else
		{
			getEntityManager().merge(observatory);
		}
	}

	@Transactional
	public void delete(Long id) throws Exception {
		
		Observatory observatory = getEntityManager().find(Observatory.class, id);
		if (observatory != null)
		{
			getEntityManager().remove(observatory);
		}
	}


	@Transactional
	public Observatory getObservatoryByShortLabel(String shortLabel, boolean full) {
		Query query = em.createQuery("select observatory from Observatory observatory where observatory.shortLabel=:shortLabel"); 
		query.setParameter("shortLabel", shortLabel);
		Observatory aux =(Observatory) query.getSingleResult();
		if (full==true)
		{
			aux.ensureFullyLoaded();
		}
		return aux; 
	}

	public Observatory create() {
		return new Observatory();
	}

	

}
