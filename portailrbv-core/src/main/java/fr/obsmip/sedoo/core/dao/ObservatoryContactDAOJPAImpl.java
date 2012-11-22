package fr.obsmip.sedoo.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.pattern.EnsureExceptionHandling;

import fr.obsmip.sedoo.core.domain.ObservatoryContact;
import fr.obsmip.sedoo.core.domain.Person;

@Repository
public class ObservatoryContactDAOJPAImpl implements ObservatoryContactDAO{

	protected EntityManager em;
    public EntityManager getEntityManager() {
	        return em;
	    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	    }

    @Transactional
	public ObservatoryContact getObservatoryContactById(Long id, boolean full) 
	{
    	ObservatoryContact contact = getEntityManager().find(ObservatoryContact.class, id); 
    	if (full == true)
    	{
    		contact.ensureFullyLoaded();
    	}
    	return contact;
	}

	@Transactional
	public void save(ObservatoryContact contact) 
	{
		if (contact.getId() == null)
		{
			getEntityManager().persist(contact);
		}
		else
		{
			getEntityManager().merge(contact);
		}
	}

	@Transactional
	public void delete(Long id) throws Exception {
		
		ObservatoryContact contact = getEntityManager().find(ObservatoryContact.class, id);
		if (contact != null)
		{
			getEntityManager().remove(contact);
		}
	}


	public ObservatoryContact create() {
		return new ObservatoryContact();
	}

	

}
