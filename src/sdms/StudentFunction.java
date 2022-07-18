package sdms;

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentFunction { //ѧ��������
	String stu_num;
	JFrame studentJFrame=new JFrame("ѧ���������ϵͳ-----[ѧ��ģʽ]");
	Container stuCon=studentJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //���ø��ֹ���ҳ�������
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	
	public StudentFunction(String stu_num) { //�������
		this.stu_num=stu_num;
		studentJFrame.setSize(1300,800);
		studentJFrame.setLocationRelativeTo(null);
		studentJFrame.setResizable(false);
		studentJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stuCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn1=new JPanel(); //������Ϣ����������
		pn1.setBackground(Color.white);
		pn1.setBounds(0,0,250,30);
		JLabel lb1=new JLabel("ѧ����"+stu_num);
		lb1.setFont(new Font("����",0,18));
		lb1.setForeground(Color.blue);
		pn1.add(lb1);
		JPanel pn2=new JPanel(); //������Ϣ������ǰ����
		pn2.setBackground(Color.white);
		pn2.setBounds(250,0,1045,30);
		JLabel lb2=new JLabel("");
		lb2.setFont(new Font("����",0,20));
		lb2.setForeground(Color.black);
		pn2.add(lb2);
		JPanel pn_menu=new JPanel(); //�˵������й���ѡ��
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt1=new JButton("������Ϣ"); //[������Ϣ]
		bt1.setFont(new Font("����",0,20));
		bt1.setContentAreaFilled(false);
		bt1.setBounds(0,50,249,50);
		JButton bt2=new JButton("���ᱨ��"); //[���ᱨ��]
		bt2.setFont(new Font("����",0,20));
		bt2.setContentAreaFilled(false);
		bt2.setBounds(0,105,249,50);
		JButton bt3=new JButton("�����뷴��"); //[�����뷴��]
		bt3.setFont(new Font("����",0,20));
		bt3.setContentAreaFilled(false);
		bt3.setBounds(0,160,249,50);
		JButton bt4=new JButton("�޸�����"); //[�޸�����]
		bt4.setFont(new Font("����",0,20));
		bt4.setContentAreaFilled(false);
		bt4.setBounds(0,215,249,50);
		JButton bt5=new JButton("�˳�"); //[�˳�]
		bt5.setFont(new Font("����",0,20));
		bt5.setContentAreaFilled(false);
		bt5.setBounds(0,270,249,50);
		pn_menu.add(bt1);
		pn_menu.add(bt2);
		pn_menu.add(bt3);
		pn_menu.add(bt4);
		pn_menu.add(bt5);
		JPanel pn_welcome=new JPanel(); //��ӭҳ
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("��ӭʹ��");
		lb_welcome.setFont(new Font("����",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		stuCon.add(pn1);
		stuCon.add(pn2);
		stuCon.add(pn_menu);
		stuCon.add(pn_function);
		pn_function.add(pn_welcome);
		studentJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //�鿴������Ϣ
				lb2.setText("[������Ϣ]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(info());
				studentJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //�������ᱨ��
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[���ᱨ��]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(repair());
				studentJFrame.validate();
			}
		});
		bt3.addActionListener(new ActionListener() { //���н����뷴��
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[�����뷴��]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(advise());
				studentJFrame.validate();
			}
		});
		bt4.addActionListener(new ActionListener() { //�޸�����
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[�޸�����]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(changePassword());
				studentJFrame.validate();
			}
		});
		bt5.addActionListener(new ActionListener() { //�˳�ѧ��ģʽ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ��˳�ѧ��ģʽ��");
				choiceTips();
			}
		});
	}
	
	public JPanel info() { //[������Ϣ]����
		String[] stu_info=new String[9]; //ѧ����Ϣ
		String[] stay_info={"��","��","��","��"}; //ס����Ϣ
		Calendar cal=Calendar.getInstance();
		try {
			String sql="SELECT * FROM student WHERE stu_num="+stu_num; //SQL���
			PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
			ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
            while(rs.next()) {
            	stu_info[0]=rs.getString("stu_num");
                stu_info[1]=rs.getString("name");
                stu_info[2]=rs.getString("sex");
                stu_info[3]=String.valueOf(cal.get(Calendar.YEAR)-rs.getInt("birth"));
                stu_info[4]=rs.getString("grade");
                stu_info[5]=rs.getString("faculty");
                stu_info[6]=rs.getString("class");
                stu_info[7]=rs.getString("phone");
                stu_info[8]=rs.getString("yes_no");
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
		JLabel[] lb=new JLabel[9]; //ѧ����Ϣ
		for(int i=0;i<9;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,30));
			lb[i].setBounds(100,102+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("ѧ    �ţ� "+stu_info[0]);
		lb[1].setText("��    ���� "+stu_info[1]);
		lb[2].setText("��    �� "+stu_info[2]);
		lb[3].setText("��    �䣺 "+stu_info[3]);
		lb[4].setText("��    ���� "+stu_info[4]+"��");
		lb[5].setText("Ժ    ϵ�� "+stu_info[5]);
		lb[6].setText("��    ���� "+stu_info[6]);
		lb[7].setText("��    ���� "+stu_info[7]);
		lb[8].setText("�Ƿ���ס�� "+stu_info[8]);
		if(stu_info[8].equals("��")) { //����ס�ޣ����ȡ��ϸס����Ϣ
			try {
				String sql="SELECT * FROM stayinfo WHERE stu_num="+stu_num; //SQL���
				PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
				ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
	            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
	            while(rs.next()) {
	            	stay_info[0]=rs.getString("floor_num");
	            	stay_info[1]=rs.getString("layer");
	            	stay_info[2]=rs.getString("room_num");
	            	stay_info[3]=rs.getString("time");
	            }
	            rs.close();
	            ps.close();
			}catch(SQLException e){
	            e.printStackTrace();
	        }
		}
		JLabel[] lb1=new JLabel[4]; //ס����Ϣ
		for(int i=0;i<4;i++) {
			lb1[i]=new JLabel();
			lb1[i].setFont(new Font("����",0,30));
			lb1[i].setBounds(610,402+i*60,400,50);
			pn_info.add(lb1[i]);
		}
		lb1[0].setText("¥    �ţ� "+stay_info[0]);
		lb1[1].setText("¥    �㣺 "+stay_info[1]);
		lb1[2].setText("�� �� �ţ� "+stay_info[2]);
		lb1[3].setText("��סʱ�䣺 "+stay_info[3]);
		pn_info.add(lb_infoImage);
		return pn_info;
	}
	
	public JPanel repair() { //[���ᱨ��]����
		JPanel pn_repair=new JPanel();
		pn_repair.setLayout(null);
		pn_repair.setSize(1045,735);
		pn_repair.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_floor=new JLabel("¥�ţ�"), lb_layer=new JLabel("¥�㣺"), lb_room=new JLabel("����ţ�"), lb_info=new JLabel("������ϸ��");
		JTextField tf_floor=new JTextField(), tf_layer=new JTextField(), tf_room=new JTextField();
		JTextArea ta_info=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(); //������
		JButton bt=new JButton("�ύ");
		lb_floor.setFont(new Font("����",0,25));
		lb_floor.setBounds(197,190,125,50);
		lb_layer.setFont(new Font("����",0,25));
		lb_layer.setBounds(197,250,125,50);
		lb_room.setFont(new Font("����",0,25));
		lb_room.setBounds(172,310,125,50);
		lb_info.setFont(new Font("����",0,25));
		lb_info.setBounds(147,370,125,50);
		tf_floor.setFont(new Font(null,0,25));
		tf_floor.setBounds(272,195,250,40);
		tf_layer.setFont(new Font(null,0,25));
		tf_layer.setBounds(272,255,250,40);
		tf_layer.setDocument(new NumLimit()); //�����ı���ֻ����������
		tf_room.setFont(new Font(null,0,25));
		tf_room.setBounds(272,315,250,40);
		tf_room.setDocument(new NumLimit()); //�����ı���ֻ����������
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(272,375,500,160);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		scrollPane.setBounds(272,375,500,160);
		scrollPane.setViewportView(ta_info);
		bt.setFont(new Font("����",0,20));
		bt.setBounds(472,560,100,50);
		bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_image=new JLabel(new ImageIcon("image/���ᱨ��.png"));
		lb_image.setBounds(800,205,200,200);
		pn_repair.add(lb_floor); pn_repair.add(lb_layer); pn_repair.add(lb_room); pn_repair.add(lb_info);
		pn_repair.add(tf_floor); pn_repair.add(tf_layer); pn_repair.add(tf_room); pn_repair.add(scrollPane);
		pn_repair.add(bt); pn_repair.add(lb_image);
		bt.addActionListener(new ActionListener() { //�ύ���ᱨ��
			public void actionPerformed(ActionEvent e) {
				String floor=tf_floor.getText(), layer=tf_layer.getText(), room=tf_room.getText(), info=ta_info.getText();
				if(floor.equals("")||layer.equals("")||room.equals("")||info.equals("")) { //��δ��д�������������ʾ
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //����д���������������Ƿ����
					try { //��ѯ�����Ƿ����
						String sql="SELECT floor_num, layer, room_num FROM dormitory WHERE BINARY floor_num='"+floor+"' AND layer="+layer+" AND room_num="+room; //SQL���
						PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
						ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
			            ResultSet rs=ps.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ����
			            if(!rs.next()) { //�����᲻���ڣ��������ʾ
			            	lb_tips.setText("���᲻���ڣ�");
							functionTips();
			            }
			            else { //��������ڣ����ύ����
			            	sql="INSERT INTO repair VALUES(?,?,?,?,?,'��')"; //SQL���
			            	ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
			            	ps.setString(1,stu_num); //SQL����һ��?ֵ
							ps.setString(2,floor); //SQL���ڶ���?ֵ
							ps.setInt(3,Integer.parseInt(layer)); //SQL��������?ֵ
							ps.setInt(4,Integer.parseInt(room)); //SQL�����ĸ�?ֵ
							ps.setString(5,info); //SQL�������?ֵ
							ps.executeUpdate(); //���£�ִ�в������
							tf_floor.setText("");
							tf_layer.setText("");
							tf_room.setText("");
							ta_info.setText("");
							lb_tips.setText("�ύ�ɹ�����ȴ�������Ա����");
							functionTips();
			            }
			            rs.close();
			            ps.close();
					}catch(SQLException se){
			            se.printStackTrace();
			        }
				}
			}
		});
		return pn_repair;
	}
	
	public JPanel advise() { //[�����뷴��]����
		JPanel pn_advise=new JPanel();
		pn_advise.setLayout(null);
		pn_advise.setSize(1045,735);
		pn_advise.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_info=new JLabel("�����뷴����");
		JTextArea ta_info=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(); //������
		JButton bt=new JButton("�ύ");
		lb_info.setFont(new Font("����",0,25));
		lb_info.setBounds(172,217,150,50);
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(172,267,700,250);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		scrollPane.setBounds(172,267,700,250);
		scrollPane.setViewportView(ta_info);
		bt.setFont(new Font("����",0,20));
		bt.setBounds(472,555,100,50);
		bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_image=new JLabel(new ImageIcon("image/�����뷴��.png"));
		lb_image.setBounds(422,20,200,200);
		pn_advise.add(lb_info);
		pn_advise.add(scrollPane);
		pn_advise.add(bt);
		pn_advise.add(lb_image);
		bt.addActionListener(new ActionListener() { //�ύ�����뷴��
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="INSERT INTO advice VALUES(?,?)"; //SQL���
					PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
					ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
					ps.setString(1,stu_num); //SQL����һ��?ֵ
					ps.setString(2,ta_info.getText()); //SQL���ڶ���?ֵ
	            	ps.executeUpdate(); //���£�ִ�в������
	            	ta_info.setText("");
					lb_tips.setText("�ύ�ɹ�����л���Ľ����뷴����");
					functionTips();
		            ps.close();
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		return pn_advise;
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
						String sql="SELECT password FROM student WHERE stu_num="+stu_num; //SQL���
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
							String sql="UPDATE student set password=? WHERE stu_num=?"; //SQL���
							PreparedStatement ps; //����PreparedStatement�����ps������ִ��SQL���
							ps=HomePage.connection.prepareStatement(sql); //�Ѳ������ݿⷵ�صĽ�����浽ps��
							ps.setString(1,String.valueOf(pf_new1.getPassword())); //SQL����һ��?ֵ
							ps.setString(2,stu_num); //SQL���ڶ���?ֵ
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
		JDialog tips=new JDialog(studentJFrame,"  ��ʾ",true);
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
	
	public void choiceTips() { //����ʱ��������ʾѡ�񴰿�
		JDialog choiceTips=new JDialog(studentJFrame,"  ��ʾ",true);
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
				yesOperation();
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
					yesOperation();
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
					yesOperation();
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
	
	public void yesOperation() { //ѡ���ǡ�֮�󣬸�����ʾ���ݣ�ѡ��Ҫ���еĲ���
		if(lb_tips.getText().equals("�Ƿ��˳�ѧ��ģʽ��")) { //�˳�ѧ��ģʽ�����ص���ӭ����
			try {
				HomePage.connection.close(); //�ر����ݿ�����
			} catch (SQLException e) {
				e.printStackTrace();
			}
			studentJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
	}
}