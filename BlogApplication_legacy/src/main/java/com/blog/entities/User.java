package com.blog.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "about")
	private String about;
	
	@Column(name = "created_on", columnDefinition = "TIMESTAMP")
	private Instant createdOn;
	
	@Column(name = "updated_on", columnDefinition = "TIMESTAMP")
	private Instant updatedOn;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "deleted_on")
	private Long deletedOn;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name =  "lk_user_role",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")			
	)
	private Set<Role> roles = new HashSet<>();
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = Instant.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = Instant.now();
	}
	
	
}
