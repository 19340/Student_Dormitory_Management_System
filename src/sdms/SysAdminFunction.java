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

public class SysAdminFunction implements ActionListener { //系统管理员功能类
	JFrame sysAdminJFrame=new JFrame("学生宿舍管理系统-----[系统管理模式]");
	Container sysCon=sysAdminJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //放置各种功能页面的容器
	JLabel lb_topFunction=new JLabel(); //顶部信息栏，当前功能
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	JPanel pn_first=new JPanel(); //选项卡1
	JPanel pn_first_1=new JPanel(); //选项卡1_1
	JPanel pn_first_2=new JPanel(); //选项卡1_2
	JPanel pn_second=new JPanel(); //选项卡2
	JPanel pn_second_1=new JPanel(); //选项卡2_1
	JPanel pn_second_2=new JPanel(); //选项卡2_2
	Floor floor=null;
	Dormitory dormitory=null;
	Admin admin=null;
	Student student=null;
	Stay stay=null;
	InOut inout=null;
	Repair repair=null;
	Advice advice=null;
	
	public SysAdminFunction() { //整体界面
		sysAdminJFrame.setSize(1300,800);
		sysAdminJFrame.setLocationRelativeTo(null);
		sysAdminJFrame.setResizable(false);
		sysAdminJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sysAdminJFrame.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn_topUser=new JPanel(); //顶部信息栏，操作者
		pn_topUser.setBackground(Color.white);
		pn_topUser.setBounds(0,0,250,30);
		JLabel lb_topUser=new JLabel("系统管理员：admin");
		lb_topUser.setFont(new Font("黑体",0,18));
		lb_topUser.setForeground(Color.blue);
		pn_topUser.add(lb_topUser);
		JPanel pn_topFunction=new JPanel(); //顶部信息栏，当前功能
		pn_topFunction.setBackground(Color.white);
		pn_topFunction.setBounds(250,0,1045,30);
		lb_topFunction.setFont(new Font("黑体",0,20));
		lb_topFunction.setForeground(Color.black);
		pn_topFunction.add(lb_topFunction);
		JPanel pn_menu=new JPanel(); //菜单，进行功能选择
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt_floor=new JButton("宿舍楼管理"); //[宿舍楼管理]
		bt_floor.setFont(new Font("黑体",0,20));
		bt_floor.setContentAreaFilled(false);
		bt_floor.setBounds(0,50,249,50);
		JButton bt_dormitory=new JButton("宿舍管理"); //[宿舍管理]
		bt_dormitory.setFont(new Font("黑体",0,20));
		bt_dormitory.setContentAreaFilled(false);
		bt_dormitory.setBounds(0,105,249,50);
		JButton bt_admin=new JButton("宿管管理");//[宿管管理]
		bt_admin.setFont(new Font("黑体",0,20));
		bt_admin.setContentAreaFilled(false);
		bt_admin.setBounds(0,160,249,50);
		JButton bt_student=new JButton("学生管理"); //[学生管理]
		bt_student.setFont(new Font("黑体",0,20));
		bt_student.setContentAreaFilled(false);
		bt_student.setBounds(0,215,249,50);
		JButton bt_stay=new JButton("住宿管理"); //[住宿管理]
		bt_stay.setFont(new Font("黑体",0,20));
		bt_stay.setContentAreaFilled(false);
		bt_stay.setBounds(0,270,249,50);
		JButton bt_allocation=new JButton("寝室分配"); //[寝室分配]
		bt_allocation.setFont(new Font("黑体",0,20));
		bt_allocation.setContentAreaFilled(false);
		bt_allocation.setBounds(0,325,249,50);
		JButton bt_inout=new JButton("出入登记"); //[出入登记]
		bt_inout.setFont(new Font("黑体",0,20));
		bt_inout.setContentAreaFilled(false);
		bt_inout.setBounds(0,380,249,50);
		JButton bt_repair=new JButton("宿舍报修"); //[宿舍报修]
		bt_repair.setFont(new Font("黑体",0,20));
		bt_repair.setContentAreaFilled(false);
		bt_repair.setBounds(0,435,249,50);
		JButton bt_advice=new JButton("建议与反馈"); //[建议与反馈]
		bt_advice.setFont(new Font("黑体",0,20));
		bt_advice.setContentAreaFilled(false);
		bt_advice.setBounds(0,490,249,50);
		JButton bt_escape=new JButton("删库跑路"); //[删库跑路]
		bt_escape.setFont(new Font("黑体",0,20));
		bt_escape.setContentAreaFilled(false);
		bt_escape.setBounds(0,545,249,50);
		JButton bt_out=new JButton("退出"); //[退出]
		bt_out.setFont(new Font("黑体",0,20));
		bt_out.setContentAreaFilled(false);
		bt_out.setBounds(0,600,249,50);
		bt_floor.addActionListener(this);
		bt_dormitory.addActionListener(this);
		bt_admin.addActionListener(this);
		bt_student.addActionListener(this);
		bt_stay.addActionListener(this);
		bt_allocation.addActionListener(this);
		bt_inout.addActionListener(this);
		bt_repair.addActionListener(this);
		bt_advice.addActionListener(this);
		bt_escape.addActionListener(this);
		bt_out.addActionListener(this);
		pn_menu.add(bt_floor);
		pn_menu.add(bt_dormitory);
		pn_menu.add(bt_admin);
		pn_menu.add(bt_student);
		pn_menu.add(bt_stay);
		pn_menu.add(bt_allocation);
		pn_menu.add(bt_inout);
		pn_menu.add(bt_repair);
		pn_menu.add(bt_advice);
		pn_menu.add(bt_escape);
		pn_menu.add(bt_out);
		JPanel pn_welcome=new JPanel(); //欢迎页
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("欢迎使用");
		lb_welcome.setFont(new Font("黑体",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		sysCon.add(pn_topUser);
		sysCon.add(pn_topFunction);
		sysCon.add(pn_menu);
		sysCon.add(pn_function);
		pn_function.add(pn_welcome);
		sysAdminJFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { //依据选择的菜单功能，进行相应功能
		if(e.getActionCommand().equals("退出")) {
			lb_tips.setText("是否退出系统管理模式？");
			choiceTips("");
		}
		else {
			pn_function.removeAll();
			sysAdminJFrame.repaint();
			if(e.getActionCommand().equals("宿舍楼管理")) {
				lb_topFunction.setText("[宿舍楼管理]");
				pn_function.add(floorManage());
			}
			else if(e.getActionCommand().equals("宿舍管理")) {
				lb_topFunction.setText("[宿舍管理]");
				pn_function.add(dormitoryManage());
			}
			else if(e.getActionCommand().equals("宿管管理")) {
				lb_topFunction.setText("[宿管管理]");
				pn_function.add(adminManage());
			}
			else if(e.getActionCommand().equals("学生管理")) {
				lb_topFunction.setText("[学生管理]");
				pn_function.add(studentManage());
			}
			else if(e.getActionCommand().equals("住宿管理")) {
				lb_topFunction.setText("[住宿管理]");
				pn_function.add(stayManage());
			}
			else if(e.getActionCommand().equals("寝室分配")) {
				lb_topFunction.setText("[寝室分配]");
				pn_function.add(allocation());
			}
			else if(e.getActionCommand().equals("出入登记")) {
				lb_topFunction.setText("[出入登记]");
				pn_function.add(inout());
			}
			else if(e.getActionCommand().equals("宿舍报修")) {
				lb_topFunction.setText("[宿舍报修]");
				pn_function.add(repairManage());
			}
			else if(e.getActionCommand().equals("建议与反馈")) {
				lb_topFunction.setText("[建议与反馈]");
				allAdvice();
			}
			else if(e.getActionCommand().equals("删库跑路")) {
				lb_topFunction.setText("[删库跑路]");
				pn_function.add(escape());
			}
			sysAdminJFrame.validate();
		}
	}
	
	public JTabbedPane floorManage() { //宿舍楼管理
		JTabbedPane tp_floor=new JTabbedPane();
		tp_floor.setFont(new Font("黑体",0,25));
		tp_floor.setBounds(0,0,1045,735);
		allFloor();
		tp_floor.addTab(" 全部 ",pn_first);
		queryFloorInfo_1();
		tp_floor.addTab(" 查询 ",pn_second);
		return tp_floor;
	}
	
	public void allFloor() { //所有宿舍楼
		String[] columnNames={"楼号","层数","房间数","类别","居住性别","宿管编号","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取floor表信息
			String sql="SELECT * FROM floor"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取宿舍楼元组总数
            if(count==0) { //若floor表无元组
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若floor表有元组
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取所有宿舍楼信息
                	rowData[i][0]=rs.getString("floor_num"); //楼号
        			rowData[i][1]=rs.getString("layer_amount"); //层数
        			rowData[i][2]=rs.getString("room_amount"); //房间数
        			rowData[i][3]=rs.getString("category"); //类别
        			rowData[i][4]=rs.getString("sex"); //居住性别
        			rowData[i][5]=rs.getString("admin_num"); //宿管编号
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条宿舍楼信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,1));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitFloorInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),1,null);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加宿舍楼信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addFloorInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitFloorInfo(String floor_num, int x, String query_sql) { //查看宿舍楼信息，可修改和删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改宿舍楼信息",new ImageIcon("image/modify.png")),bt_delete=new JButton("删除宿舍楼信息",new ImageIcon("image/delete.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("层    数：");
		lb[2].setText("房 间 数：");
		lb[3].setText("类    别：");
		lb[4].setText("居住性别：");
		lb[5].setText("宿管编号：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM floor WHERE floor_num='"+floor_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            while(rs.next()) {
            	floor=new Floor(rs.getString("floor_num"),rs.getString("layer_amount"),rs.getString("room_amount"),rs.getString("category"),rs.getString("sex"),rs.getString("admin_num"));
            	tf[0].setText(floor.floor_num);
            	tf[1].setText(String.valueOf(floor.layer_amount));
            	tf[2].setText(String.valueOf(floor.room_amount));
            	tf[3].setText(floor.category);
            	tf[4].setText(floor.sex);
            	tf[5].setText(floor.admin_num);
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				floor=null;
				if(x==1)
					allFloor();
				else
					queryFloorInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //修改宿舍楼信息
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(true);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //删除宿舍楼信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该宿舍楼信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				try { 
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
						lb_tips.setText("请填写完整！");
						functionTips();
					}
					else { //已填写完整
						boolean flag=false; //代表宿舍楼号是否重复，或宿管是否重复管理
						//检测宿舍楼号是否重复
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
			            rs=ps.executeQuery(sql);
			            flag=rs.next();
			            rs.first();
			            flag=flag&&!(tf[0].getText().equals(floor.floor_num));
			            if(flag) { //宿舍楼号重复，进行提示
			            	lb_tips.setText("宿舍楼号重复！");
							functionTips();
							flag=false;
			            }
			            else {
			            	//检测宿管编号是否存在
			            	sql="SELECT admin_num FROM admin WHERE admin_num='"+tf[5].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(!rs.next()) { //宿管编号不存在，进行提示
				            	lb_tips.setText("宿管编号不存在！");
								functionTips();
				            }
				            else {
				            	//检测宿管是否重复管理
				            	sql="SELECT admin_num FROM floor WHERE admin_num='"+tf[5].getText()+"'";
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            flag=rs.next();
					            flag=flag&&!(tf[5].getText().equals(floor.admin_num));
					            if(flag) { //宿管重复管理，进行提示
					            	lb_tips.setText("该宿管已有管理的宿舍楼！");
									functionTips();
					            }
					            else { //输入数据符合要求，进行保存
					            	try {
					            		sql="UPDATE floor SET floor_num=?,layer_amount=?,room_amount=?,category=?,sex=?,admin_num=? WHERE floor_num='"+floor.floor_num+"'";
										ps=HomePage.connection.prepareStatement(sql);
										ps.setString(1,tf[0].getText()); //SQL语句第一个?值
										ps.setInt(2,Integer.parseInt(tf[1].getText())); //SQL语句第二个?值
										ps.setInt(3,Integer.parseInt(tf[2].getText())); //SQL语句第三个?值
										ps.setString(4,tf[3].getText()); //SQL语句第四个?值
										ps.setString(5,tf[4].getText()); //SQL语句第五个?值
										ps.setString(6,tf[5].getText()); //SQL语句第六个?值
										ps.executeUpdate(); //更新，执行修改操作
					                    rs.close();
					                    ps.close();
					                    floor=new Floor(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
					                    for(int i=0;i<6;i++)
					    					tf[i].setEditable(false);
					                    if(x==1) {
					                    	pn_first_2.remove(bt_save);
						    				pn_first_2.remove(bt_cancel);
						    				sysAdminJFrame.repaint();
						    				pn_first_2.add(bt_modify);
						    				pn_first_2.add(bt_delete);
						    				sysAdminJFrame.validate();
					                    }
					                    else {
					                    	pn_second_2.remove(bt_save);
					                    	pn_second_2.remove(bt_cancel);
						    				sysAdminJFrame.repaint();
						    				pn_second_2.add(bt_modify);
						    				pn_second_2.add(bt_delete);
						    				sysAdminJFrame.validate();
					                    }
					        		}catch(SQLException se){
					        			lb_tips.setText("数据库操作出错！");
					    				functionTips();
					                    se.printStackTrace();
					                }catch(NumberFormatException ne){
					        			lb_tips.setText("数据转换出错！");
					    				functionTips();
					                    ne.printStackTrace();
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
		bt_cancel.addActionListener(new ActionListener() { //取消
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(false);
				tf[0].setText(floor.floor_num);
            	tf[1].setText(String.valueOf(floor.layer_amount));
            	tf[2].setText(String.valueOf(floor.room_amount));
            	tf[3].setText(floor.category);
            	tf[4].setText(floor.sex);
            	tf[5].setText(floor.admin_num);
            	if(x==1) {
            		pn_first_2.remove(bt_save);
                	pn_first_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void addFloorInfo() { //添加宿舍楼信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_floorImage=new JLabel(new ImageIcon("image/floor.png"));
		lb_floorImage.setBounds(750,205,200,200);
		String[] sex= {"--------请选择--------","         混合","          男","          女"};
		JComboBox<String> cb_sex=new JComboBox<String>(sex);
		cb_sex.setFont(new Font("黑体",0,20));
		cb_sex.setBounds(392,375,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(lb_floorImage);
		pn_first_2.add(cb_sex);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("层    数：");
		lb[2].setText("房 间 数：");
		lb[3].setText("类    别：");
		lb[4].setText("居住性别：");
		lb[5].setText("宿管编号：");
		JTextField[] tf=new JTextField[5];
		for(int i=0;i<5;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==1||i==2)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			if(i==4)
				tf[i].setBounds(392,155+i*55+55,260,40);
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allFloor();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认添加宿舍楼信息
			public void actionPerformed(ActionEvent e) {
				String sex=null;
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||cb_sex.getSelectedIndex()==0) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					if((String)cb_sex.getSelectedItem()=="          男")
						sex="男";
					else if((String)cb_sex.getSelectedItem()=="          女")
						sex="女";
					else
						sex="混合";
					floor=new Floor(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),sex,tf[4].getText());				
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测宿舍楼号是否存在
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(rs.next()) { //宿舍楼号存在，则进行提示
			            	lb_tips.setText("宿舍楼号已经存在！");
							functionTips();
			            }
			            else {
			            	//检测宿管编号是否存在
			            	sql="SELECT admin_num FROM admin WHERE admin_num='"+tf[4].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(!rs.next()) { //宿管编号不存在，进行提示
				            	lb_tips.setText("宿管编号不存在！");
								functionTips();
				            }
				            else {
				            	//检测宿管是否重复管理
				            	sql="SELECT admin_num FROM floor WHERE admin_num='"+tf[4].getText()+"'";
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            if(rs.next()) { //宿管重复管理，进行提示
					            	lb_tips.setText("该宿管已有管理的宿舍楼！");
									functionTips();
					            }
					            else { //输入数据符合要求，进行添加
					            	try {
					            		sql="INSERT INTO floor VALUES(?,?,?,?,?,?)"; //SQL语句
						            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
						            	ps.setString(1,floor.floor_num); //SQL语句第一个?值
										ps.setInt(2,floor.layer_amount); //SQL语句第二个?值
										ps.setInt(3,floor.room_amount); //SQL语句第三个?值
										ps.setString(4,floor.category); //SQL语句第四个?值
										ps.setString(5,floor.sex); //SQL语句第五个?值
										ps.setString(6,floor.admin_num); //SQL语句第六个?值
										ps.executeUpdate(); //更新，执行插入操作
										lb_tips.setText("宿舍楼添加成功！");
										functionTips();
										floor=null;
										for(int i=0;i<5;i++) {
											tf[i].setEditable(false);
										}
										cb_sex.setEnabled(false);
										pn_first_2.remove(bt_confirm);
										sysAdminJFrame.repaint();
					                    rs.close();
					                    ps.close();
					        		}catch(SQLException se){
					        			lb_tips.setText("数据库操作出错！");
					    				functionTips();
					                    se.printStackTrace();
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryFloorInfo_1() { //查询宿舍楼信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("层    数：");
		lb[2].setText("房 间 数：");
		lb[3].setText("类    别：");
		lb[4].setText("居住性别：");
		lb[5].setText("宿管编号：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==1||i==2)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询宿舍楼信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM floor";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND floor_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND layer_amount="+tf[1].getText();
					if(!tf[2].getText().equals(""))
						sql=sql+" AND room_amount="+tf[2].getText();
					if(!tf[3].getText().equals(""))
						sql=sql+" AND category='"+tf[3].getText()+"'";
					if(!tf[4].getText().equals(""))
						sql=sql+" AND sex='"+tf[4].getText()+"'";
					if(!tf[5].getText().equals(""))
						sql=sql+" AND admin_num='"+tf[5].getText()+"'";
				}
				queryFloorInfo_2(sql);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryFloorInfo_2(String sql) { //查询宿舍楼信息结果
		String[] columnNames={"楼号","层数","房间数","类别","居住性别","宿管编号","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的宿舍楼个数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的宿舍楼个数
            if(count==0) { //若无符合条件的宿舍楼
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的宿舍楼
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取符合条件的宿舍楼信息
                	rowData[i][0]=rs.getString("floor_num"); //楼号
        			rowData[i][1]=rs.getString("layer_amount"); //层数
        			rowData[i][2]=rs.getString("room_amount"); //房间数
        			rowData[i][3]=rs.getString("category"); //类别
        			rowData[i][4]=rs.getString("sex"); //居住性别
        			rowData[i][5]=rs.getString("admin_num"); //宿管编号
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条宿舍楼信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,1));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitFloorInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryFloorInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JTabbedPane dormitoryManage() { //宿舍管理
		JTabbedPane tp_dormitory=new JTabbedPane();
		tp_dormitory.setFont(new Font("黑体",0,25));
		tp_dormitory.setBounds(0,0,1045,735);
		allDormitory();
		tp_dormitory.addTab(" 全部 ",pn_first);
		queryDormitoryInfo_1();
		tp_dormitory.addTab(" 查询 ",pn_second);
		return tp_dormitory;
	}
	
	public void allDormitory() { //所有宿舍
		String[] columnNames={"楼号","楼层","宿舍号","总床位数","剩余床位数","单价","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取dormitory表信息
			String sql="SELECT * FROM dormitory"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取宿舍元组总数
            if(count==0) { //若dormitory表无元组
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若dormitory表有元组
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取所有宿舍信息
                	rowData[i][0]=rs.getString("floor_num"); //楼号
        			rowData[i][1]=rs.getString("layer"); //楼层
        			rowData[i][2]=rs.getString("room_num"); //宿舍号
        			rowData[i][3]=rs.getString("bed_total"); //总床位数
        			rowData[i][4]=rs.getString("bed_surplus"); //剩余床位数
        			rowData[i][5]=rs.getString("price"); //单价
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条宿舍信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,2));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitDormitoryInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),(String)table.getValueAt(button.getRow(),button.getColumn()-5),(String)table.getValueAt(button.getRow(),button.getColumn()-4),1,null);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加宿舍信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addDormitoryInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitDormitoryInfo(String floor_num, String layer, String room_num, int x, String query_sql) { //查看宿舍信息，可修改和删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改宿舍信息",new ImageIcon("image/modify.png")),bt_delete=new JButton("删除宿舍信息",new ImageIcon("image/delete.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("楼    层：");
		lb[2].setText("宿 舍 号：");
		lb[3].setText("总床位数：");
		lb[4].setText("剩余床位数：");
		lb[5].setText("单    价：");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM dormitory WHERE floor_num='"+floor_num+"' AND layer="+layer+" AND room_num="+room_num; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_modify.addActionListener(new ActionListener() { //修改宿舍信息
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(true);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //删除宿舍信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该宿舍信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				try {
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
						lb_tips.setText("请填写完整！");
						functionTips();
					}
					else { //已填写完整
						//检测宿舍楼号是否存在
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //宿舍楼号不存在，进行提示
			            	lb_tips.setText("宿舍楼号不存在！");
							functionTips();
			            }
			            else {
			            	//检测宿舍是否重复
			            	boolean flag=false; //代表宿舍是否重复
							sql="SELECT * FROM dormitory WHERE floor_num='"+tf[0].getText()+"' AND layer='"+tf[1].getText()+"' AND room_num='"+tf[2].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
				            rs=ps.executeQuery(sql);
				            flag=rs.next();
				            rs.first();
				            flag=flag&&!(tf[0].getText().equals(dormitory.floor_num))&&!(tf[1].getText().equals(String.valueOf(dormitory.layer)))&&!(tf[2].getText().equals(String.valueOf(dormitory.room_num)));
				            if(flag) { //宿舍重复，进行提示
				            	lb_tips.setText("宿舍重复！");
								functionTips();
								flag=false;
				            }
				            else { //输入数据符合要求，进行保存
				            	try {
				            		sql="UPDATE dormitory SET floor_num=?,layer=?,room_num=?,bed_total=?,bed_surplus=?,price=? WHERE floor_num='"+dormitory.floor_num+"' AND layer="+layer+" AND room_num="+room_num;
									ps=HomePage.connection.prepareStatement(sql);
									ps.setString(1,tf[0].getText()); //SQL语句第一个?值
									ps.setInt(2,Integer.parseInt(tf[1].getText())); //SQL语句第二个?值
									ps.setInt(3,Integer.parseInt(tf[2].getText())); //SQL语句第三个?值
									ps.setInt(4,Integer.parseInt(tf[3].getText())); //SQL语句第四个?值
									ps.setInt(5,Integer.parseInt(tf[4].getText())); //SQL语句第五个?值
									ps.setInt(6,Integer.parseInt(tf[5].getText())); //SQL语句第六个?值
									ps.executeUpdate(); //更新，执行修改操作
				                    rs.close();
				                    ps.close();
				                    dormitory=new Dormitory(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
				                    for(int i=0;i<6;i++)
				    					tf[i].setEditable(false);
				                    if(x==1) {
				                    	pn_first_2.remove(bt_save);
					    				pn_first_2.remove(bt_cancel);
					    				sysAdminJFrame.repaint();
					    				pn_first_2.add(bt_modify);
					    				pn_first_2.add(bt_delete);
					    				sysAdminJFrame.validate();
				                    }
				                    else {
				                    	pn_second_2.remove(bt_save);
				                    	pn_second_2.remove(bt_cancel);
					    				sysAdminJFrame.repaint();
					    				pn_second_2.add(bt_modify);
					    				pn_second_2.add(bt_delete);
					    				sysAdminJFrame.validate();
				                    }
				        		}catch(SQLException se){
				        			lb_tips.setText("数据库操作出错！");
				    				functionTips();
				                    se.printStackTrace();
				                }catch(NumberFormatException ne){
				        			lb_tips.setText("数据转换出错！");
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
		bt_cancel.addActionListener(new ActionListener() { //取消
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
                	sysAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void addDormitoryInfo() { //添加宿舍信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("楼    层：");
		lb[2].setText("宿 舍 号：");
		lb[3].setText("总床位数：");
		lb[4].setText("剩余床位数：");
		lb[5].setText("单    价：");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i>=1)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_confirm.addActionListener(new ActionListener() { //确认添加宿舍信息
			public void actionPerformed(ActionEvent e) {
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					dormitory=new Dormitory(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());				
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测宿舍楼号是否存在
						sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //宿舍楼号不存在，进行提示
			            	lb_tips.setText("宿舍楼号不存在！");
							functionTips();
			            }
						else {
							//检测宿舍是否存在
							sql="SELECT * FROM dormitory WHERE floor_num='"+tf[0].getText()+"' AND layer="+tf[1].getText()+" AND room_num="+tf[2].getText();
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(rs.next()) { //宿舍存在，则进行提示
				            	lb_tips.setText("宿舍已经存在！");
								functionTips();
				            }
				            else { //输入数据符合要求，进行添加
				            	try {
				            		sql="INSERT INTO dormitory VALUES(?,?,?,?,?,?)"; //SQL语句
					            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
					            	ps.setString(1,dormitory.floor_num); //SQL语句第一个?值
									ps.setInt(2,dormitory.layer); //SQL语句第二个?值
									ps.setInt(3,dormitory.room_num); //SQL语句第三个?值
									ps.setInt(4,dormitory.bed_total); //SQL语句第四个?值
									ps.setInt(5,dormitory.bed_surplus); //SQL语句第五个?值
									ps.setInt(6,dormitory.price); //SQL语句第六个?值
									ps.executeUpdate(); //更新，执行插入操作
									lb_tips.setText("宿舍添加成功！");
									functionTips();
									dormitory=null;
									for(int i=0;i<6;i++) {
										tf[i].setEditable(false);
									}
									pn_first_2.remove(bt_confirm);
									sysAdminJFrame.repaint();
				                    rs.close();
				                    ps.close();
				        		}catch(SQLException se){
				        			lb_tips.setText("数据库操作出错！");
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryDormitoryInfo_1() { //查询宿舍信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("楼    号：");
		lb[1].setText("楼    层：");
		lb[2].setText("宿 舍 号：");
		lb[3].setText("总床位数：");
		lb[4].setText("剩余床位数：");
		lb[5].setText("单    价：");
		lb[4].setBounds(250,150+4*55,150,50);
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i>=1)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询宿舍信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM dormitory";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
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
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryDormitoryInfo_2(String sql) { //查询宿舍信息结果
		String[] columnNames={"楼号","楼层","宿舍号","总床位数","剩余床位数","单价","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的宿舍个数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的宿舍个数
            if(count==0) { //若无符合条件的宿舍
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的宿舍
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取符合条件的宿舍信息
                	rowData[i][0]=rs.getString("floor_num"); //楼号
        			rowData[i][1]=rs.getString("layer"); //楼层
        			rowData[i][2]=rs.getString("room_num"); //宿舍号
        			rowData[i][3]=rs.getString("bed_total"); //总床位数
        			rowData[i][4]=rs.getString("bed_surplus"); //剩余床位数
        			rowData[i][5]=rs.getString("price"); //单价
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条宿舍信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,2));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitDormitoryInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),(String)table.getValueAt(button.getRow(),button.getColumn()-5),(String)table.getValueAt(button.getRow(),button.getColumn()-4),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JTabbedPane adminManage() { //宿管管理
		JTabbedPane tp_admin=new JTabbedPane();
		tp_admin.setFont(new Font("黑体",0,25));
		tp_admin.setBounds(0,0,1045,735);
		allAdmin();
		tp_admin.addTab(" 全部 ",pn_first);
		queryAdminInfo_1();
		tp_admin.addTab(" 查询 ",pn_second);
		return tp_admin;
	}
	
	public void allAdmin() { //所有宿管
		String[] columnNames={"编号","姓名","性别","手机","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取admin表信息
			String sql="SELECT * FROM admin"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取宿管元组总数
            if(count==0) { //若admin表无元组
            	rowData=new String[1][5];
            	for(int i=0;i<5;i++)
            		rowData[0][i]="无";
            }
            else { //若admin表有元组
            	rowData=new String[count][5];
                rs.first();
                int i=0;
                do { //获取所有宿管信息
                	rowData[i][0]=rs.getString("admin_num"); //编号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("sex"); //性别
        			rowData[i][3]=rs.getString("phone"); //手机
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条宿管信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,3));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitAdminInfo((String)table.getValueAt(button.getRow(),button.getColumn()-4),1,null);
	            }
	        };
			table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(4).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加宿管信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addAdminInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitAdminInfo(String admin_num, int x, String query_sql) { //查看宿管信息，可修改和删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改宿管信息",new ImageIcon("image/modify.png")),bt_delete=new JButton("删除宿管信息",new ImageIcon("image/delete.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
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
		JLabel[] lb=new JLabel[5];
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("编  号：");
		lb[1].setText("密  码：");
		lb[2].setText("姓  名：");
		lb[3].setText("性  别：");
		lb[4].setText("手  机：");
		JTextField[] tf=new JTextField[5];
		for(int i=0;i<5;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM admin WHERE admin_num='"+admin_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            while(rs.next()) {
            	admin=new Admin(rs.getString("admin_num"),rs.getString("password"),rs.getString("name"),rs.getString("sex"),rs.getString("phone"));
            	tf[0].setText(admin.admin_num);
            	tf[1].setText(admin.password);
            	tf[2].setText(admin.name);
            	tf[3].setText(admin.sex);
            	tf[4].setText(admin.phone);
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				admin=null;
				if(x==1)
					allAdmin();
				else
					queryAdminInfo_2(query_sql);
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //修改宿管信息
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<5;i++)
					tf[i].setEditable(true);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //删除宿管信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该宿管信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				try { 
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")) {
						lb_tips.setText("请填写完整！");
						functionTips();
					}
					else { //已填写完整
						boolean flag=false; //代表宿管编号是否重复
						//检测宿管编号是否重复
						sql="SELECT admin_num FROM admin WHERE admin_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
			            rs=ps.executeQuery(sql);
			            flag=rs.next();
			            rs.first();
			            flag=flag&&!(tf[0].getText().equals(admin.admin_num));
			            if(flag) { //宿管编号重复，进行提示
			            	lb_tips.setText("宿管编号重复！");
							functionTips();
							flag=false;
			            }
			            else { //输入数据符合要求，进行保存
			            	try {
			            		sql="UPDATE admin SET admin_num=?,password=?,name=?,sex=?,phone=? WHERE admin_num='"+admin.admin_num+"'";
								ps=HomePage.connection.prepareStatement(sql);
								ps.setString(1,tf[0].getText()); //SQL语句第一个?值
								ps.setString(2,tf[1].getText()); //SQL语句第二个?值
								ps.setString(3,tf[2].getText()); //SQL语句第三个?值
								ps.setString(4,tf[3].getText()); //SQL语句第四个?值
								ps.setString(5,tf[4].getText()); //SQL语句第五个?值
								ps.executeUpdate(); //更新，执行修改操作
			                    rs.close();
			                    ps.close();
			                    admin=new Admin(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText());
			                    for(int i=0;i<5;i++)
			    					tf[i].setEditable(false);
			                    if(x==1) {
			                    	pn_first_2.remove(bt_save);
				    				pn_first_2.remove(bt_cancel);
				    				sysAdminJFrame.repaint();
				    				pn_first_2.add(bt_modify);
				    				pn_first_2.add(bt_delete);
				    				sysAdminJFrame.validate();
			                    }
			                    else {
			                    	pn_second_2.remove(bt_save);
			                    	pn_second_2.remove(bt_cancel);
				    				sysAdminJFrame.repaint();
				    				pn_second_2.add(bt_modify);
				    				pn_second_2.add(bt_delete);
				    				sysAdminJFrame.validate();
			                    }
			        		}catch(SQLException se){
			        			lb_tips.setText("数据库操作出错！");
			    				functionTips();
			                    se.printStackTrace();
			                }
			            }
					}
				}catch(SQLException se){
		            se.printStackTrace();
		        }
			}
		});
		bt_cancel.addActionListener(new ActionListener() { //取消
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<5;i++)
					tf[i].setEditable(false);
				tf[0].setText(admin.admin_num);
            	tf[1].setText(admin.password);
            	tf[2].setText(admin.name);
            	tf[3].setText(admin.sex);
            	tf[4].setText(admin.phone);
            	if(x==1) {
            		pn_first_2.remove(bt_save);
                	pn_first_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void addAdminInfo() { //添加宿管信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		String[] sex= {"--------请选择--------","          男","          女"};
		JComboBox<String> cb_sex=new JComboBox<String>(sex);
		cb_sex.setFont(new Font("黑体",0,20));
		cb_sex.setBounds(392,360,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(lb_infoImage);
		pn_first_2.add(cb_sex);
		JLabel[] lb=new JLabel[5];
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("编  号：");
		lb[1].setText("密  码：");
		lb[2].setText("姓  名：");
		lb[3].setText("性  别：");
		lb[4].setText("手  机：");
		JTextField[] tf=new JTextField[4];
		for(int i=0;i<4;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			if(i==3)
				tf[i].setBounds(392,195+i*55+55,260,40);
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				allAdmin();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认添加宿管信息
			public void actionPerformed(ActionEvent e) {
				String sex=null;
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||cb_sex.getSelectedIndex()==0) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					if((String)cb_sex.getSelectedItem()=="          男")
						sex="男";
					else if((String)cb_sex.getSelectedItem()=="          女")
						sex="女";
					admin=new Admin(tf[0].getText(),tf[1].getText(),tf[2].getText(),sex,tf[3].getText());				
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测宿管编号是否存在
						sql="SELECT admin_num FROM admin WHERE admin_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(rs.next()) { //宿管编号存在，则进行提示
			            	lb_tips.setText("宿管编号已经存在！");
							functionTips();
			            }
			            else { //输入数据符合要求，进行添加
			            	try {
			            		sql="INSERT INTO admin VALUES(?,?,?,?,?)"; //SQL语句
				            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
				            	ps.setString(1,admin.admin_num); //SQL语句第一个?值
								ps.setString(2,admin.password); //SQL语句第二个?值
								ps.setString(3,admin.name); //SQL语句第三个?值
								ps.setString(4,admin.sex); //SQL语句第四个?值
								ps.setString(5,admin.phone); //SQL语句第五个?值
								ps.executeUpdate(); //更新，执行插入操作
								lb_tips.setText("宿管添加成功！");
								functionTips();
								admin=null;
								for(int i=0;i<4;i++) {
									tf[i].setEditable(false);
								}
								cb_sex.setEnabled(false);
								pn_first_2.remove(bt_confirm);
								sysAdminJFrame.repaint();
			                    rs.close();
			                    ps.close();
			        		}catch(SQLException se){
			        			lb_tips.setText("数据库操作出错！");
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryAdminInfo_1() { //查询宿管信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("编  号：");
		lb[1].setText("密  码：");
		lb[2].setText("姓  名：");
		lb[3].setText("性  别：");
		lb[4].setText("手  机：");
		JTextField[] tf=new JTextField[5];
		for(int i=0;i<5;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询宿管信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM admin";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
					sql=sql+" WHERE 1=1";
					if(!tf[0].getText().equals(""))
						sql=sql+" AND admin_num='"+tf[0].getText()+"'";
					if(!tf[1].getText().equals(""))
						sql=sql+" AND password='"+tf[1].getText()+"'";
					if(!tf[2].getText().equals(""))
						sql=sql+" AND name='"+tf[2].getText()+"'";
					if(!tf[3].getText().equals(""))
						sql=sql+" AND sex='"+tf[3].getText()+"'";
					if(!tf[4].getText().equals(""))
						sql=sql+" AND phone='"+tf[4].getText()+"'";
				}
				queryAdminInfo_2(sql);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryAdminInfo_2(String sql) { //查询宿管信息结果
		String[] columnNames={"编号","姓名","性别","手机","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的宿管个数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的宿管个数
            if(count==0) { //若无符合条件的宿管
            	rowData=new String[1][5];
            	for(int i=0;i<5;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的宿管
            	rowData=new String[count][5];
                rs.first();
                int i=0;
                do { //获取符合条件的宿管信息
                	rowData[i][0]=rs.getString("admin_num"); //编号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("sex"); //性别
        			rowData[i][3]=rs.getString("phone"); //手机
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条宿管信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,3));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitAdminInfo((String)table.getValueAt(button.getRow(),button.getColumn()-4),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(4).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				queryAdminInfo_1();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JTabbedPane studentManage() { //学生管理
		JTabbedPane tp_student=new JTabbedPane();
		tp_student.setFont(new Font("黑体",0,25));
		tp_student.setBounds(0,0,1045,735);
		allStudent();
		tp_student.addTab(" 全部 ",pn_first);
		queryStudentInfo_1();
		tp_student.addTab(" 查询 ",pn_second);
		return tp_student;
	}
	
	public void allStudent() { //所有学生
		String[] columnNames={"学号","姓名","性别","年级","院系","班级","是否入住","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student表信息
			String sql="SELECT * FROM student"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取学生元组总数
            if(count==0) { //若student表无元组
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="无";
            }
            else { //若student表有元组
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //获取所有学生信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("sex"); //性别
        			rowData[i][3]=rs.getString("grade")+"级"; //年级
        			rowData[i][4]=rs.getString("faculty"); //院系
        			rowData[i][5]=rs.getString("class"); //班级
        			rowData[i][6]=rs.getString("yes_no"); //是否入住
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条学生信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,4));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitStudentInfo((String)table.getValueAt(button.getRow(),button.getColumn()-7),1,null);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加学生信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addStudentInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitStudentInfo(String stu_num, int x, String query_sql) { //查看学生信息，可修改和删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改学生信息",new ImageIcon("image/modify.png")),bt_delete=new JButton("删除学生信息",new ImageIcon("image/delete.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("密    码：");
		lb[2].setText("姓    名：");
		lb[3].setText("性    别：");
		lb[4].setText("出生年份：");
		lb[5].setText("年    级：");
		lb[6].setText("院    系：");
		lb[7].setText("班    级：");
		lb[8].setText("手    机：");
		lb[9].setText("是否入住：");
		JTextField[] tf=new JTextField[10];
		for(int i=0;i<10;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM student WHERE stu_num='"+stu_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_modify.addActionListener(new ActionListener() { //修改学生信息
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<10;i++)
					if(i!=9)
						tf[i].setEditable(true);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //删除学生信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该学生信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				try {
					if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")||tf[6].getText().equals("")||tf[7].getText().equals("")||tf[8].getText().equals("")||tf[9].getText().equals("")) {
						lb_tips.setText("请填写完整！");
						functionTips();
					}
					else { //已填写完整
						boolean flag=false; //代表学号是否重复
						//检测学号是否重复
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
			            rs=ps.executeQuery(sql);
			            flag=rs.next();
			            rs.first();
			            flag=flag&&!(tf[0].getText().equals(student.stu_num));
			            if(flag) { //学号重复，进行提示
			            	lb_tips.setText("学号重复！");
							functionTips();
							flag=false;
			            }
			            else { //输入数据符合要求，进行保存
			            	try {
			            		sql="UPDATE student SET stu_num=?,password=?,name=?,sex=?,birth=?,grade=?,faculty=?,class=?,phone=?,yes_no=? WHERE stu_num='"+student.stu_num+"'";
								ps=HomePage.connection.prepareStatement(sql);
								ps.setString(1,tf[0].getText()); //SQL语句第一个?值
								ps.setString(2,tf[1].getText()); //SQL语句第二个?值
								ps.setString(3,tf[2].getText()); //SQL语句第三个?值
								ps.setString(4,tf[3].getText()); //SQL语句第四个?值
								ps.setInt(5,Integer.parseInt(tf[4].getText())); //SQL语句第五个?值
								ps.setInt(6,Integer.parseInt(tf[5].getText())); //SQL语句第六个?值
								ps.setString(7,tf[6].getText()); //SQL语句第七个?值
								ps.setString(8,tf[7].getText()); //SQL语句第八个?值
								ps.setString(9,tf[8].getText()); //SQL语句第九个?值
								ps.setString(10,tf[9].getText()); //SQL语句第十个?值
								ps.executeUpdate(); //更新，执行修改操作
			                    rs.close();
			                    ps.close();
			                    student=new Student(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText(),tf[6].getText(),tf[7].getText(),tf[8].getText(),tf[9].getText());
			                    for(int i=0;i<10;i++)
			    					tf[i].setEditable(false);
			                    if(x==1) {
			                    	pn_first_2.remove(bt_save);
				    				pn_first_2.remove(bt_cancel);
				    				sysAdminJFrame.repaint();
				    				pn_first_2.add(bt_modify);
				    				pn_first_2.add(bt_delete);
				    				sysAdminJFrame.validate();
			                    }
			                    else {
			                    	pn_second_2.remove(bt_save);
			                    	pn_second_2.remove(bt_cancel);
				    				sysAdminJFrame.repaint();
				    				pn_second_2.add(bt_modify);
				    				pn_second_2.add(bt_delete);
				    				sysAdminJFrame.validate();
			                    }
			        		}catch(SQLException se){
			        			lb_tips.setText("数据库操作出错！");
			    				functionTips();
			                    se.printStackTrace();
			                }catch(NumberFormatException ne){
			        			lb_tips.setText("数据转换出错！");
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
		bt_cancel.addActionListener(new ActionListener() { //取消
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
                	sysAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void addStudentInfo() { //添加学生信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(447,625,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		String[] sex= {"--------请选择--------","          男","          女"};
		JComboBox<String> cb_sex=new JComboBox<String>(sex);
		cb_sex.setFont(new Font("黑体",0,20));
		cb_sex.setBounds(392,235,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(lb_infoImage);
		pn_first_2.add(cb_sex);
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("密    码：");
		lb[2].setText("姓    名：");
		lb[3].setText("性    别：");
		lb[4].setText("出生年份：");
		lb[5].setText("年    级：");
		lb[6].setText("院    系：");
		lb[7].setText("班    级：");
		lb[8].setText("手    机：");
		lb[9].setText("是否入住：");
		JTextField[] tf=new JTextField[9];
		for(int i=0;i<9;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			if(i>=3)
				tf[i].setBounds(392,70+i*55+55,260,40);
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_first_2.add(tf[i]);
		}
		tf[8].setText("否");
		tf[8].setEditable(false);
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_confirm.addActionListener(new ActionListener() { //确认添加学生信息
			public void actionPerformed(ActionEvent e) {
				String sex=null;
				if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")||tf[6].getText().equals("")||tf[7].getText().equals("")||tf[8].getText().equals("")||cb_sex.getSelectedIndex()==0) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					if((String)cb_sex.getSelectedItem()=="          男")
						sex="男";
					else if((String)cb_sex.getSelectedItem()=="          女")
						sex="女";
					student=new Student(tf[0].getText(),tf[1].getText(),tf[2].getText(),sex,tf[3].getText(),tf[4].getText(),tf[5].getText(),tf[6].getText(),tf[7].getText(),"否");				
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测学号是否存在
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(rs.next()) { //学号存在，则进行提示
			            	lb_tips.setText("学号已经存在！");
							functionTips();
			            }
			            else { //输入数据符合要求，进行添加
			            	try {
			            		sql="INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?,?)"; //SQL语句
				            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
				            	ps.setString(1,student.stu_num); //SQL语句第一个?值
								ps.setString(2,student.password); //SQL语句第二个?值
								ps.setString(3,student.name); //SQL语句第三个?值
								ps.setString(4,student.sex); //SQL语句第四个?值
								ps.setInt(5,student.birth); //SQL语句第五个?值
								ps.setInt(6,student.grade); //SQL语句第六个?值
								ps.setString(7,student.faculty); //SQL语句第七个?值
								ps.setString(8,student.clas); //SQL语句第八个?值
								ps.setString(9,student.phone); //SQL语句第九个?值
								ps.setString(10,student.yes_no); //SQL语句第十个?值
								ps.executeUpdate(); //更新，执行插入操作
								lb_tips.setText("学生添加成功！");
								functionTips();
								student=null;
								for(int i=0;i<9;i++) {
									tf[i].setEditable(false);
								}
								cb_sex.setEnabled(false);
								pn_first_2.remove(bt_confirm);
								sysAdminJFrame.repaint();
			                    rs.close();
			                    ps.close();
			        		}catch(SQLException se){
			        			lb_tips.setText("数据库操作出错！");
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryStudentInfo_1() { //查询学生信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,65+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("密    码：");
		lb[2].setText("姓    名：");
		lb[3].setText("性    别：");
		lb[4].setText("出生年份：");
		lb[5].setText("年    级：");
		lb[6].setText("院    系：");
		lb[7].setText("班    级：");
		lb[8].setText("手    机：");
		lb[9].setText("是否入住：");
		JTextField[] tf=new JTextField[10];
		for(int i=0;i<10;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,70+i*55,260,40);
			if(i==4||i==5)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询学生信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")&&tf[6].getText().equals("")&&tf[7].getText().equals("")&&tf[8].getText().equals("")&&tf[9].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
					sql=sql+" WHERE 1=1";
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
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryStudentInfo_2(String sql) { //查询学生信息结果
		String[] columnNames={"学号","姓名","性别","年级","院系","班级","是否入住","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的学生个数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的学生个数
            if(count==0) { //若无符合条件的学生
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的学生
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //获取符合条件的学生信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("sex"); //性别
        			rowData[i][3]=rs.getString("grade")+"级"; //年级
        			rowData[i][4]=rs.getString("faculty"); //院系
        			rowData[i][5]=rs.getString("class"); //班级
        			rowData[i][6]=rs.getString("yes_no"); //是否入住
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条学生信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,4));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitStudentInfo((String)table.getValueAt(button.getRow(),button.getColumn()-7),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JTabbedPane stayManage() { //住宿管理
		JTabbedPane tp_stay=new JTabbedPane();
		tp_stay.setFont(new Font("黑体",0,25));
		tp_stay.setBounds(0,0,1045,735);
		allStay();
		tp_stay.addTab(" 全部 ",pn_first);
		queryStayInfo_1();
		tp_stay.addTab(" 查询 ",pn_second);
		return tp_stay;
	}
	
	public void allStay() { //所有住宿
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","入住时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_stayinfo视图信息
			String sql="SELECT * FROM student_stayinfo"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取住宿元组总数
            if(count==0) { //若student_stayinfo视图无元组
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若student_stayinfo视图有元组
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取所有住宿信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //楼号
        			rowData[i][3]=rs.getString("layer"); //楼层
        			rowData[i][4]=rs.getString("room_num"); //宿舍号
        			rowData[i][5]=rs.getString("time"); //入住时间
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条住宿信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,5));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitStayInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),1,null);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加住宿信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addStayInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitStayInfo(String stu_num, int x, String query_sql) { //查看住宿信息，可修改和删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改住宿信息",new ImageIcon("image/modify.png")),bt_delete=new JButton("删除住宿信息",new ImageIcon("image/delete.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(755,380,190,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,430,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(750,380,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("楼    号：");
		lb[3].setText("楼    层：");
		lb[4].setText("宿 舍 号：");
		lb[5].setText("入住时间：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			tf[i].setEditable(false);
			if(x==1)
				pn_first_2.add(tf[i]);
			else
				pn_second_2.add(tf[i]);
		}
		try {
			String sql="SELECT * FROM student_stayinfo WHERE stu_num='"+stu_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_modify.addActionListener(new ActionListener() { //修改住宿信息
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<6;i++)
					tf[i].setEditable(true);
				tf[1].setText("");
				tf[1].setEditable(false);
				if(x==1) {
					pn_first_2.remove(bt_modify);
					pn_first_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_first_2.add(bt_save);
					pn_first_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
				else {
					pn_second_2.remove(bt_modify);
					pn_second_2.remove(bt_delete);
					sysAdminJFrame.repaint();
					pn_second_2.add(bt_save);
					pn_second_2.add(bt_cancel);
					sysAdminJFrame.validate();
				}
			}
		});
		bt_delete.addActionListener(new ActionListener() { //删除住宿信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该住宿信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				String sql; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				try { 
					if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
						lb_tips.setText("请填写完整！");
						functionTips();
					}
					else { //已填写完整
						//检测学号是否存在
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //学号不存在，进行提示
			            	lb_tips.setText("学号不存在！");
							functionTips();
			            }
			            else {
			            	//检测学号是否重复
			            	boolean flag=false; //代表学号是否重复，或宿舍是否为原宿舍
							sql="SELECT stu_num FROM stayinfo WHERE stu_num='"+tf[0].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
				            rs=ps.executeQuery(sql);
				            flag=rs.next();
				            rs.first();
				            flag=flag&&!(tf[0].getText().equals(stay.stu_num));
				            if(flag) { //学号重复，即该学生已经住宿，进行提示
				            	lb_tips.setText("该学生已经住宿！");
								functionTips();
								flag=false;
				            }
				            else {
				            	//检测宿舍是否存在
				            	sql="SELECT * FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            if(!rs.next()) { //宿舍不存在，进行提示
					            	lb_tips.setText("宿舍不存在！");
									functionTips();
					            }
					            else {
					            	//检测宿舍床位是否已满
					            	sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
						            int now_bed=rs.getInt("bed_surplus");
						            flag=tf[2].getText().equals(stay.floor_num)&&tf[3].getText().equals(String.valueOf(stay.layer))&&tf[4].getText().equals(String.valueOf(stay.room_num));
						            if(now_bed==0&&!flag) { //剩余床位数为0且不为原宿舍，即新宿舍床位已满，进行提示
						            	lb_tips.setText("该宿舍床位已满！");
										functionTips();
						            }
						            else { //输入数据符合要求，进行保存
						            	try {
						            		//修改stayinfo表数据
						            		sql="UPDATE stayinfo SET stu_num=?,floor_num=?,layer=?,room_num=?,time=? WHERE stu_num='"+stay.stu_num+"'";
											ps=HomePage.connection.prepareStatement(sql);
											ps.setString(1,tf[0].getText()); //SQL语句第一个?值
											ps.setString(2,tf[2].getText()); //SQL语句第二个?值
											ps.setInt(3,Integer.parseInt(tf[3].getText())); //SQL语句第三个?值
											ps.setInt(4,Integer.parseInt(tf[4].getText())); //SQL语句第四个?值
											ps.setString(5,tf[5].getText()); //SQL语句第五个?值
											ps.executeUpdate(); //更新，执行修改操作
											if(!flag) { //若修改了宿舍，则修改dormitory表的相关宿舍的剩余床位数
												//原宿舍剩余床位数+1
												sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
												ps=HomePage.connection.prepareStatement(sql);
									            rs=ps.executeQuery(sql);
									            rs.next();
									            int old_bed=rs.getInt("bed_surplus");
												sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
												ps=HomePage.connection.prepareStatement(sql);
												ps.setInt(1,old_bed+1); //SQL语句第一个?值
												ps.executeUpdate(); //更新，执行修改操作
												//现宿舍剩余床位数-1
												sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+tf[2].getText()+"' AND layer='"+tf[3].getText()+"' AND room_num='"+tf[4].getText()+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setInt(1,now_bed-1); //SQL语句第一个?值
												ps.executeUpdate(); //更新，执行修改操作
											}
											//若修改了学号，原学生住宿改为否，则现学生住宿改为是
											if(!tf[0].getText().equals(stay.stu_num)) {
												sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setString(1,"否"); //SQL语句第一个?值
												ps.executeUpdate(); //更新，执行修改操作
												sql="UPDATE student SET yes_no=? WHERE stu_num='"+tf[0].getText()+"'";
												ps=HomePage.connection.prepareStatement(sql);
												ps.setString(1,"是"); //SQL语句第一个?值
												ps.executeUpdate(); //更新，执行修改操作
											}
											//获取修改后的住宿信息的学生名字
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
							    				sysAdminJFrame.repaint();
							    				pn_first_2.add(bt_modify);
							    				pn_first_2.add(bt_delete);
							    				sysAdminJFrame.validate();
						                    }
						                    else {
						                    	pn_second_2.remove(bt_save);
						                    	pn_second_2.remove(bt_cancel);
							    				sysAdminJFrame.repaint();
							    				pn_second_2.add(bt_modify);
							    				pn_second_2.add(bt_delete);
							    				sysAdminJFrame.validate();
						                    }
						        		}catch(SQLException se){
						        			lb_tips.setText("数据库操作出错！");
						    				functionTips();
						                    se.printStackTrace();
						                }catch(NumberFormatException ne){
						        			lb_tips.setText("数据转换出错！");
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
		bt_cancel.addActionListener(new ActionListener() { //取消
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
                	sysAdminJFrame.repaint();
    				pn_first_2.add(bt_modify);
    				pn_first_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
            	else {
            		pn_second_2.remove(bt_save);
            		pn_second_2.remove(bt_cancel);
                	sysAdminJFrame.repaint();
                	pn_second_2.add(bt_modify);
                	pn_second_2.add(bt_delete);
    				sysAdminJFrame.validate();
            	}
			}
		});
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void addStayInfo() { //添加住宿信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_time=new JButton("获取当前时间",new ImageIcon("image/time.png"));
		bt_time.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("楼    号：");
		lb[3].setText("楼    层：");
		lb[4].setText("宿 舍 号：");
		lb[5].setText("入住时间：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==1)
				tf[i].setEditable(false);
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_first_2.add(tf[i]);
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_time.addActionListener(new ActionListener() { //获取当前时间
			public void actionPerformed(ActionEvent e) {
				Date date=new Date();
				SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
				tf[5].setText(timeFormat.format(date));
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认添加住宿信息
			public void actionPerformed(ActionEvent e) {
				if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||tf[4].getText().equals("")||tf[5].getText().equals("")) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测学号是否存在
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //学号不存在，进行提示
			            	lb_tips.setText("学号不存在！");
							functionTips();
			            }
			            else {
			            	//检测学号是否重复
							sql="SELECT stu_num FROM stayinfo WHERE stu_num='"+tf[0].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(rs.next()) { //学号重复，即该学生已经住宿，进行提示
				            	lb_tips.setText("该学生已经住宿！");
								functionTips();
				            }
				            else {
				            	//检测宿舍是否存在
				            	sql="SELECT * FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer="+tf[3].getText()+" AND room_num="+tf[4].getText();
								ps=HomePage.connection.prepareStatement(sql);
					            rs=ps.executeQuery(sql);
					            if(!rs.next()) { //宿舍不存在，进行提示
					            	lb_tips.setText("宿舍不存在！");
									functionTips();
					            }
					            else {
					            	//检测宿舍床位是否已满
					            	sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+tf[2].getText()+"' AND layer="+tf[3].getText()+" AND room_num="+tf[4].getText();
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
						            int now_bed=rs.getInt("bed_surplus");
						            if(now_bed==0) { //剩余床位数为0，即宿舍床位已满，进行提示
						            	lb_tips.setText("该宿舍床位已满！");
										functionTips();
						            }
						            else { //输入数据符合要求，进行保存
						            	try {
						            		//获取住宿学生姓名
						            		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						            		sql="SELECT name FROM student WHERE stu_num='"+tf[0].getText()+"'";
											ps=HomePage.connection.prepareStatement(sql);
								            rs=ps.executeQuery(sql);
								            rs.next();
						            		String stu_name=rs.getString("name");
						            		stay=new Stay(tf[0].getText(),stu_name,tf[2].getText(),tf[3].getText(),tf[4].getText(),tf[5].getText());
						            		//添加住宿信息
						            		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)"; //SQL语句
							            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
							            	ps.setString(1,stay.stu_num); //SQL语句第一个?值
											ps.setString(2,stay.floor_num); //SQL语句第二个?值
											ps.setInt(3,stay.layer); //SQL语句第三个?值
											ps.setInt(4,stay.room_num); //SQL语句第四个?值
											ps.setString(5,timeFormat.format(stay.time)); //SQL语句第五个?值
											ps.executeUpdate(); //更新，执行插入操作
											//对应宿舍剩余床位数-1
											sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
											ps=HomePage.connection.prepareStatement(sql);
											ps.setInt(1,now_bed-1); //SQL语句第一个?值
											ps.executeUpdate(); //更新，执行修改操作
											//学生是否住宿改为“是”
											sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'";
											ps=HomePage.connection.prepareStatement(sql);
											ps.setString(1,"是"); //SQL语句第一个?值
											ps.executeUpdate(); //更新，执行修改操作
											lb_tips.setText("住宿信息添加成功！");
											functionTips();
											stay=null;
											for(int i=0;i<6;i++) {
												tf[i].setEditable(false);
											}
											tf[1].setText(stu_name);
											pn_first_2.remove(bt_confirm);
											sysAdminJFrame.repaint();
						                    rs.close();
						                    ps.close();
						        		}catch(SQLException se){
						        			lb_tips.setText("数据库操作出错！");
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryStayInfo_1() { //查询住宿信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("楼    号：");
		lb[3].setText("楼    层：");
		lb[4].setText("宿 舍 号：");
		lb[5].setText("入住时间：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询住宿信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_stayinfo";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
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
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryStayInfo_2(String sql) { //查询住宿信息结果
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","入住时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的住宿信息条数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的住宿信息条数
            if(count==0) { //若无符合条件的住宿信息
            	rowData=new String[1][7];
            	for(int i=0;i<7;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的住宿信息
            	rowData=new String[count][7];
                rs.first();
                int i=0;
                do { //获取符合条件的住宿信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //楼号
        			rowData[i][3]=rs.getString("layer"); //楼层
        			rowData[i][4]=rs.getString("room_num"); //宿舍号
        			rowData[i][5]=rs.getString("time"); //入住时间
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条住宿信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,5));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                visitStayInfo((String)table.getValueAt(button.getRow(),button.getColumn()-6),2,sql);
	            }
	        };
			table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(6).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JPanel allocation() { //寝室分配
		JPanel pn_allocation=new JPanel();
		pn_allocation.setLayout(null);
		pn_allocation.setSize(1045,735);
		pn_allocation.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_dorImage=new JLabel(new ImageIcon("image/宿舍.png"));
		lb_dorImage.setBounds(0,404,1045,331);
		JLabel lb=new JLabel("<html>注：<br>分配规则：按照性别、年级、院系、班级进行分配，分配宿舍为普通宿舍。<br>一键自动分配：将为系统内未住宿的学生自动分配宿舍。<br>一键重新分配：将删除所有住宿信息，重新为系统内的学生自动分配宿舍。</html>");
		lb.setFont(new Font("黑体",0,20));
		lb.setBounds(190,270,665,130);
		JButton bt1=new JButton("一键自动分配",new ImageIcon("image/一键自动分配.png"));
		bt1.setFont(new Font("黑体",0,17));
		bt1.setBounds(345,200,170,50);
		bt1.setForeground(Color.red.darker());
		bt1.setContentAreaFilled(false);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt2=new JButton("一键重新分配",new ImageIcon("image/一键重新分配.png"));
		bt2.setFont(new Font("黑体",0,17));
		bt2.setBounds(530,200,170,50);
		bt2.setForeground(Color.red.darker());
		bt2.setContentAreaFilled(false);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_allocation.add(lb_dorImage);
		pn_allocation.add(lb);
		pn_allocation.add(bt1);
		pn_allocation.add(bt2);
		bt1.addActionListener(new ActionListener() { //一键自动分配
			public void actionPerformed(ActionEvent e) {
				try {
					automaticallyAllocate();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		bt2.addActionListener(new ActionListener() { //一键重新分配，先删除所有住宿信息，再进行分配
			public void actionPerformed(ActionEvent e) {
				try {
					String sql;
					PreparedStatement ps;
					ResultSet rs;
					//删除所有住宿信息
            		sql="DELETE FROM stayinfo";
	            	ps=HomePage.connection.prepareStatement(sql);
					ps.executeUpdate();
					//所有学生是否入住改为“否”
					sql="UPDATE student SET yes_no='否'";
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.executeUpdate();
	            	//所有宿舍剩余床位改为等于总床位
	            	sql="SELECT * FROM dormitory";
	        		ps=HomePage.connection.prepareStatement(sql);
	                rs=ps.executeQuery(sql);
	            	while(rs.next()) {
	            		sql="UPDATE dormitory SET bed_surplus='"+rs.getString("bed_total")+"' WHERE floor_num='"+rs.getString("floor_num")+"' AND layer='"+rs.getString("layer")+"' AND room_num='"+rs.getString("room_num")+"'";
		            	ps=HomePage.connection.prepareStatement(sql);
		            	ps.executeUpdate();
	            	}
	            	rs.close();
	            	ps.close();
					automaticallyAllocate();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		return pn_allocation;
	}
	
	public void automaticallyAllocate() throws SQLException { //自动分配宿舍
		ArrayList<Stu> male_stu=new ArrayList<Stu>(); //男学生
		ArrayList<Stu> female_stu=new ArrayList<Stu>(); //女学生
		ArrayList<Dor> male_dor=new ArrayList<Dor>(); //男宿舍
		ArrayList<Dor> female_dor=new ArrayList<Dor>(); //女宿舍
		String sql; //SQL语句
		PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
		ResultSet rs; //ResultSet类，用来存放获取的结果集
		//获取未住宿的男学生
		sql="SELECT * FROM student WHERE sex='男' AND yes_no='否' ORDER BY grade,faculty,class";
		ps=HomePage.connection.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        while(rs.next()) {
        	Stu stu=new Stu(rs.getString("stu_num"),rs.getString("grade"),rs.getString("faculty"),rs.getString("class"));
        	male_stu.add(stu);
        }
        //获取未住宿的女学生
        sql="SELECT * FROM student WHERE sex='女' AND yes_no='否' ORDER BY grade,faculty,class";
		ps=HomePage.connection.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        while(rs.next()) {
        	Stu stu=new Stu(rs.getString("stu_num"),rs.getString("grade"),rs.getString("faculty"),rs.getString("class"));
        	female_stu.add(stu);
        }
        //获取未住人的男普通宿舍
        sql="SELECT * FROM dormitory join floor on dormitory.floor_num=floor.floor_num WHERE sex='男' AND bed_surplus=4 ORDER BY dormitory.floor_num,layer,room_num";
		ps=HomePage.connection.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        while(rs.next()) {
        	Dor dor=new Dor(rs.getString("dormitory.floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("bed_surplus"));
        	male_dor.add(dor);
        }
        //获取未住人的女普通宿舍
        sql="SELECT * FROM dormitory join floor on dormitory.floor_num=floor.floor_num WHERE sex='女' AND bed_surplus=4 ORDER BY dormitory.floor_num,layer,room_num";
		ps=HomePage.connection.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        while(rs.next()) {
        	Dor dor=new Dor(rs.getString("dormitory.floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("bed_surplus"));
        	female_dor.add(dor);
        }
        if(male_stu.size()==0&&female_stu.size()==0) {
        	lb_tips.setText("无未住宿的学生，无需分配！");
			functionTips();
        }
        else if(male_stu.size()>male_dor.size()*4) {
        	lb_tips.setText("男生宿舍不足，请添加宿舍或手动分配！");
			functionTips();
        }
        else if(female_stu.size()>female_dor.size()*4) {
        	lb_tips.setText("女生宿舍不足，请添加宿舍或手动分配！");
			functionTips();
        }
        else {
        	int i=0,j=0;
        	boolean flag=false; //代表是否有学生未分配宿舍，需手动分配
        	SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
        	for(i=0,j=0;i<male_stu.size()&&j<male_dor.size();i++) { //分配男生宿舍
        		Stu stu=male_stu.get(i);
        		Dor dor=male_dor.get(j);
            	if(dor.bed_surplus==4) { //当前宿舍剩余床位数为4，直接安排当前学生入住
            		//添加住宿信息
            		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)";
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.setString(1,stu.stu_num);
					ps.setString(2,dor.floor_num);
					ps.setInt(3,dor.layer);
					ps.setInt(4,dor.room_num);
					ps.setString(5,timeFormat.format(new Date()));
					ps.executeUpdate();
					//学生是否入住改为“是”
					sql="UPDATE student SET yes_no='是' WHERE stu_num='"+stu.stu_num+"'";
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.executeUpdate();
	            	//宿舍剩余床位-1
	            	dor.bed_surplus-=1;
	            	sql="UPDATE dormitory SET bed_surplus="+dor.bed_surplus+" WHERE floor_num='"+dor.floor_num+"' AND layer="+dor.layer+" AND room_num="+dor.room_num;
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.executeUpdate();
            	}
            	else if(dor.bed_surplus==0) { //当前宿舍剩余床位数为0，跳到下一宿舍
            		i--;
            		j++;
            	}
            	else { //当前宿舍剩余床位数为1,2,3
            		if(stu.grade==male_stu.get(i-1).grade&&stu.faculty.equals(male_stu.get(i-1).faculty)&&stu.clas.equals(male_stu.get(i-1).clas)) {
            			//若当前学生与上一个学生同年级同院系同专业，则当前学生入住该宿舍
            			//添加住宿信息
                		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)";
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.setString(1,stu.stu_num);
    					ps.setString(2,dor.floor_num);
    					ps.setInt(3,dor.layer);
    					ps.setInt(4,dor.room_num);
    					ps.setString(5,timeFormat.format(new Date()));
    					ps.executeUpdate();
    					//学生是否入住改为“是”
    					sql="UPDATE student SET yes_no='是' WHERE stu_num='"+stu.stu_num+"'";
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.executeUpdate();
    	            	//宿舍剩余床位-1
    	            	dor.bed_surplus-=1;
    	            	sql="UPDATE dormitory SET bed_surplus="+dor.bed_surplus+" WHERE floor_num='"+dor.floor_num+"' AND layer="+dor.layer+" AND room_num="+dor.room_num;
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.executeUpdate();
            		}
            		else { //若当前学生与上一个学生不同年级或不同院系或不同专业，则跳到下一宿舍
            			i--;
                		j++;
            		}
            	}
            }
        	if(i<male_stu.size()) //分配完宿舍，若i小于未住宿男学生集合的个数，则代表有部分男学生尚未分配宿舍，需手动分配
        		flag=true;
        	for(i=0,j=0;i<female_stu.size()&&j<female_dor.size();i++) { //分配女生宿舍
        		Stu stu=female_stu.get(i);
        		Dor dor=female_dor.get(j);
            	if(dor.bed_surplus==4) { //当前宿舍剩余床位数为4，直接安排当前学生入住
            		//添加住宿信息
            		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)";
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.setString(1,stu.stu_num);
					ps.setString(2,dor.floor_num);
					ps.setInt(3,dor.layer);
					ps.setInt(4,dor.room_num);
					ps.setString(5,timeFormat.format(new Date()));
					ps.executeUpdate();
					//学生是否入住改为“是”
					sql="UPDATE student SET yes_no='是' WHERE stu_num='"+stu.stu_num+"'";
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.executeUpdate();
	            	//宿舍剩余床位-1
	            	dor.bed_surplus-=1;
	            	sql="UPDATE dormitory SET bed_surplus="+dor.bed_surplus+" WHERE floor_num='"+dor.floor_num+"' AND layer="+dor.layer+" AND room_num="+dor.room_num;
	            	ps=HomePage.connection.prepareStatement(sql);
	            	ps.executeUpdate();
            	}
            	else if(dor.bed_surplus==0) { //当前宿舍剩余床位数为0，跳到下一宿舍
            		i--;
            		j++;
            	}
            	else { //当前宿舍剩余床位数为1,2,3
            		if(stu.grade==female_stu.get(i-1).grade&&stu.faculty.equals(female_stu.get(i-1).faculty)&&stu.clas.equals(female_stu.get(i-1).clas)) {
            			//若当前学生与上一个学生同年级同院系同专业，则当前学生入住该宿舍
            			//添加住宿信息
                		sql="INSERT INTO stayinfo VALUES(?,?,?,?,?)";
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.setString(1,stu.stu_num);
    					ps.setString(2,dor.floor_num);
    					ps.setInt(3,dor.layer);
    					ps.setInt(4,dor.room_num);
    					ps.setString(5,timeFormat.format(new Date()));
    					ps.executeUpdate();
    					//学生是否入住改为“是”
    					sql="UPDATE student SET yes_no='是' WHERE stu_num='"+stu.stu_num+"'";
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.executeUpdate();
    	            	//宿舍剩余床位-1
    	            	dor.bed_surplus-=1;
    	            	sql="UPDATE dormitory SET bed_surplus="+dor.bed_surplus+" WHERE floor_num='"+dor.floor_num+"' AND layer="+dor.layer+" AND room_num="+dor.room_num;
    	            	ps=HomePage.connection.prepareStatement(sql);
    	            	ps.executeUpdate();
            		}
            		else { //若当前学生与上一个学生不同年级或不同院系或不同专业，则跳到下一宿舍
            			i--;
                		j++;
            		}
            	}
            }
        	if(i<female_stu.size()) //分配完宿舍，若i小于未住宿女学生集合的个数，则代表有部分女学生尚未分配宿舍，需手动分配
        		flag=true;
        	if(flag) {
        		lb_tips.setText("分配完毕，有部分学生尚未分配，请手动分配！");
    			functionTips();
        	}
        	else {
        		lb_tips.setText("全部分配完毕！");
    			functionTips();
        	}
        	rs.close();
        	ps.close();
        }
	}
	
	public JTabbedPane inout() { //出入登记
		JTabbedPane tp_inout=new JTabbedPane();
		tp_inout.setFont(new Font("黑体",0,25));
		tp_inout.setBounds(0,0,1045,735);
		allInout();
		tp_inout.addTab(" 全部 ",pn_first);
		queryInoutInfo_1();
		tp_inout.addTab(" 查询 ",pn_second);
		return tp_inout;
	}
	
	public void allInout() { //所有出入信息
		String[] columnNames={"学号","姓名","宿舍楼","类别","时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_inout视图信息
			String sql="SELECT * FROM student_inout"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取出入信息元组总数
            if(count==0) { //若student_inout视图无元组
            	rowData=new String[1][6];
            	for(int i=0;i<6;i++)
            		rowData[0][i]="无";
            }
            else { //若student_inout视图有元组
            	rowData=new String[count][6];
                rs.first();
                int i=0;
                do { //获取所有出入信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //宿舍楼
        			rowData[i][3]=rs.getString("category"); //类别
        			rowData[i][4]=rs.getString("time"); //时间
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条出入信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("登记",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_add);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,6));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“删除”按钮，可删除出入信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                String sql="DELETE FROM in_out WHERE stu_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-5)+"' AND floor_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-3)+"' AND category='"+(String)table.getValueAt(button.getRow(),button.getColumn()-2)+"' AND time='"+(String)table.getValueAt(button.getRow(),button.getColumn()-1)+"'";
	                lb_tips.setText("是否确定删除该出入信息？");
	                choiceTips(sql);
	                allInout();
	            }
	        };
			table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender("删除")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"删除");
			table.getColumnModel().getColumn(5).setCellEditor(editor); //设置表格的编辑器
		}
		bt_add.addActionListener(new ActionListener() { //添加出入信息
			public void actionPerformed(ActionEvent e) {
				pn_first_2.removeAll();
				addInoutInfo();
			}
		});
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void addInoutInfo() { //添加出入信息
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,695);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_confirm=new JButton("确认添加",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(447,520,150,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_time=new JButton("获取当前时间",new ImageIcon("image/time.png"));
		bt_time.setFont(new Font("黑体",0,17));
		bt_time.setBounds(765,415,170,40);
		bt_time.setContentAreaFilled(false);
		bt_time.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,205,200,200);
		String[] inorout= {"--------请选择--------","          出","          入"};
		JComboBox<String> cb_inorout=new JComboBox<String>(inorout);
		cb_inorout.setFont(new Font("黑体",0,20));
		cb_inorout.setBounds(392,360,260,40);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_confirm);
		pn_first_2.add(bt_time);
		pn_first_2.add(lb_infoImage);
		pn_first_2.add(cb_inorout);
		JLabel[] lb=new JLabel[5];
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("学  号：");
		lb[1].setText("姓  名：");
		lb[2].setText("宿舍楼：");
		lb[3].setText("类  别：");
		lb[4].setText("时  间：");
		JTextField[] tf=new JTextField[4];
		for(int i=0;i<4;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			if(i==3)
				tf[i].setBounds(392,195+i*55+55,260,40);
			pn_first_2.add(tf[i]);
		}
		tf[1].setEditable(false);
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_time.addActionListener(new ActionListener() { //获取当前时间
			public void actionPerformed(ActionEvent e) {
				Date date=new Date();
				SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tf[3].setText(timeFormat.format(date));
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认添加出入信息
			public void actionPerformed(ActionEvent e) {
				String inorout=null;
				if(tf[0].getText().equals("")||tf[2].getText().equals("")||tf[3].getText().equals("")||cb_inorout.getSelectedIndex()==0) {
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //已填写完整
					if((String)cb_inorout.getSelectedItem()=="          出")
						inorout="出";
					else if((String)cb_inorout.getSelectedItem()=="          入")
						inorout="入";
					String sql; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ResultSet rs; //ResultSet类，用来存放获取的结果集
					try {
						//检测学号是否存在
						sql="SELECT stu_num FROM student WHERE stu_num='"+tf[0].getText()+"'";
						ps=HomePage.connection.prepareStatement(sql);
			            rs=ps.executeQuery(sql);
			            if(!rs.next()) { //学号不存在，进行提示
			            	lb_tips.setText("学号不存在！");
							functionTips();
			            }
			            else {
			            	//检测宿舍楼是否存在
							sql="SELECT floor_num FROM floor WHERE floor_num='"+tf[2].getText()+"'";
							ps=HomePage.connection.prepareStatement(sql);
				            rs=ps.executeQuery(sql);
				            if(!rs.next()) { //宿舍楼不存在，进行提示
				            	lb_tips.setText("宿舍楼不存在！");
								functionTips();
				            }
				            else { //输入数据符合要求，进行添加
				            	try {
				            		//获取住宿学生姓名
				            		sql="SELECT name FROM student WHERE stu_num='"+tf[0].getText()+"'";
									ps=HomePage.connection.prepareStatement(sql);
						            rs=ps.executeQuery(sql);
						            rs.next();
				            		String stu_name=rs.getString("name");
				            		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				            		inout=new InOut(tf[0].getText(),stu_name,tf[2].getText(),inorout,tf[3].getText());
				            		//添加出入信息
				            		sql="INSERT INTO in_out VALUES(?,?,?,?)"; //SQL语句
					            	ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
					            	ps.setString(1,inout.stu_num); //SQL语句第一个?值
									ps.setString(2,inout.floor_num); //SQL语句第二个?值
									ps.setString(3,inout.category); //SQL语句第三个?值
									ps.setString(4,timeFormat.format(inout.time)); //SQL语句第四个?值
									ps.executeUpdate(); //更新，执行插入操作
									lb_tips.setText("出入登记成功！");
									functionTips();
									for(int i=0;i<4;i++) {
										tf[i].setEditable(false);
									}
									cb_inorout.setEnabled(false);
									tf[1].setText(inout.name);
									inout=null;
									pn_first_2.remove(bt_time);
									pn_first_2.remove(bt_confirm);
									sysAdminJFrame.repaint();
				                    rs.close();
				                    ps.close();
				        		}catch(SQLException se){
				        			lb_tips.setText("数据库操作出错！");
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
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public void queryInoutInfo_1() { //查询出入信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(285,190+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("学  号：");
		lb[1].setText("姓  名：");
		lb[2].setText("宿舍楼：");
		lb[3].setText("类  别：");
		lb[4].setText("时  间：");
		JTextField[] tf=new JTextField[5];
		for(int i=0;i<5;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,195+i*55,260,40);
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询出入信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_inout";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
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
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryInoutInfo_2(String sql) { //查询出入信息结果
		String[] columnNames={"学号","姓名","宿舍楼","类别","时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的出入信息条数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的出入信息条数
            if(count==0) { //若无符合条件的出入信息
            	rowData=new String[1][6];
            	for(int i=0;i<6;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的出入信息
            	rowData=new String[count][6];
                rs.first();
                int i=0;
                do { //获取符合条件的出入信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //宿舍楼
        			rowData[i][3]=rs.getString("category"); //类别
        			rowData[i][4]=rs.getString("time"); //时间
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条出入信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,6));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“删除”按钮，可删除出入信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                String delete_sql="DELETE FROM in_out WHERE stu_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-5)+"' AND floor_num='"+(String)table.getValueAt(button.getRow(),button.getColumn()-3)+"' AND category='"+(String)table.getValueAt(button.getRow(),button.getColumn()-2)+"' AND time='"+(String)table.getValueAt(button.getRow(),button.getColumn()-1)+"'";
	                lb_tips.setText("是否确定删除该出入信息？");
	                choiceTips(delete_sql);
	                queryInoutInfo_2(sql);
	            }
	        };
			table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender("删除")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"删除");
			table.getColumnModel().getColumn(5).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public JTabbedPane repairManage() { //宿舍报修管理
		JTabbedPane tp_repair=new JTabbedPane();
		tp_repair.setFont(new Font("黑体",0,25));
		tp_repair.setBounds(0,0,1045,735);
		allRepair();
		tp_repair.addTab(" 全部 ",pn_first);
		queryRepairInfo_1();
		tp_repair.addTab(" 查询 ",pn_second);
		return tp_repair;
	}
	
	public void allRepair() { //所有宿舍报修
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","报修详细","是否处理","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_repair视图信息
			String sql="SELECT * FROM student_repair"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取报修信息元组总数
            if(count==0) { //若student_repair视图无元组
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="无";
            }
            else { //若student_repair视图有元组
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //获取所有报修信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //楼号
        			rowData[i][3]=rs.getString("layer"); //楼层
        			rowData[i][4]=rs.getString("room_num"); //宿舍号
        			rowData[i][5]=rs.getString("info"); //报修详细
        			if(rs.getString("yes_no").equals("是"))
        				rowData[i][6]="<html><font color='green'>是</font></html>";
        			else
        				rowData[i][6]="<html><font color='red'>否</font></html>";
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first.setLayout(null);
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,695);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("  共"+count+"条宿舍报修信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,7));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                int row=button.getRow();
	                String yesno;
	                if(((String)table.getValueAt(row,button.getColumn()-1)).equals("<html><font color='green'>是</font></html>"))
	                	yesno="是";
	                else
	                	yesno="否";
	                pn_first_2.removeAll();
	                visitRepairInfo((String)table.getValueAt(row,button.getColumn()-7),(String)table.getValueAt(row,button.getColumn()-5),(String)table.getValueAt(row,button.getColumn()-4),(String)table.getValueAt(row,button.getColumn()-3),(String)table.getValueAt(row,button.getColumn()-2),yesno,1,null);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //设置表格的编辑器
		}
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_first.removeAll();
		sysAdminJFrame.repaint();
		pn_first.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitRepairInfo(String stu_num, String floor_num, String layer, String room_num, String info, String yes_no,int x, String query_sql) { //查看宿舍报修信息，可删除
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
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_delete=new JButton("删除宿舍报修",new ImageIcon("image/delete.png"));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(755,360,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JToggleButton tbt_yesno=new JToggleButton();
		tbt_yesno.setBounds(525,100+5*55,65,40);
		tbt_yesno.setBorderPainted(false);
		tbt_yesno.setContentAreaFilled(false);
		tbt_yesno.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbt_yesno.setIcon(new ImageIcon("image/否.png"));
		tbt_yesno.setSelectedIcon(new ImageIcon("image/是.png"));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/宿舍报修.png"));
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
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(140,95+i*55,130,50);
			if(x==1)
				pn_first_2.add(lb[i]);
			else
				pn_second_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("楼    号：");
		lb[3].setText("楼    层：");
		lb[4].setText("宿 舍 号：");
		lb[5].setText("是否处理：");
		lb[6].setText("报修详细：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
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
		JScrollPane scrollPane=new JScrollPane(); //滚动条
		scrollPane.setBounds(272,100+6*55,500,160);
		scrollPane.setViewportView(ta_info);
		if(x==1)
			pn_first_2.add(scrollPane);
		else
			pn_second_2.add(scrollPane);
		try {
			String sql="SELECT * FROM student_repair WHERE stu_num='"+stu_num+"' AND floor_num='"+floor_num+"' AND layer='"+layer+"' AND room_num='"+room_num+"' AND info='"+info+"' AND yes_no='"+yes_no+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            while(rs.next()) {
            	repair=new Repair(rs.getString("stu_num"),rs.getString("name"),rs.getString("floor_num"),rs.getString("layer"),rs.getString("room_num"),rs.getString("info"),rs.getString("yes_no"));
            	tf[0].setText(repair.stu_num);
            	tf[1].setText(repair.name);
            	tf[2].setText(repair.floor_num);
            	tf[3].setText(String.valueOf(repair.layer));
            	tf[4].setText(String.valueOf(repair.room_num));
            	tf[5].setText(repair.yes_no);
            	ta_info.setText(repair.info);
            	if(repair.yes_no.equals("是"))
            		tbt_yesno.setSelected(true); //选中为是
            	else
            		tbt_yesno.setSelected(false); //未选中为否
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_delete.addActionListener(new ActionListener() { //删除宿舍报修信息
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该宿舍报修信息？");
				if(x==1)
					choiceTips("");
				else
					choiceTips(query_sql);
			}
		});
		tbt_yesno.addChangeListener(new ChangeListener() { //是否处理
	        public void stateChanged(ChangeEvent e) {
	            if(tbt_yesno.isSelected())
	            	tf[5].setText("是");
	            else
	            	tf[5].setText("否");
	            try {
					String sql="UPDATE repair SET yes_no=? WHERE stu_num='"+repair.stu_num+"' AND floor_num='"+repair.floor_num+"' AND layer="+repair.layer+" AND room_num="+repair.room_num+" AND info='"+repair.info+"' AND yes_no='"+repair.yes_no+"'"; //SQL语句
					PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
					ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
					ps.setString(1,tf[5].getText()); //SQL语句第一个?值
	            	ps.executeUpdate(); //更新，执行修改操作
	            	repair.yes_no=tf[5].getText();
		            ps.close();
				}catch(SQLException se){
					lb_tips.setText("数据库操作出错！");
					functionTips();
		            se.printStackTrace();
		        }
	        }
	    });
		if(x==1) {
			pn_first.removeAll();
			sysAdminJFrame.repaint();
			pn_first.add(pn_first_2);
			sysAdminJFrame.validate();
		}
		else {
			pn_second.removeAll();
			sysAdminJFrame.repaint();
			pn_second.add(pn_second_2);
			sysAdminJFrame.validate();
		}
	}
	
	public void queryRepairInfo_1() { //查询宿舍报修信息
		pn_second.setLayout(null);
		pn_second_1.removeAll();
		pn_second_1.setLayout(null);
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_query=new JButton("查询",new ImageIcon("image/query.png"));
		bt_query.setFont(new Font("黑体",0,17));
		bt_query.setBounds(447,520,150,50);
		bt_query.setContentAreaFilled(false);
		bt_query.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/宿舍报修.png"));
		lb_infoImage.setBounds(750,205,200,200);
		pn_second_1.add(bt_query);
		pn_second_1.add(lb_infoImage);
		JLabel[] lb=new JLabel[6];
		for(int i=0;i<6;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(260,150+i*55,130,50);
			pn_second_1.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("楼    号：");
		lb[3].setText("楼    层：");
		lb[4].setText("宿 舍 号：");
		lb[5].setText("是否处理：");
		JTextField[] tf=new JTextField[6];
		for(int i=0;i<6;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(392,155+i*55,260,40);
			if(i==3||i==4)
				tf[i].setDocument(new NumLimit()); //限制文本框只能输入数字
			pn_second_1.add(tf[i]);
		}
		bt_query.addActionListener(new ActionListener() { //查询宿舍报修信息
			public void actionPerformed(ActionEvent e) {
				String sql="SELECT * FROM student_repair";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
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
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void queryRepairInfo_2(String sql) { //查询宿舍报修信息结果
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","报修详细","是否处理","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //查询到的宿舍报修信息条数
		try {
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取查询到的宿舍报修信息条数
            if(count==0) { //若无符合条件的宿舍报修信息
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="无";
            }
            else { //若有符合条件的宿舍报修信息
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //获取符合条件的宿舍报修信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("floor_num"); //楼号
        			rowData[i][3]=rs.getString("layer"); //楼层
        			rowData[i][4]=rs.getString("room_num"); //宿舍号
        			rowData[i][5]=rs.getString("info"); //报修详细
        			if(rs.getString("yes_no").equals("是"))
        				rowData[i][6]="<html><font color='green'>是</font></html>";
        			else
        				rowData[i][6]="<html><font color='red'>否</font></html>";
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_second_1.removeAll();
		pn_second_1.setBounds(0,0,1045,695);
		pn_second_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel(" 共查询到"+count+"条宿舍报修信息！ ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,7));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_second_1.add(pn_top);
		pn_second_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可修改和删除信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                int row=button.getRow();
	                String yesno;
	                if(((String)table.getValueAt(row,button.getColumn()-1)).equals("<html><font color='green'>是</font></html>"))
	                	yesno="是";
	                else
	                	yesno="否";
	                pn_first_2.removeAll();
	                visitRepairInfo((String)table.getValueAt(row,button.getColumn()-7),(String)table.getValueAt(row,button.getColumn()-5),(String)table.getValueAt(row,button.getColumn()-4),(String)table.getValueAt(row,button.getColumn()-3),(String)table.getValueAt(row,button.getColumn()-2),yesno,2,sql);
	            }
	        };
			table.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(7).setCellEditor(editor); //设置表格的编辑器
		}
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_second.removeAll();
		sysAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		sysAdminJFrame.validate();
	}
	
	public void allAdvice() { //所有建议与反馈
		String[] columnNames={"学号","姓名","建议与反馈","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_advice视图信息
			String sql="SELECT * FROM student_advice"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取建议与反馈信息元组总数
            if(count==0) { //若student_advice视图无元组
            	rowData=new String[1][4];
            	for(int i=0;i<4;i++)
            		rowData[0][i]="无";
            }
            else { //若student_advice视图有元组
            	rowData=new String[count][4];
                rs.first();
                int i=0;
                do { //获取所有建议与反馈信息
                	rowData[i][0]=rs.getString("stu_num"); //学号
        			rowData[i][1]=rs.getString("name"); //姓名
        			rowData[i][2]=rs.getString("info"); //建议与反馈详细信息
        			i++;
                } while(rs.next());
            }
            rs.close();
            ps.close();
		}catch(SQLException e){
            e.printStackTrace();
        }
		//界面
		pn_first_1.removeAll();
		pn_first_1.setBounds(0,0,1045,735);
		pn_first_1.setLayout(null);
		JPanel pn_top=new JPanel();
		pn_top.setBounds(0,0,1045,50);
		pn_top.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_num=new JLabel("  共"+count+"条建议与反馈！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(columnNames,rowData,8));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",Font.BOLD,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,685);
		pn_first_1.add(pn_top);
		pn_first_1.add(scrollPane);
		if(count!=0) {
			MyEvent e=new MyEvent() { //点击“查看”按钮，查看信息，可查看信息
	            public void invoke(ActionEvent e) {
	                MyButton button=(MyButton)e.getSource();
	                pn_first_2.removeAll();
	                visitAdviceInfo((String)table.getValueAt(button.getRow(),button.getColumn()-3),(String)table.getValueAt(button.getRow(),button.getColumn()-1));
	            }
	        };
			table.getColumnModel().getColumn(3).setCellRenderer(new MyButtonRender("查看")); //设置表格的渲染器
			MyButtonEditor editor=new MyButtonEditor(e,"查看");
			table.getColumnModel().getColumn(3).setCellEditor(editor); //设置表格的编辑器
		}
		bt_export.addActionListener(new ActionListener() { //将表格导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(sysAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		pn_function.removeAll();
		sysAdminJFrame.repaint();
		pn_function.add(pn_first_1);
		sysAdminJFrame.validate();
	}
	
	public void visitAdviceInfo(String stu_num, String info) { //查看建议与反馈信息，可删除
		pn_first_2.removeAll();
		pn_first_2.setLayout(null);
		pn_first_2.setBounds(0,0,1045,735);
		pn_first_2.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_delete=new JButton("删除建议与反馈",new ImageIcon("image/delete.png"));
		bt_delete.setFont(new Font("黑体",0,17));
		bt_delete.setBounds(805,410,190,40);
		bt_delete.setForeground(Color.red);
		bt_delete.setContentAreaFilled(false);
		bt_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/建议与反馈.png"));
		lb_infoImage.setBounds(800,200,200,200);
		pn_first_2.add(bt_back);
		pn_first_2.add(bt_delete);
		pn_first_2.add(lb_infoImage);
		JLabel[] lb=new JLabel[3];
		for(int i=0;i<3;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(140,225+i*55,130,50);
			pn_first_2.add(lb[i]);
		}
		lb[0].setText("学    号：");
		lb[1].setText("姓    名：");
		lb[2].setText("建议与反馈：");
		lb[2].setBounds(117,225+2*55,150,50);
		JTextField[] tf=new JTextField[2];
		for(int i=0;i<2;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
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
		JScrollPane scrollPane=new JScrollPane(); //滚动条
		scrollPane.setBounds(272,230+2*55,500,160);
		scrollPane.setViewportView(ta_info);
		pn_first_2.add(scrollPane);
		try {
			String sql="SELECT * FROM student_advice WHERE stu_num='"+stu_num+"' AND info='"+info+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
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
		bt_back.addMouseListener(new MouseListener() { //返回
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
		bt_delete.addActionListener(new ActionListener() { //删除建议与反馈
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否确定删除该建议与反馈信息？");
				choiceTips("");
			}
		});
		pn_function.removeAll();
		sysAdminJFrame.repaint();
		pn_function.add(pn_first_2);
		sysAdminJFrame.validate();
	}
	
	public JPanel escape() { //删库跑路
		JPanel pn_escape=new JPanel();
		pn_escape.setLayout(null);
		pn_escape.setSize(1045,735);
		pn_escape.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_runImage=new JLabel(new ImageIcon("image/跑.png"));
		lb_runImage.setBounds(492,50,60,41);
		JLabel lb1=new JLabel("请输入删库跑路专用密码");
		lb1.setFont(new Font("黑体",0,35));
		lb1.setBounds(330,100,385,50);
		JLabel lb2=new JLabel("<html>注：<br>输入密码并确认后，将删除系统数据库，届时该系统将无法再使用，需重新建立数据库才可再使用。<br><font color='red'>该操作不可撤销，请慎重考虑！</font></html>");
		lb2.setFont(new Font("黑体",0,20));
		lb2.setBounds(372,500,300,130);
		JPasswordField pf=new JPasswordField();
		pf.setFont(new Font(null,0,25));
		pf.setBounds(392,250,260,40);
		JButton bt_confirm=new JButton("确认删库跑路",new ImageIcon("image/confirm.png"));
		bt_confirm.setFont(new Font("黑体",0,17));
		bt_confirm.setBounds(437,350,170,50);
		bt_confirm.setContentAreaFilled(false);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_escape.add(lb_runImage);
		pn_escape.add(lb1);
		pn_escape.add(lb2);
		pn_escape.add(pf);
		pn_escape.add(bt_confirm);
		bt_confirm.addActionListener(new ActionListener() { //确认删库跑路
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(pf.getPassword()).equals("ESCAPE")) { //密码正确
					lb_tips.setText("是否确定删库跑路？");
					choiceTips("");
				}
				else { //密码错误
					lb_tips.setText("密码错误！");
					functionTips();
				}
			}
		});
		return pn_escape;
	}
	
	public void functionTips() { //操作时弹出的提示信息窗口
		JDialog tips=new JDialog(sysAdminJFrame,"  提示",true);
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
	
	public void choiceTips(String str) { //操作时弹出的提示选择窗口
		JDialog choiceTips=new JDialog(sysAdminJFrame,"  提示",true);
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
				yesOperation(str);
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
					yesOperation(str);
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
	
	public void yesOperation(String str) { //选择“是”之后，根据提示内容，选择要进行的操作
		if(lb_tips.getText().equals("是否退出系统管理模式？")) { //确定退出系统管理模式，返回到欢迎界面
			try {
				HomePage.connection.close(); //关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sysAdminJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
		else if(lb_tips.getText().equals("是否确定删除该宿舍楼信息？")) { //确定删除宿舍楼，进行删除操作
			try {
				String sql="DELETE FROM floor WHERE floor_num='"+floor.floor_num+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("宿舍楼删除成功！");
				functionTips();
	            ps.close();
	            floor=null;
	            if(str.equals(""))
	            	allFloor();
	            else
	            	queryFloorInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该宿舍信息？")) { //确定删除宿舍，进行删除操作
			try {
				String sql="DELETE FROM dormitory WHERE floor_num='"+dormitory.floor_num+"' AND layer="+dormitory.layer+" AND room_num="+dormitory.room_num; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("宿舍删除成功！");
				functionTips();
	            ps.close();
	            dormitory=null;
	            if(str.equals(""))
	            	allDormitory();
	            else
	            	queryDormitoryInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该宿管信息？")) { //确定删除宿管，进行删除操作
			try {
				String sql="DELETE FROM admin WHERE admin_num='"+admin.admin_num+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("宿管删除成功！");
				functionTips();
	            ps.close();
	            admin=null;
	            if(str.equals(""))
	            	allAdmin();
	            else
	            	queryAdminInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该学生信息？")) { //确定删除学生，进行删除操作
			try {
				String sql="DELETE FROM student WHERE stu_num='"+student.stu_num+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("学生删除成功！");
				functionTips();
	            ps.close();
	            student=null;
	            if(str.equals(""))
	            	allStudent();
	            else
	            	queryStudentInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该住宿信息？")) { //确定删除住宿信息，进行删除操作
			try {
				//删除住宿信息
				String sql="DELETE FROM stayinfo WHERE stu_num='"+stay.stu_num+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ResultSet rs; //ResultSet类，用来存放获取的结果集
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
            	//原宿舍剩余床位数+1
				sql="SELECT bed_surplus FROM dormitory WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
				ps=HomePage.connection.prepareStatement(sql);
	            rs=ps.executeQuery(sql);
	            rs.next();
	            int old_bed=rs.getInt("bed_surplus");
				sql="UPDATE dormitory SET bed_surplus=? WHERE floor_num='"+stay.floor_num+"' AND layer="+stay.layer+" AND room_num="+stay.room_num;
				ps=HomePage.connection.prepareStatement(sql);
				ps.setInt(1,old_bed+1); //SQL语句第一个?值
				ps.executeUpdate(); //更新，执行修改操作
            	//修改该学生是否入住为“否”
            	sql="UPDATE student SET yes_no=? WHERE stu_num='"+stay.stu_num+"'"; //SQL语句
				ps=HomePage.connection.prepareStatement(sql);
				ps.setString(1,"否"); //SQL语句第一个?值
				ps.executeUpdate(); //更新，执行修改操作
				lb_tips.setText("住宿信息删除成功！");
				functionTips();
				rs.close();
	            ps.close();
	            stay=null;
	            if(str.equals(""))
	            	allStay();
	            else
	            	queryStayInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该出入信息？")) { //确定删除出入信息，进行删除操作
			try {
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(str); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("出入信息删除成功！");
				functionTips();
	            ps.close();
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该宿舍报修信息？")) { //确定删除宿舍报修信息，进行删除操作
			try {
				String sql="DELETE FROM repair WHERE stu_num='"+repair.stu_num+"' AND floor_num='"+repair.floor_num+"' AND layer="+repair.layer+" AND room_num="+repair.room_num+" AND info='"+repair.info+"' AND yes_no='"+repair.yes_no+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("宿舍报修删除成功！");
				functionTips();
	            ps.close();
	            repair=null;
	            if(str.equals(""))
	            	allRepair();
	            else
	            	queryRepairInfo_2(str);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删除该建议与反馈信息？")) { //确定删除建议与反馈信息，进行删除操作
			try {
				String sql="DELETE FROM advice WHERE stu_num='"+advice.stu_num+"' AND info='"+advice.info+"'"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除操作
				lb_tips.setText("建议与反馈删除成功！");
				functionTips();
	            ps.close();
	            advice=null;
	            allAdvice();
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
		else if(lb_tips.getText().equals("是否确定删库跑路？")) { //确定删库跑路，删除数据库
			try {
				String sql="DROP DATABASE dormitory_system"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            	ps.executeUpdate(); //更新，执行删除数据库操作
	            ps.close();
	            HomePage.connection.close(); //关闭数据库连接
	            lb_tips.setText("数据库删除成功，系统将自行关闭！");
				functionTips();
	            System.exit(0);
			}catch(SQLException e){
				lb_tips.setText("数据库操作出错！");
				functionTips();
	            e.printStackTrace();
	        }
		}
	}
}