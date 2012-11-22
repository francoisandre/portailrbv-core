package fr.obsmip.sedoo.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="PERSON_TYPE", discriminatorType=DiscriminatorType.INTEGER)
public class Person implements LazyLoadable {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="ORGANISATION_NAME")
	private String organisationName;
	
	@Column(name="PERSON_NAME")
	private String personName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ROLES")
	private String roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public void ensureFullyLoaded() {
		
	}

}
