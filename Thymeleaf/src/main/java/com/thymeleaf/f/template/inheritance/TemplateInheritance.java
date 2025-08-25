package com.thymeleaf.f.template.inheritance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateInheritance {

	@GetMapping("/templateInheritance")
	public String templateInheritance() {
		
		
		return "about";
	}
}
