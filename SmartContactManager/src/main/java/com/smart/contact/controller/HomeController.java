package com.smart.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.contact.constant.ResponceTypeConstant;
import com.smart.contact.dao.UserRepository;
import com.smart.contact.dto.ResponceMessageDTO;
import com.smart.contact.dto.UserDTO;
import com.smart.contact.entities.User;
import com.smart.contact.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/")
	public String home() {
		
		
		return "home";
	}
	
	@GetMapping("/about")
	public String about() {
		
		
		return "about";
	}
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		
		model.addAttribute("user", new UserDTO());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") UserDTO dto,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {

		try {
			if (!agreement) {
				throw new Exception("You have not agreed the terms and condtions");
			}

			dto.setRole("ROLE_USER");
			dto.setEnabled(true);

			User userEntity = this.userMapper.toUserEntity(dto);
			this.userRepository.save(userEntity);
			model.addAttribute("user", new UserDTO());
			session.setAttribute("message", new ResponceMessageDTO("Successfully Registered !!", ResponceTypeConstant.TYPE_SUCCESS));

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", dto);
			session.setAttribute("message", new ResponceMessageDTO("Something went wrong : " + e, ResponceTypeConstant.TYPE_ALERT));

		}
		return "signup";
	}
	
}
