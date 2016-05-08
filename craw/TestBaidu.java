package craw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBaidu {
	static String SendGet(String url){
		String result="";
		BufferedReader in=null;
		try{
			URL realUrl=new URL(url);
			URLConnection connection=realUrl.openConnection();
			connection.connect();
			in=new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
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
	static String RegexString(String targetStr,String patternStr)
	{
		Pattern pattern=Pattern.compile(patternStr);
		Matcher matcher=pattern.matcher(targetStr);
		if(matcher.find())
			return matcher.group(1);
		return "nothing";
	}
}
