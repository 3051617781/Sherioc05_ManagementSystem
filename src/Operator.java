
//档案录入员 上传文件、下载文件、文件列表、修改密码、退出

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Hashtable;

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
                System.out.println("请输入档案号:");
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
        System.out.println("请输入源文件名：");
        String sourceName = Main.scanner.nextLine();
        // String name = 

        System.out.println("请输入档案号：");
        String id = Main.scanner.nextLine();
        System.out.println("请输入档案描述：");
        String description = Main.scanner.nextLine();
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 

        byte[] buffer = new byte[1024];
		File tempFile = new File(sourceName);
		String filename = tempFile.getName();

        //保存在hashtable
		try {
           if( DataProcessing.insertDoc(id,getName(),timestamp,description,filename)==false){
                System.out.println("上传hashtable失败！");
           }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


		try (BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile))) {
            BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(uploadPath + filename));

            while(true){
            	int byteRead = infile.read(buffer);
            	if(byteRead == -1)
            		break; //EOF
            	targetfile.write(buffer,0,byteRead);
            }
            infile.close();
            targetfile.close();
            
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
