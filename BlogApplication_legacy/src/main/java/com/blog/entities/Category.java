package com.blog.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name =  "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tital;
	private String description;
	@Column(name = "created_on", columnDefinition = "TIMESTAMP")
	private Instant createdOn;
	@Column(name = "updated_on", columnDefinition = "TIMESTAMP")
	private Instant updatedOn;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = Instant.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = Instant.now();
	}
}
