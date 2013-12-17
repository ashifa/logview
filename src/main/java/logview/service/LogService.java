package logview.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class LogService {

	private static List<LogRecord> list = new ArrayList<LogRecord>();
	private static DateFormat sdf = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss yyyy", Locale.US);

	static {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT0"));
	}

	/*
	 * private static DateFormat dateFormatter = new SimpleDateFormat(
	 * "yyyy-MM-dd \n HH:mm:ss");
	 */

	public static void main(String[] args) throws IOException, ParseException {
		LogService logservice = new LogService();
		logservice.parseFile("gesys_GEHC.log");
		logservice.summaryByTimeUnit("second");
	}

	public Map<Long, Integer> summaryByTimeUnit(String timeUnit) {

		Map<Long, Integer> map = new TreeMap<Long, Integer>();

		for (LogRecord itr : list) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(itr.getDate());
			switch (timeUnit.toLowerCase()) {
			case "hour":
				cal.set(Calendar.MINUTE, 0);
			case "minute":
				cal.set(Calendar.SECOND, 0);
			case "second":
				break;
			default:
				System.out.println("param from jason not supported");
			}

			long longDate = cal.getTimeInMillis();

			if (map.containsKey(longDate))
				map.put(longDate, map.get(longDate) + 1);
			else
				map.put(longDate, 1);
		}

		return map;
	}

	public List<LogRecord> parseFile(String logFile) {
		list.clear();
		URL targetInfo = Thread.currentThread().getContextClassLoader()
				.getResource(logFile);
		BufferedReader reader;
		try {
			if (targetInfo != null) {

				reader = new BufferedReader(
						new FileReader(targetInfo.getFile()));

			} else {
				reader = new BufferedReader(new FileReader(logFile));
			}

			StringBuilder strBuild = new StringBuilder();
			String str;

			str = reader.readLine();
			while ((str = reader.readLine()) != null) {

				if (str.startsWith("SR ")) {
					strBuild.setLength(0);

				} else if (str.startsWith("EN ")) {

					String tmpStr = strBuild.toString();
					Pattern p = Pattern.compile(
							"\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4}",
							Pattern.MULTILINE);
					Matcher m = p.matcher(tmpStr);
					if (m.find() == true) {
						String found = m.group().trim();
						Date date = sdf.parse(found);// "Fri Apr 18 17:22:06 2013"
						 
						list.add(new LogRecord(date.getTime(), tmpStr.split("<br/><br/>",2)));

					}
				} else {
					strBuild.append(str + "<br/>");
				}

			}

			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = list.subList(0, list.size());
		return list;

	}
}
