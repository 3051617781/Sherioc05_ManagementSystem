
// 管理员 修改用户、删除用户、新增用户、列出用户、下载文件、文件列表、修改密码、退出

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class Administrator extends User {

    Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        System.out.println("1-modifyUser 2-delUser 3-addUser 4-listUser 5-downloadFile 6-showFileList 7-changePassword 8-exit");
        switch (Main.scanner.nextLine()) {
            //修改用户
            case"1":{
                modifyUser();
                break;
            }
            //删除用户
            case"2":{
                delUser();
                break;
            }
            //增加用户
            case"3":{
                addUser();
                break;
            }
            //列出所有用户
            case"4":{
                listUser();
                break;
            }
            //下载文件
            case "5":{
                System.out.println("enter the file name:");
                String file = Main.scanner.nextLine();
                try {
                    downloadFile(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //文件列表
            case "6":{
                try {
                    showFileList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //修改密码
            case "7":{
                System.out.println("enter the new password:");
                String password = Main.scanner.nextLine();
                changeSelfInfo(password);
                break;
            }
            //退出
            case "8":{
                exitSystem();
                break;
            }
            default:
                System.out.println("Error enter!");
                break;
        }
    }

    // 修改用户
    public boolean modifyUser() {
        //修改用户
        System.out.println("enter the user name:");
        String name = Main.scanner.nextLine();
        System.out.println("enter the new password");
        String password = Main.scanner.nextLine();
        System.out.println("enter the new role");
        String role = Main.scanner.nextLine();

        try {
            if(DataProcessing.update(name, password, role)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    // 删除
    public boolean delUser() {
        System.out.println("enter the user name:");
        String name = Main.scanner.nextLine();

        try {
            if(DataProcessing.delete(name)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    // 增加
    public boolean addUser() {
        System.out.println("enter the user name:");
        String name = Main.scanner.nextLine();
        System.out.println("enter the user password:");
        String password = Main.scanner.nextLine();
        System.out.println("enter the user role:");
        String role = Main.scanner.nextLine();

        try {
            if(DataProcessing.insert(name, password, role)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }


    // 列出用户
    public void listUser() {
        Enumeration<User> users;
        try {
            users = DataProcessing.getAllUser();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        //使用while循环和hasMoreElements()方法来检查是否还有下一个用户对象可供遍历，如果有，就使用nextElement()方法获取下一个用户对象
        while(users.hasMoreElements()){
            User user = users.nextElement();
            System.out.println(user.toString()); 
        }
    }

}
