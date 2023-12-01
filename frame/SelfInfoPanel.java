package frame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//个人信息
public class SelfInfoPanel extends JInternalFrame {
    public SelfInfoPanel(){
        super("修改个人信息",true,true,true,true);
        //内部窗口的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);

        JPasswordField pwdFiled = new JPasswordField(10);
        panel.add(new JLabel("新密码"));
        panel.add(pwdFiled);
        JPasswordField pwdFiled2 = new JPasswordField(10);
        panel.add(new JLabel("确认新密码"));
        panel.add(pwdFiled2);
        JLabel prompt = new JLabel();
        panel.add(prompt);

        JButton bt = new JButton("确定");
        panel.add(bt);


        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String password = new String(pwdFiled.getPassword());
                String password_confirm = new String(pwdFiled2.getPassword());
                if(!password.equals(password_confirm)){
                    prompt.setText("两次密码不一致");
                }else{
                    MainFrame.user.setPassword(password);
                    MainFrame.setContent(new LoginPanel()); //返回登录界面
                }
            }
        });

        this.setVisible(true);
    }
}
