package client;
import common.pojo.User;
import common.util.SystemConstants;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;



    public User user; // 记录当前登录用户
    private JFrame frame; // 每个客户端的窗口

    public Client() throws Exception {
        socket = new Socket(SystemConstants.HOST, SystemConstants.PORT);
        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("client start");
        frame = new JFrame("LIBRARY SYSTEM");
        frame.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new LoginPanel(this)); // 初始化为登录界面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setContentPanel(JPanel panel){
        frame.setContentPane(panel);
    }
}
