package org.mypr.model;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements java.io.Serializable{
	
	@Id
	@Column(name = "person")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "identity_code")
	private String identity_code;
	@Column(name = "birth_date")
	private Date birth_date;
	@Column(name = "created_by")
	private Integer created_by;
	@Column(name = "updated_by")
	private Integer updated_by;
	@Column(name = "created")
	private Timestamp created;
	@Column(name = "updated")
	private Timestamp updated;
	
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="subject_type")
	//private Collection<Attribute> attributes;
	
	public Person(){}
	
	public Person(String firstName, String lastName, String identityCode, Date birthDate, int createdBy, int updatedBy, Timestamp created, Timestamp updated){
		this.first_name = firstName;
		this.last_name = lastName;
		this.identity_code = identityCode;
		this.birth_date = birthDate;
		this.created_by = createdBy;
		this.updated_by = updatedBy;
		this.created = created;
		this.updated = updated;
	}
	
	public void setPerson(int person){
		this.id = person;
	}
	
	public int getPerson(){
		return id;
	}
	
	public void setFirstName(String firstName){
		this.first_name = firstName;
	}
	
	public String getFirstName(){
		return first_name;
	}
	
	public void setLastName(String lastName){
		this.last_name = lastName;
	}
	
	public String getLastName(){
		return last_name;
	}
	
	public void setIdentityCode(String identityCode){
		this.identity_code = identityCode;
	}
	
	public String getIdentityCode(){
		return identity_code;
	}
	
	public void setBirthDate(Date birthDate){
		this.birth_date = birthDate;
	}
	
	public Date getBirthDate(){
		return birth_date;
	}
	
	public void setCreatedBy(Integer createdBy){
		this.created_by = createdBy;
	}
	
	public Integer getCreatedBy(){
		return created_by;
	}
	
	public void setUpdatedBy(Integer updatedBy){
		this.updated_by = updatedBy;
	}
	
	public Integer getUpdatedBy(){
		return updated_by;
	}
	
	public void setCreated(Timestamp created){
		this.created = created;
	}
	
	public Timestamp getCreated(){
		return created;
	}
	
	public void setUpdated(Timestamp updated){
		this.updated = updated;
	}
	
	public Timestamp getUpdated(){
		return updated;
	}
	
	/*public Collection<Attribute> getAttributes() {
		return attributes;
	}

	public void setHomeworks(Collection<Attribute> newValue) {
		this.attributes = newValue;
	}*/
}


