package cn.originmc.plugins.origincore.util.text;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class TextProcessing {
    public static BaseComponent[] Merge(BaseComponent[] a, BaseComponent[] b) {
        int index=0;
        BaseComponent[] all=new BaseComponent[a.length+b.length];
        for(BaseComponent bc:a){
            all[index]=bc;
            index++;
        }
        for(BaseComponent bc:b){
            all[index]=bc;
            index++;
        }
        return all;
    }
    public static BaseComponent[] MergeList(List<BaseComponent[]> list) {
        if (list.size() == 0) {
            return null;
        }
        BaseComponent[] returnBC = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            returnBC = Merge(returnBC, list.get(i));
        }
        return returnBC;
    }
    public static BaseComponent[] createBaseComponent(String str){
        return TextComponent.fromLegacyText(Color.toColor(str));
    }
    public static boolean isRepeat(List<String> strList,String str){
        for(String s:strList){
            if (s.equals(str)){
                return true;
            }
        }
        return false;
    }
    public static boolean hasText(List<String> textList,String text){
        for (String s:textList){
            if (s.contains(text)){
                return true;
            }
        }
        return false;
    }
    public static List<String> replaceList(List<String> textList, String oldText, String newText){
        while(hasText(textList,oldText)){
            for (int i=0;i<textList.size();i++){
                if (textList.get(i).contains(oldText)){
                    textList.set(i,textList.get(i).replace(oldText,newText));
                }
            }
        }
        return textList;
    }
}
