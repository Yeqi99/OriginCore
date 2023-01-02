package cn.originmc.plugins.origincore.util.random;

import cn.originmc.plugins.origincore.util.list.ListUtil;

import java.util.List;
import java.util.Random;

public class Randomizer {
    public static int getRandom(int startInt,int endInt){
        Random r=new Random();
        return r.nextInt(endInt-startInt+1)+startInt;
    }
    public static String getRandomCode(int len,int start,int end){
        String reStr="";
        for(int i=0;i<len;i++){
            int iChar=getRandom(start,end);
            reStr+=(char)iChar;
        }
        return reStr;
    }
    public static double getDoubleRandom(double startDouble,double endDouble,int sign){
        Random r=new Random();
        String s=String.format("%."+sign+"f",r.nextDouble (endDouble-startDouble)+startDouble);
        return Double.parseDouble(s);
    }

    //从格式化列表中随机选取一个
    public static String getRandomFromStr(String str){
        List<String> strList= ListUtil.getListFromFormatStr(str);
        return strList.get(getRandom(0, strList.size()-1));
    }

    //从格式化区间中获取随机值  例 10-30  10.5-20.5
    public static String getRandomFromSection(String str,int sign){
        String start="";
        String end="";
        boolean flag=false;
        for (char c:str.toCharArray()){
            if (!flag){
                if (c=='-'){
                    flag=true;
                    continue;
                }else {
                    start+=c;
                }
            }
            if (flag){
                end+=c;
            }
        }
        if(sign==0){
            return String.valueOf((int) getDoubleRandom(Double.parseDouble( start),Double.parseDouble(end),sign));
        }else {
            return String.valueOf(getDoubleRandom(Double.parseDouble( start),Double.parseDouble(end),sign));
        }
    }
    public static boolean randomBoolean(double chance,int sign){
        if (chance>=100){
            return true;
        }
        if (chance==0){
            return false;
        }
        double randomDouble=getDoubleRandom(0,100,sign);

        return randomDouble <= chance;
    }
}
