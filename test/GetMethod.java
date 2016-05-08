package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class GetMethod extends Thread{
	public void run()
	{
		try {
			URL url=new	URL("http://fanyi.youdao.com/openapi.do?keyfrom=scut-test&key=1129456976&type=data&doctype=xml&version=1.1&"
					+ "q=test");
			URLConnection connection=url.openConnection();
			InputStream is=connection.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			String line;
			StringBuilder builder=new StringBuilder();
			while((line=br.readLine())!=null)
			{
				builder.append(line);
				
			}
			br.close();
			isr.close();
			is.close();
			System.out.println(builder.toString());
			
		}catch(MalformedURLException e)
		{
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}

