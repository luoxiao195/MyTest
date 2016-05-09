package craw_two;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class BaiduDemo {
	private static HttpClient httpClient=new HttpClient();
	public static boolean downloadPage(String path) throws Exception{
		InputStream input=null;
		OutputStream output=null;
		GetMethod getMethod=new GetMethod(path);
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode == HttpStatus.SC_OK) {
			   input = getMethod.getResponseBodyAsStream();
			   String filename = path.substring(path.lastIndexOf('/') + 1)
						     + ".html";
			   output = new FileOutputStream(filename);
			   int tempByte = -1;
			   while ((tempByte = input.read()) > 0) {
				   output.write(tempByte);
			   }
			   // 关闭输入流
			   	if (input != null) {
			   		input.close();
			   	}
			   	// 关闭输出流
			   	if (output != null) {
			   		output.close();
			   	}
			   	return true;
		}
		return false;
	}
}
