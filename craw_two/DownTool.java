package craw_two;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;




public class DownTool {
	private String getFileNameByUrl(String url,String contentType){
		url=url.substring(7);
		if(contentType.indexOf("html")==-1){
			url=url.replaceAll("[\\?/:*<>\"]","_")+".html";
		}
		else{
			url=url.replaceAll("[\\?/:*|<>\"]", "_") + "."
		+contentType.substring(contentType.lastIndexOf("/"+1));
		}
		return url;
	}
	private void saveToLocal(byte[] data,String filePath){
		try {
			DataOutputStream out=new DataOutputStream(
					new FileOutputStream(new File(filePath)));
			for(int i=0;i<data.length;i++)
				out.write(data[i]);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String DownLoadFile(String url){
		String filePath=null;
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(5000);
		httpClient.setTimeout(5000);
		GetMethod getMethod=new GetMethod(url);
		/*
		 * 缺失get重置响应
		 * */
		try {
			int statusCode=httpClient.executeMethod(getMethod);
			if(statusCode!=HttpStatus.SC_OK)
			{
				 System.err.println("Method failed: "
					      + getMethod.getStatusLine());
					    filePath = null;
			}
			byte[] responseBody=getMethod.getResponseBody();
			filePath="temp\\"
			+getFileNameByUrl(url,getMethod.
					getResponseHeader("Content-Type").getValue());
			saveToLocal(responseBody, filePath);
		} catch (HttpException e) {
			   // 发生致命的异常，可能是协议不对或者返回的内容有问题
				System.out.println("请检查你的http地址是否正确");
				e.printStackTrace();
		} catch (IOException e) {
			   // 发生网络异常
			   	e.printStackTrace();
		} finally {
			   // 释放连接
				getMethod.releaseConnection();
		}
		return filePath;
	}
}
