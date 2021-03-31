package thumbnail;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetImgTag {
	public GetImgTag(){}
	
	public static Map getFileName(String content){
		
		//String imgPattern="<*src=[\"']?([^>\"']+)[\"']?[^>]*>";
		String imgPattern="<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>";
		//String classPattern="<*class=[\"']?([^>\"']+)[\"']?[^>]*>";
		String classPattern="<*class=[\"']?([^>\"']+)[\"']?[^>]*>";
		Pattern p1=Pattern.compile(imgPattern);
		Pattern p2=Pattern.compile(classPattern);
		Matcher m1=p1.matcher(content);
		Matcher m2=p2.matcher(content);
		
		Map map=new HashMap();
		
		while(m1.find()&&m2.find()){
			map.put(m1.group(1).substring(16), m2.group(1));
		}
		
		return map;
	}

}
