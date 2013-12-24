package logview.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import logview.service.TelnetThread;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/telnet")
public class telnetController {
	String[][] bayList = { { "bay1", "3.35.117.204" },
			{ "bay4", "3.35.117.197" } };
	static Map<String, String> bayMap = new TreeMap<String, String>();
	static {
		bayMap.put("bay1", "3.35.117.204");
		bayMap.put("bay4", "3.35.117.197");
		bayMap.put("MKET01", "3.7.25.1");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "this is default movie");

		model.addAttribute("bayList", bayList);
		model.addAttribute("bayMap", bayMap);
		return "telnetIndex";
	}

	@RequestMapping(value = "/getjson/{bayName}", method = RequestMethod.GET)
	public @ResponseBody
	List<String> printJson(@PathVariable String bayName, ModelMap model) {

		System.out.println(new Date() + " " + this.getClass().getSimpleName()
				+ "  " + bayName);

		List<String> list = new ArrayList<String>();
		list.add("cd /usr/g/service/log/; ls  -1 *core*  |||^(?! cd).*(core|CORE).*$");
		list.add("testrecord|||.*_\\d{4}.*###getver|||.*_\\d{4}.*");

		List<String> res = new TelnetThread(bayName, bayMap.get(bayName),
				"sdc", "adw2.0", "]", list).call();
		System.out.println(new Date() + " " + bayName);
		return res;

	}
}
