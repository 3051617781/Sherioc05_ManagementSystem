package frame;

import util.SystemConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPanel extends JPanel {
    public static JDesktopPane contentPanel = new JDesktopPane(); //内部窗口
    public AdminPanel(){
        this.setBounds(0,0, SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        contentPanel.setBounds(0,20,SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT-50);
        this.add(contentPanel, BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        //一级菜单
        JMenu userMenu = new JMenu("用户管理");
        JMenu fileMenu = new JMenu("档案管理");
        JMenu selfMenu = new JMenu("个人信息管理");
        menuBar.add(userMenu);
        menuBar.add(fileMenu);
        menuBar.add(selfMenu);
        //二级菜单-档案管理
        JMenuItem upLoadFile = new JMenuItem("上传文件");
        JMenuItem downLoadFile = new JMenuItem("下载文件");
        fileMenu.add(upLoadFile);
        fileMenu.add(downLoadFile);
        //二级菜单-个人信息管理
        JMenuItem change = new JMenuItem("修改信息");
        JMenuItem logout = new JMenuItem("登出");
        selfMenu.add(change);
        selfMenu.add(logout);

        //-------------绑定事件-----------
        //用户管理
        userMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new DataTablePanel());
            }
        });
        //档案管理

        //修改信息
        change.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SelfInfoPanel());
            }
        });
        //登出切换为登录界面，注意这里使用mousePressed而不是mouseClicked
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainFrame.setContent(new LoginPanel());
            }
        });

        //设置布局
        this.setLayout(new BorderLayout());
        menuBar.setBounds(0,0,SystemConstant.FRAME_WIDTH,50);//菜单栏
        this.add(menuBar,BorderLayout.NORTH);
    }

    //设置内部窗口
    public static void setContent(JInternalFrame internalFrame){
        internalFrame.setSize(SystemConstant.FRAME_WIDTH-100, SystemConstant.FRAME_HEIGHT-100);

        internalFrame.setVisible(true);
        contentPanel.removeAll(); //将当前容器中所有组件移除
        contentPanel.repaint(); //重新绘制面板
        contentPanel.add(internalFrame);
    }
}
