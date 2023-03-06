package cn.originmc.plugins.origincore.util.data.database.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlManager {
    public static boolean hook(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Connection getConnection(String address, String port, String databaseName, String userName, String passWord){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+address+":"+port+"/"+databaseName+"?serverTimezone=GMT";
            return DriverManager.getConnection(url,userName,passWord);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
