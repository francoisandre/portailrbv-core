package fr.obsmip.sedoo.core.config;

public class GeoNetworkConfig 
{
	private String rootURL;
	
	
	public String getLoginURL()
	{
		return rootURL+"/srv/xml.user.login";
	}


	public String getRootURL() {
		return rootURL;
	}


	public void setRootURL(String rootURL) {
		this.rootURL = rootURL;
	}


	public String getCSWURL() {
		return rootURL+"/srv/fre/csw";
	}


	public String getUserListURL() {
		return rootURL+"/srv/xml.user.list";
	}
	
	public String getGroupListURL() {
		return rootURL+"/srv/xml.group.list";
	}
}
