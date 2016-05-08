package HtmlParser;

import java.util.Iterator;
import java.util.Set;

public class HtmlParserToolTest {
	public static void main(String[] args)
	{
		
	}
	
}
class testHtml{
	public void testExtractLinks() {  
        String url = "http://www.baidu.com";  
        LinkFilter linkFilter = new LinkFilter(){  
            @Override  
            public boolean accept(String url) {  
                if(url.contains("baidu")){  
                    return true;  
                }else{  
                    return false;  
                }  
            }  
              
        };  
        Set<String> urlSet = HtmlParserTool.extractLinks(url, linkFilter);  
          
        Iterator<String> it = urlSet.iterator();  
        while(it.hasNext()){  
            System.out.println(it.next());  
        }  
    }  
}
