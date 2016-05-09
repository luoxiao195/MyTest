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
	static String getDeepUrl="question_link.+?href=\"(.+?)\"";
	static String SendGet(String url){
		String result="";
		System.out.println("SendGet正在抓取："+url);
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
			System.out.println("Spider get error:"+url);
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
		Zhihu zhihuTmp=new Zhihu();
		Pattern pattern=Pattern.compile(getUrl);
		Matcher matcher=pattern.matcher(content);
		while(matcher.find())
		{
			zhihuTmp.init(matcher.group(1));
			result.add(zhihuTmp);
		}
		return result;
	}
	static String getRealUrl(String url)
	{
		String ret = "http://www.zhihu.com/explore/recommendations";
		Pattern pattern=Pattern.compile("question/(.*?)/");
		Matcher matcher=pattern.matcher(url);
		if(matcher.find())
			ret="http://www.zhihu.com/question/"+matcher.group(1);
		else if(url.length()<25){
			ret="http://www.zhihu.com"+url;
		}
		return ret;
	}
	static void getTarget(String content){
		
		SpiderQueue queue=new SpiderQueue();
		Pattern pattern=Pattern.compile(getUrl);
		Matcher matcher=pattern.matcher(content);
		Zhihu tmp=new Zhihu();
		while(matcher.find())
		{
			queue.addUnvisitedUrl(matcher.group(1));
			System.out.println("from recommendations:"+matcher.group(1));
		}
		while(!queue.unVisitedUrlsEmpty())
		{
			String url=(String) queue.unVisitedUrlDequeue();
			queue.addVisiteUrl(url);
			tmp.init(url);
			
			url=getRealUrl(url);
			
			String c=SendGet(url);
			
			Pattern p=Pattern.compile(getDeepUrl);
			Matcher m=p.matcher(c);
			if(queue.getUnVisitedUrlNum()<=1000)
				while(m.find()){
					System.out.println("get url from:"+m.group(1)+" number:"+queue.getUnVisitedUrlNum());
					queue.addUnvisitedUrl(m.group(1));
				}
			
			System.out.println("the loop :"+url);
			
			FileReaderWriter.writeIntoFile(tmp.writeString(),  
                    "E:/test.txt", true);  		//将获得的数据写入文件中
		}
		
		System.out.println("queue is empty"+queue.getVisitedUrlNum());
	}
	
}
