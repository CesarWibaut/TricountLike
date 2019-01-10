package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the spent database table.
 * 
 */
@Entity
@NamedQuery(name="Spent.findAll", query="SELECT s FROM Spent s")
public class Spent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer sno;

	@Temporal(TemporalType.DATE)
	private Date createdat;

	private String descr;

	private Float ammount;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uno")
	private User user;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="spents2")
	private List<User> users;

	public Spent() {
	}

	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(Float ammount) {
		this.ammount = ammount;
	}

	/**
	 * @return the ammount
	 */
	public Float getAmmount() {
		return ammount;
	}
	
	public Integer getSno() {
		return this.sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getCreatedat() {
		return this.createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}