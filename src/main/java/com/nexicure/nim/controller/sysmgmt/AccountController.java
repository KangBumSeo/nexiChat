package com.nexicure.nim.controller.sysmgmt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sysmgmt/account")
public class AccountController {

	
	@GetMapping("/main")
	public String main(Model model) throws Exception {
		//System.out.println(mcodeD.selectMcodeDList().size());
		return "sysmgmt/maccount/list";
	}
	
}
