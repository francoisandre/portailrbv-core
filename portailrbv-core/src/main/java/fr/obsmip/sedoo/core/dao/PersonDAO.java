package fr.obsmip.sedoo.core.dao;

import fr.obsmip.sedoo.core.domain.Person;

public interface PersonDAO 
{
	void save(Person person) throws Exception;
	void delete(Long id) throws Exception;
	Person getPersonById(Long id, boolean full);
}
