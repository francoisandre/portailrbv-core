package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.Summary;
import fr.obsmip.sedoo.core.geonetwork.MetadataByIdRequest;
import fr.obsmip.sedoo.core.geonetwork.MySummaryRequest;

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
