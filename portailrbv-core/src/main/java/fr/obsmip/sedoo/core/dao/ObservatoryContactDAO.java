package fr.obsmip.sedoo.core.dao;

import fr.obsmip.sedoo.core.domain.ObservatoryContact;

public interface ObservatoryContactDAO 
{
	void save(ObservatoryContact contact) throws Exception;
	void delete(Long id) throws Exception;
	ObservatoryContact getObservatoryContactById(Long id, boolean full);
}
