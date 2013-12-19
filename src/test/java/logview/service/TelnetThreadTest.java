package logview.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TelnetThreadTest {
	
	@Test
	public void testTelnet()throws Exception{
		List<String> list = new ArrayList<String>();
		list.add("cd /usr/g/service/log/; ls  -1 *core*  |||^(?! cd).*(core|CORE).*$");
		list.add("testrecord|||.*_\\d{4}.*###getver|||.*_\\d{4}.*");
		// List<String> res = new TelnetThread("violet", "3.35.117.216", "sdc",
		// "adw2.0", "]", list).call();
		List<String> res = new TelnetThread("BJ bay4", "3.35.117.197", "sdc",
				"adw2.0", "]", list).call();
		System.out.println(res);
	}

}
