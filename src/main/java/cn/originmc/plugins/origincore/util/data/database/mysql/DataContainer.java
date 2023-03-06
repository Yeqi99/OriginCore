package cn.originmc.plugins.origincore.util.data.database.mysql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DataContainer {
    private String id;
    private List<Data> data=new ArrayList<>();
    private Connection connection;
    public DataContainer(String id,Connection connection){
        setId(id);
        setConnection(connection);
    }

    /***
     * 从Mysql连接中获取数据
     */
    public void load(){

    }

    /***
     * 将内存中的数据更新到MySQL中
     */
    public void updateToMySQL(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
