package cn.hhu.bu;

/**
 * Created by buxiaofeng on 2017/5/29.
 */
public class BSItem {
    public String bsName;
    public String bsCode;
    public BSItem(String bsName,String bsCode){
        this.bsCode=bsCode;
        this.bsName=bsName;
    }
    public String toString(){
        return this.bsName;
    }

}
