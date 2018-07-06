package guru.springframework.handlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {
	
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z");
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointer(NullPointerException npe) {
		ModelAndView modelView = new ModelAndView("error");
		modelView.addObject("reason", "Some missing data");
		return modelView;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		ModelAndView modelView = new ModelAndView("error");
		fillCause(modelView, req, resp, e);
		return modelView;
	}

	private void fillCause(ModelAndView modelView, HttpServletRequest req, HttpServletResponse resp, Exception e) {
		modelView.addObject("timestamp", dateFormat.format(Calendar.getInstance().getTime()));
		modelView.addObject("status", resp.getStatus());
		modelView.addObject("path", req.getServletPath());
		modelView.addObject("error", e.getMessage());
		modelView.addObject("trace", Arrays.asList(e.getStackTrace()).stream().map(s -> s.toString() + "\n").reduce(String::concat));
		
	}
}
