package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.DrainageBasin;
import fr.obsmip.sedoo.core.domain.Observatory;

public interface DrainageBasinDAO 
{
	void save(DrainageBasin drainageBasin) throws Exception;
	void delete(Long id) throws Exception;
	DrainageBasin getDrainageBasinById(Long id, boolean full);
	List<DrainageBasin> getDrainageBasinByObservatoryId(Long id);
}
