package craw_two;

public class MainTest {
	
	
	
	public static void main(String[] args) {
		try {
			  // ץȡ�ٶ���ҳ�����
			if(BaiduDemo.downloadPage("http://www.baidu.com"))
				System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
