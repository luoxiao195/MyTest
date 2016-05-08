package craw;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://www.baidu.com";
		String result=new TestBaidu().SendGet(url);
		String imgSrc=new TestBaidu().RegexString(result, "src=\"(.+?)\"");
		System.out.println(imgSrc);
	}

}
