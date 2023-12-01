package frame;

import util.SystemConstant;

import javax.swing.*;

public class UserPanel extends JPanel {
    public UserPanel(){
        this.setBounds(0,0, SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        this.add(new JLabel("普通用户"));
    }
}
