package common.util;

import java.sql.*;
import java.util.ArrayList;
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

    public static Object[][] docExecuteQuery(String sql){
        List<Object[]> list= new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection  = DBUtils.open();
            ps = DBUtils.preparedStatement(sql, null, connection);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Object[]{rs.getString("ID"),
                        rs.getString("filename"),
                        rs.getString("description"),
                        rs.getString("creator"),
                        rs.getTimestamp("timestamp")
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps, rs); //关闭资源
        }
        //封装为二维数组返回
        Object[][] result = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public static Object[][] userExecuteQuery(String sql ,List param){
        List<Object[]> list= new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection  = DBUtils.open();
            ps = DBUtils.preparedStatement(sql, param, connection);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Object[]{rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role")});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps, rs); //关闭资源
        }

        //封装为二维数组返回
        Object[][] result = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
            switch ((int)result[i][2]){
                case 0: result[i][2] = "管理员"; break;
                case 1: result[i][2] = "档案员"; break;
                case 2: result[i][2] = "浏览者"; break;
            }
        }
        return result;
    }
}
