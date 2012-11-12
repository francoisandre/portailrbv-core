package fr.obsmip.sedoo.core.domain;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.obsmip.sedoo.core.misc.StringTools;

@Entity
@Table(name="DRAINAGE_BASIN")
public class DrainageBasin {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NAME")
	private String name;

	@Transient
	private String url;
	
	@Transient
	private List<ObservationSite> observationSites;

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
	
	ObservationSite getObservationSiteByName(String name)
	{
		String protectedName = StringTools.protectIdentifier(name);
		Iterator<ObservationSite> iterator = getObservationSites().iterator();
		while (iterator.hasNext()) {
			ObservationSite observationSite = iterator.next();
			if (observationSite.getCode().compareToIgnoreCase(protectedName)==0)
			{
				return observationSite;
			}
		}
		return null;
	}

	public List<ObservationSite> getObservationSites() {
		return observationSites;
	}

	public void setObservationSites(List<ObservationSite> observationSites) {
		this.observationSites = observationSites;
	}

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
	
	

}
