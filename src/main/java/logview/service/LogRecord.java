package logview.service;

public class LogRecord{
	long date;
	String[] content;
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
	public LogRecord(long date,String[] content){
		this.date=date;
		this.content=content;
	}
}