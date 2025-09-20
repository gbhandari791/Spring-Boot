package com.blog.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "created_on", columnDefinition = "TIMESTAMP")
	private Instant createdOn;
	@Column(name = "updated_on", columnDefinition = "TIMESTAMP")
	private Instant updatedOn;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = Instant.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = Instant.now();
	}
	
	
}
