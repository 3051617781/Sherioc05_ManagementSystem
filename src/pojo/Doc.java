package pojo;

import java.sql.Timestamp;

public class Doc {
    //必填
    private String ID; //档案号 0001
    private String filename; //文件名
    //选填
    private String description; //档案描述

    //系统自动生成
    private String creator; //创建者
    private Timestamp timestamp; //创建时间
//    LocalDate now = LocalDate.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String formattedDate = now.format(formatter);

    public Doc(String ID, String filename, String description, String creator) {
        this.ID = ID;
        this.filename = filename;
        this.description = description;
        this.creator = creator;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
