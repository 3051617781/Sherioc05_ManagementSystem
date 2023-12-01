package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestFrame {
    public static void fun(String[] args) {
        //面板
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0,0,900,600);//位置

        //文本框,gettext
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        //单选按钮,getpassword
        JRadioButton r1 = new JRadioButton("单选1",true);
        JRadioButton r2 = new JRadioButton("单选2");
        ButtonGroup group = new ButtonGroup();
        group.add(r1); group.add(r2);
        panel.add(r1); panel.add(r2);
        //多选按钮,isselected
        JCheckBox c1 = new JCheckBox("多选1",true);
        JCheckBox c2 = new JCheckBox("多选2",true);
        panel.add(c1); panel.add(c2);
        //下拉框,getselecteditem
        JComboBox<String>comboBox = new JComboBox<>();
        comboBox.addItem("下拉1");
        comboBox.addItem("下拉2");
        panel.add(comboBox);

        //按钮
        JButton button = new JButton("按钮");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        panel.add(button);

        //表格
        JTable table = new JTable(){
            //重写iscelleditable方法，禁止直接编辑单元格
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置表格只能单选
        String[] columnNames = {"name","password","role"};
        String[][]data = {{"jack","123","admin"},{"mike","123","operator"}};
        TableModel tableModel = new DefaultTableModel(data, columnNames); //数据与tablemodel绑定
        table.setModel((tableModel));
        panel.add(table);
//        panel.add(table.getTableHeader(),BorderLayout.NORTH); //表头添加到面板顶部
//        panel.add(table,BorderLayout.CENTER); //表头添加到面板中间
        //表格-隐藏列
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc = tcm.getColumn(2);
        tc.setMaxWidth(0); tc.setPreferredWidth(0); tc.setWidth(0); tc.setMinWidth(0);
        /*
        int::table.getSelectedRow();table选中的行
        table.getValueAt(row, column);
             .setValueAt("",row,colunm);
        JOptionPane.showMessageDialog(topPanel,"提示内容","系统提示",JoptionPane.WARNING_MESSAGE);//系统提示框
        * */

        //菜单
        JMenuBar menuBar = new JMenuBar();
        JMenu parentMenu = new JMenu("主菜单");
        JMenuItem childMenu = new JMenuItem("子菜单");
        menuBar.add(parentMenu); parentMenu.add(childMenu);
        panel.add(menuBar);

        //内部面板
        JDesktopPane contentpanel = new JDesktopPane();

        //内部窗口
        JInternalFrame internalFrame = new JInternalFrame("subsystem",true,true,true,true);
        internalFrame.setSize(800,500);
        internalFrame.setVisible(true);
        contentpanel.removeAll();
        contentpanel.repaint();
        contentpanel.add(internalFrame);

        //窗体
        JFrame frame = new JFrame("system");
        frame.setSize(900,600);
        frame.setLayout(new BorderLayout());
//        frame.setContentPane(panel);
        frame.add(panel,BorderLayout.CENTER);//设置多个frame的panel的布局
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }




}
