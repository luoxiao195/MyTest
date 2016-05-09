package craw_two;

public class MainTest {
	
	
	
	public static void main(String[] args) {
		try {
			  // 抓取百度首页，输出
			if(BaiduDemo.downloadPage("http://www.baidu.com"))
				System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
