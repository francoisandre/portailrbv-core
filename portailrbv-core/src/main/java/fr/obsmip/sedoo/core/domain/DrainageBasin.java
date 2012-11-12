package fr.obsmip.sedoo.core.domain;

import java.util.Iterator;
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
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="drainageBasin", fetch=FetchType.EAGER)
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
	
	Site getObservationSiteByLabel(String name)
	{
		String protectedName = StringTools.protectIdentifier(name);
		Iterator<Site> iterator = getSites().iterator();
		while (iterator.hasNext()) {
			Site observationSite = iterator.next();
			if (observationSite.getLabel().compareToIgnoreCase(protectedName)==0)
			{
				return observationSite;
			}
		}
		return null;
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

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	

}
