package client;

import common.Dao.UserDao;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//个人管理：修改密码
public class PasswordPanel extends JPanel{
    public PasswordPanel(Client client){
        this.add(new JLabel("请输入新密码"));
        JPasswordField pwdField = new JPasswordField(10); this.add(pwdField);
        this.add(new JLabel("请确认输入"));
        JPasswordField confirmField = new JPasswordField(10); this.add(confirmField);

        JButton btn1 = new JButton("确定"); this.add(btn1);
        JButton btn2 = new JButton("取消"); this.add(btn2);

        //绑定事件
        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String pwd = new String(pwdField.getPassword());
                String confirm = new String(confirmField.getPassword());
                if(!pwd.equals(confirm)){
                    JOptionPane.showMessageDialog(btn1.getParent(),"两次输入密码不一致，请重新输入!","system show", JOptionPane.WARNING_MESSAGE);
                }else{
                    UserDao.changeSelfPassword(client.user.getUsername(),pwd);
                    client.setContentPanel(new LoginPanel(client)); //重置密码后回到登录界面
                }
            }
        });

        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.setContentPanel(new SystemPanel(client));
            }
        });

    }
}
