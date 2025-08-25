package com.thymeleaf.a.getting.started;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/home")
	public String home(Model model) {
		
		model.addAttribute("name", "Gaurav");
		model.addAttribute("age", "21");
		
		model.addAttribute("num1", 2);
		model.addAttribute("num2", 4);
		
		return "index";
	}
}
