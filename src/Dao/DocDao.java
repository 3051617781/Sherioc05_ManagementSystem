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

    //查询文件：展示所有文件
    public static Object[][] getAllDocs(){
        String sql = "select ID, filename, description, creator, timestamp from doc";
        return DBUtils.docExecuteQuery(sql);
    }

    //查询文件：依据字段模糊查询
    public static Object[][] search( String text){
        String sql = "select ID, filename, description, creator, timestamp from doc where filename like concat('%',?,'%') ";
        return DBUtils.docExecuteQuery(sql);
    }

    //上传文件：更新数据库表
    public static void upload(String ID, String filename, String description, String creator ){
        String sql = "insert into doc(ID, filename, description, creator) values(?,?,?,?)";
        List param = new ArrayList<>();
        param.add(ID); param.add(filename); param.add(description); param.add(creator);
        DBUtils.executeUpdate(sql, param);
    }

    //上传文件：文件保存到硬盘
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

    //删除文件
    public static void delFile(String ID){
        String sql = "delete from doc where ID = ?";
        List param = new ArrayList<>(); param.add(ID);
        DBUtils.executeUpdate(sql,param);

    }

}


