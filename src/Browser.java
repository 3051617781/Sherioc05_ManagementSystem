
//档案浏览者 下载文件、文件列表、修改密码、退出



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
                downloadFile(file);
            }
            //文件列表
            case "2":{
                showFileList();
            }
            //修改密码
            case "3":{
                System.out.println("enter the new password:");
                String password = Main.scanner.nextLine();
                changeSelfInfo(password);
            }
            //退出
            case "4":{
                exitSystem();
            }
            default:
                System.out.println("Error enter!");
                break;
        }
    }


    
}
