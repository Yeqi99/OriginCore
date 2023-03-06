package cn.originmc.plugins.origincore.util.data.database.mysql;

import cn.originmc.plugins.origincore.OriginCore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MysqlData {
    private String address;
    private String port;
    private String databaseName;
    private String userName;
    private String passWord;
    private Connection connection;
    private List<DataContainer> dataContainers=new ArrayList<>();
    public MysqlData(String address,String port,String databaseName,String userName,String passWord){
        setAddress(address);
        setPort(port);
        setDatabaseName(databaseName);
        setUserName(userName);
        setPassWord(passWord);
    }

    /**
     * 将MySQL连接中所有的表读入内存
     */
    public void load(){
        dataContainers.clear();

    }

    /**
     *
     */
    public void updateToMySQL(){

    }
    public boolean connect(){
        setConnection(MysqlManager.getConnection(getAddress(),getPort(),getDatabaseName(),getUserName(),getPassWord()));
        return isConnect();
    }
    public boolean isConnect(){
        return getConnection() != null;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<DataContainer> getDataContainers() {
        return dataContainers;
    }

    public void setDataContainers(List<DataContainer> dataContainers) {
        this.dataContainers = dataContainers;
    }
}
