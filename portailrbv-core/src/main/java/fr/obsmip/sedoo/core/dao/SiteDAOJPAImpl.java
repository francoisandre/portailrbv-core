package fr.obsmip.sedoo.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.obsmip.sedoo.core.domain.Site;

@Repository
public class SiteDAOJPAImpl implements SiteDAO{

	protected EntityManager em;
    public EntityManager getEntityManager() {
	        return em;
	    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	    }

	@Transactional
	public void delete(Long id) throws Exception {
		
		Site site = getEntityManager().find(Site.class, id);
		if (site != null)
		{
			getEntityManager().remove(site);
		}
	}



	

}
