package himedia.myportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.myportal.exceptions.MainControllerException;

@Controller
public class MainController {
	@RequestMapping({"/", "/main"})
	public String main() {
//		return "/WEB-INF/views/home.jsp";
		return "home";
	}
	
	@RequestMapping("/except")
	public String except() {
		throw new RuntimeException("force Exception");	//	v1
//		throw new MainControllerException();			//	v2
	}
	
	//	ExceptionHandler v1
	/*
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String handlerControllerException(RuntimeException e) {
		return "Exception: " + e.getMessage();
	}
	*/
	
	//	ExceptionHandler v2
	@ExceptionHandler(MainControllerException.class)
	public String handleControllerException(MainControllerException e, Model model) {
		model.addAttribute("name", e.getClass().getSimpleName());
		model.addAttribute("message", e.getMessage());
		return "errors/exception";
	}
	
	//	개별 컨트롤러에서 예외 처리를 하는 것 보다 
	//	@ControllerAdvice를 이용, 전역적으로 예외를 처리하는 것이 좋은 방법
}
