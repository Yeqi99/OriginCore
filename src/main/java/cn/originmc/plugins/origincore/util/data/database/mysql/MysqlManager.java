package cn.originmc.plugins.origincore.util.data.database.mysql;


import javax.swing.table.TableRowSorter;
import java.sql.*;

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
    public static boolean hasData(String name,Connection connection){
        boolean flag=false;
        try {
            DatabaseMetaData meta=connection.getMetaData();
            String[] type ={"TABLE"};
            ResultSet rs=meta.getTables(null,null,name,type);
            flag=rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
