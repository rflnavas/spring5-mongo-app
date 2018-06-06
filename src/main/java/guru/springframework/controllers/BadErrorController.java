package guru.springframework.controllers;

import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Cannot create a specific controller to handle errors in this way
 * See https://stackoverflow.com/questions/25356781/spring-boot-remove-whitelabel-error-page
 * @author privado
 *
 */
//@Controller
public class BadErrorController {

	@RequestMapping("/error")
	public String handleError() {
		return "error";
	}
}
