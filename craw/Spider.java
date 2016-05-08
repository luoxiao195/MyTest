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
	static String getUrl="<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>";
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
	static ArrayList<Zhihu> getRec(String content)
	{
		ArrayList<Zhihu>result=new ArrayList<Zhihu>();
		Pattern pattern=Pattern.compile(getUrl);
		Matcher matcher=pattern.matcher(content);
		while(matcher.find())
		{
			Zhihu zhihuTmp=new Zhihu(matcher.group(1));
			result.add(zhihuTmp);
		}
		return result;
	}
}
