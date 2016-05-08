package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	private static String RootUrlRegex="(http://.*?/))";
	private static String currentUrlRegex = "(http://.*/)";
	private static String Chregex="([\u4e00-\u9fa5]+)";
	public static String getString(String dealStr,String regexStr,
			String splitStr,int n){
		String reStr="";
		if(dealStr==null||regexStr==null||n<1)
			return reStr;
		splitStr=(splitStr==null)?"":splitStr;
		Pattern pattern=Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE|
				Pattern.DOTALL);
		Matcher matcher=pattern.matcher(dealStr);
		StringBuffer stringBuffer=new StringBuffer();
		while(matcher.find())
		{
			stringBuffer.append(matcher.group(n).trim());
			stringBuffer.append(splitStr);
		}
		reStr=stringBuffer.toString();
		if(splitStr!=""&&reStr.endsWith(splitStr))
		{
			reStr=reStr.substring(0,reStr.length()-splitStr.length());
		}
		return reStr;
	}
	public static String getString(String dealStr,String regexStr,int n){
		return getString(dealStr,regexStr,null,n);
	}
	
}
