package craw;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://www.zhihu.com/explore/recommendations";
		String result=Spider.SendGet(url);
		/*ArrayList<Zhihu> zhi=Spider.getRec(result);
		for (Zhihu zhihu : zhi) {  
            FileReaderWriter.writeIntoFile(zhihu.writeString(),  
                    "E:/test.txt", true);  		//����õ�����д���ļ���
        }
        zhi.clear();
        */
		Spider.getTarget(result);
		
	}

}
