package fr.obsmip.sedoo.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.obsmip.sedoo.core.domain.Person;

@Repository
public class PersonDAOJPAImpl implements PersonDAO{

	protected EntityManager em;
    public EntityManager getEntityManager() {
	        return em;
	    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	    }

    @Transactional
	public Person getPersonById(Long id, boolean full) 
	{
    	Person person = getEntityManager().find(Person.class, id); 
    	if (full == true)
    	{
    	}
    	return person;
	}

	@Transactional
	public void save(Person person) 
	{
		if (person.getId() == null)
		{
			getEntityManager().persist(person);
		}
		else
		{
			getEntityManager().merge(person);
		}
	}

	@Transactional
	public void delete(Long id) throws Exception {
		
		Person person = getEntityManager().find(Person.class, id);
		if (person != null)
		{
			getEntityManager().remove(person);
		}
	}


	public Person create() {
		return new Person();
	}

	

}
