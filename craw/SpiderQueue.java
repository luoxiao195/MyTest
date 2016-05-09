package craw;

import java.util.HashSet;
import java.util.Set;

public class SpiderQueue {
	private static Set<Object>visitedUrl=new HashSet<>();
	private static Queue unVisitedUrl=new Queue();
	public  void addVisiteUrl(String Url){
		visitedUrl.add(Url);
	}
	public  void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	public  int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	public  Object unVisitedUrlDequeue(){
		return unVisitedUrl.deQueue();
	}
	
	public  void addUnvisitedUrl(String url){
		if(url!=null&&!url.trim().equals("")&&!visitedUrl.contains(url)
				&&!unVisitedUrl.contians(url)){
			unVisitedUrl.enQueue(url);
			System.out.println("add to list success"+url);
		}
		else if(url==null){
			System.out.println("url=null");
		}
		else if(url.trim().equals("")){
			System.out.println("url equals null");
		}
		else if(visitedUrl.contains(url)){
			System.out.println("vistedList alearld have");
		}
		else if(unVisitedUrl.contians(url)){
			System.out.println("unVisitedList alearld have");
		}
		else
			System.out.println("something happened");
			
	}
	public  boolean unVisitedUrlsEmpty(){
		return unVisitedUrl.empty();
	}
	public int getUnVisitedUrlNum(){
		return unVisitedUrl.getNum();
	}
}
