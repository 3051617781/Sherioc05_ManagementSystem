package frame;

import com.sun.java.accessibility.util.EventID;
import dao.DataDao;
import util.SystemConstant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataTablePanel extends JInternalFrame {
    public DataTablePanel(){
        super("数据列表",true,true,true,true);
        this.setSize(SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        JTable table = new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//单选
        TableModel tableModel = new DefaultTableModel(DataDao.toList(DataDao.data), DataDao.columnNames);
        table.setModel(tableModel);

        //顶方操作工具栏
        JPanel topPanel = new JPanel();
        //查询输入框
        JTextField nameField = new JTextField(20);
        topPanel.add(new JLabel("查询条件："));
        topPanel.add(nameField);
        //按钮
        JButton searchButton = new JButton("查询");
        topPanel.add(searchButton);
        JButton addButton = new JButton("添加");
        topPanel.add(addButton);
        JButton editButton = new JButton("修改");
        topPanel.add(editButton);
        JButton delButton = new JButton("删除");
        topPanel.add(delButton);

        //表格面板
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        panel.add(table.getTableHeader(),BorderLayout.NORTH);//表头->表格面板
        panel.add(table,BorderLayout.CENTER);//表格->表格面板

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //查询后，重置tableModel
                TableModel tableModel = new DefaultTableModel(DataDao.search(0,nameField.getText()),//依据name查询
                        DataDao.columnNames);
                table.setModel(tableModel);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        delButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        //总布局
        this.add(topPanel,BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
