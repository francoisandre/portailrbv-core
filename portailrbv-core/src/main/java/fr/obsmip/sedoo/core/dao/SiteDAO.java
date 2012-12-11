package fr.obsmip.sedoo.core.dao;


public interface SiteDAO 
{
	void delete(Long id) throws Exception;

	void deleteFromDrainageBasinId(Long id);
}
