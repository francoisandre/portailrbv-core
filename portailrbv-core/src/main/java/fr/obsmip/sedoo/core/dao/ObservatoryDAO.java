package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.Observatory;

public interface ObservatoryDAO 
{
	Observatory getObservatoryById(Long id, boolean full);
	Observatory create();
	List<Observatory> findAll();
	Long save(Observatory observatory) throws Exception;
	void delete(Long id) throws Exception;
	Observatory getObservatoryByShortLabel(String shortLabel, boolean full);
}
