package cn.originmc.plugins.origincore.util.data.database.mysql;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String,Object> data=new HashMap<>();

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
