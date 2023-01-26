package cn.originmc.plugins.origincore.util.random;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.list.ListUtil;

import java.util.*;

public class Randomizer {
    /**
     * 获取区间内随机一个整型数值
     * @param startInt 起始界
     * @param endInt 结束界
     * @return 随机整数
     */
    public static int getRandom(int startInt,int endInt){
        Random r=new Random();
        return r.nextInt(endInt-startInt+1)+startInt;
    }
    public static List<Object> randomObjects(Map<Object,Double> objectMap,int outputSize){
        Map<Object,Double> map=new HashMap<>();
        List<Object> objects=new ArrayList<>();
        for (int i=0;i<outputSize;i++){
            double amount=0;
            map.clear();
            for (Map.Entry<Object, Double> entry : objectMap.entrySet()) {
                amount+=entry.getValue();
                map.put(entry.getKey(),amount);
            }
            double randomNum=Randomizer.getDoubleRandom(0,amount,2);
            for (Map.Entry<Object, Double> entry : map.entrySet()) {
                if (entry.getValue()>=randomNum){
                    objects.add(entry.getKey());
                    objectMap.remove(entry.getKey());
                    break;
                }
            }
        }
        return objects;
    }
    public static List<Object> randomObjects(Map<Object,Double> objectMap,int outputSize,int sign){
        Map<Object,Double> map=new HashMap<>();
        List<Object> objects=new ArrayList<>();
        for (int i=0;i<outputSize;i++){
            double amount=0;
            for (Map.Entry<Object, Double> entry : objectMap.entrySet()) {
                map.put(entry.getKey(),amount);
                amount+=entry.getValue();
            }
            double randomNum=Randomizer.getDoubleRandom(0,amount,sign);
            for (Map.Entry<Object, Double> entry : map.entrySet()) {
                if (entry.getValue()>=randomNum){
                    objects.add(entry.getKey());
                    objectMap.remove(entry.getKey());
                    break;
                }
            }
        }
        return objects;
    }
    /**
     * 获取指定长度的随机字符串
     * @param len 字符串长度
     * @param start ASCII起始界
     * @param end ASCII结束界
     * @return 随机字符串
     */
    public static String getRandomCode(int len,int start,int end){
        String reStr="";
        for(int i=0;i<len;i++){
            int iChar=getRandom(start,end);
            reStr+=(char)iChar;
        }
        return reStr;
    }

    /**
     * 获取双精度随机数
     * @param startDouble 起始界
     * @param endDouble 结束界
     * @param sign 保留小数
     * @return 随即双精度
     */
    public static double getDoubleRandom(double startDouble,double endDouble,int sign){
        Random r=new Random();
        String s=String.format("%."+sign+"f",r.nextDouble (endDouble-startDouble)+startDouble);
        return Double.parseDouble(s);
    }

    /**
     * 从格式化列表中随机选取一个结果
     * @param str 格式化字符串
     * @return 随机字符串
     */
    public static String getRandomFromStr(String str){
        List<String> strList= ListUtil.getListFromFormatStr(str);
        return strList.get(getRandom(0, strList.size()-1));
    }

    /**
     * 从格式化区间中获取随机值  例 10-30  10.5-20.5
     * @param str 格式化字符串
     * @param sign 保留小数
     * @return 随机字符串
     */
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

    /**
     * 随机逻辑值
     * @param chance 百分比概率
     * @param sign 概率判定时小数判定长度
     * @return 逻辑值
     */
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
