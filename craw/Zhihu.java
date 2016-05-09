package craw;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
	//获得标题的正则
	public static String getQuestion="zh-question-title.+?<h2.+?>(.+?)</h2>";
	//获得描述的正则
	public static String getDetail="zh-question-detail.+?<div.+?>(.*?)</div>";
	//获得答案的正则
	public static String getAnswer="data-author-name=\"(.+?)\".+?<div.+?>(.+?)</div>";
	
	public String question;
	public String zhihuUrl;
	public ArrayList<String> authorName;
	public ArrayList<String> answers;
	public String questionDesc;
	public void init(String Url)
	{
		question="";
		zhihuUrl="";
		answers=new ArrayList<String>();
		authorName=new ArrayList<String>();
		questionDesc="";
		try {
			if(getRealUrl(Url)){
				String content=Spider.SendGet(zhihuUrl);
				System.out.println("zhihu spider begin:"+zhihuUrl);
				Pattern pattern;
				Matcher matcher;
				if(content!=null){
					pattern=Pattern.compile(getQuestion);
					matcher=pattern.matcher(content);
					if(matcher.find())
						question=matcher.group(1);
					else{
						question="lost";
						System.out.println("lost question:"+Url);
					}
					pattern=Pattern.compile(getDetail);
					matcher=pattern.matcher(content);
					if(matcher.find())
						questionDesc=matcher.group(1);
					else{
						questionDesc="lost";
						System.out.println("lost questionDesc:"+Url);
					}
					pattern=Pattern.compile(getAnswer);
					matcher=pattern.matcher(content);
					while(matcher.find())
					{
						authorName.add(matcher.group(1));
						answers.add(matcher.group(2));
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("zhihu class error");
			e.printStackTrace();
		}
	}
	public Zhihu(){
		question="";
		zhihuUrl="";
		answers=new ArrayList<String>();
		authorName=new ArrayList<String>();
		questionDesc="";
		//
			//System.out.println("Zhihu类正在抓取："+zhihuUrl);
		
			
	}
	public boolean getAll() {
		  return true;
	}
	@Override
	public String toString()
	{
		return "question:"+question+"\n description:"+questionDesc+"\n link:"+zhihuUrl
				+"\n answer:"+answers+"\n";
	}
	public boolean getRealUrl(String url)
	{
		Pattern pattern=Pattern.compile("question/(.*?)/");
		Matcher matcher=pattern.matcher(url);
		if(matcher.find())
			zhihuUrl="http://www.zhihu.com/question/"+matcher.group(1);
		else if(url.length()<25){
			zhihuUrl="http://www.zhihu.com"+url;
		}
		else
			return false;
		return true;
	}
	public String writeString() {  
        String result = "";  
        result += "问题：" + question + "\r\n";  
        result += "描述：" + questionDesc+ "\r\n";  
        result += "链接：" + zhihuUrl + "\r\n";  
        for (int i = 0; i < answers.size(); i++) {
        	result += "作者" + i + "：" + authorName.get(i) + "\r\n";  
            result += "回答" + i + "：" + answers.get(i) + "\r\n";  
        }  
        result += "\r\n\r\n";  
        result = result.replaceAll("<br>", "\r\n");  
        result = result.replaceAll("<.*?>", "");
        return result;  
}  
	
}
