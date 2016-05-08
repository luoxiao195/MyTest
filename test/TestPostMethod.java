package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestPostMethod extends Thread{
	HttpClient client=HttpClients.createDefault();
	
	public void run()
	{
		HttpPost post=new HttpPost("http://fanyi.youdao.com/openapi.do");
		
		//HttpGet get=new HttpGet("http://www.baidu.com");
		try {
			//?keyfrom=scut-test&key=1129456976&type=data&doctype=xml&version=1.1&"
			//		+ "q=test"
			List<BasicNameValuePair> parameters=new ArrayList<>();
			parameters.add(new BasicNameValuePair("keyfrom", "scut-test"));
			parameters.add(new BasicNameValuePair("key", "1129456976"));
			parameters.add(new BasicNameValuePair("type", "data"));
			parameters.add(new BasicNameValuePair("doctype", "xml"));
			parameters.add(new BasicNameValuePair("version", "1.1"));
			parameters.add(new BasicNameValuePair("q", "test"));
			post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
			HttpResponse response=client.execute(post);
			HttpEntity entity=response.getEntity();
			String res=EntityUtils.toString(entity,"UTF-8");
			System.out.println(res);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
