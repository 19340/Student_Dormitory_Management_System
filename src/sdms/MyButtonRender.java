package sdms;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MyButtonRender implements TableCellRenderer { //自定义的表格渲染器
	private JButton bt;
    public MyButtonRender(String operation) {
    	bt=new JButton(operation);
    	bt.setFont(new Font("黑体",0,20));
    	bt.setForeground(Color.blue);
    	bt.setContentAreaFilled(false);
    	bt.setBorderPainted(false);
    	bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return bt;
    }
}