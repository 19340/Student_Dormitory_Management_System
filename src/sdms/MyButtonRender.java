package sdms;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MyButtonRender implements TableCellRenderer { //�Զ���ı����Ⱦ��
	private JButton bt;
    public MyButtonRender(String operation) {
    	bt=new JButton(operation);
    	bt.setFont(new Font("����",0,20));
    	bt.setForeground(Color.blue);
    	bt.setContentAreaFilled(false);
    	bt.setBorderPainted(false);
    	bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return bt;
    }
}