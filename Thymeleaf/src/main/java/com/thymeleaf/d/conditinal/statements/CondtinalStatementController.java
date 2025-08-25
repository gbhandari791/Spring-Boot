package com.thymeleaf.d.conditinal.statements;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CondtinalStatementController {

	@GetMapping("/conditionalStatement")
	public String conditionalStatement(Model model) {
		
		model.addAttribute("age", 17);
		model.addAttribute("isActive", true);
		model.addAttribute("role", "Admin");
		return "conditionalStatement";
	}
}
