package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NamedQuery(name="User.findByMail", query="SELECT u FROM User u where u.email = :email")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uno;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private String role;

	//bi-directional many-to-many association to Event
	@ManyToMany
	@JoinTable(
		name="participate"
		, joinColumns={
			@JoinColumn(name="uno")
			}
		, inverseJoinColumns={
			@JoinColumn(name="eno")
			}
		)
	private List<Event> events;

	//bi-directional many-to-one association to Spent
	@OneToMany(mappedBy="user")
	private List<Spent> spents1;

	//bi-directional many-to-many association to Spent
	@ManyToMany
	@JoinTable(
		name="spent_for"
		, joinColumns={
			@JoinColumn(name="uno")
			}
		, inverseJoinColumns={
			@JoinColumn(name="sno")
			}
		)
	private List<Spent> spents2;

	public User() {
	}

	public Integer getUno() {
		return this.uno;
	}

	public void setUno(Integer uno) {
		this.uno = uno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Spent> getSpents1() {
		return this.spents1;
	}

	public void setSpents1(List<Spent> spents1) {
		this.spents1 = spents1;
	}

	public Spent addSpents1(Spent spents1) {
		getSpents1().add(spents1);
		spents1.setUser(this);

		return spents1;
	}

	public Spent removeSpents1(Spent spents1) {
		getSpents1().remove(spents1);
		spents1.setUser(null);

		return spents1;
	}

	public List<Spent> getSpents2() {
		return this.spents2;
	}

	public void setSpents2(List<Spent> spents2) {
		this.spents2 = spents2;
	}

	public String toString(){
		return firstname + " " + lastname;
	}

}