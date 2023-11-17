
//档案录入员 上传文件、下载文件、文件列表、修改密码、退出

import java.io.IOException;
import java.sql.SQLException;

public class Operator extends User{

    Operator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        System.out.println("1-uploadFile 2-downloadFile 3-showFileList 4-changePassword 5-exit");
        switch (Main.scanner.nextLine()) {
            case"1":{
                uploadFile();
                break;
            }
            //下载文件
            case "2":{
                System.out.println("enter the file name:");
                String file = Main.scanner.nextLine();
                try {
                    downloadFile(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //文件列表
            case "3":{
                try {
                    showFileList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //修改密码
            case "4":{
                System.out.println("enter the new password:");
                String password = Main.scanner.nextLine();
                changeSelfInfo(password);
                break;
            }
            //退出
            case "5":{
                exitSystem();
                break;
            }
            default:
                System.out.println("Error enter!");
                break;
        }
    }
    
    //上传文件
    public boolean uploadFile(){
        System.out.println("enter the file path:");
        String path = Main.scanner.nextLine();
        
		return true;
    }
}
