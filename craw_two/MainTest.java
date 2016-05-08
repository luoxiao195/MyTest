package craw_two;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class MainTest {
	private static HttpClient  httpClient=new HttpClient();
	public static boolean downloadPage(String path) throws Exception
	{
		InputStream input = null;
		OutputStream output = null;
		
		GetMethod getMethod=new GetMethod(path);
		int statusCode=httpClient.executeMethod(getMethod);
		
		if(statusCode==HttpStatus.SC_OK){
			input=getMethod.getResponseBodyAsStream();
			String filename=path.substring(path.lastIndexOf('/')+1)+".html";
			
		}
			
		
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//new BaiduDemo().downloadPage("http://www.baidu.com");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
				
	}

}
