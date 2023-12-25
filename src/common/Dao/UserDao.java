package common.Dao;

import common.pojo.User;
import common.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class UserDao {

    public static final Object[]columnNames = {"姓名","密码","角色"};

    //登录：根据username查询返回用户
    public static User getUserByName(String username){
        String sql = "select username, password, role from user where username = ? ";
        List param = new ArrayList<>(); param.add(username);
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
        return DBUtils.userExecuteQuery(sql,null);
    }

    //查询用户：模糊查询
    public static Object[][] search( String text){
        String sql = "select username, password, role from user where username like concat('%',?,'%') ";
        List param = new ArrayList<>(); param.add(text);
        return DBUtils.userExecuteQuery(sql, param);
    }

    //修改用户：修改选定用户，无新值时使用原来值
    public static void update(String username_before, String username, String password, int role){
        String sql = "update user set username = ?, password = ?, role = ? where username = ?";
        List param = new ArrayList<>(); param.add(username); param.add(password); param.add(role); param.add(username_before);
        DBUtils.executeUpdate(sql, param);
    }

    //添加用户：依据用户名、密码、所选角色添加用户
    public static void add(String username, String password, int role){
        String sql = "insert into user(username,password,role) values(?,?,?)";
        List param = new ArrayList<>(); param.add(username); param.add(password); param.add(role);
        DBUtils.executeUpdate(sql, param);
    }

    //删除用户：删除所选定用户
    public static void del(String username){
        String sql = "delete from user where username = ?";
        List param = new ArrayList<>();
        param.add(username);
        DBUtils.executeUpdate(sql,param);
    }

    //修改密码：修改个人密码
    public static void changeSelfPassword(String username, String pwd) {
        String sql = "update user set password = ? where username = ?";
        List param = new ArrayList<>(); param.add(pwd); param.add(username);
        DBUtils.executeUpdate(sql,param);
    }
}
