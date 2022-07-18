package sdms;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel { //表格模型
	private Object[] columnNames; //列名
    private Object[][] rowData; //行数据
    int x; //代表哪种表格，1~8：宿舍楼、宿舍、宿管、学生、住宿信息、出入信息、报修信息、建议与反馈
    public MyTableModel(Object[] columnNames,Object[][] rowData,int x) {
    	this.columnNames=columnNames;
    	this.rowData=rowData;
    	this.x=x;
    }
    public int getRowCount() {
        return rowData.length;
    }
    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }
    public boolean isCellEditable(int row,int column) { //表格第几列可操作
        if(x==1&&column==6)
        	return true;
        else if(x==2&&column==6)
        	return true;
        else if(x==3&&column==4)
        	return true;
        else if(x==4&&column==7)
        	return true;
        else if(x==5&&column==6)
        	return true;
        else if(x==6&&column==5)
        	return true;
        else if(x==7&&column==7)
        	return true;
        else if(x==8&&column==3)
        	return true;
        else  
        	return false;
    }
}