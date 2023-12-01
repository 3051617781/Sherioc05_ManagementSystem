package frame;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.SystemConstant;
import src.User;
public class MainFrame {
    public static final JFrame frame = new JFrame("Management System");
    public static User user; //全局变量记录当前登录用户

    public static void main(String[] args) {
        frame.setSize(SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new LoginPanel()); //默认打开登录页面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //切换主窗口内容
    public static void setContent(JPanel panel) {
        frame.setContentPane(panel);
    }

}
