package client;

import common.util.SystemConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SystemPanel extends JPanel {
    private static JPanel centerPanel = new JPanel();//中间内容面板

    public SystemPanel(Client client){
        this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        //菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("用户管理"); menuBar.add(userMenu);
        JMenu docMenu = new JMenu("档案管理"); menuBar.add(docMenu);
        JMenu selfMenu = new JMenu("个人管理"); menuBar.add(selfMenu);
        JMenuItem passwordItem = new JMenuItem("修改密码"); selfMenu.add(passwordItem);
        JMenuItem logoutItem = new JMenuItem("退出登录"); selfMenu.add(logoutItem);
        menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);//菜单栏
        this.add(menuBar, BorderLayout.NORTH);
            //菜单选项根据role设置可否点击
        switch (client.user.getRole()){
            case 0: break;
            case 1: userMenu.setEnabled(false); break;
            case 2: userMenu.setEnabled(false); break;
        }

        //中心面板
        this.add(centerPanel,BorderLayout.CENTER);

        //欢迎界面
        JPanel welcome = new JPanel();
        welcome.add(new JLabel("欢迎来到文件管理系统"));
        welcome.add(new JLabel(" 当前用户： "+ client.user.getUsername()));
        updateCenterPanel(welcome);

        //绑定点击事件
            //用户管理
        userMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateCenterPanel(new UserPanel(client));
            }
        });
            //档案管理
        docMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateCenterPanel(new DocPanel(client));
            }
        });
            //个人管理
        passwordItem.addMouseListener(new MouseAdapter() { //修改密码
            @Override
            public void mousePressed(MouseEvent e) {
                updateCenterPanel(new PasswordPanel(client));
            }
        });
        logoutItem.addMouseListener(new MouseAdapter() { //登出
            @Override
            public void mousePressed(MouseEvent e) {
                client.setContentPanel(new LoginPanel(client));//登出退回到登录界面
            }
        });
    }

    //更新中心panel内容
    public void updateCenterPanel(JPanel panel) {
        centerPanel.removeAll(); // 清除当前内容
        centerPanel.add(panel); // 添加新的组件
        centerPanel.revalidate(); // 重新验证组件
        centerPanel.repaint(); // 重新绘制组件
    }
}
