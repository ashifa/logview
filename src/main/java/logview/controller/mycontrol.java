package logview.controller;

 
import java.util.Arrays;
import java.util.Date;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logview.service.LogRecord;
import logview.service.LogService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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

	@RequestMapping(  method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "this is default movie");
		return "list";
	}

	@RequestMapping(value = "/logview", method = RequestMethod.GET)
	public String pringLog(ModelMap model) {
		List<LogRecord> list = logService.parseFile( "gesys_GEHC.log"  );
		LogRecord[] logrecordArray = list.toArray(new LogRecord[0]);
		model.addAttribute("list", logrecordArray);
		return "logview";
	}
	
	
	@RequestMapping(value="/getjson/{timeUnit}",method = RequestMethod.GET)
	public @ResponseBody SummaryData printJson(@PathVariable String timeUnit, ModelMap model){
		
		System.out.println("in getjson"+new Date()+timeUnit);

		Map<Long, Integer> map = logService.summaryByTimeUnit(timeUnit);

		SummaryData mydata = new SummaryData();
		 
		mydata.timeX=  map.keySet().toArray( new Long[1] );
//		Arrays.sort(mydata.timeX);
		int[] countsY = new int[mydata.timeX.length];
		for(int itr=0; itr<  mydata.timeX.length; itr++)
			countsY[itr]=map.get(mydata.timeX[itr]);
		mydata.countsY=countsY;
		return mydata;
	}
	
	@RequestMapping(value="/getjson", method = RequestMethod.PUT)
	public @ResponseBody String[] putMyData(
			@RequestBody String[] md) {
		String[] str = new String[2];
		str[0]="zhaojian";
		str[1]="aaaa";
		System.out.println(md);
		return md;
	}

}

class SummaryData{
	public Long[] getTimeX() {
		return timeX;
	}
	public void setTimeX(Long[] timeX) {
		this.timeX = timeX;
	}
	public int[] getCountsY() {
		return countsY;
	}
	public void setCountsY(int[] countsY) {
		this.countsY = countsY;
	}
	Long[] timeX;
	int[] countsY;
}