package frame.contents;

import Dao.UserDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//用户管理：用户增删查改
public class UserPanel extends JPanel {
    public UserPanel(){
        this.setLayout(new BorderLayout());
        //顶部增删改查操作栏
        JPanel topPanel = new JPanel();
        JTextField nameField = new JTextField(10); //查询条件
        topPanel.add(new JLabel("用户名"));
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
        TableModel tableModel = new DefaultTableModel(UserDao.getAllUsers(), UserDao.columnNames);//数据表
        table.setModel(tableModel);

        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(table,BorderLayout.CENTER);

        //修改面板
        JPanel editPanel = new JPanel();
        editPanel.add(new JLabel("设置用户名: "));
        JTextField editName = new JTextField(10);
        editPanel.add(editName);

        editPanel.add(new JLabel("设置密码: "));
        JPasswordField editPassword = new JPasswordField(10);
        editPanel.add(editPassword);

        editPanel.add(new JLabel("设置角色: "));
        String[] options = {"管理员", "档案员", "浏览者"}; //映射0，1，2
        JComboBox<String> comboBox = new JComboBox<>(options);  editPanel.add(comboBox);
        JButton editBtn = new JButton("修改");editPanel.add(editBtn);
        JButton addBtn = new JButton("添加"); editPanel.add(addBtn);
        JButton delBtn = new JButton("删除"); editPanel.add(delBtn);

        //top面板和表格面板在UserPanel中的布局
        this.add(topPanel,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(editPanel,BorderLayout.SOUTH);

        //绑定事件
            //条件模糊查询
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //重新初始化tablemodel，刷新表格
                TableModel tableModel = new DefaultTableModel(UserDao.search(nameField.getText()),
                        UserDao.columnNames);
                table.setModel(tableModel);
            }
        });
            //修改用户
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();//获取当前选中的行号
                if(rowNum<=-1){
                    return;
                }
                String rename = editName.getText();
                String rpwd = new String (editPassword.getPassword());
                int role = comboBox.getSelectedIndex();

                int selectRow = table.getSelectedRow();//获取当前选中的行号
                if(selectRow >-1){
                    if(rename.equals("")) rename = (String) table.getValueAt(selectRow,0);
                    if(rpwd.equals(""))rpwd = (String) table.getValueAt(selectRow,1);
                    //更新数据
                    UserDao.update((String) table.getValueAt(selectRow,0),//依据用户名查询
                            rename, rpwd,role);//更新值
                    //刷新表
                    table.setModel(new DefaultTableModel(UserDao.getAllUsers(),
                            UserDao.columnNames));
                }
            }
        });
            //添加用户
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String rename = editName.getText();
                String rpwd = new String (editPassword.getPassword());
                int role = comboBox.getSelectedIndex();
                //添加数据
                UserDao.add(rename, rpwd,role);
                //刷新表
                table.setModel(new DefaultTableModel(UserDao.getAllUsers(),
                        UserDao.columnNames));
            }
        });

            //删除用户
        delBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();//获取当前选中的行号
                if(rowNum > -1){
                    UserDao.del((String) table.getValueAt(rowNum,0));
                    //刷新表
                    table.setModel(new DefaultTableModel(UserDao.getAllUsers(),
                            UserDao.columnNames));
                }
            }
        });
    }
}
