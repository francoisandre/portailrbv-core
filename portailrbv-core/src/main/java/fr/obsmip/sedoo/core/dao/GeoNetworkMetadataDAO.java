package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.geonetwork.MetadataByIdRequest;
import fr.obsmip.sedoo.core.geonetwork.MySummaryRequest;
import fr.sedoo.commons.metadata.utils.domain.Metadata;
import fr.sedoo.commons.metadata.utils.domain.Summary;

public class GeoNetworkMetadataDAO implements MetadataDAO{
	
	public GeoNetworkMetadataDAO() {
	}

	public Metadata getMetadataById(String id) throws Exception 
	{
		MetadataByIdRequest request = new MetadataByIdRequest(id, "admin", "admin");
		return request.getMetadata();
	}

	public List<Summary> getSummaries() 
	{
		MySummaryRequest request = new MySummaryRequest("admin", "admin");
		return request.getSummaries();
	}

	public String getPDFURL(String metadataId) {
		// TODO Auto-generated method stub
		return null;
	}

}
