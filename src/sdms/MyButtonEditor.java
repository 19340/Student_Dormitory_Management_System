package sdms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyButtonEditor extends DefaultCellEditor { //�Զ���ı��༭��
	private MyButton bt;
	private MyEvent event;
    public MyButtonEditor(String operation) {
        super(new JTextField());
        bt=new MyButton(operation);
    	bt.setFont(new Font("����",0,20));
    	bt.setForeground(Color.red.darker());
    	bt.setContentAreaFilled(false);
    	bt.setBorderPainted(false);
    	bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //�����Զ�����¼�������
            	event.invoke(e);
            }
        });
    }
    public MyButtonEditor(MyEvent e, String operation) {
        this(operation);
        this.event=e;
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { //��д�༭������������һ����ť��JTable
    	setClickCountToStart(0); //���1�δ����¼�
    	//�����������İ�ť���ڵ��к��зŽ�button����
        bt.setRow(row);
        bt.setColumn(column);
        return bt;
    }
}