package com.thymeleaf.e.fragments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragMentController {

	@GetMapping("/fragment")
	public String fragMents(Model model) {
		
		model.addAttribute("dynamicFooter", "This foorter is passed from controller");
		return "fragment";
	}
}
