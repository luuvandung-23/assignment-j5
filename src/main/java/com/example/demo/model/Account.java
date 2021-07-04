package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	@Id
	@NotBlank(message="Không được để trống username")
	String username;
	@NotBlank(message="Không được để trống password")
	String password;
	@NotBlank(message="Không được để trống fullname")
	String fullname;
	@NotBlank(message="Không được để trống email")
	@Email(message="Chưa đúng định dạng email")
	String email;
	String photo;
	boolean activated;
	boolean role;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
