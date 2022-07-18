package sdms;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login { //��¼��
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	
	public void adminLogin(String account,String password) { //����Ա��¼������ϵͳ����Ա���޹ܣ�
		boolean flag=false; //�Ƿ���ҵ���Ӧ�˺�����
		if(account.equals("admin") && password.equals("admin")) { //�����Ƿ�Ϊϵͳ����Ա�˺����룬���ǣ������ϵͳ����Ա���ܽ���
			lb_tips.setText("��¼�ɹ���");
			loginTips();
			new SysAdminFunction();
			HomePage.mainJFrame.dispose();
		}
		else { //�����ǣ�������޹��˺�������
			String admin_num=null, pass; //�޹��˺�����
			try {
				String sql="SELECT admin_num, password FROM admin"; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
	            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
	            while(rs.next()){ //���������
	            	admin_num=rs.getString("admin_num");
	                pass=rs.getString("password");
	                if(account.equals(admin_num) && password.equals(pass)) {
	                	flag=true;
	                	break;
	                }
	            }
	            rs.close();
	            ps.close();
			}catch(SQLException e){
	            e.printStackTrace();
	        }
			if(flag) { //���޹��˺�������ȷ��������޹ܹ��ܽ���
				lb_tips.setText("��¼�ɹ���");
				loginTips();
				new DorAdminFunction(admin_num);
				HomePage.mainJFrame.dispose();
			}
			else { //���˺���������������ʾ
				lb_tips.setText("�˺Ż��������");
				loginTips();
				try {
					HomePage.connection.close(); //�ر����ݿ�����
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void studentLogin(String account,String password) { //ѧ����¼
		boolean flag=false; //�Ƿ���ҵ���Ӧ�˺�����
		String stu_num=null, pass; //ѧ���˺�����
		try {
			String sql="SELECT stu_num, password FROM student"; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) { //���������
            	stu_num=rs.getString("stu_num");
                pass=rs.getString("password");
                if(account.equals(stu_num) && password.equals(pass)) {
                	flag=true;
                	break;
                }
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		if(flag) { //��ѧ���˺�������ȷ�������ѧ�����ܽ���
			lb_tips.setText("��¼�ɹ���");
			loginTips();
			new StudentFunction(stu_num);
			HomePage.mainJFrame.dispose();
		}
		else { //���˺���������������ʾ
			lb_tips.setText("�˺Ż��������");
			loginTips();
			try {
				HomePage.connection.close(); //�ر����ݿ�����
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loginTips() { //��¼ʱ��������ʾ��Ϣ����
		JDialog tips=new JDialog(HomePage.mainJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("ȷ ��");
		tips.setSize(450,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,450,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_tips.setFont(new Font("����",0,20));
		bt_tips.setBounds(175,100,100,50);
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
}