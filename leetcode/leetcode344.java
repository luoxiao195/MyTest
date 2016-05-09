package leetcode;

public class leetcode344 {
public String reverseString(String s) {
		int len=s.length();
        char start[]=s.toCharArray();
        
        char ret[]=new char[len+1];
        //System.out.print(len);
        for(int i=0;i<len;i++)
        {
        	ret[i]=start[len-1-i];
        	
        	
        }
       return  String.valueOf(ret).trim();
    }
}
