package test;

import java.util.Date;

public class DateDemo {
	public void dates()
	{
		String strDate,strTime="";
		Date objDate=new Date();
		System.out.println(objDate);
		long time=objDate.getTime();
		System.out.println(time);
		strDate=objDate.toString();
		strTime=strDate.substring(11,(strDate.length()-4));
		strTime="time:"+strTime.substring(0,8);
		System.out.println(strTime);
	}
}
