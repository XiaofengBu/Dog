package cn.hhu.bu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by buxiaofeng on 2017/5/26.
 */
public class Main {
    public static void main(String []args){
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
        MainFrame mf=new MainFrame();

    }
}
