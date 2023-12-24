package frame.contents;

import Dao.DocDao;
import Dao.UserDao;
import frame.MainFrame;
import pojo.Doc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class DocPanel extends JPanel{
    public DocPanel(){
        this.setLayout(new BorderLayout());
        //顶部增删改查操作栏
        JPanel topPanel = new JPanel();
        JTextField nameField = new JTextField(10); //查询条件
        topPanel.add(new JLabel("档案名"));
        topPanel.add(nameField);
        JButton searchBtn = new JButton("查询"); topPanel.add(searchBtn);

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

        JButton uploadBtn = new JButton("上传文件"); docPanel.add(uploadBtn);
        JButton downloadBtn = new JButton("下载文件"); docPanel.add(downloadBtn);

        //top面板和表格面板在的布局
        this.add(topPanel,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(docPanel,BorderLayout.SOUTH);

        //绑定事件
            //条件模糊查询
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //重新初始化tablemodel，刷新表格
                TableModel tableModel = new DefaultTableModel(DocDao.search(nameField.getText()),
                        UserDao.columnNames);
                table.setModel(tableModel);
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
                            UserDao.columnNames);
                    table.setModel(tableModel);

                } else {
                    filename.setText("No file selected");
                }

            }
        });

        // TODO: 2023/12/24  下载文件
            //下载文件
//        downloadBtn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int rowNum = table.getSelectedRow();
//                if(rowNum<=-1){
//                    return;
//                }
//                String downloadFilename = (String) table.getValueAt(rowNum,1);
//
//                JFrame frame = new JFrame("File downloader");
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(frame);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    File downloadTarget = fileChooser.getSelectedFile();
//                    filename.setText(downloadTarget.getName());
//                    //从stroage下载文件到选定目录
//                    DocDao.downloadFile(downloadTarget, );
//                } else {
//                    filename.setText("No file selected");
//                }
//
//
//            }
//        });
    }
}

