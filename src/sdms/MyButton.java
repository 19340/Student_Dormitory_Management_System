package sdms;

import javax.swing.JButton;

public class MyButton extends JButton { //自定义的按钮类，按钮存放了其所在的行列数
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