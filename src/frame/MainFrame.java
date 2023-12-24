package frame;

import pojo.User;
import util.SystemConstants;

import javax.swing.*;

public class MainFrame {
    public static final JFrame frame = new JFrame("LIBRARY SYSTEM");
    public static User user ; //记录当前登录用户

    public static void main(String[] args) {
        frame.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new LoginPanel());//初始化为登录界面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setContentPanel(JPanel panel){
        frame.setContentPane(panel);
    }
}
