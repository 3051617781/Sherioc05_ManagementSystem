package frame;
import Dao.UserDao;
import pojo.User;
import util.SystemConstants;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

        this.add(new JLabel("用户名"));
        JTextField nameField = new JTextField(10);
        this.add(nameField);

        this.add(new JLabel("密码"));
        JPasswordField pwdField  = new JPasswordField(10);
        this.add(pwdField);


        JButton loginButton = new JButton("login");
        this.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = nameField.getText();
                String password = String.valueOf(pwdField.getPassword());
                //初始化用户和界面
                User user = null;
                JPanel panel = null;
                user = UserDao.getUserByName(username);

                if(user == null){
                    JOptionPane.showMessageDialog(loginButton.getParent(),"无该用户!","system show", JOptionPane.WARNING_MESSAGE);
                }else if(!user.getPassword().equals(password)){
                    JOptionPane.showMessageDialog(loginButton.getParent(),"密码错误!","system show", JOptionPane.WARNING_MESSAGE);
                }else {//成功登录
                    MainFrame.user = user;
                    MainFrame.setContentPanel(new SystemPanel());
                }
            }
        });
    }
}
