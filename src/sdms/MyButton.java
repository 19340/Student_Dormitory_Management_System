package sdms;

import javax.swing.JButton;

public class MyButton extends JButton { //�Զ���İ�ť�࣬��ť����������ڵ�������
	private int row;
    private int column;
    public MyButton() {
    	
    }
    public MyButton(String name) {
        super(name);
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row=row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column=column;
    }
}