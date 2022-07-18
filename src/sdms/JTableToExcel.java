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

public class JTableToExcel { //用于将表格导出成Excel文件
	public static void export(File file, JTable table) { //导出方法
		try {
			WritableWorkbook workbook=null; //创建工作薄
			if (file.exists()) { //文件已经存在
				workbook=Workbook.createWorkbook(file, Workbook.getWorkbook(file));
			} else { //文件还不存在
				workbook=Workbook.createWorkbook(file);
			}
			// 创建工作表
			WritableSheet sheet=workbook.createSheet("Sheet1", workbook.getNumberOfSheets());
			// 取得table的行数(rowNum), 列数(colNum)
			int rowNum=table.getRowCount()-1;
			int colNum=table.getColumnCount()-1;
			// 填写列名
			fillColumnName(sheet, table, colNum);
			// 填写数据 
			fillCell(sheet, table, rowNum, colNum);
			// 写入工作表
			workbook.write();
			workbook.close();
			// 导出成功提示框
			int dialog=JOptionPane.showConfirmDialog(null, "表格导出成功！是否现在打开？", "提示", JOptionPane.YES_NO_OPTION);
			if (dialog==JOptionPane.YES_OPTION) { //打开Excel文件
				Runtime.getRuntime().exec("cmd /c start \"\" \"" + file + "\"");
			}
		} catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(null, "导出数据前请关闭工作表！");
	    } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, "导出失败！");
        }
    }
	
    private static void fillColumnName(WritableSheet sheet, JTable table, int colNum) throws WriteException { //填写列名
        WritableFont font=new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); //定义字体
        WritableCellFormat format=new WritableCellFormat(font); //定义格式化对象
        format.setAlignment(Alignment.CENTRE); //水平居中显示
        for(int i=0;i<colNum;i++) { //设置列宽
        	sheet.setColumnView(i, 20);
        }
        for(int col=0; col<colNum; col++) {
        	Label colName=new Label(col, 0, table.getModel().getColumnName(col), format);
        	sheet.addCell(colName);
        }
    }
    
    private static void fillCell(WritableSheet sheet, JTable table, int rowNum, int colNum ) throws WriteException { //填写数据
        WritableFont font=new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); //定义字体
        WritableCellFormat format=new WritableCellFormat(font); //定义格式化对象
        format.setAlignment(Alignment.CENTRE); //水平居中显示
        for(int i=0; i<colNum; i++) { //列
			for(int j=1; j<=rowNum; j++) { //行
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