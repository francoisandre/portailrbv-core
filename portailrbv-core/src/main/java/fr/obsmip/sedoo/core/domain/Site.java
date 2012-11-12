package fr.obsmip.sedoo.core.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Site {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="LABEL")
	private String label;
	
	@Column(name="LATITUDE")
	private Double latitude;
	
	@Column(name="LONGITUDE")
	private Double longitude;
	
	@Column(name="ALTITUDE")
	private Double altitude;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DRAINAGE_BASIN_ID")
	private DrainageBasin drainageBasin;
	
}
