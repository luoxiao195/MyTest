package test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestClientGet extends Thread{
	HttpClient client=HttpClients.createDefault();
	
	public void run()
	{
		HttpGet get=new HttpGet("http://www.baidu.com");
		try {
			HttpResponse response=client.execute(get);
			HttpEntity entity=response.getEntity();
			String res=EntityUtils.toString(entity,"UTF-8");
			System.out.println(res);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
