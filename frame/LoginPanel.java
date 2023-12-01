package frame;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import dao.UserDao;
import src.User;
import util.SystemConstant;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel{
    public LoginPanel(){
        this.setBounds(0,0,SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
        this.add(new JLabel("用户名"));
        JTextField nameField = new JTextField(10);
        this.add(nameField);
        this.add(new JLabel("密码"));
        JPasswordField passwordField = new JPasswordField(10);
        this.add(passwordField);

        //权限区分
        JRadioButton userButton = new JRadioButton("普通用户");
        JRadioButton admRadioButton = new JRadioButton("管理员",true);
        JRadioButton operatorRadioButton = new JRadioButton("档案员");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(userButton);
        buttonGroup.add(admRadioButton);
        buttonGroup.add(operatorRadioButton);
        this.add(userButton);
        this.add(admRadioButton);
        this.add(operatorRadioButton);

        JButton loginButton = new JButton("登录");
        this.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());
                User user = null;
                JPanel panel = null;
                if(admRadioButton.isSelected()){
                    user = UserDao.ADMIN_USERS.get(username);
                    panel = new AdminPanel();
                }else if(userButton.isSelected()){
                    user = UserDao.USERS.get(username);
                    panel = new UserPanel();
                } else if (operatorRadioButton.isSelected()) {
                    user = UserDao.OPERATOR_USERS.get(username);
                    panel = new OperatorPanel();
                }

                if(user == null || !user.getPassword().equals(password)){
                    JOptionPane.showMessageDialog(loginButton.getParent(),"用户名或密码错误","系统提示", JOptionPane.WARNING_MESSAGE);
                }else {
                    MainFrame.setContent(panel);
                    MainFrame.user = user;
                }

            }
        });
        

    }
}
