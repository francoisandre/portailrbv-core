package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.Summary;


public interface MetadataDAO
{

	Metadata getMetadataById(String id) throws Exception;
	List<Summary> getSummaries();
	String getPDFURL(String metadataId);

}
