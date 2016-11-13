package segment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Baike {
	//?��????url
	//�?�?请�?�??��??
	
	
	
	public static void  fetch(String url) throws IOException
	{
  
		HttpClient httpClient = new HttpClient();		
		GetMethod getMethod = new GetMethod(url);	
		int statusCode = httpClient.executeMethod(getMethod);
		if(statusCode >= 200 && statusCode < 400)				
			{				
				String result;				
				result = getMethod.getResponseBodyAsString();    //getMethod.getRespon	
				  String response =   new String(getMethod.getResponseBodyAsString().getBytes("ISO-8859-1"),"UTF-8");    

			       //打印返回的信息    

			     System.out.println(response);    
				
				
			//	 System.out.println(result);
				 Document doc = Jsoup.parse(response);//result.toString());//(temp);	
		  		 String title =doc.head().select("title").text(); //doc2.head().select("title").text(); 
		  		 System.out.println("title:"+title);
		  		 
		  		 //抽取所有 dt,所有dd

		  		 Elements ListDiv = doc.getElementsByAttributeValue("class","basicInfo-item name");
		            for (Element element :ListDiv) {
		                System.out.println(element.html());}
		            
		            Elements ListDiv2 = doc.getElementsByAttributeValue("class","basicInfo-item value");
		            for (Element element :ListDiv2) {
		                System.out.println(element.html());}
		            
		            
		            //提取正文 
		           //parse(response);
		            System.out.println( TextExtract.parse(response));
		  		// System.out.println("result"+result);
		  		 getMethod.releaseConnection();
                 //saveFile("D:\\�?�?天�?\\"+temp+".txt", result.toString());
			}
	}
	
	public static String InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }
	
	
	
	public static String toGbkString(String s)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < s.length(); i++)
		{
		char c = s.charAt(i);
		if(c >= 0 && c <= 255){
			sb.append(c);
			}else{
				byte[] b;
				try{
					b = String.valueOf(c).getBytes("UTF-8");
				}catch (Exception e) {
					// TODO: handle exception
				e.printStackTrace();
					b = new byte[0];
				}
			for(int j = 0; j < b.length; j++){
					int k = b[j];
					if(k < 0)
						k+=256;
					sb.append("%"+Integer.toHexString(k).toUpperCase());
				}
			}
	}
		return sb.toString();
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException 
	{
		String key="大丽花";
		String s=toGbkString(key);
		System.out.println(s);
	
	        String url="http://baike.baidu.com/item/"+s;
	        fetch(url);
	        		
	}
	
	
	

}
