package fr.obsmip.sedoo.core.domain;

import java.io.Serializable;

public class ThesaurusItem implements Serializable
{
	
	private String id;
	private String label;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ThesaurusItem(String id, String label) 
	{
		this.id = id;
		this.label = label;
	}

}
