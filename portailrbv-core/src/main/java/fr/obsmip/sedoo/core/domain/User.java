package fr.obsmip.sedoo.core.domain;

public class User 
{
	private String login;
	private String firstName;
	private String lastName;
	private String email;
	private boolean admin = false;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void setAdministrator(String value)
	{
		if (value.compareToIgnoreCase("Administrator")==0)
		{
			setAdmin(true);
		}
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
