package fr.obsmip.sedoo.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import fr.obsmip.sedoo.core.domain.Metadata;
import fr.obsmip.sedoo.core.domain.MetadataTools;
import fr.obsmip.sedoo.core.domain.Summary;

public class FakeMetadataDAO implements MetadataDAO{

	
	private HashMap< String, Metadata> metadatas = new HashMap<String, Metadata>();
	
	public FakeMetadataDAO() {
		
		Metadata aux = createFakeMetadata(1);
		metadatas.put(aux.getUuid(), aux);
		aux = createFakeMetadata(2);
		metadatas.put(aux.getUuid(), aux);
		aux = createFakeMetadata(3);
		metadatas.put(aux.getUuid(), aux);
		aux = createFakeMetadata(4);
		metadatas.put(aux.getUuid(), aux);
		
	}
	
	private Metadata createFakeMetadata(int i) {
		Metadata m = new Metadata();
		m.setResourceTitle("title #"+i);
		m.setResourceAbstract("abstract #"+i);
		m.setUuid("my Id-"+i);
		m.setUseConditions("useConditions #"+i);
		//TODO Public Access
		return m; 
	}

	@Override
	public Metadata getMetadataById(String id) throws Exception {
		return metadatas.get(id);
	}

	@Override
	public List<Summary> getSummaries() {
		List<Summary> summaries = new ArrayList<Summary>();
		Iterator<String> iterator = metadatas.keySet().iterator();
		while (iterator.hasNext()) {
			Metadata metadata = metadatas.get(iterator.next());
			summaries.add(MetadataTools.toSummary(metadata)); 
		}
		return summaries;
	}

	@Override
	public String getPDFURL(String metadataId) {
		// TODO Auto-generated method stub
		return null;
	}

}
