package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.Summary;


public interface MetadataDAO
{

	Metadata getMetadataById(String id) throws Exception;
	List<Summary> getSummaries();
	String getPDFURL(String metadataId);

}
