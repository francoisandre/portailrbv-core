package fr.obsmip.sedoo.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DRAINAGE_BASIN")
public class DrainageBasin implements LazyLoadable {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NAME")
	private String name;

	@Column(name="NORTH_BOUND_LATITUDE")
	private Double northBoundLatitude;
	
	@Column(name="SOUTH_BOUND_LATITUDE")
	private Double southBoundLatitude;
	
	@Column(name="WEST_BOUND_LONGITUDE")
	private Double westBoundLongitude;
	
	@Column(name="EAST_BOUND_LONGITUDE")
	private Double eastBoundLongitude;
	
	@Transient
	private String url;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="drainageBasin", fetch=FetchType.LAZY)
	private List<Site> sites;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OBSERVATORY_ID")
	private Observatory observatory;
	
//	Site getObservationSiteByLabel(String name)
//	{
//		String protectedName = StringTools.protectIdentifier(name);
//		Iterator<Site> iterator = getSites().iterator();
//		while (iterator.hasNext()) {
//			Site observationSite = iterator.next();
//			if (observationSite.getLabel().compareToIgnoreCase(protectedName)==0)
//			{
//				return observationSite;
//			}
//		}
//		return null;
//	}

	
	public Observatory getObservatory() {
		return observatory;
	}

	public void setObservatory(Observatory observatory) {
		this.observatory = observatory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public void addSite(Site site) {
		if (sites == null)
		{
			sites = new ArrayList<Site>();
		}
		site.setDrainageBasin(this);
		sites.add(site);		
	}

	@Override
	public void ensureFullyLoaded() {
		getSites().size();
	}

	public Double getNorthBoundLatitude() {
		return northBoundLatitude;
	}

	public void setNorthBoundLatitude(Double northBoundLatitude) {
		this.northBoundLatitude = northBoundLatitude;
	}

	public Double getSouthBoundLatitude() {
		return southBoundLatitude;
	}

	public void setSouthBoundLatitude(Double southBoundLatitude) {
		this.southBoundLatitude = southBoundLatitude;
	}

	public Double getWestBoundLongitude() {
		return westBoundLongitude;
	}

	public void setWestBoundLongitude(Double westBoundLongitude) {
		this.westBoundLongitude = westBoundLongitude;
	}

	public Double getEastBoundLongitude() {
		return eastBoundLongitude;
	}

	public void setEastBoundLongitude(Double eastBoundLongitude) {
		this.eastBoundLongitude = eastBoundLongitude;
	}
	
	

}
