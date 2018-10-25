package cn.hhu.bu;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by buxiaofeng on 2017/5/26.
 */
public class Crawler {
    private static String mainURL="http://www.hanzizidian.com/bs.html";
    private String rootURL="http://www.hanzizidian.com/";
    public void getbh() {
        try {
            String result="";
            URL url = new URL(mainURL);
            URLConnection URLconnection = url.openConnection();
            URLconnection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(URLconnection.getInputStream()));
            String line="";
            while((line=in.readLine())!=null){
                result+=line;
            }
            String []strs=result.split("bschr");
            for (int i=0;i<strs.length;i++){
                this.getbs(strs[i],i+1);
            }
            in.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void getbs(String str,int bhCount){
        String result="";
        try {
            String pattern = "<{1}a{1}\\s{1}href=\"{1}(bs{1}[0-9a-zA-Z]{6})\">{1}(.)</a>{1}";

            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            File file=new File("doc/bh" +bhCount+".properties");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            OutputStreamWriter osw=new OutputStreamWriter(fileOutputStream, "UTF-8");
            BufferedWriter  bw=new BufferedWriter(osw);
            int i=1;
            while (m.find( )) {
                System.out.println("Found value: " + m.group(0) );
                System.out.println("Found value: " + m.group(1) );
                System.out.println("Found value: " + m.group(2) );
                bw.write(m.group(1)+","+m.group(2)+ "\t\n");
                i++;
            }
            bw.close();
            osw.close();
            fileOutputStream.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public void getHZ() {
        try {
            FileInputStream fileInputStream = new FileInputStream("doc/bs.properties");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
            BufferedReader br=new BufferedReader(inputStreamReader);
            String line="";
            while((line=br.readLine())!=null) {
                String[] params = line.split(",");
                URL url = new URL(rootURL + params[0]);
                URLConnection URLconnection = url.openConnection();
                URLconnection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(URLconnection.getInputStream()));
                String htmlLine = "";
                String result = "";
                while ((htmlLine = in.readLine()) != null) {
                    result += htmlLine;
                }
                String pattern = "<{1}a{1}\\s{1}href=\"{1}([0-9a-zA-Z]{6})\"{1}\\s{1}title=\"{1}.{0,30}\">{1}(.)</a>{1}";

                // 创建 Pattern 对象
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(result);
                File file = new File("doc/" + params[0] + ".properties");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                while (m.find()) {
                    System.out.println("Found value: " + m.group(2));
                    bw.write(m.group(2) + "\t\n");
                }
                bw.close();
                osw.close();
                fileOutputStream.close();
            }
            br.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
