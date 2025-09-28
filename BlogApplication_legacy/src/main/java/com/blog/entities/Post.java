package com.blog.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;
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
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = Instant.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = Instant.now();
	}
	
	
}
