package pl.inder00.tools.basic;

import pl.inder00.tools.basic.utils.GroupUtil;

public class Group {
	
	private String name;
	private String format;
	
	public Group(String name, String format){
		
		this.name = name;
		this.format = format;
		GroupUtil.addGroup(this);
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

}
