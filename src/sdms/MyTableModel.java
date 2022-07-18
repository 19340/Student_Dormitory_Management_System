package sdms;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel { //���ģ��
	private Object[] columnNames; //����
    private Object[][] rowData; //������
    int x; //�������ֱ��1~8������¥�����ᡢ�޹ܡ�ѧ����ס����Ϣ��������Ϣ��������Ϣ�������뷴��
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
    public boolean isCellEditable(int row,int column) { //���ڼ��пɲ���
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