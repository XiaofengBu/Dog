package cn.hhu.bu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by buxiaofeng on 2017/5/26.
 */
public class Main {
    public static void main(String []args) throws IOException {
/*        Crawler crawler=new Crawler();
        //crawler.getbh();
        //crawler.getbs();
        //crawler.getHZ();
        try {
            FileInputStream fileInputStream = new FileInputStream("doc/obj.properties");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            while((line=br.readLine())!=null) {
                if(line==""||line=="\n")
                    continue;
                else{
                    System.out.print("\""+line.split(",")[0]+"\",");
                }
            }
            br.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/
//        List<String> testArray = Arrays.asList(new String []{"aa","test","cc","dd"});
//        testArray.stream().filter(word -> !word.equals("aa")).forEach(System.out::println);
        //testArray.forEach((string) -> System.out.println(string));
        //MainFrame mf=new MainFrame();
        FileInputStream fileInputStream = new FileInputStream("\\\\198.87.131.3\\project(2017)\\1\\1\\ylmfeng_W7x64_1208.iso");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while((line=br.readLine())!=null) {
            char c = 0x1b;
            String [] lines = line.split(String.valueOf(c));
            for(int i =0 ; i<lines.length;i++){
                System.out.print(lines[i]+"$$");
            }
            System.out.println();
        }

    }
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u001b", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

}
