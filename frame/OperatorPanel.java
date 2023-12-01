package frame;

import util.SystemConstant;

import javax.swing.*;

public class OperatorPanel extends JPanel{
    public OperatorPanel(){
        this.setBounds(0,0, SystemConstant.FRAME_WIDTH,SystemConstant.FRAME_HEIGHT);
        this.add(new JLabel("档案员"));
    }
}
