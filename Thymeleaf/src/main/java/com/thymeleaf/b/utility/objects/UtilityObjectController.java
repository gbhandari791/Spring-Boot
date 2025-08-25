package com.thymeleaf.b.utility.objects;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilityObjectController {

	@GetMapping("/utilityObjects")
	public String utilityObjects(Model m) {
		
		m.addAttribute("name", "Gaurav");
		
		m.addAttribute("number", 10);
		m.addAttribute("nPercent", 0.85);
		
		m.addAttribute("today", new Date());
		
		List<Integer> listNumbers = List.of(1 , 2, 3);
		m.addAttribute("listNumbers", listNumbers);
		
		return "utilityObjects";
	}
}
