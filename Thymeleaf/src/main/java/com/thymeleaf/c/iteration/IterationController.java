package com.thymeleaf.c.iteration;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IterationController {

	@GetMapping("/iteration")
	public String iteration(Model model) {
		
		List<String> fruits = List.of("Apple", "Mango", "Banana");
		model.addAttribute("fruits", fruits);
		
		
		List<IterationDTO> listUsers = List.of(
				new IterationDTO(10, "John"),
				new IterationDTO(20, "peter")
				);
		model.addAttribute("listUsers", listUsers);
		
		return "iteration";
	}
}
