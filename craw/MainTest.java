package craw;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://www.zhihu.com/explore/recommendations";
		String result=new Spider().SendGet(url);
		ArrayList<Zhihu> zhi=new Spider().getRec(result);
		for (Zhihu zhihu : zhi) {  
            FileReaderWriter.writeIntoFile(zhihu.writeString(),  
                    "E:/test.txt", true);  
        }  
	}

}
