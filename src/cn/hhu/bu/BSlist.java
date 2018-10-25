package cn.hhu.bu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by buxiaofeng on 2017/5/29.
 */
public class BSlist {
    public HashSet<BSItem> bsSet=new HashSet<>();
    public BSlist(int bh){
        try{
            FileInputStream fileInputStream = new FileInputStream("doc/bh" +bh+".properties");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            while((line=br.readLine())!=null) {
                String [] bs=line.split(",");
                BSItem bsItem=new BSItem(bs[1],bs[0]);
                bsSet.add(bsItem);
            }
            br.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
