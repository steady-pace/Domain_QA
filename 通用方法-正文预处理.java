package segment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordSeg {

	private static final List<String> DIC = new ArrayList<>();
    private static int MAX_LENGTH;
    static{
        try {
            System.out.println("开始初始化词典");
            int max=1;
            int count=0;
            List<String> lines = Files.readAllLines(Paths.get("D:/dic.txt"), Charset.forName("utf-8"));
            for(String line : lines){
                DIC.add(line);
                count++;
                if(line.length()>max){
                    max=line.length();
                }
            }
            MAX_LENGTH = max;
            System.out.println("完成初始化词典，词数目："+count);
            System.out.println("最大分词长度："+MAX_LENGTH);
        } catch (IOException ex) {
            System.err.println("词典装载失败:"+ex.getMessage());
        }
         
    }
    
    
    
    
    
    
    
    
    public static void saveFile_zhuijia(String fileName,String content) throws IOException
	{
	    File file =new File(fileName);
	    if(!file.exists())
	    {       
	       // System.out.println("娑�锟斤拷锟斤拷);
	        System.out.println(file.createNewFile());
	        file.createNewFile();
	    }

	    
	        FileOutputStream fos = new FileOutputStream(file,true);
	        OutputStreamWriter out =new OutputStreamWriter(fos,"utf8");//(fos,"gb2312");
	        BufferedWriter bw = new BufferedWriter(out);
	        bw.write(content);	       	        
	        bw.write("\r\n");
	        
	        
	       
	       
	        bw.flush();
	      //  System.out.println("锟斤拷缂�锟斤拷锟�");

	}
    

  	 public static ArrayList<File> getListFiles(Object obj) {  
  	        File directory = null;  
  	        if (obj instanceof File) {  
  	            directory = (File) obj;  
  	        } else {  
  	            directory = new File(obj.toString());  
  	        }  
  	        ArrayList<File> files = new ArrayList<File>();  
  	        if (directory.isFile()) {  
  	            files.add(directory);  
  	            return files;  
  	        } else if (directory.isDirectory()) {  
  	            File[] fileArr = directory.listFiles();  
  	            for (int i = 0; i < fileArr.length; i++) {  
  	                File fileOne = fileArr[i];  
  	                files.addAll(getListFiles(fileOne));  
  	            }  
  	        }  
  	        return files;  
  	    }  
  	 
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException{
        String text = "杨尚川是APDPlat应用级产品开发平台的作者";  
        
        //遍历所有所有的文件 
        //每个文件，读一行切分一行。并写入文件中
        
        
        
        WordSeg w=new WordSeg();
        
        
        
       // ArrayList<File> files=getListFiles("D:\\TFIDF");  
        ArrayList<File> files=getListFiles("D:\\手机-正文");  
        System.out.println(files);
	//UseDemo t=new UseDemo();
        
    for(int i=0;i<files.size()-1;i++){
       
        String s2=files.get(i).toString();//"D:\\缁憋拷锟�\\httpwwwchinavegancomdaleiDWJFDWYQ.html";
           System.out.println(s2);
           
		//t.solve(s2,i);
          String temp= readTxtFile(s2);
          
        //  String c= new String(temp.getBytes("ISO-8859-1"), "utf8");
          System.out.println("待切分文本："+temp);
          System.out.println(temp);
          
          //������棰� 
          
         // String sbyte=getEncoding(temp);
        //  String a = new String(temp.getBytes(sbyte),"utf8");
       //   System.out.println(TextExtract.parse(temp)+"\n\n\n\n\n\n");
        //  System.out.println("##############################");
          
          
          //���ユ��浠�
          //saveFile_zhuijia("D:\\TFIDF-分词后\\"+i+".txt",seg(temp));
          
        //  w.saveFile_zhuijia("D:\\TFIDF-分词后\\"+i+".txt",temp);
          w.saveFile_zhuijia("D:\\2\\"+i+".txt",temp);
    }

        
        
        
        
        
        
      
    }
    
    
    
    
    
    
    
 	 public static String readTxtFile(String filePath){
	   		
         try {
        	 StringBuilder build=new StringBuilder();
                 String encoding="utf-8";//"GB2312";
                 File file=new File(filePath);
                 
                 if(file.isFile() && file.exists()){ //�ゆ����浠舵����瀛���
                     InputStreamReader read = new InputStreamReader(
                     new FileInputStream(file),encoding);//�����扮����煎�
                     BufferedReader bufferedReader = new BufferedReader(read);
                     String lineTxt = null;
                     while((lineTxt = bufferedReader.readLine()) != null){
                        // System.out.println(lineTxt);
                    	 lineTxt=lineTxt+"\n";
                       //  build.append(lineTxt);
                        // System.out.println(seg(lineTxt));
                         for(String temp:seg(lineTxt))
                         {
                        	 build.append(temp+" ");
                        	 
                         }
                     }
                     
                     read.close();
                     
                    
                     return   build.toString();
                     
                    
                     
                     
         }else{
             System.out.println("�句��版��瀹�����浠�");
         }
         } catch (Exception e) {
             System.out.println("璇诲����浠跺��瀹瑰�洪��");
             e.printStackTrace();
         }
		
      return null;
     }
   	 
   	 
   	 
    
    
    
    
    
    
    
    
    public static List<String> seg(String text){        
        List<String> result = new ArrayList<>();
        while(text.length()>0){
            int len=MAX_LENGTH;
            if(text.length()<len){
                len=text.length();
            }
            //取指定的最大长度的文本去词典里面匹配
            String tryWord = text.substring(0, 0+len);
            while(!DIC.contains(tryWord)){
                //如果长度为一且在词典中未找到匹配，则按长度为一切分
                if(tryWord.length()==1){
                    break;
                }
                //如果匹配不到，则长度减一继续匹配
                tryWord=tryWord.substring(0, tryWord.length()-1);
            }
            result.add(tryWord);
            //从待分词文本中去除已经分词的文本
            text=text.substring(tryWord.length());
        }
        return result;
    }
}
