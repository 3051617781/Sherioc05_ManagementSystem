package util;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.sql.*;
import java.util.List;

public class DBUtils {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/library_system";
    private static final String username = "root";
    private static final String password = "1234";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection open(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static PreparedStatement preparedStatement(String sql,List param,Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement(sql); //预编译防止sql注入
            if(param != null){
                for(int i = 0; i < param.size(); i++){
                    statement.setObject(i+1, param.get(i));
                }
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try{
            if(resultSet!= null) {
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(statement!=null)statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(connection!=null)connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int executeUpdate(String sql, List param){
        PreparedStatement statement = null;
        Connection connection =null;
        try{
            connection = DBUtils.open();
            statement = DBUtils.preparedStatement(sql, param, connection);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBUtils.closeAll(connection, statement, null);
        }
        return 0;
    }

}
