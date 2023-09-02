package com.esd.MedTab.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Admin")
public class Admin {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "admin_id", unique = true)   
	private Integer id;

	@OneToOne
    @JoinColumn(name = "user_id")
	@Autowired
	private User user;
	
	public Admin() {
	}
	
	public Admin(User user) {
		super();
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Admin [user=" + user + "]";
	}
	
}
