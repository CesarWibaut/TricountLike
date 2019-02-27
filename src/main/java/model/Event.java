package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eno;

	private Boolean active;

	private String descr;

	private String name;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="events")
	private List<User> users;

	//bi-directional many-to-one association to Spent
	@OneToMany(mappedBy="event")
	private List<Spent> spents;

	public Event() {
	}

	public Integer getEno() {
		return this.eno;
	}

	public void setEno(Integer eno) {
		this.eno = eno;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Spent> getSpents() {
		return this.spents;
	}

	public void setSpents(List<Spent> spents) {
		this.spents = spents;
	}

	public Spent addSpent(Spent spent) {
		getSpents().add(spent);
		spent.setEvent(this);

		return spent;
	}

	public Spent removeSpent(Spent spent) {
		getSpents().remove(spent);
		spent.setEvent(null);

		return spent;
	}

}