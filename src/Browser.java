
//档案浏览者 下载文件、文件列表、修改密码、退出

import java.io.IOException;
import java.sql.SQLException;

public class Browser extends User{

    Browser(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public void showMenu() {
        System.out.println("1-downloadFile 2-showFileList 3-changePassword 4-exit");
        switch (Main.scanner.nextLine()) {
            //下载文件
            case "1":{
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
            case "2":{
                try {
                    showFileList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //修改密码
            case "3":{
                System.out.println("enter the new password:");
                String password = Main.scanner.nextLine();
                changeSelfInfo(password);
                break;
            }
            //退出
            case "4":{
                exitSystem();
                break;
            }
            default:
                System.out.println("Error enter!");
                break;
        }
    }


    
}
