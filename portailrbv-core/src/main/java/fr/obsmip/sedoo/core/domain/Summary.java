package fr.obsmip.sedoo.core.domain;

public class Summary 
{
	private String resourceAbstract;
	private String resourceTitle;
	private String identifier;
	
	public String getResourceTitle() {
		return resourceTitle;
	}
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
	public String getResourceAbstract() {
		return resourceAbstract;
	}
	public void setResourceAbstract(String resourceAbstract) {
		this.resourceAbstract = resourceAbstract;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
