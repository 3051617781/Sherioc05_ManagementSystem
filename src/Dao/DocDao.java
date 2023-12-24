package Dao;

import util.DBUtils;
import util.SystemConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocDao {
    public static final Object[]columnNames = {"ID","档案名","档案描述","上传者","上传时间"};

    public static Object[][] getAllDocs(){
        String sql = "select ID, filename, description, creator, timestamp from doc";
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

    public static Object[][] search( String text){
        String sql = "select ID, filename, description, creator, timestamp from doc where filename like concat('%',?,'%') ";
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
                list.add(new Object[]{rs.getString("ID"),
                        rs.getString("filename"),
                        rs.getString("description"),
                        rs.getString("creator"),
                        rs.getTimestamp("timestamp")});
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

    public static void upload(String ID, String filename, String description, String creator ){
        String sql = "insert into doc(ID, filename, description, creator) values(?,?,?,?)";
        List param = new ArrayList<>();
        param.add(ID);
        param.add(filename);
        param.add(description);
        param.add(creator);
        DBUtils.executeUpdate(sql, param);
    }

    // 文件保存到硬盘
    public static void saveFile(File selectedFile){
        File targetFile = new File(SystemConstants.FILE_PATH + selectedFile.getName());
        try (FileInputStream inputStream = new FileInputStream(selectedFile);
             FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void downloadFile(File selectedFile) {
        File targetFile = new File(SystemConstants.FILE_PATH + selectedFile.getName());
        try (FileInputStream inputStream = new FileInputStream(selectedFile);
             FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


