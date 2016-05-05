package com.a1systems.base_jetty;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	public static class Cls {
		protected  String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@RequestMapping("/")
	public String main(@ModelAttribute("item") Cls a, Model ui) {
		ui.addAttribute("var1", (new Date()).toString()+a.getName());

		return "def/def";
	}
}
