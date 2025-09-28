package com.blog.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.CustomException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payloads.PostDto;
import com.blog.payloads.PageDto;
import com.blog.payloads.PagedResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.FileUploadService;
import com.blog.services.PostService;
import com.blog.util.GeneralUtil;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private FileUploadService uploadService;
	
	Set<String> ALLOWED_SORTED_FIELDS = Set.of("id", "title", "createdOn");
	
	@Override
	public PostDto createPost(PostDto dto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post postEntity = postMapper.dtoToEntity(dto);
		if(dto.getImage() != null && !dto.getImage().trim().isEmpty()) {
			postEntity.setImageName(dto.getImage());
		}
		postEntity.setUser(user);
		postEntity.setCategory(cat);
		
		Post dbPost = postRepo.save(postEntity);
		
		return postMapper.entityToDto(dbPost);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		if(post.getImageName() != null && !post.getImageName().trim().isEmpty()) {
			post.setImageName(dto.getImage());
		}
		
		Post dbPost = postRepo.save(post);
		return postMapper.entityToDto(dbPost);
	}

	@Override
	public PostDto getPost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return this.postMapper.entityToDto(post);
	}

	@Override
	public PagedResponse<PostDto> getAllPosts(PageDto page) {
		
		Pageable pageAble = getPageable(page);
		Page<Post> postPage = this.postRepo.findAll(pageAble);
		List<Post> posts = postPage.getContent();
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}

	@Override
	public PagedResponse<PostDto> getPostByUser(Integer userId, PageDto page) {
						
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Pageable pageAble = getPageable(page);	
		Page<Post> postPage = postRepo.findByUser(user, pageAble);
		List<Post> posts = postPage.getContent();
		List<PostDto> postDtos = getPostDtoList(posts);
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}

	@Override
	public PagedResponse<PostDto> getPostByCategory(Integer categoryId, PageDto page) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Pageable pageAble = getPageable(page);		
		Page<Post> postPage = postRepo.findByCategory(cat, pageAble);
		List<Post> posts = postPage.getContent();		
		List<PostDto> postDtos = getPostDtoList(posts);
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}
	
	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
	}

	@Override
	public PagedResponse<PostDto> searchByTital(String search, PageDto page) {
		
		Pageable pageAble = getPageable(page);
		search = search != null ? search.trim() : "";
		Page<Post> postPage = this.postRepo.findByTitleContainingIgnoreCase(search, pageAble);
		List<PostDto> postDtoList = getPostDtoList(postPage.getContent());
		return GeneralUtil.createPagedResponse(postDtoList, postPage);
	}
	
	private Pageable getPageable(PageDto page) {
		Sort sort = getSort(page.getSortBy(), page.getSortOrder());
		return PageRequest.of(page.getPage() -1 , page.getSize(), sort);
	}
	private Sort getSort(String sortBy, String sortOrder) {
		sortBy =  sortBy != null ? sortBy.toLowerCase() : "";
		if(!ALLOWED_SORTED_FIELDS.contains(sortBy)) {
			throw new CustomException("Invalid sort field : " + sortBy);
		}
		
		return "desc".equalsIgnoreCase(sortOrder) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	}
	private List<PostDto> getPostDtoList(List<Post> posts){
		
		return posts.stream().map(post -> this.postMapper.entityToDto(post)).collect(Collectors.toList());
	}

	@Override
	public boolean uploadPostImage(Integer postId, MultipartFile file) {
	
		Post post = this.postRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "Id", postId));
		
		String fileName = uploadService.uploadImage(file);
		if(fileName != null) {
			post.setImageName(fileName);
			postRepo.save(post);
			return true;
		}
		
		return false;
	}
	
}
