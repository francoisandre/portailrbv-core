package fr.obsmip.sedoo.core.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.obsmip.sedoo.core.misc.StringTools;


@Entity
@Table(name="OBSERVATORY")
public class Observatory
{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LONG_LABEL")
	private String longLabel;
	
	@Column(name="SHORT_LABEL")
	private String shortLabel;
	@Transient
	private List<String>  mainURL;
	@Transient
	private List<String>  relatedURL;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="observatory", fetch=FetchType.EAGER)
	private List<DrainageBasin> drainageBasins;

	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<DrainageBasin> getDrainageBasins() {
		return drainageBasins;
	}
	public void setDrainageBasins(List<DrainageBasin> drainageBasins) {
		this.drainageBasins = drainageBasins;
	}
	
	public DrainageBasin getDrainageBasinByName(String name)
	{
		String protectedName = StringTools.protectIdentifier(name);
		Iterator<DrainageBasin> iterator = drainageBasins.iterator();
		while (iterator.hasNext()) {
			DrainageBasin drainageBasin = iterator.next();
			if (drainageBasin.getName().compareToIgnoreCase(protectedName)==0)
			{
				return drainageBasin;
			}
		}
		return null;
	}
	
	
	public String getLongLabel() {
		return longLabel;
	}
	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}
	
	

	public List<String> getMainURL() {
		return mainURL;
	}
	public void setMainURL(List<String> mainURL) {
		this.mainURL = mainURL;
	}
	public List<String> getRelatedURL() {
		return relatedURL;
	}
	public void setRelatedURL(List<String> relatedURL) {
		this.relatedURL = relatedURL;
	}
	public Site getObservationSiteByName(String name) 
	{
		Iterator<DrainageBasin> iterator = drainageBasins.iterator();
		while (iterator.hasNext()) 
		{
			DrainageBasin currentDrainageBasin = iterator.next();
			Site observationSiteByName = currentDrainageBasin.getObservationSiteByName(name);
			if (observationSiteByName != null)
			{
				return observationSiteByName;
			}
		}
		return null;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShortLabel() {
		return shortLabel;
	}
	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}
	public void addDrainageBasin(DrainageBasin drainageBasin) 
	{
		if (drainageBasins == null)
		{
			drainageBasins = new ArrayList<DrainageBasin>();
		}
		
		drainageBasin.setObservatory(this);
		drainageBasins.add(drainageBasin);
	}
}
