package sdms;

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentFunction { //学生功能类
	String stu_num;
	JFrame studentJFrame=new JFrame("学生宿舍管理系统-----[学生模式]");
	Container stuCon=studentJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //放置各种功能页面的容器
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	
	public StudentFunction(String stu_num) { //整体界面
		this.stu_num=stu_num;
		studentJFrame.setSize(1300,800);
		studentJFrame.setLocationRelativeTo(null);
		studentJFrame.setResizable(false);
		studentJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stuCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn1=new JPanel(); //顶部信息栏，操作者
		pn1.setBackground(Color.white);
		pn1.setBounds(0,0,250,30);
		JLabel lb1=new JLabel("学生："+stu_num);
		lb1.setFont(new Font("黑体",0,18));
		lb1.setForeground(Color.blue);
		pn1.add(lb1);
		JPanel pn2=new JPanel(); //顶部信息栏，当前功能
		pn2.setBackground(Color.white);
		pn2.setBounds(250,0,1045,30);
		JLabel lb2=new JLabel("");
		lb2.setFont(new Font("黑体",0,20));
		lb2.setForeground(Color.black);
		pn2.add(lb2);
		JPanel pn_menu=new JPanel(); //菜单，进行功能选择
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt1=new JButton("个人信息"); //[个人信息]
		bt1.setFont(new Font("黑体",0,20));
		bt1.setContentAreaFilled(false);
		bt1.setBounds(0,50,249,50);
		JButton bt2=new JButton("宿舍报修"); //[宿舍报修]
		bt2.setFont(new Font("黑体",0,20));
		bt2.setContentAreaFilled(false);
		bt2.setBounds(0,105,249,50);
		JButton bt3=new JButton("建议与反馈"); //[建议与反馈]
		bt3.setFont(new Font("黑体",0,20));
		bt3.setContentAreaFilled(false);
		bt3.setBounds(0,160,249,50);
		JButton bt4=new JButton("修改密码"); //[修改密码]
		bt4.setFont(new Font("黑体",0,20));
		bt4.setContentAreaFilled(false);
		bt4.setBounds(0,215,249,50);
		JButton bt5=new JButton("退出"); //[退出]
		bt5.setFont(new Font("黑体",0,20));
		bt5.setContentAreaFilled(false);
		bt5.setBounds(0,270,249,50);
		pn_menu.add(bt1);
		pn_menu.add(bt2);
		pn_menu.add(bt3);
		pn_menu.add(bt4);
		pn_menu.add(bt5);
		JPanel pn_welcome=new JPanel(); //欢迎页
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("欢迎使用");
		lb_welcome.setFont(new Font("黑体",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		stuCon.add(pn1);
		stuCon.add(pn2);
		stuCon.add(pn_menu);
		stuCon.add(pn_function);
		pn_function.add(pn_welcome);
		studentJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //查看个人信息
				lb2.setText("[个人信息]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(info());
				studentJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //进行宿舍报修
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[宿舍报修]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(repair());
				studentJFrame.validate();
			}
		});
		bt3.addActionListener(new ActionListener() { //进行建议与反馈
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[建议与反馈]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(advise());
				studentJFrame.validate();
			}
		});
		bt4.addActionListener(new ActionListener() { //修改密码
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[修改密码]");
				pn_function.removeAll();
				studentJFrame.repaint();
				pn_function.add(changePassword());
				studentJFrame.validate();
			}
		});
		bt5.addActionListener(new ActionListener() { //退出学生模式
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否退出学生模式？");
				choiceTips();
			}
		});
	}
	
	public JPanel info() { //[个人信息]功能
		String[] stu_info=new String[9]; //学生信息
		String[] stay_info={"无","无","无","无"}; //住宿信息
		Calendar cal=Calendar.getInstance();
		try {
			String sql="SELECT * FROM student WHERE stu_num="+stu_num; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		JLabel[] lb=new JLabel[9]; //学生信息
		for(int i=0;i<9;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,30));
			lb[i].setBounds(100,102+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("学    号： "+stu_info[0]);
		lb[1].setText("姓    名： "+stu_info[1]);
		lb[2].setText("性    别： "+stu_info[2]);
		lb[3].setText("年    龄： "+stu_info[3]);
		lb[4].setText("年    级： "+stu_info[4]+"级");
		lb[5].setText("院    系： "+stu_info[5]);
		lb[6].setText("班    级： "+stu_info[6]);
		lb[7].setText("手    机： "+stu_info[7]);
		lb[8].setText("是否入住： "+stu_info[8]);
		if(stu_info[8].equals("是")) { //若有住宿，则获取详细住宿信息
			try {
				String sql="SELECT * FROM stayinfo WHERE stu_num="+stu_num; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
	            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		JLabel[] lb1=new JLabel[4]; //住宿信息
		for(int i=0;i<4;i++) {
			lb1[i]=new JLabel();
			lb1[i].setFont(new Font("黑体",0,30));
			lb1[i].setBounds(610,402+i*60,400,50);
			pn_info.add(lb1[i]);
		}
		lb1[0].setText("楼    号： "+stay_info[0]);
		lb1[1].setText("楼    层： "+stay_info[1]);
		lb1[2].setText("宿 舍 号： "+stay_info[2]);
		lb1[3].setText("入住时间： "+stay_info[3]);
		pn_info.add(lb_infoImage);
		return pn_info;
	}
	
	public JPanel repair() { //[宿舍报修]功能
		JPanel pn_repair=new JPanel();
		pn_repair.setLayout(null);
		pn_repair.setSize(1045,735);
		pn_repair.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_floor=new JLabel("楼号："), lb_layer=new JLabel("楼层："), lb_room=new JLabel("宿舍号："), lb_info=new JLabel("报修详细：");
		JTextField tf_floor=new JTextField(), tf_layer=new JTextField(), tf_room=new JTextField();
		JTextArea ta_info=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(); //滚动条
		JButton bt=new JButton("提交");
		lb_floor.setFont(new Font("黑体",0,25));
		lb_floor.setBounds(197,190,125,50);
		lb_layer.setFont(new Font("黑体",0,25));
		lb_layer.setBounds(197,250,125,50);
		lb_room.setFont(new Font("黑体",0,25));
		lb_room.setBounds(172,310,125,50);
		lb_info.setFont(new Font("黑体",0,25));
		lb_info.setBounds(147,370,125,50);
		tf_floor.setFont(new Font(null,0,25));
		tf_floor.setBounds(272,195,250,40);
		tf_layer.setFont(new Font(null,0,25));
		tf_layer.setBounds(272,255,250,40);
		tf_layer.setDocument(new NumLimit()); //限制文本框只能输入数字
		tf_room.setFont(new Font(null,0,25));
		tf_room.setBounds(272,315,250,40);
		tf_room.setDocument(new NumLimit()); //限制文本框只能输入数字
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(272,375,500,160);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		scrollPane.setBounds(272,375,500,160);
		scrollPane.setViewportView(ta_info);
		bt.setFont(new Font("黑体",0,20));
		bt.setBounds(472,560,100,50);
		bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_image=new JLabel(new ImageIcon("image/宿舍报修.png"));
		lb_image.setBounds(800,205,200,200);
		pn_repair.add(lb_floor); pn_repair.add(lb_layer); pn_repair.add(lb_room); pn_repair.add(lb_info);
		pn_repair.add(tf_floor); pn_repair.add(tf_layer); pn_repair.add(tf_room); pn_repair.add(scrollPane);
		pn_repair.add(bt); pn_repair.add(lb_image);
		bt.addActionListener(new ActionListener() { //提交宿舍报修
			public void actionPerformed(ActionEvent e) {
				String floor=tf_floor.getText(), layer=tf_layer.getText(), room=tf_room.getText(), info=ta_info.getText();
				if(floor.equals("")||layer.equals("")||room.equals("")||info.equals("")) { //若未填写完整，则进行提示
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //若填写完整，则检测宿舍是否存在
					try { //查询宿舍是否存在
						String sql="SELECT floor_num, layer, room_num FROM dormitory WHERE BINARY floor_num='"+floor+"' AND layer="+layer+" AND room_num="+room; //SQL语句
						PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
						ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
			            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
			            if(!rs.next()) { //若宿舍不存在，则进行提示
			            	lb_tips.setText("宿舍不存在！");
							functionTips();
			            }
			            else { //若宿舍存在，则提交报修
			            	sql="INSERT INTO repair VALUES(?,?,?,?,?,'否')"; //SQL语句
			            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
			            	ps.setString(1,stu_num); //SQL语句第一个?值
							ps.setString(2,floor); //SQL语句第二个?值
							ps.setInt(3,Integer.parseInt(layer)); //SQL语句第三个?值
							ps.setInt(4,Integer.parseInt(room)); //SQL语句第四个?值
							ps.setString(5,info); //SQL语句第五个?值
							ps.executeUpdate(); //更新，执行插入操作
							tf_floor.setText("");
							tf_layer.setText("");
							tf_room.setText("");
							ta_info.setText("");
							lb_tips.setText("提交成功，请等待工作人员处理！");
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
	
	public JPanel advise() { //[建议与反馈]功能
		JPanel pn_advise=new JPanel();
		pn_advise.setLayout(null);
		pn_advise.setSize(1045,735);
		pn_advise.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_info=new JLabel("建议与反馈：");
		JTextArea ta_info=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(); //滚动条
		JButton bt=new JButton("提交");
		lb_info.setFont(new Font("黑体",0,25));
		lb_info.setBounds(172,217,150,50);
		ta_info.setFont(new Font(null,0,25));
		ta_info.setBounds(172,267,700,250);
		ta_info.setLineWrap(true);
		ta_info.setWrapStyleWord(true);
		scrollPane.setBounds(172,267,700,250);
		scrollPane.setViewportView(ta_info);
		bt.setFont(new Font("黑体",0,20));
		bt.setBounds(472,555,100,50);
		bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_image=new JLabel(new ImageIcon("image/建议与反馈.png"));
		lb_image.setBounds(422,20,200,200);
		pn_advise.add(lb_info);
		pn_advise.add(scrollPane);
		pn_advise.add(bt);
		pn_advise.add(lb_image);
		bt.addActionListener(new ActionListener() { //提交建议与反馈
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="INSERT INTO advice VALUES(?,?)"; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
					ps.setString(1,stu_num); //SQL语句第一个?值
					ps.setString(2,ta_info.getText()); //SQL语句第二个?值
	            	ps.executeUpdate(); //更新，执行插入操作
	            	ta_info.setText("");
					lb_tips.setText("提交成功，感谢您的建议与反馈！");
					functionTips();
		            ps.close();
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		return pn_advise;
	}
	
	public JPanel changePassword() { //[修改密码]功能
		JPanel pn_changePassword=new JPanel();
		pn_changePassword.setLayout(null);
		pn_changePassword.setSize(1045,735);
		pn_changePassword.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_old=new JLabel("旧密码："),lb_new1=new JLabel("新密码："),lb_new2=new JLabel("确认密码："),lb=new JLabel("（密码不超过20位）");
		JPasswordField pf_old=new JPasswordField(),pf_new1=new JPasswordField(),pf_new2=new JPasswordField();
		lb_old.setFont(new Font("黑体",0,25));
		lb_old.setBounds(300,200,100,50);
		lb_new1.setFont(new Font("黑体",0,25));
		lb_new1.setBounds(300,260,100,50);
		lb_new2.setFont(new Font("黑体",0,25));
		lb_new2.setBounds(275,320,125,50);
		lb.setFont(new Font("黑体",0,18));
		lb.setBounds(650,260,200,50);
		pf_old.setFont(new Font(null,0,30));
		pf_old.setBounds(400,205,250,40);
		pf_new1.setFont(new Font(null,0,30));
		pf_new1.setBounds(400,265,250,40);
		pf_new2.setFont(new Font(null,0,30));
		pf_new2.setBounds(400,325,250,40);
		JLabel lb_image=new JLabel(new ImageIcon("image/修改密码.png"));
		lb_image.setBounds(447,20,150,150);
		pn_changePassword.add(lb_old);pn_changePassword.add(lb_new1);pn_changePassword.add(lb_new2);pn_changePassword.add(lb);
		pn_changePassword.add(pf_old);pn_changePassword.add(pf_new1);pn_changePassword.add(pf_new2);pn_changePassword.add(lb_image);
		JButton bt_confirm=new JButton("确认"),bt_reset=new JButton("重置");
		bt_confirm.setFont(new Font("黑体",0,20));
		bt_confirm.setBounds(405,410,100,50);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_reset.setFont(new Font("黑体",0,20));
		bt_reset.setBounds(540,410,100,50);
		bt_reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_changePassword.add(bt_confirm);pn_changePassword.add(bt_reset);
		bt_reset.addActionListener(new ActionListener() { //重置，将密码框内容清空
			public void actionPerformed(ActionEvent e) {
				pf_old.setText("");
				pf_new1.setText("");
				pf_new2.setText("");
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认
			public void actionPerformed(ActionEvent e) {
				String password=null;
				if(!((!String.valueOf(pf_old.getPassword()).equals("")) //若未填写完整，则进行提示
						&&(!String.valueOf(pf_new1.getPassword()).equals(""))
						&&(!String.valueOf(pf_new2.getPassword()).equals("")))) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //若填写完整，则进行密码判断
					try { //获取原密码
						String sql="SELECT password FROM student WHERE stu_num="+stu_num; //SQL语句
						PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
						ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
			            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
			            while(rs.next()){ //遍历结果集
			            	password=rs.getString("password");
			            }
			            rs.close();
			            ps.close();
					}catch(SQLException se){
			            se.printStackTrace();
			        }
					if(!String.valueOf(pf_old.getPassword()).equals(password)) { //若输入的旧密码错误，则进行提示
						lb_tips.setText("旧密码错误！");
						functionTips();
					}
					//若输入的旧密码正确，将判断两次新密码是否相同
					else if(!String.valueOf(pf_new1.getPassword()).equals(String.valueOf(pf_new2.getPassword()))) { //若两次新密码不相同，则进行提示
						lb_tips.setText("两次新密码不同！");
						functionTips();
					}
					//若输入的两次新密码相同，将判断密码是否不超过20位
					else if(String.valueOf(pf_new1.getPassword()).length()>20) { //若输入的密码超过20位，则进行提示
						lb_tips.setText("密码超过20位！");
						functionTips();
					}
					else { //符合改密条件，进行改密
						try {
							String sql="UPDATE student set password=? WHERE stu_num=?"; //SQL语句
							PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
							ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
							ps.setString(1,String.valueOf(pf_new1.getPassword())); //SQL语句第一个?值
							ps.setString(2,stu_num); //SQL语句第二个?值
							ps.executeUpdate(); //更新，执行修改操作
				            ps.close();
						}catch(SQLException se){
				            se.printStackTrace();
				        }
						pf_old.setText("");
						pf_new1.setText("");
						pf_new2.setText("");
						lb_tips.setText("密码修改成功！");
						functionTips();
					}
				}
			}
		});
		return pn_changePassword;
	}
	
	public void functionTips() { //操作时弹出的提示信息窗口
		JDialog tips=new JDialog(studentJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("确定");
		tips.setSize(500,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_tips.setFont(new Font("黑体",0,20));
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
	
	public void choiceTips() { //操作时弹出的提示选择窗口
		JDialog choiceTips=new JDialog(studentJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_yes=new JButton("是(Y)");
		JButton bt_no=new JButton("否(N)");
		choiceTips.setSize(500,200);
		choiceTips.setLocationRelativeTo(null);
		choiceTips.setResizable(false);
		choiceTips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_yes.setFont(new Font("黑体",0,20));
		bt_yes.setBounds(135,100,100,50);
		bt_yes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_no.setFont(new Font("黑体",0,20));
		bt_no.setBounds(260,100,100,50);
		bt_no.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		choiceTips.add(pn_tips);
		choiceTips.add(bt_yes);
		choiceTips.add(bt_no);
		bt_yes.addActionListener(new ActionListener() { //选择“是”，进行相应操作
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
				yesOperation();
			}
		});
		bt_no.addActionListener(new ActionListener() { //选择“否”，关闭提示选择窗口
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
			}
		});
		bt_yes.addKeyListener(new KeyListener() { //“是”按钮的快捷键“Y”，“否”按钮的快捷键“N”
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
		bt_no.addKeyListener(new KeyListener() { //“是”按钮的快捷键“Y”，“否”按钮的快捷键“N”
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
	
	public void yesOperation() { //选择“是”之后，根据提示内容，选择要进行的操作
		if(lb_tips.getText().equals("是否退出学生模式？")) { //退出学生模式，返回到欢迎界面
			try {
				HomePage.connection.close(); //关闭数据库连接
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