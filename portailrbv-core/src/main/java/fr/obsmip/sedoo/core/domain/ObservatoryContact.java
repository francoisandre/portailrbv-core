package fr.obsmip.sedoo.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OBSERVATORY_CONTACT")
@DiscriminatorValue("1")
public class ObservatoryContact extends Person {
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OBSERVATORY_ID")
	private Observatory observatory;

	public Observatory getObservatory() {
		return observatory;
	}

	public void setObservatory(Observatory observatory) {
		this.observatory = observatory;
	}

	public static ObservatoryContact fromPerson(Person person)
	{
		ObservatoryContact contact = new ObservatoryContact();
		contact.setPersonName(person.getPersonName());
		contact.setOrganisationName(person.getOrganisationName());
		contact.setEmail(person.getEmail());
		return contact;
	}
	
	@Override
	public void ensureFullyLoaded() {
		super.ensureFullyLoaded();
		getObservatory().getId();
	}
	
}
