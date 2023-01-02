package cn.originmc.plugins.origincore.util.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    /*
     * 格式化文本转化列表 例:  文本 ABC|BCD|DFE 列表长度为3 分别为ABC、BCD、DFE
     */
    public static List<String> getListFromFormatStr(String str){
        List<String> returnStr=new ArrayList<>();
        String someStr="";
        for (char c:str.toCharArray()){
            if (c=='|'){
                returnStr.add(someStr);
                someStr="";
                continue;
            }
            someStr+=c;
        }
        if (someStr!=""){
            returnStr.add(someStr);
        }
        return returnStr;
    }
}
