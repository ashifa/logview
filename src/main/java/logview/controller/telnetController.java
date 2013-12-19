package logview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/telnet")
public class telnetController {

	@RequestMapping(  method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "this is default movie");
		return "telnetIndex";
	}
}
