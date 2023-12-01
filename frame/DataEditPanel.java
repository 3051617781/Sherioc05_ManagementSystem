package frame;


import util.SystemConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataEditPanel extends JInternalFrame {
    public DataEditPanel(Object selectedId){
        super("编辑数据",true,true,true,true);
        this.setSize(SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        //内容面板
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        //内部窗口的内容面板
        this.setContentPane(panel);
        Box boxBase = Box.createHorizontalBox();
        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(new JLabel("姓名"));
        boxLeft.add(boxLeft.createVerticalStrut(8));
        boxLeft.add(new JLabel("密码"));
        boxLeft.add(boxLeft.createVerticalStrut(8));
        boxLeft.add(new JLabel("确认密码"));
        boxLeft.add(boxLeft.createVerticalStrut(8));
        boxLeft.add(new JLabel("权限"));
        boxLeft.add(boxLeft.createVerticalStrut(30));

        Box boxRight = Box.createHorizontalBox();
        JTextField field1 = new JTextField(10);
        boxRight.add(field1);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field2 = new JTextField(10);
        boxRight.add(field2);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field3 = new JTextField(10);
        boxRight.add(field3);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field4 = new JTextField(10);
        boxRight.add(field4);
        boxRight.add(Box.createVerticalStrut(10));

        JButton btn = new JButton("确定");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        boxRight.add(btn);
        boxBase.add(boxLeft);
        boxBase.add(boxRight);
        panel.add(boxBase);
        //显示内部窗口
        this.setVisible(true);
    }
}
