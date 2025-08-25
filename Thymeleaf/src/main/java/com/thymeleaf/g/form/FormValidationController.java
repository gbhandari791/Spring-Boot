package com.thymeleaf.g.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class FormValidationController {

	@GetMapping("/showLogin")
	public String showLogin(Model model) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("Gaurav");
		userDTO.setPassword("12345678");
		model.addAttribute("user", userDTO);
		return "login";
	}
	
	@PostMapping("/processLogin")
	public String processLogin(@Valid @ModelAttribute("user") UserDTO user, BindingResult result) {
		
		if (result.hasErrors()) {
			return "login";
		}
		return "wlcome";
	}
}
