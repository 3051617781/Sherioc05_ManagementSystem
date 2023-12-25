package frame.contents;

import Dao.DocDao;
import Dao.UserDao;
import frame.MainFrame;
import pojo.Doc;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocPanel extends JPanel{
    public DocPanel(){
        this.setLayout(new BorderLayout());
        //顶部查询和下载操作栏
        JPanel topPanel = new JPanel();
        JTextField nameField = new JTextField(10); //查询条件
        topPanel.add(new JLabel("档案名"));
        topPanel.add(nameField);
        JButton searchBtn = new JButton("查询"); topPanel.add(searchBtn);
        JButton downloadBtn = new JButton("下载文件"); topPanel.add(downloadBtn);

        //表格面板
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable(){//表格不可编辑
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //单选
        TableModel tableModel = new DefaultTableModel(DocDao.getAllDocs(), DocDao.columnNames);//数据表
        table.setModel(tableModel);

        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(table,BorderLayout.CENTER);

        //修改面板
        JPanel docPanel = new JPanel();

        docPanel.add(new JLabel("ID"));
        JTextField id = new JTextField(5); docPanel.add(id);
        docPanel.add(new JLabel("档案描述"));
        JTextField description = new JTextField(30); docPanel.add(description);

        docPanel.add(new JLabel("档案名"));
        JTextField filename = new JTextField(10); filename.setEditable(false); docPanel.add(filename);

        JButton uploadBtn = new JButton("上传文件"); uploadBtn.setEnabled(MainFrame.user.getRole() == 1? true:false);docPanel.add(uploadBtn);
        JButton delBtn = new JButton("删除文件"); delBtn.setEnabled(MainFrame.user.getRole() == 1? true:false);docPanel.add(delBtn);

        //top面板和表格面板在的布局
        this.add(topPanel,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(docPanel,BorderLayout.SOUTH);

        //绑定事件
            //条件模糊查询
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            TableModel tableModel = new DefaultTableModel(DocDao.search(nameField.getText()),
                    DocDao.columnNames);
            table.setModel(tableModel);
            }
        });

        // TODO: 2023/12/24  下载文件
            //下载文件
        downloadBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                if (rowNum <= -1) {
                    return;
                }
                String downloadFilename = (String) table.getValueAt(rowNum, 1);

                JFrame frame = new JFrame("File Copy Example");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 只允许选择文件夹
                fileChooser.showOpenDialog(frame); // 显示对话框
                File selectedFolder = fileChooser.getSelectedFile(); // 获取选中的文件夹
                try {
                    // 指定要复制的文件路径（这里假设为 "srcFile.txt"）
                    Path srcPath = Paths.get(SystemConstants.FILE_PATH + downloadFilename);
                    // 目标文件路径为选中文件夹路径 + 源文件名
                    Path destPath = Paths.get(selectedFolder.getPath(), srcPath.getFileName().toString());
                    Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

            //上传文件
        uploadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //选择文件
                JFrame frame = new JFrame("File Uploader");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filename.setText(selectedFile.getName());
                    //上传文件到stroage保存
                    DocDao.saveFile(selectedFile);
                    //更新数据库信息,刷新表格
                    DocDao.upload(id.getText(), selectedFile.getName(), description.getText(), MainFrame.user.getUsername());
                    TableModel tableModel = new DefaultTableModel(DocDao.getAllDocs(),
                            DocDao.columnNames);
                    table.setModel(tableModel);

                } else {
                    filename.setText("No file selected");
                }

            }
        });


            //删除文件
        delBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                if (rowNum <= -1) {
                    return;
                }
                String delFilID = (String) table.getValueAt(rowNum, 0); //依据ID删除
                DocDao.delFile(delFilID);

                TableModel tableModel = new DefaultTableModel(DocDao.getAllDocs(),
                        DocDao.columnNames);
                table.setModel(tableModel);
            }
        });

    }
}

