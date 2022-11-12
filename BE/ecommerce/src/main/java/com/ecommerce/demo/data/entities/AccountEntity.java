package com.ecommerce.demo.data.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.demo.constants.EAccountStatus;
import com.ecommerce.demo.constants.ERole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

	@Column(name = "fullName")
	private String fullName;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private EAccountStatus status;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "role_id")
	private ERole roleId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> role = new HashSet<>();

	@OneToMany(mappedBy = "account")
	private List<RatingEntity> ratings;

}
