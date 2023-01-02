package cn.originmc.plugins.origincore.util.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatText {
    private Map<String,String> keyMap=new HashMap<>();
    private String originText;
    public FormatText(String text){
        setOriginText(text);
        getData();
    }
    public void getData(){
        keyMap.clear();
        StringBuilder key= new StringBuilder();
        StringBuilder value= new StringBuilder();
        boolean isValue=false;
        for (int i=0;i<originText.length();i++){
            char c=originText.charAt(i);
            if (c=='^'){
                if (originText.charAt(i+1)=='^'&isValue){
                    i++;
                }else {
                    isValue=true;
                    continue;
                }
            }
            if (c=='`'){
                if (originText.charAt(i+1)=='`'&isValue){
                    i++;
                }else {
                    isValue=false;
                    keyMap.put(key.toString(),value.toString());
                    key= new StringBuilder();
                    value= new StringBuilder();
                    continue;
                }
            }
            if (isValue){
                value.append(c);
            }else {
                key.append(c);
            }
        }
        keyMap.put(key.toString(), value.toString());
    }
    public boolean hasKey(String key){
        for (String s : getKeyList()) {
            if (s.equals(key)){
                return true;
            }
        }
        return false;
    }
    public void setKey(String key,String value){
        keyMap.put(key,value);
    }
    public String getFormatString(){
        String returnString="";
        for (String s : getKeyList()) {
            returnString+=s+"^"+getValue(s)+"`";
        }
        return returnString.substring(0,returnString.length()-1);
    }
    public String getValue(String key){
        for(Map.Entry<String,String> entry:keyMap.entrySet()){
            if(entry.getKey().equalsIgnoreCase(key)){
                return entry.getValue();
            }
        }
        return null;
    }
    public List<String> getKeyList(){
        List<String> keyList=new ArrayList<>();
        for(Map.Entry<String,String> entry:keyMap.entrySet()){
            keyList.add(entry.getKey());
        }
        return keyList;
    }
    public Map<String, String> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, String> keyMap) {
        this.keyMap = keyMap;
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
        getData();
    }
}
