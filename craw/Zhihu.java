package craw;

import java.util.ArrayList;

public class Zhihu {
	public String question;
	public String zhihuUrl;
	public ArrayList<String> answers;
	public Zhihu(){
		question="";
		zhihuUrl="";
		answers=new ArrayList<String>();
	}
	@Override
	public String toString()
	{
		return "question:"+question+"\n link:"+zhihuUrl
				+"\n answer:"+answers+"\n";
	}
}
