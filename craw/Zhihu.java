package craw;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
	//��ñ��������
	public static String getQuestion="zh-question-title.+?<h2.+?>(.+?)</h2>";
	//�������������
	public static String getDetail="zh-question-detail.+?<div.+?>(.*?)</div>";
	//��ô𰸵�����
	public static String getAnswer="data-author-name=\"(.+?)\".+?<div.+?>(.+?)</div>";
	//public static String getAuthor="";
	public String question;
	public String zhihuUrl;
	public ArrayList<String> authorName;
	public ArrayList<String> answers;
	public String questionDesc;
	public Zhihu(String Url){
		question="";
		zhihuUrl="";
		answers=new ArrayList<String>();
		authorName=new ArrayList<String>();
		questionDesc="";
		if(getRealUrl(Url)){
			System.out.println("����ץȡ"+zhihuUrl);
			String content=Spider.SendGet(zhihuUrl);
			Pattern pattern;
			Matcher matcher;
			pattern=Pattern.compile(getQuestion);
			matcher=pattern.matcher(content);
			if(matcher.find())
				question=matcher.group(1);
			pattern=Pattern.compile(getDetail);
			matcher=pattern.matcher(content);
			if(matcher.find())
				questionDesc=matcher.group(1);
			pattern=Pattern.compile(getAnswer);
			matcher=pattern.matcher(content);
			while(matcher.find())
			{
				authorName.add(matcher.group(1));
				answers.add(matcher.group(2));
			}
		}
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
	boolean getRealUrl(String url)
	{
		Pattern pattern=Pattern.compile("question/(.*?)/");
		Matcher matcher=pattern.matcher(url);
		if(matcher.find())
			zhihuUrl="http://www.zhihu.com/question/"+matcher.group(1);
		else
			return false;
		return true;
	}
	public String writeString() {  
        String result = "";  
        result += "���⣺" + question + "\r\n";  
        result += "������" + questionDesc+ "\r\n";  
        result += "���ӣ�" + zhihuUrl + "\r\n";  
        for (int i = 0; i < answers.size(); i++) {
        	result += "����" + i + "��" + authorName.get(i) + "\r\n";  
            result += "�ش�" + i + "��" + answers.get(i) + "\r\n";  
        }  
        result += "\r\n\r\n";  
        result = result.replaceAll("<br>", "\r\n");  
        result = result.replaceAll("<.*?>", "");
        return result;  
}  
	
}
