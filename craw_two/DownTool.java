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
		 * ȱʧget������Ӧ
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
			   // �����������쳣��������Э�鲻�Ի��߷��ص�����������
				System.out.println("�������http��ַ�Ƿ���ȷ");
				e.printStackTrace();
		} catch (IOException e) {
			   // ���������쳣
			   	e.printStackTrace();
		} finally {
			   // �ͷ�����
				getMethod.releaseConnection();
		}
		return filePath;
	}
}
