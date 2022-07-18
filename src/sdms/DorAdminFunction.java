package sdms;

import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class DorAdminFunction implements ActionListener { //�޹ܹ�����
	String admin_num,floor_num;
	JFrame dorAdminJFrame=new JFrame("ѧ���������ϵͳ-----[�޹�ģʽ]");
	Container dorCon=dorAdminJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //���ø��ֹ���ҳ�������
	JLabel lb_topFunction=new JLabel(); //������Ϣ������ǰ����
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	JPanel pn_first=new JPanel(); //ѡ�1
	JPanel pn_first_1=new JPanel(); //ѡ�1_1
	JPanel pn_first_2=new JPanel(); //ѡ�1_2
	JPanel pn_second=new JPanel(); //ѡ�2
	JPanel pn_second_1=new JPanel(); //ѡ�2_1
	JPanel pn_second_2=new JPanel(); //ѡ�2_2
	Dormitory dormitory=null;
	Student student=null;
	Stay stay=null;
	InOut inout=null;
	Repair repair=null;
	Advice advice=null;
	
	public DorAdminFunction(String admin_num) { //�������
		this.admin_num=admin_num;
		try {
            String sql="SELECT floor_num FROM floor WHERE admin_num="+admin_num; //SQL��䣬��ѯ���޹ܹ��������¥
            PreparedStatement ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            if(rs.next()) //�����޹��й��������¥�����ȡ��Ӧ����¥��
            	floor_num=rs.getString("floor_num");
            else
            	floor_num="";
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		dorAdminJFrame.setSize(1300,800);
		dorAdminJFrame.setLocationRelativeTo(null);
		dorAdminJFrame.setResizable(false);
		dorAdminJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dorAdminJFrame.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn_topUser=new JPanel(); //������Ϣ����������
		pn_topUser.setBackground(Color.white);
		pn_topUser.setBounds(0,0,250,30);
		JLabel lb_topUser=new JLabel("�޹ܣ�"+admin_num);
		lb_topUser.setFont(new Font("����",0,18));
		lb_topUser.setForeground(Color.blue);
		pn_topUser.add(lb_topUser);
		JPanel pn_topFunction=new JPanel(); //������Ϣ������ǰ����
		pn_topFunction.setBackground(Color.white);
		pn_topFunction.setBounds(250,0,1045,30);
		lb_topFunction.setFont(new Font("����",0,20));
		lb_topFunction.setForeground(Color.black);
		pn_topFunction.add(lb_topFunction);
		JPanel pn_menu=new JPanel(); //�˵������й���ѡ��
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt_info=new JButton("������Ϣ"); //[������Ϣ]
		bt_info.setFont(new Font("����",0,20));
		bt_info.setContentAreaFilled(false);
		bt_info.setBounds(0,50,249,50);
		JButton bt_dormitory=new JButton("�������"); //[�������]
		bt_dormitory.setFont(new Font("����",0,20));
		bt_dormitory.setContentAreaFilled(false);
		bt_dormitory.setBounds(0,105,249,50);
		JButton bt_student=new JButton("ѧ������"); //[ѧ������]
		bt_student.setFont(new Font("����",0,20));
		bt_student.setContentAreaFilled(false);
		bt_student.setBounds(0,160,249,50);
		JButton bt_stay=new JButton("ס�޹���"); //[ס�޹���]
		bt_stay.setFont(new Font("����",0,20));
		bt_stay.setContentAreaFilled(false);
		bt_stay.setBounds(0,215,249,50);
		JButton bt_inout=new JButton("����Ǽ�"); //[����Ǽ�]
		bt_inout.setFont(new Font("����",0,20));
		bt_inout.setContentAreaFilled(false);
		bt_inout.setBounds(0,270,249,50);
		JButton bt_repair=new JButton("���ᱨ��"); //[���ᱨ��]
		bt_repair.setFont(new Font("����",0,20));
		bt_repair.setContentAreaFilled(false);
		bt_repair.setBounds(0,325,249,50);
		JButton bt_advice=new JButton("�����뷴��"); //[�����뷴��]
		bt_advice.setFont(new Font("����",0,20));
		bt_advice.setContentAreaFilled(false);
		bt_advice.setBounds(0,380,249,50);
		JButton bt_password=new JButton("�޸�����"); //[�޸�����]
		bt_password.setFont(new Font("����",0,20));
		bt_password.setContentAreaFilled(false);
		bt_password.setBounds(0,435,249,50);
		JButton bt_out=new JButton("�˳�"); //[�˳�]
		bt_out.setFont(new Font("����",0,20));
		bt_out.setContentAreaFilled(false);
		bt_out.setBounds(0,490,249,50);
		bt_info.addActionListener(this);
		bt_dormitory.addActionListener(this);
		bt_student.addActionListener(this);
		bt_stay.addActionListener(this);
		bt_inout.addActionListener(this);
		bt_repair.addActionListener(this);
		bt_advice.addActionListener(this);
		bt_password.addActionListener(this);
		bt_out.addActionListener(this);
		pn_menu.add(bt_info);
		pn_menu.add(bt_dormitory);
		pn_menu.add(bt_student);
		pn_menu.add(bt_stay);
		pn_menu.add(bt_inout);
		pn_menu.add(bt_repair);
		pn_menu.add(bt_advice);
		pn_menu.add(bt_password);
		pn_menu.add(bt_out);
		JPanel pn_welcome=new JPanel(); //��ӭҳ
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("��ӭʹ��");
		lb_welcome.setFont(new Font("����",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		dorCon.add(pn_topUser);
		dorCon.add(pn_topFunction);
		dorCon.add(pn_menu);
		dorCon.add(pn_function);
		pn_function.add(pn_welcome);
		dorAdminJFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { //����ѡ��Ĳ˵����ܣ�������Ӧ����
		if(e.getActionCommand().equals("�˳�")) {
			lb_tips.setText("�Ƿ��˳��޹�ģʽ��");
			choiceTips("");
		}
		else {
			pn_function.removeAll();
			dorAdminJFrame.repaint();
			if(e.getActionCommand().equals("������Ϣ")) {
				lb_topFunction.setText("[������Ϣ]");
				pn_function.add(info());
			}
			else if(e.getActionCommand().equals("�������")) {
				lb_topFunction.setText("[�������]");
				pn_function.add(dormitoryManage());
			}
			else if(e.getActionCommand().equals("ѧ������")) {
				lb_topFunction.setText("[ѧ������]");
				pn_function.add(studentManage());
			}
			else if(e.getActionCommand().equals("ס�޹���")) {
				lb_topFunction.setText("[ס�޹���]");
				pn_function.add(stayManage());
			}
			else if(e.getActionCommand().equals("����Ǽ�")) {
				lb_topFunction.setText("[����Ǽ�]");
				pn_function.add(inout());
			}
			else if(e.getActionCommand().equals("���ᱨ��")) {
				lb_topFunction.setText("[���ᱨ��]");
				pn_function.add(repairManage());
			}
			else if(e.getActionCommand().equals("�����뷴��")) {
				lb_topFunction.setText("[�����뷴��]");
				allAdvice();
			}
			else if(e.getActionCommand().equals("�޸�����")) {
				lb_topFunction.setText("[�޸�����]");
				pn_function.add(changePassword());
			}
			dorAdminJFrame.validate();
		}
	}
	
	public JPanel info() { //[������Ϣ]����
		String[] admin_info=new String[4]; //�޹���Ϣ
		String[] floor_info={"��","��","��","��","��"}; //���������¥��Ϣ
		try {
			String sql="SELECT * FROM admin WHERE admin_num="+admin_num; //SQL��䣬��ѯ�޹���Ϣ
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	admin_info[0]=rs.getString("admin_num");
            	admin_info[1]=rs.getString("name");
            	admin_info[2]=rs.getString("sex");
            	admin_info[3]=rs.getString("phone");
            }
            sql="SELECT * FROM floor WHERE admin_num="+admin_num; //SQL��䣬��ѯ���޹ܹ��������¥��Ϣ
            ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            if(rs.next()) { //�����޹��й��������¥�����ȡ��Ӧ����¥��Ϣ
            	floor_info[0]=rs.getString("floor_num");
            	floor_info[1]=rs.getString("layer_amount");
            	floor_info[2]=rs.getString("room_amount");
            	floor_info[3]=rs.getString("category");
            	floor_info[4]=rs.getString("sex");
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		JPanel pn_info=new JPanel();
		pn_info.setLayout(null);
		pn_info.setSize(1045,735);
		pn_info.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(700,130,200,200);
		JLabel[] lb=new JLabel[9];
		for(int i=0;i<9;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,30));
			lb[i].setBounds(150,97+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("��    �ţ� "+admin_info[0]);
		lb[1].setText("��    ���� "+admin_info[1]);
		lb[2].setText("��    �� "+admin_info[2]);
		lb[3].setText("��    ���� "+admin_info[3]);
		lb[4].setText("¥    �ţ� "+floor_info[0]);
		lb[5].setText("��    ���� "+floor_info[1]);
		lb[6].setText("�� �� ���� "+floor_info[2]);
		lb[7].setText("������� "+floor_info[3]);
		lb[8].setText("��ס�Ա� "+floor_info[4]);
		pn_info.add(lb_infoImage);
		return pn_info;
	}
	
	public JTabbedPane dormitoryManage() { //�������
		JTabbedPane tp_dormitory=new JTabbedPane();
		tp_dormitory.setFont(new Font("����",0,25));
		tp_dormitory.setBounds(0,0,1045,735);
		allDormitory();
		tp_dormitory.addTab(" ȫ�� ",pn_first);
		queryDormitoryInfo_1();
		tp_dormitory.addTab(" ��ѯ ",pn_second);
		return tp_dormitory;
	}
	
	public void allDormitory() { //���޹ܹ��������¥����������
		String[] columnNames={"¥��","¥��","�����","�ܴ�λ��","ʣ�ലλ��","����","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡdormitory����Ϣ
			String sql="SELECT * FROM dormitory WHERE floor_num='"+floor_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ����Ԫ������
            if(count==0) { //��dormitory����Ԫ��
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="��";
            }
            else { //��dormitory����Ԫ��
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //��ȡ���޹ܹ��������¥������������Ϣ
                	rowData[i][0]=rs.getString("floor_num"); //¥��
        			rowData[i][1]=rs.getString("layer"); //¥��
        			rowData[i][2]=rs.getString("room_num"); //�����
        			rowData[i][3]=rs.getString("bed_total"); //�ܴ�λ��
        			rowData[i][4]=rs.getString("bed_surplus"); //ʣ�ലλ��
        			rowData[i][5]=rs.getString("price"); //����
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"��������Ϣ��  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_add=new JButton("���",new ImageIcon("image/add.png")),bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("����",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,2));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitDormitoryInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),(String)table.getValueAt(button.getRow(),button.getColumn()-5),(String)table.getValueAt(button.getRow(),button.getColumn()-4),1,null);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_add.addActionListener(new ActionListener() { //���������Ϣ
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addDormitoryInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void visitDormitoryInfo(String floor_num, String layer, String room_num, int x, String query_sql) { //�鿴������Ϣ�����޸ĺ�ɾ��
		if(x==1) {
			pn_first_2.removeAll();
			pn_first_2.setLayout(null);
			pn_first_2.setBounds(0,0,1045,695);
			pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		}
		else {
			pn_second_2.removeAll();
			pn_second_2.setLayout(null);
			pn_second_2.setBounds(0,0,1045,695);
			pn_second_2.setBorder(BorderFactory.createEtchedBorder());
		}
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("�޸�������Ϣ",new ImageIcon("image/modify.png")),bt_delete=new JButton("ɾ��������Ϣ",new ImageIcon("image/delete.png")),
				bt_save=new JButton("����",new ImageIcon("image/save.png")),bt_cancel=new JButton("ȡ��",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("����",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("����",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("����",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("����",0,17));
		bt_cancel.setBounds(855,380,95,40);
		bt_cancel.setContentAreaFilled(false);
		bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_floorImage=new JLabel(new ImageIcon("image/floor.png"));
		lb_floorImage.setBounds(750,165,200,200);
		if(x==1) {
			pn_first_2.add(bt_back);
			pn_first_2.add(bt_modify);
			pn_first_2.add(bt_delete);
			pn_first_2.add(lb_floorImage);
		}
		else {
			pn_second_2.add(bt_back);
			pn_second_2.add(bt_modify);
			pn_second_2.add(bt_delete);
			pn_second_2.add(lb_floorImage);
		}
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("¥    �ţ�");
		lb[1].setText("¥    �㣺");
		lb[2].setText("�� �� �ţ�");
		lb[3].setText("�ܴ�λ����");
		lb[4].setText("ʣ�ലλ����");
		lb[5].setText("��    �ۣ�");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM dormitory WHERE floor_num='"+floor_num+"' AND layer="+layer+" AND room_num="+room_num; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	dormitory=new Dormitory(rs.getString("floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("bed_total"),rs.getString("bed_surplus"),rs.getString("price"));
            	tf[0].setText(dormitory.floor_num);
            	tf[1].setText(String.valueOf(dormitory.layer));
            	tf[2].setText(String.valueOf(dormitory.room_num));
            	tf[3].setText(String.valueOf(dormitory.bed_total));
            	tf[4].setText(String.valueOf(dormitory.bed_surplus));
            	tf[5].setText(String.valueOf(dormitory.price));
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				dormitory=null;
				if(x==1)
					allDormitory();
				else
					queryDormitoryInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //�޸�������Ϣ
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(true);
				tf[0].setEditable(false);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //ɾ��������Ϣ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ�ȷ��ɾ����������Ϣ��");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
				try {
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
						lb_tips.setText("����д������");
						functionTips();
					}
					else { //����д����
						//�������¥���Ƿ����
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //����¥�Ų����ڣ�������ʾ
			            	lb_tips.setText("����¥�Ų����ڣ�");
							functionTips();
			            }
			            else {
			            	//��������Ƿ��ظ�
			            	boolean flag=false; //���������Ƿ��ظ�
							sql="SELECT * FROM dormitory WHERE floor_num='"+tf[0].getText()+"' AND layer='"+tf[1].getText()+"' AND room_num='"+tf[2].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
				            rs=ps.executeQuery(sql);
				            flag=rs.next();
				            rs.first();
				            flag=flag&&!(tf[0].getText().equals(dormitory.floor_num))&&!(tf[1].getText().equals(String.valueOf(dormitory.layer)))&&!(tf[2].getText().equals(String.valueOf(dormitory.room_num)));
				            if(flag) { //�����ظ���������ʾ
				            	lb_tips.setText("�����ظ���");
								functionTips();
								flag=false;
				            }
				            else { //�������ݷ���Ҫ�󣬽��б���
				            	try {
				            		sql="UPDATE dormitory SET floor_num=?,layer=?,room_num=?,bed_total=?,bed_surplus=?,price=? WHERE floor_num='"+dormitory.floor_num+"' AND layer="+layer+" AND room_num="+room_num;
									ps=HomePage.connection.prepareStatement(sql);
									ps.setString(1,tf[0].getText()); //SQL����һ��?ֵ
									ps.setInt(2,Integer.parseInt(tf[1].getText())); //SQL���ڶ���?ֵ
									ps.setInt(3,Integer.parseInt(tf[2].getText())); //SQL��������?ֵ
									ps.setInt(4,Integer.parseInt(tf[3].getText())); //SQL�����ĸ�?ֵ
									ps.setInt(5,Integer.parseInt(tf[4].getText())); //SQL�������?ֵ
									ps.setInt(6,Integer.parseInt(tf[5].getText())); //SQL��������?ֵ
									ps.executeUpdate(); //���£�ִ���޸Ĳ���
				                    rs.close();
				                    ps.close();
				                    dormitory=new Dormitory(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
				                    for(int i=0;i<6;i++)
				    					tf[i].setEditable(false);
				                    if(x==1) {
				                    	pn_first_2.remove(bt_save);
					    				pn_first_2.remove(bt_cancel);
					    				dorAdminJFrame.repaint();
					    				pn_first_2.add(bt_modify);
					    				pn_first_2.add(bt_delete);
					    				dorAdminJFrame.validate();
				                    }
				                    else {
				                    	pn_second_2.remove(bt_save);
				                    	pn_second_2.remove(bt_cancel);
				                    	dorAdminJFrame.repaint();
					    				pn_second_2.add(bt_modify);
					    				pn_second_2.add(bt_delete);
					    				dorAdminJFrame.validate();
				                    }
				        		}catch(SQLException se){
				        			lb_tips.setText("���ݿ��������");
				    				functionTips();
				                    se.printStackTrace();
				                }catch(NumberFormatException ne){
				        			lb_tips.setText("����ת������");
				    				functionTips();
				                    ne.printStackTrace();
				                }
				            }
			            }
					}
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		bt_cancel.addActionListener(new ActionListener() { //ȡ��
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(false);
				tf[0].setText(dormitory.floor_num);
            	tf[1].setText(String.valueOf(dormitory.layer));
            	tf[2].setText(String.valueOf(dormitory.room_num));
            	tf[3].setText(String.valueOf(dormitory.bed_total));
            	tf[4].setText(String.valueOf(dormitory.bed_surplus));
            	tf[5].setText(String.valueOf(dormitory.price));
            	if(x==1) {
            		pn_first_2.remove(bt_save);
                	pn_first_2.remove(bt_cancel);
                	dorAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				dorAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
            		dorAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
                	dorAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			dorAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			dorAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			dorAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			dorAdminJFrame.validate();
		}
	}
	
	public void addDormitoryInfo() { //���������Ϣ
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("ȷ�����",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("����",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_floorImage=new JLabel(new ImageIcon("image/floor.png"));
		lb_floorImage.setBounds(750,205,200,200);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(lb_floorImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("¥    �ţ�");
		lb[1].setText("¥    �㣺");
		lb[2].setText("�� �� �ţ�");
		lb[3].setText("�ܴ�λ����");
		lb[4].setText("ʣ�ലλ����");
		lb[5].setText("��    �ۣ�");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==0) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
			if(i>=1)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allDormitory();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ�����������Ϣ
			public void actionPerformed(ActionEvent e) {
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д����
					dormitory=new Dormitory(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());				
					String sql; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
					try {
						//�������¥���Ƿ����
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //����¥�Ų����ڣ�������ʾ
			            	lb_tips.setText("����¥�Ų����ڣ�");
							functionTips();
			            }
						else {
							//��������Ƿ����
							sql="SELECT * FROM dormitory WHERE floor_num='"+tf[0].getText()+"' AND layer="+tf[1].getText()+" AND room_num="+tf[2].getText();
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(rs.next()) { //������ڣ��������ʾ
				            	lb_tips.setText("�����Ѿ����ڣ�");
								functionTips();
				            }
				            else { //�������ݷ���Ҫ�󣬽������
				            	try {
				            		sql="INSERT INTO dormitory VALUES(?,?,?,?,?,?)"; //SQL���
					            	ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
					            	ps.setString(1,dormitory.floor_num); //SQL����һ��?ֵ
									ps.setInt(2,dormitory.layer); //SQL���ڶ���?ֵ
									ps.setInt(3,dormitory.room_num); //SQL��������?ֵ
									ps.setInt(4,dormitory.bed_total); //SQL�����ĸ�?ֵ
									ps.setInt(5,dormitory.bed_surplus); //SQL�������?ֵ
									ps.setInt(6,dormitory.price); //SQL��������?ֵ
									ps.executeUpdate(); //���£�ִ�в������
									lb_tips.setText("������ӳɹ���");
									functionTips();
									dormitory=null;
									for(int i=0;i<6;i++) {
										tf[i].setEditable(false);
									}
									pn_first_2.remove(bt_confirm);
									dorAdminJFrame.repaint();
				                    rs.close();
				                    ps.close();
				        		}catch(SQLException se){
				        			lb_tips.setText("���ݿ��������");
				    				functionTips();
				                    se.printStackTrace();
				                }
				            }
						}
					}catch(SQLException se){
			            se.printStackTrace();
			        }
				}
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
	}
	
	public void queryDormitoryInfo_1() { //��ѯ������Ϣ
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("��ѯ",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("����",0,17));
		bt_query.setBounds(447,520,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_floorImage=new JLabel(new ImageIcon("image/floor.png"));
		lb_floorImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_floorImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("¥    �ţ�");
		lb[1].setText("¥    �㣺");
		lb[2].setText("�� �� �ţ�");
		lb[3].setText("�ܴ�λ����");
		lb[4].setText("ʣ�ലλ����");
		lb[5].setText("��    �ۣ�");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==0) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("���޹��������¥��"); //���޹��޹��������¥�����ѯ�����κν��
				else
					tf[i].setText(floor_num); //�޹ܽ��ܲ�ѯ����������¥
			}
			if(i>=1)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //��ѯ������Ϣ
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM dormitory";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//�޲�ѯ����
				}
				else { //�в�ѯ����
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND floor_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND layer="+tf[1].getText();
					if(!tf[2].getText().equals(""))
						sql=sql+" AND room_num="+tf[2].getText();
					if(!tf[3].getText().equals(""))
						sql=sql+" AND bed_total="+tf[3].getText();
					if(!tf[4].getText().equals(""))
						sql=sql+" AND bed_surplus="+tf[4].getText();
					if(!tf[5].getText().equals(""))
						sql=sql+" AND price="+tf[5].getText();
				}
				queryDormitoryInfo_2(sql);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void queryDormitoryInfo_2(String sql) { //��ѯ������Ϣ���
		String[] columnNames={"¥��","¥��","�����","�ܴ�λ��","ʣ�ലλ��","����","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //��ѯ�����������
		try {
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ��ѯ�����������
            if(count==0) { //���޷�������������
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="��";
            }
            else { //���з�������������
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //��ȡ����������������Ϣ
                	rowData[i][0]=rs.getString("floor_num"); //¥��
        			rowData[i][1]=rs.getString("layer"); //¥��
        			rowData[i][2]=rs.getString("room_num"); //�����
        			rowData[i][3]=rs.getString("bed_total"); //�ܴ�λ��
        			rowData[i][4]=rs.getString("bed_surplus"); //ʣ�ലλ��
        			rowData[i][5]=rs.getString("price"); //����
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" ����ѯ��"+count+"��������Ϣ�� ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,2));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitDormitoryInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),(String)table.getValueAt(button.getRow(),button.getColumn()-5),(String)table.getValueAt(button.getRow(),button.getColumn()-4),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryDormitoryInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public JTabbedPane studentManage() { //ѧ������
		JTabbedPane tp_student=new JTabbedPane();
		tp_student.setFont(new Font("����",0,25));
		tp_student.setBounds(0,0,1045,735);
		allStudent();
		tp_student.addTab(" ȫ�� ",pn_first);
		queryStudentInfo_1();
		tp_student.addTab(" ��ѯ ",pn_second);
		return tp_student;
	}
	
	public void allStudent() { //���޹ܹ��������¥������ѧ��
		String[] columnNames={"ѧ��","����","�Ա�","�꼶","Ժϵ","�༶","�Ƿ���ס","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡadminview_student��ͼ��Ϣ
			String sql="SELECT * FROM adminview_student WHERE floor_num='"+floor_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡѧ��Ԫ������
            if(count==0) { //��adminview_student��ͼ��Ԫ��
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="��";
            }
            else { //��adminview_student��ͼ��Ԫ��
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //��ȡ���޹ܹ��������¥������ѧ����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("sex"); //�Ա�
        			rowData[i][3]=rs.getString("grade")+"��"; //�꼶
        			rowData[i][4]=rs.getString("faculty"); //Ժϵ
        			rowData[i][5]=rs.getString("class"); //�༶
        			rowData[i][6]=rs.getString("yes_no"); //�Ƿ���ס
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"��ѧ����Ϣ��  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_add=new JButton("���",new ImageIcon("image/add.png")),bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("����",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,4));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitStudentInfo((String)table.getValueAt(button.getRow(),button.getColumn()-7),1,null);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_add.addActionListener(new ActionListener() { //���ѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addStudentInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void visitStudentInfo(String stu_num, int x, String query_sql) { //�鿴ѧ����Ϣ�����޸ĺ�ɾ��
		if(x==1) {
			pn_first_2.removeAll();
			pn_first_2.setLayout(null);
			pn_first_2.setBounds(0,0,1045,695);
			pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		}
		else {
			pn_second_2.removeAll();
			pn_second_2.setLayout(null);
			pn_second_2.setBounds(0,0,1045,695);
			pn_second_2.setBorder(BorderFactory.createEtchedBorder());
		}
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("�޸�ѧ����Ϣ",new ImageIcon("image/modify.png")),bt_delete=new JButton("ɾ��ѧ����Ϣ",new ImageIcon("image/delete.png")),
				bt_save=new JButton("����",new ImageIcon("image/save.png")),bt_cancel=new JButton("ȡ��",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("����",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("����",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("����",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("����",0,17));
		bt_cancel.setBounds(855,380,95,40);
		bt_cancel.setContentAreaFilled(false);
		bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,165,200,200);
		if(x==1) {
			pn_first_2.add(bt_back);
			pn_first_2.add(bt_modify);
			pn_first_2.add(bt_delete);
			pn_first_2.add(lb_infoImage);
		}
		else {
			pn_second_2.add(bt_back);
			pn_second_2.add(bt_modify);
			pn_second_2.add(bt_delete);
			pn_second_2.add(lb_infoImage);
		}
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    �룺");
		lb[2].setText("��    ����");
		lb[3].setText("��    ��");
		lb[4].setText("������ݣ�");
		lb[5].setText("��    ����");
		lb[6].setText("Ժ    ϵ��");
		lb[7].setText("��    ����");
		lb[8].setText("��    ����");
		lb[9].setText("�Ƿ���ס��");
		JTextField[] tf=new JTextField[10];
		for(int i=0;i<10;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM student WHERE stu_num='"+stu_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	student=new Student(rs.getString("stu_num"),rs.getString("password"),rs.getString("name"),rs.getString("sex"),rs.getString("birth"),rs.getString("grade"),rs.getString("faculty"),rs.getString("class"),rs.getString("phone"),rs.getString("yes_no"));
            	tf[0].setText(student.stu_num);
            	tf[1].setText(student.password);
            	tf[2].setText(student.name);
            	tf[3].setText(student.sex);
            	tf[4].setText(String.valueOf(student.birth));
            	tf[5].setText(String.valueOf(student.grade));
            	tf[6].setText(student.faculty);
            	tf[7].setText(student.clas);
            	tf[8].setText(student.phone);
            	tf[9].setText(student.yes_no);
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				student=null;
				if(x==1)
					allStudent();
				else
					queryStudentInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //�޸�ѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<10;i++)
					if(i!=9)
						tf[i].setEditable(true);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //ɾ��ѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ�ȷ��ɾ����ѧ����Ϣ��");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
				try {
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")||tf[6].getText().equals("")||tf[7].getText().equals("")||tf[8].getText().equals("")||tf[9].getText().equals("")) {
						lb_tips.setText("����д������");
						functionTips();
					}
					else { //����д����
						boolean flag=false; //����ѧ���Ƿ��ظ�
						//���ѧ���Ƿ��ظ�
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
			            rs=ps.executeQuery(sql);
			            flag=rs.next();
			            rs.first();
			            flag=flag&&!(tf[0].getText().equals(student.stu_num));
			            if(flag) { //ѧ���ظ���������ʾ
			            	lb_tips.setText("ѧ���ظ���");
							functionTips();
							flag=false;
			            }
			            else { //�������ݷ���Ҫ�󣬽��б���
			            	try {
			            		sql="UPDATE student SET stu_num=?,password=?,name=?,sex=?,birth=?,grade=?,faculty=?,class=?,phone=?,yes_no=? WHERE stu_num='"+student.stu_num+"'";
								ps=HomePage.connection.prepareStatement(sql);
								ps.setString(1,tf[0].getText()); //SQL����һ��?ֵ
								ps.setString(2,tf[1].getText()); //SQL���ڶ���?ֵ
								ps.setString(3,tf[2].getText()); //SQL��������?ֵ
								ps.setString(4,tf[3].getText()); //SQL�����ĸ�?ֵ
								ps.setInt(5,Integer.parseInt(tf[4].getText())); //SQL�������?ֵ
								ps.setInt(6,Integer.parseInt(tf[5].getText())); //SQL��������?ֵ
								ps.setString(7,tf[6].getText()); //SQL�����߸�?ֵ
								ps.setString(8,tf[7].getText()); //SQL���ڰ˸�?ֵ
								ps.setString(9,tf[8].getText()); //SQL���ھŸ�?ֵ
								ps.setString(10,tf[9].getText()); //SQL����ʮ��?ֵ
								ps.executeUpdate(); //���£�ִ���޸Ĳ���
			                    rs.close();
			                    ps.close();
			                    student=new Student(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText(),tf[6].getText(),tf[7].getText(),tf[8].getText(),tf[9].getText());
			                    for(int i=0;i<10;i++)
			    					tf[i].setEditable(false);
			                    if(x==1) {
			                    	pn_first_2.remove(bt_save);
				    				pn_first_2.remove(bt_cancel);
				    				dorAdminJFrame.repaint();
				    				pn_first_2.add(bt_modify);
				    				pn_first_2.add(bt_delete);
				    				dorAdminJFrame.validate();
			                    }
			                    else {
			                    	pn_second_2.remove(bt_save);
			                    	pn_second_2.remove(bt_cancel);
			                    	dorAdminJFrame.repaint();
				    				pn_second_2.add(bt_modify);
				    				pn_second_2.add(bt_delete);
				    				dorAdminJFrame.validate();
			                    }
			        		}catch(SQLException se){
			        			lb_tips.setText("���ݿ��������");
			    				functionTips();
			                    se.printStackTrace();
			                }catch(NumberFormatException ne){
			        			lb_tips.setText("����ת������");
			    				functionTips();
			                    ne.printStackTrace();
			                }
			            }
					}
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		bt_cancel.addActionListener(new ActionListener() { //ȡ��
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<10;i++)
					tf[i].setEditable(false);
				tf[0].setText(student.stu_num);
            	tf[1].setText(student.password);
            	tf[2].setText(student.name);
            	tf[3].setText(student.sex);
            	tf[4].setText(String.valueOf(student.birth));
            	tf[5].setText(String.valueOf(student.grade));
            	tf[6].setText(student.faculty);
            	tf[7].setText(student.clas);
            	tf[8].setText(student.phone);
            	tf[9].setText(student.yes_no);
            	if(x==1) {
            		pn_first_2.remove(bt_save);
                	pn_first_2.remove(bt_cancel);
                	dorAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				dorAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
            		dorAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
                	dorAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			dorAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			dorAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			dorAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			dorAdminJFrame.validate();
		}
	}
	
	public void addStudentInfo() { //���ѧ����Ϣ
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("ȷ�����",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("����",0,17));
		bt_confirm.setBounds(447,625,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		String[] sex= {"--------��ѡ��--------","          ��","          Ů"};
		JComboBox<String> cb_sex=new JComboBox<String>(sex);
		cb_sex.setFont(new Font("����",0,20));
		cb_sex.setBounds(392,235,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(lb_infoImage);
		pn_first_2.add(cb_sex);
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    �룺");
		lb[2].setText("��    ����");
		lb[3].setText("��    ��");
		lb[4].setText("������ݣ�");
		lb[5].setText("��    ����");
		lb[6].setText("Ժ    ϵ��");
		lb[7].setText("��    ����");
		lb[8].setText("��    ����");
		lb[9].setText("�Ƿ���ס��");
		JTextField[] tf=new JTextField[9];
		for(int i=0;i<9;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			if(i>=3)
				tf[i].setBounds(392,70+i*55+55,260,40);
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_first_2.add(tf[i]);
		}
		tf[8].setText("��");
		tf[8].setEditable(false);
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allStudent();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ�����ѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				String sex=null;
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")||tf[6].getText().equals("")||tf[7].getText().equals("")||tf[8].getText().equals("")||cb_sex.getSelectedIndex()==0) {
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д����
					if((String)cb_sex.getSelectedItem()=="          ��")
						sex="��";
					else if((String)cb_sex.getSelectedItem()=="          Ů")
						sex="Ů";
					student=new Student(tf[0].getText(),tf[1].getText(),tf[2].getText(),sex,tf[3].getText(),tf[4].getText(),tf[5].getText(),tf[6].getText(),tf[7].getText(),"��");				
					String sql; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
					try {
						//���ѧ���Ƿ����
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(rs.next()) { //ѧ�Ŵ��ڣ��������ʾ
			            	lb_tips.setText("ѧ���Ѿ����ڣ�");
							functionTips();
			            }
			            else { //�������ݷ���Ҫ�󣬽������
			            	try {
			            		sql="INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?,?)"; //SQL���
				            	ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
				            	ps.setString(1,student.stu_num); //SQL����һ��?ֵ
								ps.setString(2,student.password); //SQL���ڶ���?ֵ
								ps.setString(3,student.name); //SQL��������?ֵ
								ps.setString(4,student.sex); //SQL�����ĸ�?ֵ
								ps.setInt(5,student.birth); //SQL�������?ֵ
								ps.setInt(6,student.grade); //SQL��������?ֵ
								ps.setString(7,student.faculty); //SQL�����߸�?ֵ
								ps.setString(8,student.clas); //SQL���ڰ˸�?ֵ
								ps.setString(9,student.phone); //SQL���ھŸ�?ֵ
								ps.setString(10,student.yes_no); //SQL����ʮ��?ֵ
								ps.executeUpdate(); //���£�ִ�в������
								lb_tips.setText("ѧ����ӳɹ���");
								functionTips();
								student=null;
								for(int i=0;i<9;i++) {
									tf[i].setEditable(false);
								}
								cb_sex.setEnabled(false);
								pn_first_2.remove(bt_confirm);
								dorAdminJFrame.repaint();
			                    rs.close();
			                    ps.close();
			        		}catch(SQLException se){
			        			lb_tips.setText("���ݿ��������");
			    				functionTips();
			                    se.printStackTrace();
			                }
			            }
					}catch(SQLException se){
			            se.printStackTrace();
			        }
				}
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
	}
	
	public void queryStudentInfo_1() { //��ѯѧ����Ϣ
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("��ѯ",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("����",0,17));
		bt_query.setBounds(447,625,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_infoImage);
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    �룺");
		lb[2].setText("��    ����");
		lb[3].setText("��    ��");
		lb[4].setText("������ݣ�");
		lb[5].setText("��    ����");
		lb[6].setText("Ժ    ϵ��");
		lb[7].setText("��    ����");
		lb[8].setText("��    ����");
		lb[9].setText("�Ƿ���ס��");
		JTextField[] tf=new JTextField[10];
		for(int i=0;i<10;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			if(i==4||i==5)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //��ѯѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM adminview_student WHERE floor_num='"+floor_num+"' AND 1=1";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")&&tf[6].getText().equals("")&&tf[7].getText().equals("")&&tf[8].getText().equals("")&&tf[9].getText().equals("")) {
					//�޲�ѯ����
				}
				else { //�в�ѯ����
					if(!tf[0].getText().equals(""))
						sql=sql+" AND stu_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND password='"+tf[1].getText()+"'";
					if(!tf[2].getText().equals(""))
						sql=sql+" AND name='"+tf[2].getText()+"'";
					if(!tf[3].getText().equals(""))
						sql=sql+" AND sex='"+tf[3].getText()+"'";
					if(!tf[4].getText().equals(""))
						sql=sql+" AND birth="+tf[4].getText();
					if(!tf[5].getText().equals(""))
						sql=sql+" AND grade="+tf[5].getText();
					if(!tf[6].getText().equals(""))
						sql=sql+" AND faculty='"+tf[6].getText()+"'";
					if(!tf[7].getText().equals(""))
						sql=sql+" AND class='"+tf[7].getText()+"'";
					if(!tf[8].getText().equals(""))
						sql=sql+" AND phone='"+tf[8].getText()+"'";
					if(!tf[9].getText().equals(""))
						sql=sql+" AND yes_no='"+tf[9].getText()+"'";
				}
				queryStudentInfo_2(sql);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void queryStudentInfo_2(String sql) { //��ѯѧ����Ϣ���
		String[] columnNames={"ѧ��","����","�Ա�","�꼶","Ժϵ","�༶","�Ƿ���ס","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //��ѯ����ѧ������
		try {
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ��ѯ����ѧ������
            if(count==0) { //���޷���������ѧ��
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="��";
            }
            else { //���з���������ѧ��
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //��ȡ����������ѧ����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("sex"); //�Ա�
        			rowData[i][3]=rs.getString("grade")+"��"; //�꼶
        			rowData[i][4]=rs.getString("faculty"); //Ժϵ
        			rowData[i][5]=rs.getString("class"); //�༶
        			rowData[i][6]=rs.getString("yes_no"); //�Ƿ���ס
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" ����ѯ��"+count+"��ѧ����Ϣ�� ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,4));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitStudentInfo((String)table.getValueAt(button.getRow(),button.getColumn()-7),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryStudentInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public JTabbedPane stayManage() { //ס�޹���
		JTabbedPane tp_stay=new JTabbedPane();
		tp_stay.setFont(new Font("����",0,25));
		tp_stay.setBounds(0,0,1045,735);
		allStay();
		tp_stay.addTab(" ȫ�� ",pn_first);
		queryStayInfo_1();
		tp_stay.addTab(" ��ѯ ",pn_second);
		return tp_stay;
	}
	
	public void allStay() { //���޹ܹ��������¥������ס��
		String[] columnNames={"ѧ��","����","¥��","¥��","�����","��סʱ��","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡstudent_stayinfo��ͼ��Ϣ
			String sql="SELECT * FROM student_stayinfo WHERE floor_num='"+floor_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡס��Ԫ������
            if(count==0) { //��student_stayinfo��ͼ��Ԫ��
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="��";
            }
            else { //��student_stayinfo��ͼ��Ԫ��
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //��ȡ����ס����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //¥��
        			rowData[i][3]=rs.getString("layer"); //¥��
        			rowData[i][4]=rs.getString("room_num"); //�����
        			rowData[i][5]=rs.getString("time"); //��סʱ��
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"��ס����Ϣ��  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_add=new JButton("���",new ImageIcon("image/add.png")),bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("����",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,5));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitStayInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),1,null);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_add.addActionListener(new ActionListener() { //���ס����Ϣ
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addStayInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void visitStayInfo(String stu_num, int x, String query_sql) { //�鿴ס����Ϣ�����޸ĺ�ɾ��
		if(x==1) {
			pn_first_2.removeAll();
			pn_first_2.setLayout(null);
			pn_first_2.setBounds(0,0,1045,695);
			pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		}
		else {
			pn_second_2.removeAll();
			pn_second_2.setLayout(null);
			pn_second_2.setBounds(0,0,1045,695);
			pn_second_2.setBorder(BorderFactory.createEtchedBorder());
		}
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("�޸�ס����Ϣ",new ImageIcon("image/modify.png")),bt_delete=new JButton("ɾ��ס����Ϣ",new ImageIcon("image/delete.png")),
				bt_save=new JButton("����",new ImageIcon("image/save.png")),bt_cancel=new JButton("ȡ��",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("����",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("����",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("����",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("����",0,17));
		bt_cancel.setBounds(855,380,95,40);
		bt_cancel.setContentAreaFilled(false);
		bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,165,200,200);
		if(x==1) {
			pn_first_2.add(bt_back);
			pn_first_2.add(bt_modify);
			pn_first_2.add(bt_delete);
			pn_first_2.add(lb_infoImage);
		}
		else {
			pn_second_2.add(bt_back);
			pn_second_2.add(bt_modify);
			pn_second_2.add(bt_delete);
			pn_second_2.add(lb_infoImage);
		}
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("¥    �ţ�");
		lb[3].setText("¥    �㣺");
		lb[4].setText("�� �� �ţ�");
		lb[5].setText("��סʱ�䣺");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM student_stayinfo WHERE stu_num='"+stu_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
            while(rs.next()) {
            	stay=new Stay(rs.getString("stu_num"),rs.getString("name"),rs.getString("floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("time"));
            	tf[0].setText(stay.stu_num);
            	tf[1].setText(stay.name);
            	tf[2].setText(stay.floor_num);
            	tf[3].setText(String.valueOf(stay.layer));
            	tf[4].setText(String.valueOf(stay.room_num));
            	tf[5].setText(timeFormat.format(stay.time));
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				stay=null;
				if(x==1)
					allStay();
				else
					queryStayInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //�޸�ס����Ϣ
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(true);
				tf[1].setText("");
				tf[1].setEditable(false);
				tf[2].setEditable(false);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					dorAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					dorAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //ɾ��ס����Ϣ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ�ȷ��ɾ����ס����Ϣ��");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
				try { 
					if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
						lb_tips.setText("����д������");
						functionTips();
					}
					else { //����д����
						//���ѧ���Ƿ����
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //ѧ�Ų����ڣ�������ʾ
			            	lb_tips.setText("ѧ�Ų����ڣ�");
							functionTips();
			            }
			            else {
			            	//���ѧ���Ƿ��ظ�
			            	boolean flag=false; //����ѧ���Ƿ��ظ����������Ƿ�Ϊԭ����
							sql="SELECT stu_num FROM stayinfo WHERE stu_num='"+tf[0].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
				            rs=ps.executeQuery(sql);
				            flag=rs.next();
				            rs.first();
				            flag=flag&&!(tf[0].getText().equals(stay.stu_num));
				            if(flag) { //ѧ���ظ�������ѧ���Ѿ�ס�ޣ�������ʾ
				            	lb_tips.setText("��ѧ���Ѿ�ס�ޣ�");
								functionTips();
								flag=false;
				            }
				            else {
				            	//��������Ƿ����
				            	sql="SELECT * FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            if(!rs.next()) { //���᲻���ڣ�������ʾ
					            	lb_tips.setText("���᲻���ڣ�");
									functionTips();
					            }
					            else {
					            	//������ᴲλ�Ƿ�����
					            	sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
						            int now_bed=rs.getInt("bed_surplus");
						            flag=tf[2].getText().equals(stay.floor_num)&&tf[3].getText().equals(String.valueOf(stay.layer))&&tf[4].getText().equals(String.valueOf(stay.room_num));
						            if(now_bed==0&&!flag) { //ʣ�ലλ��Ϊ0�Ҳ�Ϊԭ���ᣬ�������ᴲλ������������ʾ
						            	lb_tips.setText("�����ᴲλ������");
										functionTips();
						            }
						            else { //�������ݷ���Ҫ�󣬽��б���
						            	try {
						            		//�޸�stayinfo������
						            		sql="UPDATE stayinfo SET stu_num=?,floor_num=?,layer=?,room_num=?,time=? WHERE stu_num='"+stay.stu_num+"'";
											ps=HomePage.connection.prepareStatement(sql);
											ps.setString(1,tf[0].getText()); //SQL����һ��?ֵ
											ps.setString(2,tf[2].getText()); //SQL���ڶ���?ֵ
											ps.setInt(3,Integer.parseInt(tf[3].getText())); //SQL��������?ֵ
											ps.setInt(4,Integer.parseInt(tf[4].getText())); //SQL�����ĸ�?ֵ
											ps.setString(5,tf[5].getText()); //SQL�������?ֵ
											ps.executeUpdate(); //���£�ִ���޸Ĳ���
											if(!flag) { //���޸������ᣬ���޸�dormitory�����������ʣ�ലλ��
												//ԭ����ʣ�ലλ��+1
												sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
												ps=HomePage.connection.prepareStatement(sql);
									            rs=ps.executeQuery(sql);
									            rs.next();
									            int old_bed=rs.getInt("bed_surplus");
												sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
												ps=HomePage.connection.prepareStatement(sql);
												ps.setInt(1,old_bed+1); //SQL����һ��?ֵ
												ps.executeUpdate(); //���£�ִ���޸Ĳ���
												//������ʣ�ലλ��-1
												sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setInt(1,now_bed-1); //SQL����һ��?ֵ
												ps.executeUpdate(); //���£�ִ���޸Ĳ���
											}
											//���޸���ѧ�ţ�ԭѧ��ס�޸�Ϊ������ѧ��ס�޸�Ϊ��
											if(!tf[0].getText().equals(stay.stu_num)) {
												sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setString(1,"��"); //SQL����һ��?ֵ
												ps.executeUpdate(); //���£�ִ���޸Ĳ���
												sql="UPDATE student SET yes_no=? WHERE stu_num='"+tf[0].getText()+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setString(1,"��"); //SQL����һ��?ֵ
												ps.executeUpdate(); //���£�ִ���޸Ĳ���
											}
											//��ȡ�޸ĺ��ס����Ϣ��ѧ������
											sql="SELECT name FROM student_stayinfo WHERE stu_num='"+tf[0].getText()+"'";
											ps=HomePage.connection.prepareStatement(sql);
											rs=ps.executeQuery(sql);
											rs.next();
											String stu_name=rs.getString("name");
						                    rs.close();
						                    ps.close();
						                    stay=new Stay(tf[0].getText(),stu_name,tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
						                    for(int i=0;i<6;i++)
						    					tf[i].setEditable(false);
						                    tf[1].setText(stu_name);
						                    if(x==1) {
						                    	pn_first_2.remove(bt_save);
							    				pn_first_2.remove(bt_cancel);
							    				dorAdminJFrame.repaint();
							    				pn_first_2.add(bt_modify);
							    				pn_first_2.add(bt_delete);
							    				dorAdminJFrame.validate();
						                    }
						                    else {
						                    	pn_second_2.remove(bt_save);
						                    	pn_second_2.remove(bt_cancel);
						                    	dorAdminJFrame.repaint();
							    				pn_second_2.add(bt_modify);
							    				pn_second_2.add(bt_delete);
							    				dorAdminJFrame.validate();
						                    }
						        		}catch(SQLException se){
						        			lb_tips.setText("���ݿ��������");
						    				functionTips();
						                    se.printStackTrace();
						                }catch(NumberFormatException ne){
						        			lb_tips.setText("����ת������");
						    				functionTips();
						                    ne.printStackTrace();
						                }
						            }
					            }
				            }
			            }
					}
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		bt_cancel.addActionListener(new ActionListener() { //ȡ��
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
				for(int i=0;i<6;i++)
					tf[i].setEditable(false);
				tf[0].setText(stay.stu_num);
            	tf[1].setText(stay.name);
            	tf[2].setText(stay.floor_num);
            	tf[3].setText(String.valueOf(stay.layer));
            	tf[4].setText(String.valueOf(stay.room_num));
            	tf[5].setText(timeFormat.format(stay.time));
            	if(x==1) {
            		pn_first_2.remove(bt_save);
                	pn_first_2.remove(bt_cancel);
                	dorAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				dorAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
            		dorAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
                	dorAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			dorAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			dorAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			dorAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			dorAdminJFrame.validate();
		}
	}
	
	public void addStayInfo() { //���ס����Ϣ
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("ȷ�����",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("����",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_time=new JButton("��ȡ��ǰʱ��",new ImageIcon("image/time.png"));
		bt_time.setFont(new Font("����",0,17));
		bt_time.setBounds(765,430,170,40);
		bt_time.setContentAreaFilled(false);
		bt_time.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(bt_time);
		pn_first_2.add(lb_infoImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("¥    �ţ�");
		lb[3].setText("¥    �㣺");
		lb[4].setText("�� �� �ţ�");
		lb[5].setText("��סʱ�䣺");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==1)
				tf[i].setEditable(false);
			if(i==2) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allStay();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_time.addActionListener(new ActionListener() { //��ȡ��ǰʱ��
			public void actionPerformed(ActionEvent e) {
				Date date=new Date();
				SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
				tf[5].setText(timeFormat.format(date));
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ�����ס����Ϣ
			public void actionPerformed(ActionEvent e) {
				if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д����
					String sql; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
					try {
						//���ѧ���Ƿ����
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //ѧ�Ų����ڣ�������ʾ
			            	lb_tips.setText("ѧ�Ų����ڣ�");
							functionTips();
			            }
			            else {
			            	//���ѧ���Ƿ��ظ�
							sql="SELECT stu_num FROM stayinfo WHERE stu_num='"+tf[0].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(rs.next()) { //ѧ���ظ�������ѧ���Ѿ�ס�ޣ�������ʾ
				            	lb_tips.setText("��ѧ���Ѿ�ס�ޣ�");
								functionTips();
				            }
				            else {
				            	//��������Ƿ����
				            	sql="SELECT * FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer="+tf[3].getText()+" AND room_num="+tf[4].getText();
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            if(!rs.next()) { //���᲻���ڣ�������ʾ
					            	lb_tips.setText("���᲻���ڣ�");
									functionTips();
					            }
					            else {
					            	//������ᴲλ�Ƿ�����
					            	sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer="+tf[3].getText()+" AND room_num="+tf[4].getText();
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
						            int now_bed=rs.getInt("bed_surplus");
						            if(now_bed==0) { //ʣ�ലλ��Ϊ0�������ᴲλ������������ʾ
						            	lb_tips.setText("�����ᴲλ������");
										functionTips();
						            }
						            else { //�������ݷ���Ҫ�󣬽��б���
						            	try {
						            		//��ȡס��ѧ������
						            		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						            		sql="SELECT name FROM student WHERE stu_num='"+tf[0].getText()+"'";
											ps=HomePage.connection.prepareStatement(sql);
								            rs=ps.executeQuery(sql);
								            rs.next();
						            		String stu_name=rs.getString("name");
						            		stay=new Stay(tf[0].getText(),stu_name,tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
						            		//���ס����Ϣ
						            		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)"; //SQL���
							            	ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
							            	ps.setString(1,stay.stu_num); //SQL����һ��?ֵ
											ps.setString(2,stay.floor_num); //SQL���ڶ���?ֵ
											ps.setInt(3,stay.layer); //SQL��������?ֵ
											ps.setInt(4,stay.room_num); //SQL�����ĸ�?ֵ
											ps.setString(5,timeFormat.format(stay.time)); //SQL�������?ֵ
											ps.executeUpdate(); //���£�ִ�в������
											//��Ӧ����ʣ�ലλ��-1
											sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
											ps=HomePage.connection.prepareStatement(sql);
											ps.setInt(1,now_bed-1); //SQL����һ��?ֵ
											ps.executeUpdate(); //���£�ִ���޸Ĳ���
											//ѧ���Ƿ�ס�޸�Ϊ���ǡ�
											sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'";
											ps=HomePage.connection.prepareStatement(sql);
											ps.setString(1,"��"); //SQL����һ��?ֵ
											ps.executeUpdate(); //���£�ִ���޸Ĳ���
											lb_tips.setText("ס����Ϣ��ӳɹ���");
											functionTips();
											stay=null;
											for(int i=0;i<6;i++) {
												tf[i].setEditable(false);
											}
											tf[1].setText(stu_name);
											pn_first_2.remove(bt_confirm);
											dorAdminJFrame.repaint();
						                    rs.close();
						                    ps.close();
						        		}catch(SQLException se){
						        			lb_tips.setText("���ݿ��������");
						    				functionTips();
						                    se.printStackTrace();
						                }
						            }
					            }
				            }
			            }
					}catch(SQLException se){
			            se.printStackTrace();
			        }
				}
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
	}
	
	public void queryStayInfo_1() { //��ѯס����Ϣ
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("��ѯ",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("����",0,17));
		bt_query.setBounds(447,520,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_infoImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("¥    �ţ�");
		lb[3].setText("¥    �㣺");
		lb[4].setText("�� �� �ţ�");
		lb[5].setText("��סʱ�䣺");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("���޹��������¥��"); //���޹��޹��������¥�����ѯ�����κν��
				else
					tf[i].setText(floor_num); //�޹ܽ��ܲ�ѯ����������¥
			}
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //��ѯס����Ϣ
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_stayinfo";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//�޲�ѯ����
				}
				else { //�в�ѯ����
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND stu_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND name='"+tf[1].getText()+"'";
					if(!tf[2].getText().equals(""))
						sql=sql+" AND floor_num='"+tf[2].getText()+"'";
					if(!tf[3].getText().equals(""))
						sql=sql+" AND layer="+tf[3].getText();
					if(!tf[4].getText().equals(""))
						sql=sql+" AND room_num="+tf[4].getText();
					if(!tf[5].getText().equals(""))
						sql=sql+" AND time='"+tf[5].getText()+"'";
				}
				queryStayInfo_2(sql);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void queryStayInfo_2(String sql) { //��ѯס����Ϣ���
		String[] columnNames={"ѧ��","����","¥��","¥��","�����","��סʱ��","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //��ѯ����ס����Ϣ����
		try {
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ��ѯ����ס����Ϣ����
            if(count==0) { //���޷���������ס����Ϣ
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="��";
            }
            else { //���з���������ס����Ϣ
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //��ȡ����������ס����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //¥��
        			rowData[i][3]=rs.getString("layer"); //¥��
        			rowData[i][4]=rs.getString("room_num"); //�����
        			rowData[i][5]=rs.getString("time"); //��סʱ��
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" ����ѯ��"+count+"��ס����Ϣ�� ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,5));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitStayInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryStayInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public JTabbedPane inout() { //����Ǽ�
		JTabbedPane tp_inout=new JTabbedPane();
		tp_inout.setFont(new Font("����",0,25));
		tp_inout.setBounds(0,0,1045,735);
		allInout();
		tp_inout.addTab(" ȫ�� ",pn_first);
		queryInoutInfo_1();
		tp_inout.addTab(" ��ѯ ",pn_second);
		return tp_inout;
	}
	
	public void allInout() { //���޹ܹ��������¥�����г�����Ϣ
		String[] columnNames={"ѧ��","����","����¥","���","ʱ��","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡstudent_inout��ͼ��Ϣ
			String sql="SELECT * FROM student_inout WHERE floor_num='"+floor_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ������ϢԪ������
            if(count==0) { //��student_inout��ͼ��Ԫ��
            	rowData=new String[1][6];
            	for(int i=0;i<6;i++)
            		rowData[0][i]="��";
            }
            else { //��student_inout��ͼ��Ԫ��
            	rowData=new String[count][6];
                rs.first();
                int i=0;
                do { //��ȡ���޹ܹ��������¥�����г�����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //����¥
        			rowData[i][3]=rs.getString("category"); //���
        			rowData[i][4]=rs.getString("time"); //ʱ��
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"��������Ϣ��  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_add=new JButton("�Ǽ�",new ImageIcon("image/add.png")),bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("����",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,6));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //�����ɾ������ť����ɾ��������Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                String sql="DELETE FROM in_out WHERE stu_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-5)+"' AND floor_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-3)+"' AND category='"+(String)table.getValueAt(button.getRow(),button.getColumn()-2)+"' AND time='"+(String)table.getValueAt(button.getRow(),button.getColumn()-1)+"'";
	                lb_tips.setText("�Ƿ�ȷ��ɾ���ó�����Ϣ��");
	                choiceTips(sql);
	                allInout();
	            }
	        };
			table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender("ɾ��")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"ɾ��");
			table.getColumnModel().getColumn(5).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_add.addActionListener(new ActionListener() { //��ӳ�����Ϣ
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addInoutInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void addInoutInfo() { //��ӳ�����Ϣ
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("ȷ�����",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("����",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_time=new JButton("��ȡ��ǰʱ��",new ImageIcon("image/time.png"));
		bt_time.setFont(new Font("����",0,17));
		bt_time.setBounds(765,415,170,40);
		bt_time.setContentAreaFilled(false);
		bt_time.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		String[] inorout= {"--------��ѡ��--------","          ��","          ��"};
		JComboBox<String> cb_inorout=new JComboBox<String>(inorout);
		cb_inorout.setFont(new Font("����",0,20));
		cb_inorout.setBounds(392,360,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(bt_time);
		pn_first_2.add(lb_infoImage);
		pn_first_2.add(cb_inorout);
		JLabel[] lb=new JLabel[5];
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("ѧ  �ţ�");
		lb[1].setText("��  ����");
		lb[2].setText("����¥��");
		lb[3].setText("��  ��");
		lb[4].setText("ʱ  �䣺");
		JTextField[] tf=new JTextField[4];
		for(int i=0;i<4;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			if(i==2) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
			if(i==3)
				tf[i].setBounds(392,195+i*55+55,260,40);
			pn_first_2.add(tf[i]);
		}
		tf[1].setEditable(false);
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allInout();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_time.addActionListener(new ActionListener() { //��ȡ��ǰʱ��
			public void actionPerformed(ActionEvent e) {
				Date date=new Date();
				SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tf[3].setText(timeFormat.format(date));
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ����ӳ�����Ϣ
			public void actionPerformed(ActionEvent e) {
				String inorout=null;
				if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||cb_inorout.getSelectedIndex()==0) {
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д����
					if((String)cb_inorout.getSelectedItem()=="          ��")
						inorout="��";
					else if((String)cb_inorout.getSelectedItem()=="          ��")
						inorout="��";
					String sql; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
					try {
						//���ѧ���Ƿ����
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //ѧ�Ų����ڣ�������ʾ
			            	lb_tips.setText("ѧ�Ų����ڣ�");
							functionTips();
			            }
			            else {
			            	//�������¥�Ƿ����
							sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[2].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(!rs.next()) { //����¥�����ڣ�������ʾ
				            	lb_tips.setText("����¥�����ڣ�");
								functionTips();
				            }
				            else { //�������ݷ���Ҫ�󣬽������
				            	try {
				            		//��ȡס��ѧ������
				            		sql="SELECT name FROM student WHERE stu_num='"+tf[0].getText()+"'";
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
				            		String stu_name=rs.getString("name");
				            		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				            		inout=new InOut(tf[0].getText(),stu_name,tf[2].getText(),inorout,tf[3].getText());
				            		//��ӳ�����Ϣ
				            		sql="INSERT INTO in_out VALUES(?,?,?,?)"; //SQL���
					            	ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
					            	ps.setString(1,inout.stu_num); //SQL����һ��?ֵ
									ps.setString(2,inout.floor_num); //SQL���ڶ���?ֵ
									ps.setString(3,inout.category); //SQL��������?ֵ
									ps.setString(4,timeFormat.format(inout.time)); //SQL�����ĸ�?ֵ
									ps.executeUpdate(); //���£�ִ�в������
									lb_tips.setText("����Ǽǳɹ���");
									functionTips();
									for(int i=0;i<4;i++) {
										tf[i].setEditable(false);
									}
									cb_inorout.setEnabled(false);
									tf[1].setText(inout.name);
									inout=null;
									pn_first_2.remove(bt_time);
									pn_first_2.remove(bt_confirm);
									dorAdminJFrame.repaint();
				                    rs.close();
				                    ps.close();
				        		}catch(SQLException se){
				        			lb_tips.setText("���ݿ��������");
				    				functionTips();
				                    se.printStackTrace();
				                }
				            }
			            }
					}catch(SQLException se){
			            se.printStackTrace();
			        }
				}
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
	}
	
	public void queryInoutInfo_1() { //��ѯ������Ϣ
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("��ѯ",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("����",0,17));
		bt_query.setBounds(447,520,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_infoImage);
		JLabel[] lb=new JLabel[5];
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("ѧ  �ţ�");
		lb[1].setText("��  ����");
		lb[2].setText("����¥��");
		lb[3].setText("��  ��");
		lb[4].setText("ʱ  �䣺");
		JTextField[] tf=new JTextField[5];
		for(int i=0;i<5;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("���޹��������¥��"); //���޹��޹��������¥�����ѯ�����κν��
				else
					tf[i].setText(floor_num); //�޹ܽ��ܲ�ѯ����������¥
			}
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //��ѯ������Ϣ
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_inout";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")) {
					//�޲�ѯ����
				}
				else { //�в�ѯ����
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND stu_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND name='"+tf[1].getText()+"'";
					if(!tf[2].getText().equals(""))
						sql=sql+" AND floor_num='"+tf[2].getText()+"'";
					if(!tf[3].getText().equals(""))
						sql=sql+" AND category='"+tf[3].getText()+"'";
					if(!tf[4].getText().equals(""))
						sql=sql+" AND time='"+tf[4].getText()+"'";
				}
				queryInoutInfo_2(sql);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void queryInoutInfo_2(String sql) { //��ѯ������Ϣ���
		String[] columnNames={"ѧ��","����","����¥","���","ʱ��","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //��ѯ���ĳ�����Ϣ����
		try {
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ��ѯ���ĳ�����Ϣ����
            if(count==0) { //���޷��������ĳ�����Ϣ
            	rowData=new String[1][6];
            	for(int i=0;i<6;i++)
            		rowData[0][i]="��";
            }
            else { //���з��������ĳ�����Ϣ
            	rowData=new String[count][6];
                rs.first();
                int i=0;
                do { //��ȡ���������ĳ�����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //����¥
        			rowData[i][3]=rs.getString("category"); //���
        			rowData[i][4]=rs.getString("time"); //ʱ��
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" ����ѯ��"+count+"��������Ϣ�� ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,6));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //�����ɾ������ť����ɾ��������Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                String delete_sql="DELETE FROM in_out WHERE stu_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-5)+"' AND floor_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-3)+"' AND category='"+(String)table.getValueAt(button.getRow(),button.getColumn()-2)+"' AND time='"+(String)table.getValueAt(button.getRow(),button.getColumn()-1)+"'";
	                lb_tips.setText("�Ƿ�ȷ��ɾ���ó�����Ϣ��");
	                choiceTips(delete_sql);
	                queryInoutInfo_2(sql);
	            }
	        };
			table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender("ɾ��")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"ɾ��");
			table.getColumnModel().getColumn(5).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryInoutInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public JTabbedPane repairManage() { //���ᱨ�޹���
		JTabbedPane tp_repair=new JTabbedPane();
		tp_repair.setFont(new Font("����",0,25));
		tp_repair.setBounds(0,0,1045,735);
		allRepair();
		tp_repair.addTab(" ȫ�� ",pn_first);
		queryRepairInfo_1();
		tp_repair.addTab(" ��ѯ ",pn_second);
		return tp_repair;
	}
	
	public void allRepair() { //���޹ܹ��������¥���������ᱨ��
		String[] columnNames={"ѧ��","����","¥��","¥��","�����","������ϸ","�Ƿ���","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡstudent_repair��ͼ��Ϣ
			String sql="SELECT * FROM student_repair WHERE floor_num='"+floor_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ������ϢԪ������
            if(count==0) { //��student_repair��ͼ��Ԫ��
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="��";
            }
            else { //��student_repair��ͼ��Ԫ��
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //��ȡ���޹ܹ��������¥�����б�����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //¥��
        			rowData[i][3]=rs.getString("layer"); //¥��
        			rowData[i][4]=rs.getString("room_num"); //�����
        			rowData[i][5]=rs.getString("info"); //������ϸ
        			if(rs.getString("yes_no").equals("��"))
        				rowData[i][6]="<html><font color='green'>��</font></html>";
        			else
        				rowData[i][6]="<html><font color='red'>��</font></html>";
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"�����ᱨ����Ϣ��  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,7));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                int row=button.getRow();
	                String yesno;
	                if(((String)table.getValueAt(row,button.getColumn()-1)).equals("<html><font color='green'>��</font></html>"))
	                	yesno="��";
	                else
	                	yesno="��";
	                pn_first_2.removeAll();
	                visitRepairInfo((String)table.getValueAt(row,button.getColumn()-7),(String)table.getValueAt(row,button.getColumn()-5),(String)table.getValueAt(row,button.getColumn()-4),(String)table.getValueAt(row,button.getColumn()-3),(String)table.getValueAt(row,button.getColumn()-2),yesno,1,null);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void visitRepairInfo(String stu_num, String floor_num, String layer, String room_num, String info, String yes_no,int x, String query_sql) { //�鿴���ᱨ����Ϣ����ɾ��
		if(x==1) {
			pn_first_2.removeAll();
			pn_first_2.setLayout(null);
			pn_first_2.setBounds(0,0,1045,695);
			pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		}
		else {
			pn_second_2.removeAll();
			pn_second_2.setLayout(null);
			pn_second_2.setBounds(0,0,1045,695);
			pn_second_2.setBorder(BorderFactory.createEtchedBorder());
		}
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_delete=new JButton("ɾ�����ᱨ��",new ImageIcon("image/delete.png"));
		bt_delete.setFont(new Font("����",0,17));
		bt_delete.setBounds(755,360,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JToggleButton tbt_yesno=new JToggleButton();
		tbt_yesno.setBounds(525,100+5*55,65,40);
		tbt_yesno.setBorderPainted(false);
		tbt_yesno.setContentAreaFilled(false);
		tbt_yesno.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbt_yesno.setIcon(new ImageIcon("image/��.png"));
		tbt_yesno.setSelectedIcon(new ImageIcon("image/��.png"));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/���ᱨ��.png"));
		lb_infoImage.setBounds(750,135,200,200);
		if(x==1) {
			pn_first_2.add(bt_back);
			pn_first_2.add(bt_delete);
			pn_first_2.add(tbt_yesno);
			pn_first_2.add(lb_infoImage);
		}
		else {
			pn_second_2.add(bt_back);
			pn_second_2.add(bt_delete);
			pn_second_2.add(tbt_yesno);
			pn_second_2.add(lb_infoImage);
		}
		JLabel[] lb=new JLabel[7];
		for(int i=0;i<7;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(140,95+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("¥    �ţ�");
		lb[3].setText("¥    �㣺");
		lb[4].setText("�� �� �ţ�");
		lb[5].setText("�Ƿ���");
		lb[6].setText("������ϸ��");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(272,100+i*55,250,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		JTextArea ta_info=new JTextArea();
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(272,100+6*55,500,160);
		ta_info.setEditable(false);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		JScrollPane scrollPane=new JScrollPane(); //������
		scrollPane.setBounds(272,100+6*55,500,160);
		scrollPane.setViewportView(ta_info);
		if(x==1)
			pn_first_2.add(scrollPane);
		else
			pn_second_2.add(scrollPane);
		try {
			String sql="SELECT * FROM student_repair WHERE stu_num='"+stu_num+"' AND floor_num='"+floor_num+"' AND layer='"+layer+"' AND room_num='"+room_num+"' AND info='"+info+"' AND yes_no='"+yes_no+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	repair=new Repair(rs.getString("stu_num"),rs.getString("name"),rs.getString("floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("info"),rs.getString("yes_no"));
            	tf[0].setText(repair.stu_num);
            	tf[1].setText(repair.name);
            	tf[2].setText(repair.floor_num);
            	tf[3].setText(String.valueOf(repair.layer));
            	tf[4].setText(String.valueOf(repair.room_num));
            	tf[5].setText(repair.yes_no);
            	ta_info.setText(repair.info);
            	if(repair.yes_no.equals("��"))
            		tbt_yesno.setSelected(true); //ѡ��Ϊ��
            	else
            		tbt_yesno.setSelected(false); //δѡ��Ϊ��
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				repair=null;
				if(x==1)
					allRepair();
				else
					queryRepairInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_delete.addActionListener(new ActionListener() { //ɾ�����ᱨ����Ϣ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ�ȷ��ɾ�������ᱨ����Ϣ��");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		tbt_yesno.addChangeListener(new ChangeListener() { //�Ƿ���
	        public void stateChanged(ChangeEvent e) {
	            if(tbt_yesno.isSelected())
	            	tf[5].setText("��");
	            else
	            	tf[5].setText("��");
	            try {
					String sql="UPDATE repair SET yes_no=? WHERE stu_num='"+repair.stu_num+"' AND floor_num='"+repair.floor_num+"' AND layer="+repair.layer+" AND room_num="+repair.room_num+" AND info='"+repair.info+"' AND yes_no='"+repair.yes_no+"'"; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
					ps.setString(1,tf[5].getText()); //SQL����һ��?ֵ
	            	ps.executeUpdate(); //���£�ִ���޸Ĳ���
	            	repair.yes_no=tf[5].getText();
		            ps.close();
				}catch(SQLException se){
					lb_tips.setText("���ݿ��������");
					functionTips();
		            se.printStackTrace();
		        }
	        }
	    });
		if(x==1) {
			pn_first.removeAll();
			dorAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			dorAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			dorAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			dorAdminJFrame.validate();
		}
	}
	
	public void queryRepairInfo_1() { //��ѯ���ᱨ����Ϣ
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("��ѯ",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("����",0,17));
		bt_query.setBounds(447,520,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/���ᱨ��.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_infoImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("¥    �ţ�");
		lb[3].setText("¥    �㣺");
		lb[4].setText("�� �� �ţ�");
		lb[5].setText("�Ƿ���");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("���޹��������¥��"); //���޹��޹��������¥�����ѯ�����κν��
				else
					tf[i].setText(floor_num); //�޹ܽ��ܲ�ѯ����������¥
			}
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //�����ı���ֻ����������
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //��ѯ���ᱨ����Ϣ
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_repair";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//�޲�ѯ����
				}
				else { //�в�ѯ����
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND stu_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND name='"+tf[1].getText()+"'";
					if(!tf[2].getText().equals(""))
						sql=sql+" AND floor_num='"+tf[2].getText()+"'";
					if(!tf[3].getText().equals(""))
						sql=sql+" AND layer="+tf[3].getText();
					if(!tf[4].getText().equals(""))
						sql=sql+" AND room_num="+tf[4].getText();
					if(!tf[5].getText().equals(""))
						sql=sql+" AND yes_no='"+tf[5].getText()+"'";
				}
				queryRepairInfo_2(sql);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void queryRepairInfo_2(String sql) { //��ѯ���ᱨ����Ϣ���
		String[] columnNames={"ѧ��","����","¥��","¥��","�����","������ϸ","�Ƿ���","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //��ѯ�������ᱨ����Ϣ����
		try {
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ��ѯ�������ᱨ����Ϣ����
            if(count==0) { //���޷������������ᱨ����Ϣ
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="��";
            }
            else { //���з������������ᱨ����Ϣ
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //��ȡ�������������ᱨ����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("floor_num"); //¥��
        			rowData[i][3]=rs.getString("layer"); //¥��
        			rowData[i][4]=rs.getString("room_num"); //�����
        			rowData[i][5]=rs.getString("info"); //������ϸ
        			if(rs.getString("yes_no").equals("��"))
        				rowData[i][6]="<html><font color='green'>��</font></html>";
        			else
        				rowData[i][6]="<html><font color='red'>��</font></html>";
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" ����ѯ��"+count+"�����ᱨ����Ϣ�� ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,7));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ�����޸ĺ�ɾ����Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                int row=button.getRow();
	                String yesno;
	                if(((String)table.getValueAt(row,button.getColumn()-1)).equals("<html><font color='green'>��</font></html>"))
	                	yesno="��";
	                else
	                	yesno="��";
	                pn_first_2.removeAll();
	                visitRepairInfo((String)table.getValueAt(row,button.getColumn()-7),(String)table.getValueAt(row,button.getColumn()-5),(String)table.getValueAt(row,button.getColumn()-4),(String)table.getValueAt(row,button.getColumn()-3),(String)table.getValueAt(row,button.getColumn()-2),yesno,2,sql);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryRepairInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
	}
	
	public void allAdvice() { //���޹ܹ��������¥����ѧ���Ľ����뷴��
		String[] columnNames={"ѧ��","����","�����뷴��","����"}; //�������
		String[][] rowData=null; //�������
		int count=0; //���Ԫ������
		try { //��ȡadminview_advice��ͼ��Ϣ
			String sql="SELECT * FROM adminview_advice WHERE admin_num='"+admin_num+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            rs.last();
            count=rs.getRow(); //��ȡ�����뷴����ϢԪ������
            if(count==0) { //��adminview_advice��ͼ��Ԫ��
            	rowData=new String[1][4];
            	for(int i=0;i<4;i++)
            		rowData[0][i]="��";
            }
            else { //��adminview_advice��ͼ��Ԫ��
            	rowData=new String[count][4];
                rs.first();
                int i=0;
                do { //��ȡ���޹ܹ��������¥�����н����뷴����Ϣ
                	rowData[i][0]=rs.getString("stu_num"); //ѧ��
        			rowData[i][1]=rs.getString("name"); //����
        			rowData[i][2]=rs.getString("info"); //�����뷴����ϸ��Ϣ
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//����
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,735);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		pn_top.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  ���޹��������¥��  ");
		else
			lb_num=new JLabel("  "+floor_num+"����¥����"+count+"�������뷴����  ");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_export=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,8));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",Font.BOLD,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,685);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //������鿴����ť���鿴��Ϣ���ɲ鿴��Ϣ
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitAdviceInfo((String)table.getValueAt(button.getRow(),button.getColumn()-3),(String)table.getValueAt(button.getRow(),button.getColumn()-1));
	            }
	        };
			table.getColumnModel().getColumn(3).setCellRenderer(new MyButtonRender("�鿴")); //���ñ�����Ⱦ��
			MyButtonEditor editor=new MyButtonEditor(e,"�鿴");
			table.getColumnModel().getColumn(3).setCellEditor(editor); //���ñ��ı༭��
		}
		bt_export.addActionListener(new ActionListener() { //����񵼳���Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(dorAdminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_function.removeAll();
		dorAdminJFrame.repaint();
		pn_function.add(pn_first_1);
		dorAdminJFrame.validate();
	}
	
	public void visitAdviceInfo(String stu_num, String info) { //�鿴�����뷴����Ϣ����ɾ��
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,735);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_delete=new JButton("ɾ�������뷴��",new ImageIcon("image/delete.png"));
		bt_delete.setFont(new Font("����",0,17));
		bt_delete.setBounds(805,410,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/�����뷴��.png"));
		lb_infoImage.setBounds(800,200,200,200);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_delete);
		pn_first_2.add(lb_infoImage);
		JLabel[] lb=new JLabel[3];
		for(int i=0;i<3;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(140,225+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ�");
		lb[1].setText("��    ����");
		lb[2].setText("�����뷴����");
		lb[2].setBounds(117,225+2*55,150,50);
		JTextField[] tf=new JTextField[2];
		for(int i=0;i<2;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(272,230+i*55,250,40);
			tf[i].setEditable(false);
			pn_first_2.add(tf[i]);
		}
		JTextArea ta_info=new JTextArea();
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(272,230+2*55,500,160);
		ta_info.setEditable(false);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		JScrollPane scrollPane=new JScrollPane(); //������
		scrollPane.setBounds(272,230+2*55,500,160);
		scrollPane.setViewportView(ta_info);
		pn_first_2.add(scrollPane);
		try {
			String sql="SELECT * FROM adminview_advice WHERE stu_num='"+stu_num+"' AND info='"+info+"'"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	advice=new Advice(rs.getString("stu_num"),rs.getString("name"),rs.getString("info"));
            	tf[0].setText(advice.stu_num);
            	tf[1].setText(advice.name);
            	ta_info.setText(advice.info);
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //����
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				advice=null;
				allAdvice();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_delete.addActionListener(new ActionListener() { //ɾ�������뷴��
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ�ȷ��ɾ���ý����뷴����Ϣ��");
				choiceTips("");
			}
		});
		pn_function.removeAll();
		dorAdminJFrame.repaint();
		pn_function.add(pn_first_2);
		dorAdminJFrame.validate();
	}
	
	public JPanel changePassword() { //[�޸�����]����
		JPanel pn_changePassword=new JPanel();
		pn_changePassword.setLayout(null);
		pn_changePassword.setSize(1045,735);
		pn_changePassword.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_old=new JLabel("�����룺"),lb_new1=new JLabel("�����룺"),lb_new2=new JLabel("ȷ�����룺"),lb=new JLabel("�����벻����20λ��");
		JPasswordField pf_old=new JPasswordField(),pf_new1=new JPasswordField(),pf_new2=new JPasswordField();
		lb_old.setFont(new Font("����",0,25));
		lb_old.setBounds(300,200,100,50);
		lb_new1.setFont(new Font("����",0,25));
		lb_new1.setBounds(300,260,100,50);
		lb_new2.setFont(new Font("����",0,25));
		lb_new2.setBounds(275,320,125,50);
		lb.setFont(new Font("����",0,18));
		lb.setBounds(650,260,200,50);
		pf_old.setFont(new Font(null,0,30));
		pf_old.setBounds(400,205,250,40);
		pf_new1.setFont(new Font(null,0,30));
		pf_new1.setBounds(400,265,250,40);
		pf_new2.setFont(new Font(null,0,30));
		pf_new2.setBounds(400,325,250,40);
		JLabel lb_image=new JLabel(new ImageIcon("image/�޸�����.png"));
		lb_image.setBounds(447,20,150,150);
		pn_changePassword.add(lb_old);pn_changePassword.add(lb_new1);pn_changePassword.add(lb_new2);pn_changePassword.add(lb);
		pn_changePassword.add(pf_old);pn_changePassword.add(pf_new1);pn_changePassword.add(pf_new2);pn_changePassword.add(lb_image);
		JButton bt_confirm=new JButton("ȷ��"),bt_reset=new JButton("����");
		bt_confirm.setFont(new Font("����",0,20));
		bt_confirm.setBounds(405,410,100,50);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_reset.setFont(new Font("����",0,20));
		bt_reset.setBounds(540,410,100,50);
		bt_reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_changePassword.add(bt_confirm);pn_changePassword.add(bt_reset);
		bt_reset.addActionListener(new ActionListener() { //���ã���������������
			public void actionPerformed(ActionEvent e) {
				pf_old.setText("");
				pf_new1.setText("");
				pf_new2.setText("");
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ��
			public void actionPerformed(ActionEvent e) {
				String password=null;
				if(!((!String.valueOf(pf_old.getPassword()).equals("")) //��δ��д�������������ʾ
						&&(!String.valueOf(pf_new1.getPassword()).equals(""))
						&&(!String.valueOf(pf_new2.getPassword()).equals("")))) {
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д����������������ж�
					try { //��ȡԭ����
						String sql="SELECT password FROM admin WHERE admin_num="+admin_num; //SQL���
						PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
						ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
			            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
			            while(rs.next()){ //���������
			            	password=rs.getString("password");
			            }
			            rs.close();
			            ps.close();
					}catch(SQLException se){
			            se.printStackTrace();
			        }
					if(!String.valueOf(pf_old.getPassword()).equals(password)) { //������ľ���������������ʾ
						lb_tips.setText("���������");
						functionTips();
					}
					//������ľ�������ȷ�����ж������������Ƿ���ͬ
					else if(!String.valueOf(pf_new1.getPassword()).equals(String.valueOf(pf_new2.getPassword()))) { //�����������벻��ͬ���������ʾ
						lb_tips.setText("���������벻ͬ��");
						functionTips();
					}
					//�������������������ͬ�����ж������Ƿ񲻳���20λ
					else if(String.valueOf(pf_new1.getPassword()).length()>20) { //����������볬��20λ���������ʾ
						lb_tips.setText("���볬��20λ��");
						functionTips();
					}
					else { //���ϸ������������и���
						try {
							String sql="UPDATE admin set password=? WHERE admin_num=?"; //SQL���
							PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
							ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
							ps.setString(1,String.valueOf(pf_new1.getPassword())); //SQL����һ��?ֵ
							ps.setString(2,admin_num); //SQL���ڶ���?ֵ
							ps.executeUpdate(); //���£�ִ���޸Ĳ���
				            ps.close();
						}catch(SQLException se){
				            se.printStackTrace();
				        }
						pf_old.setText("");
						pf_new1.setText("");
						pf_new2.setText("");
						lb_tips.setText("�����޸ĳɹ���");
						functionTips();
					}
				}
			}
		});
		return pn_changePassword;
	}
	
	public void functionTips() { //����ʱ��������ʾ��Ϣ����
		JDialog tips=new JDialog(dorAdminJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("ȷ��");
		tips.setSize(500,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_tips.setFont(new Font("����",0,20));
		bt_tips.setBounds(200,100,100,50);
		bt_tips.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		tips.add(pn_tips);
		tips.add(bt_tips);
		bt_tips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tips.dispose();
			}
		});
		tips.setVisible(true);
	}
	
	public void choiceTips(String str) { //����ʱ��������ʾѡ�񴰿�
		JDialog choiceTips=new JDialog(dorAdminJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_yes=new JButton("��(Y)");
		JButton bt_no=new JButton("��(N)");
		choiceTips.setSize(500,200);
		choiceTips.setLocationRelativeTo(null);
		choiceTips.setResizable(false);
		choiceTips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_yes.setFont(new Font("����",0,20));
		bt_yes.setBounds(135,100,100,50);
		bt_yes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_no.setFont(new Font("����",0,20));
		bt_no.setBounds(260,100,100,50);
		bt_no.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		choiceTips.add(pn_tips);
		choiceTips.add(bt_yes);
		choiceTips.add(bt_no);
		bt_yes.addActionListener(new ActionListener() { //ѡ���ǡ���������Ӧ����
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
				yesOperation(str);
			}
		});
		bt_no.addActionListener(new ActionListener() { //ѡ�񡰷񡱣��ر���ʾѡ�񴰿�
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
			}
		});
		bt_yes.addKeyListener(new KeyListener() { //���ǡ���ť�Ŀ�ݼ���Y�������񡱰�ť�Ŀ�ݼ���N��
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_Y) {
					choiceTips.dispose();
					yesOperation(str);
				}
				if(e.getKeyCode()==KeyEvent.VK_N) {
					choiceTips.dispose();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		bt_no.addKeyListener(new KeyListener() { //���ǡ���ť�Ŀ�ݼ���Y�������񡱰�ť�Ŀ�ݼ���N��
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_Y) {
					choiceTips.dispose();
					yesOperation(str);
				}
				if(e.getKeyCode()==KeyEvent.VK_N) {
					choiceTips.dispose();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		choiceTips.setVisible(true);
	}
	
	public void yesOperation(String str) { //ѡ���ǡ�֮�󣬸�����ʾ���ݣ�ѡ��Ҫ���еĲ���
		if(lb_tips.getText().equals("�Ƿ��˳��޹�ģʽ��")) { //ȷ���˳��޹�ģʽ�����ص���ӭ����
			try {
				HomePage.connection.close(); //�ر����ݿ�����
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dorAdminJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ����������Ϣ��")) { //ȷ��ɾ�����ᣬ����ɾ������
			try {
				String sql="DELETE FROM dormitory WHERE floor_num='"+dormitory.floor_num+"' AND layer="+dormitory.layer+" AND room_num="+dormitory.room_num; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
				lb_tips.setText("����ɾ���ɹ���");
				functionTips();
	            ps.close();
	            dormitory=null;
	            if(str.equals(""))
	            	allDormitory();
	            else
	            	queryDormitoryInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ����ѧ����Ϣ��")) { //ȷ��ɾ��ѧ��������ɾ������
			try {
				String sql="DELETE FROM student WHERE stu_num='"+student.stu_num+"'"; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
				lb_tips.setText("ѧ��ɾ���ɹ���");
				functionTips();
	            ps.close();
	            student=null;
	            if(str.equals(""))
	            	allStudent();
	            else
	            	queryStudentInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ����ס����Ϣ��")) { //ȷ��ɾ��ס����Ϣ������ɾ������
			try {
				//ɾ��ס����Ϣ
				String sql="DELETE FROM stayinfo WHERE stu_num='"+stay.stu_num+"'"; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ResultSet rs; //ResultSet�࣬������Ż�ȡ�Ľ����
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
            	//ԭ����ʣ�ലλ��+1
				sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
				ps=HomePage.connection.prepareStatement(sql);
	            rs=ps.executeQuery(sql);
	            rs.next();
	            int old_bed=rs.getInt("bed_surplus");
				sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
				ps=HomePage.connection.prepareStatement(sql);
				ps.setInt(1,old_bed+1); //SQL����һ��?ֵ
				ps.executeUpdate(); //���£�ִ���޸Ĳ���
            	//�޸ĸ�ѧ���Ƿ���סΪ����
            	sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'"; //SQL���
				ps=HomePage.connection.prepareStatement(sql);
				ps.setString(1,"��"); //SQL����һ��?ֵ
				ps.executeUpdate(); //���£�ִ���޸Ĳ���
				lb_tips.setText("ס����Ϣɾ���ɹ���");
				functionTips();
				rs.close();
	            ps.close();
	            stay=null;
	            if(str.equals(""))
	            	allStay();
	            else
	            	queryStayInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ���ó�����Ϣ��")) { //ȷ��ɾ��������Ϣ������ɾ������
			try {
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(str); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
				lb_tips.setText("������Ϣɾ���ɹ���");
				functionTips();
	            ps.close();
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ�������ᱨ����Ϣ��")) { //ȷ��ɾ�����ᱨ����Ϣ������ɾ������
			try {
				String sql="DELETE FROM repair WHERE stu_num='"+repair.stu_num+"' AND floor_num='"+repair.floor_num+"' AND layer="+repair.layer+" AND room_num="+repair.room_num+" AND info='"+repair.info+"' AND yes_no='"+repair.yes_no+"'"; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
				lb_tips.setText("���ᱨ��ɾ���ɹ���");
				functionTips();
	            ps.close();
	            repair=null;
	            if(str.equals(""))
	            	allRepair();
	            else
	            	queryRepairInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("�Ƿ�ȷ��ɾ���ý����뷴����Ϣ��")) { //ȷ��ɾ�������뷴����Ϣ������ɾ������
			try {
				String sql="DELETE FROM advice WHERE stu_num='"+advice.stu_num+"' AND info='"+advice.info+"'"; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            	ps.executeUpdate(); //���£�ִ��ɾ������
				lb_tips.setText("�����뷴��ɾ���ɹ���");
				functionTips();
	            ps.close();
	            advice=null;
	            allAdvice();
			}catch(SQLException e){
				lb_tips.setText("���ݿ��������");
				functionTips();
	            e.printStackTrace();
	        }
		}
	}
}