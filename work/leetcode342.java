package work;

public class leetcode342 {
	public boolean isPowerOfFour(int num) {
        if((num&(num-1))==0)
        {
        	if((num&0x55555555)==1)
        		return true;
        		
        }
		return false;
    }
}
