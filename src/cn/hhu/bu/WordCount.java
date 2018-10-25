package cn.hhu.bu;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by buxiaofeng on 2017/5/26.
 */
public class WordCount {
    public Set<Character> wordSet=new HashSet<>();
    public Set<Character> countSet=new HashSet<>();
    public WordCount(String bsCode){
        try {
            FileInputStream fileInputStream = new FileInputStream("doc/"+bsCode+".properties");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            while((line=br.readLine())!=null) {
                wordSet.add(line.toCharArray()[0]);
            }
            br.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void getWord(String text){
        char []chars=text.toCharArray();
        for(char c:chars){
            if(wordSet.contains(c)){
                countSet.add(c);
                System.out.println(c);
            }
        }
    }
    public void readFile(File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, this.getFileCode(file));
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            while((line=br.readLine())!=null) {
                this.getWord(line);
                System.out.println(line);
            }
            br.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public String getFileCode(File file) {
        String code = null;
        try {
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            System.out.println(Integer.toHexString(p));
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xe8b0:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code="Unicode";
                    break;
                default:
                    code = "GBK";
            }
            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("code:" + code);
        return code;
    }
}
