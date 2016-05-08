package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostMethod extends Thread{
	public void run()
	{
		try {
			URL url=new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection connect=(HttpURLConnection) url.openConnection();
			connect.addRequestProperty("encoding","UTF-8");
			connect.setDoInput(true);
			connect.setDoOutput(true);
			connect.setRequestMethod("POST");
			
			
			OutputStream os=connect.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(os);
			BufferedWriter bw=new BufferedWriter(osw);
			
			
			
			bw.write("http://fanyi.youdao.com/openapi.do?keyfrom=scut-test&key=1129456976&type=data&doctype=xml&version=1.1&"
					+ "q=test");
			bw.flush();
			InputStream is =connect.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			String line;
			StringBuffer builder=new StringBuffer();
			while((line=br.readLine())!=null)
			{
				builder.append(line);
			}
			bw.close();
			osw.close();
			os.close();
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
