package com.aisdigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Controller
	class HelloControler {
		@GetMapping("/")
		public RedirectView redirectWithUsingRedirectView(
				RedirectAttributes attributes) {
			attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
			attributes.addAttribute("attribute", "redirectWithRedirectView");
			return new RedirectView("http://localhost:8080/swagger-ui.html");
		}

	}
}
