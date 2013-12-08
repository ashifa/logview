package logview.controller;

import java.util.List;

import javax.annotation.Resource;

import logview.service.LogRecord;
import logview.service.LogService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mycontrol")
public class mycontrol {

	@Resource
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@RequestMapping(value="/",  method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "this is default movie");
		return "list";
	}

	@RequestMapping(value = "/mylog", method = RequestMethod.GET)
	public String pringLog(ModelMap model) {
		List<LogRecord> list = logService.parseFile("gesys_GEHC.log");
		model.addAttribute("list", list);
		return "list";
	}

}