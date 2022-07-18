package sdms;

import java.io.*;
import javax.swing.*;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JTableToExcel { //���ڽ���񵼳���Excel�ļ�
	public static void export(File file, JTable table) { //��������
		try {
			WritableWorkbook workbook=null; //����������
			if (file.exists()) { //�ļ��Ѿ�����
				workbook=Workbook.createWorkbook(file, Workbook.getWorkbook(file));
			} else { //�ļ���������
				workbook=Workbook.createWorkbook(file);
			}
			// ����������
			WritableSheet sheet=workbook.createSheet("Sheet1", workbook.getNumberOfSheets());
			// ȡ��table������(rowNum), ����(colNum)
			int rowNum=table.getRowCount()-1;
			int colNum=table.getColumnCount()-1;
			// ��д����
			fillColumnName(sheet, table, colNum);
			// ��д���� 
			fillCell(sheet, table, rowNum, colNum);
			// д�빤����
			workbook.write();
			workbook.close();
			// �����ɹ���ʾ��
			int dialog=JOptionPane.showConfirmDialog(null, "��񵼳��ɹ����Ƿ����ڴ򿪣�", "��ʾ", JOptionPane.YES_NO_OPTION);
			if (dialog==JOptionPane.YES_OPTION) { //��Excel�ļ�
				Runtime.getRuntime().exec("cmd /c start \"\" \"" + file + "\"");
			}
		} catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(null, "��������ǰ��رչ�����");
	    } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
        }
    }
	
    private static void fillColumnName(WritableSheet sheet, JTable table, int colNum) throws WriteException { //��д����
        WritableFont font=new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); //��������
        WritableCellFormat format=new WritableCellFormat(font); //�����ʽ������
        format.setAlignment(Alignment.CENTRE); //ˮƽ������ʾ
        for(int i=0;i<colNum;i++) { //�����п�
        	sheet.setColumnView(i, 20);
        }
        for(int col=0; col<colNum; col++) {
        	Label colName=new Label(col, 0, table.getModel().getColumnName(col), format);
        	sheet.addCell(colName);
        }
    }
    
    private static void fillCell(WritableSheet sheet, JTable table, int rowNum, int colNum ) throws WriteException { //��д����
        WritableFont font=new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); //��������
        WritableCellFormat format=new WritableCellFormat(font); //�����ʽ������
        format.setAlignment(Alignment.CENTRE); //ˮƽ������ʾ
        for(int i=0; i<colNum; i++) { //��
			for(int j=1; j<=rowNum; j++) { //��
				String str=table.getValueAt(j-1, i).toString();
				Label labelN=new Label(i, j, str);
				try {
					sheet.addCell(labelN);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
    
}