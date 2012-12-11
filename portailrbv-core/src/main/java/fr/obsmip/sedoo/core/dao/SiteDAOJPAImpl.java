package fr.obsmip.sedoo.core.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
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

	 @Transactional
		public void deleteFromDrainageBasinId(Long id) 
		{
	    	DrainageBasin drainageBasin = getEntityManager().find(DrainageBasin.class, id); 
	    	List<Site> sites = drainageBasin.getSites();
	    	Iterator<Site> iterator = sites.listIterator();
	    	while (iterator.hasNext()) {
				getEntityManager().remove(iterator.next());
				
			}
		}


	

}
