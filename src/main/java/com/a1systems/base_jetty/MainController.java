package com.a1systems.base_jetty;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main(Model ui) {
		ui.addAttribute("var1", (new Date()).toString());

		return "def/def";
	}
}
