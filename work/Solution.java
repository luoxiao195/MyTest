package work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
    	List<Integer>ret=new ArrayList<Integer>();
    	Map<Integer,FEntity>map=new HashMap<Integer,FEntity>();
    	for(int t:nums)
    	{
    		FEntity f;
    		if(map.containsKey(t))
    		{
    			f=map.get(t);
    			f.frequent++;
    		}
    		else
    		{
    			f=new FEntity(t,1);
    		}
    		map.put(t,f);
    	}
    	List<FEntity> values=new ArrayList<FEntity>();
    	Set<Integer>keys=map.keySet();
    	for(Integer key:keys)
    	{
    		values.add(map.get(key));
    	}

    	Collections.sort(values);
    	for(int i=0;i<k;i++)
    	{
    		ret.add(values.get(values.size()-1-i).num);
    	}
    	return ret;
    }
}
class FEntity implements Comparable<FEntity>{
	protected int num,frequent;
	public FEntity(int num,int frequent)
	{
		this.num=num;
		this.frequent=frequent;
	}
	@Override
	public int compareTo(FEntity arg0)
	{
		if(frequent>arg0.frequent)
			return 1;
		else if(frequent==arg0.frequent)
			return 0;
		else
			return -1;
	}
}