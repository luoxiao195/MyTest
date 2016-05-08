package craw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
	static String getEdit="question_link.+?>(.+?)<";
	static String getUrl="question_link.+?>(.+?)<";
	static String SendGet(String url){
		String result="";
		BufferedReader in=null;
		try{
			URL realUrl=new URL(url);
			URLConnection connection=realUrl.openConnection();
			connection.connect();
			in=new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while((line=in.readLine())!=null)
			{
				result+=line;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("get error");
			e.printStackTrace();
		}
		finally{
			try{
				if(in!=null)
					in.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return result;
	}
	static ArrayList<String> RegexString(String targetStr,String patternStr)
	{
		Pattern pattern=Pattern.compile(patternStr);
		Matcher matcher=pattern.matcher(targetStr);
		ArrayList<String> result=new ArrayList<String>();
		while(matcher.find()){
			result.add(matcher.group(1));
			
		}
			
			
		return result;
	}
	static ArrayList<Zhihu> GetZhihu(String content)
	{
		ArrayList<Zhihu> result=new ArrayList<Zhihu>();
		
		Pattern questionPattern=Pattern.compile(getEdit);
		Matcher questionMatcher=questionPattern.matcher(content);
		Pattern urlPattern=Pattern.compile(getUrl);
		Matcher urlMatcher=urlPattern.matcher(content);
		boolean ifFind=questionMatcher.find()&&urlMatcher.find();
		while(ifFind){
			Zhihu zhihuTemp=new Zhihu();
			zhihuTemp.question=questionMatcher.group(1);
			zhihuTemp.zhihuUrl="http://www.zhihu.com"+urlMatcher.group(1);
			result.add(zhihuTemp);
			ifFind=questionMatcher.find()&&urlMatcher.find();
		}
		
		return result;
	}
}
