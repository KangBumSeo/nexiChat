package com.nexicure.nim.controller.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

	private Logger logger = LogManager.getLogger(AdviceController.class);
	
	@ExceptionHandler(Exception.class)
	public String handleExcption(Exception ex, Model model) {
		logger.debug("Exception [AdviceController]: "+ ex.getMessage());
		
		model.addAttribute("msg", "");
		return "screen/defaulterror";
	}
}
