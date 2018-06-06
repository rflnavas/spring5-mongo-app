package guru.springframework.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class BasicController implements ErrorController{
	
	protected static final String PATH = "/error";
	
	@RequestMapping("/error")
	public String handleError() {
		
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
