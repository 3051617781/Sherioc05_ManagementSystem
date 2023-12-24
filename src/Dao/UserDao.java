package Dao;

import pojo.User;
import util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class UserDao {

    public static final Object[]columnNames = {"姓名","密码","角色"};

    //登录：根据username查询返回用户
    public static User getUserByName(String username){
        String sql = "select username, password, role from user where username = ? ";
        List param = new ArrayList<>();
        param.add(username);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection  = DBUtils.open();
            ps = DBUtils.preparedStatement(sql, param, connection);
            rs = ps.executeQuery();
            if(rs.next()){//封装结果集到user对象，返回
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getInt("role"));
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps, rs); //关闭资源
        }
        return null;
    }

    //显示全部用户：查询所有用户
    public static Object[][] getAllUsers(){
        String sql = "select username, password, role from user";
        List<Object[]> list= new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection  = DBUtils.open();
            ps = DBUtils.preparedStatement(sql, null, connection);
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

    //按条件查询用户
    public static Object[][] search( String text){
        String sql = "select username, password, role from user where username like concat('%',?,'%') ";
        List param = new ArrayList<>();
        param.add(text);
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

    public static void update(String username_before, String username, String password, int role){
        String sql = "update user set username = ?, password = ?, role = ? where username = ?";
        List param = new ArrayList<>();
        param.add(username);
        param.add(password);
        param.add(role);
        param.add(username_before);
        DBUtils.executeUpdate(sql, param);
    }

    public static void add(String username, String password, int role){
        String sql = "insert into user(username,password,role) values(?,?,?)";
        List param = new ArrayList<>();
        param.add(username);
        param.add(password);
        param.add(role);
        DBUtils.executeUpdate(sql, param);
    }

    public static void del(String username){
        String sql = "delete from user where username = ?";
        List param = new ArrayList<>();
        param.add(username);
        DBUtils.executeUpdate(sql,param);
    }
}
