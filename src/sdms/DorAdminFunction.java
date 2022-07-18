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

public class DorAdminFunction implements ActionListener { //宿管功能类
	String admin_num,floor_num;
	JFrame dorAdminJFrame=new JFrame("学生宿舍管理系统-----[宿管模式]");
	Container dorCon=dorAdminJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //放置各种功能页面的容器
	JLabel lb_topFunction=new JLabel(); //顶部信息栏，当前功能
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	JPanel pn_first=new JPanel(); //选项卡1
	JPanel pn_first_1=new JPanel(); //选项卡1_1
	JPanel pn_first_2=new JPanel(); //选项卡1_2
	JPanel pn_second=new JPanel(); //选项卡2
	JPanel pn_second_1=new JPanel(); //选项卡2_1
	JPanel pn_second_2=new JPanel(); //选项卡2_2
	Dormitory dormitory=null;
	Student student=null;
	Stay stay=null;
	InOut inout=null;
	Repair repair=null;
	Advice advice=null;
	
	public DorAdminFunction(String admin_num) { //整体界面
		this.admin_num=admin_num;
		try {
            String sql="SELECT floor_num FROM floor WHERE admin_num="+admin_num; //SQL语句，查询该宿管管理的宿舍楼
            PreparedStatement ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            if(rs.next()) //若该宿管有管理的宿舍楼，则获取相应宿舍楼号
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
		JPanel pn_topUser=new JPanel(); //顶部信息栏，操作者
		pn_topUser.setBackground(Color.white);
		pn_topUser.setBounds(0,0,250,30);
		JLabel lb_topUser=new JLabel("宿管："+admin_num);
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
		JButton bt_info=new JButton("个人信息"); //[个人信息]
		bt_info.setFont(new Font("黑体",0,20));
		bt_info.setContentAreaFilled(false);
		bt_info.setBounds(0,50,249,50);
		JButton bt_dormitory=new JButton("宿舍管理"); //[宿舍管理]
		bt_dormitory.setFont(new Font("黑体",0,20));
		bt_dormitory.setContentAreaFilled(false);
		bt_dormitory.setBounds(0,105,249,50);
		JButton bt_student=new JButton("学生管理"); //[学生管理]
		bt_student.setFont(new Font("黑体",0,20));
		bt_student.setContentAreaFilled(false);
		bt_student.setBounds(0,160,249,50);
		JButton bt_stay=new JButton("住宿管理"); //[住宿管理]
		bt_stay.setFont(new Font("黑体",0,20));
		bt_stay.setContentAreaFilled(false);
		bt_stay.setBounds(0,215,249,50);
		JButton bt_inout=new JButton("出入登记"); //[出入登记]
		bt_inout.setFont(new Font("黑体",0,20));
		bt_inout.setContentAreaFilled(false);
		bt_inout.setBounds(0,270,249,50);
		JButton bt_repair=new JButton("宿舍报修"); //[宿舍报修]
		bt_repair.setFont(new Font("黑体",0,20));
		bt_repair.setContentAreaFilled(false);
		bt_repair.setBounds(0,325,249,50);
		JButton bt_advice=new JButton("建议与反馈"); //[建议与反馈]
		bt_advice.setFont(new Font("黑体",0,20));
		bt_advice.setContentAreaFilled(false);
		bt_advice.setBounds(0,380,249,50);
		JButton bt_password=new JButton("修改密码"); //[修改密码]
		bt_password.setFont(new Font("黑体",0,20));
		bt_password.setContentAreaFilled(false);
		bt_password.setBounds(0,435,249,50);
		JButton bt_out=new JButton("退出"); //[退出]
		bt_out.setFont(new Font("黑体",0,20));
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
		JPanel pn_welcome=new JPanel(); //欢迎页
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("欢迎使用");
		lb_welcome.setFont(new Font("黑体",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		dorCon.add(pn_topUser);
		dorCon.add(pn_topFunction);
		dorCon.add(pn_menu);
		dorCon.add(pn_function);
		pn_function.add(pn_welcome);
		dorAdminJFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { //依据选择的菜单功能，进行相应功能
		if(e.getActionCommand().equals("退出")) {
			lb_tips.setText("是否退出宿管模式？");
			choiceTips("");
		}
		else {
			pn_function.removeAll();
			dorAdminJFrame.repaint();
			if(e.getActionCommand().equals("个人信息")) {
				lb_topFunction.setText("[个人信息]");
				pn_function.add(info());
			}
			else if(e.getActionCommand().equals("宿舍管理")) {
				lb_topFunction.setText("[宿舍管理]");
				pn_function.add(dormitoryManage());
			}
			else if(e.getActionCommand().equals("学生管理")) {
				lb_topFunction.setText("[学生管理]");
				pn_function.add(studentManage());
			}
			else if(e.getActionCommand().equals("住宿管理")) {
				lb_topFunction.setText("[住宿管理]");
				pn_function.add(stayManage());
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
			else if(e.getActionCommand().equals("修改密码")) {
				lb_topFunction.setText("[修改密码]");
				pn_function.add(changePassword());
			}
			dorAdminJFrame.validate();
		}
	}
	
	public JPanel info() { //[个人信息]功能
		String[] admin_info=new String[4]; //宿管信息
		String[] floor_info={"无","无","无","无","无"}; //管理的宿舍楼信息
		try {
			String sql="SELECT * FROM admin WHERE admin_num="+admin_num; //SQL语句，查询宿管信息
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            while(rs.next()) {
            	admin_info[0]=rs.getString("admin_num");
            	admin_info[1]=rs.getString("name");
            	admin_info[2]=rs.getString("sex");
            	admin_info[3]=rs.getString("phone");
            }
            sql="SELECT * FROM floor WHERE admin_num="+admin_num; //SQL语句，查询该宿管管理的宿舍楼信息
            ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            if(rs.next()) { //若该宿管有管理的宿舍楼，则获取相应宿舍楼信息
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
			lb[i].setFont(new Font("黑体",0,30));
			lb[i].setBounds(150,97+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("编    号： "+admin_info[0]);
		lb[1].setText("姓    名： "+admin_info[1]);
		lb[2].setText("性    别： "+admin_info[2]);
		lb[3].setText("手    机： "+admin_info[3]);
		lb[4].setText("楼    号： "+floor_info[0]);
		lb[5].setText("层    数： "+floor_info[1]);
		lb[6].setText("房 间 数： "+floor_info[2]);
		lb[7].setText("宿舍类别： "+floor_info[3]);
		lb[8].setText("居住性别： "+floor_info[4]);
		pn_info.add(lb_infoImage);
		return pn_info;
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
	
	public void allDormitory() { //该宿管管理的宿舍楼的所有宿舍
		String[] columnNames={"楼号","楼层","宿舍号","总床位数","剩余床位数","单价","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取dormitory表信息
			String sql="SELECT * FROM dormitory WHERE floor_num='"+floor_num+"'"; //SQL语句
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
                do { //获取该宿管管理的宿舍楼的所有宿舍信息
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条宿舍信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
			if(i==0) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
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
									dorAdminJFrame.repaint();
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
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
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
			if(i==0) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("您无管理的宿舍楼！"); //若宿管无管理的宿舍楼，则查询不到任何结果
				else
					tf[i].setText(floor_num); //宿管仅能查询其管理的宿舍楼
			}
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
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
	
	public void allStudent() { //该宿管管理的宿舍楼的所有学生
		String[] columnNames={"学号","姓名","性别","年级","院系","班级","是否入住","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取adminview_student视图信息
			String sql="SELECT * FROM adminview_student WHERE floor_num='"+floor_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取学生元组总数
            if(count==0) { //若adminview_student视图无元组
            	rowData=new String[1][8];
            	for(int i=0;i<8;i++)
            		rowData[0][i]="无";
            }
            else { //若adminview_student视图有元组
            	rowData=new String[count][8];
                rs.first();
                int i=0;
                do { //获取该宿管管理的宿舍楼的所有学生信息
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条学生信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
								dorAdminJFrame.repaint();
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
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
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
				String sql="SELECT * FROM adminview_student WHERE floor_num='"+floor_num+"' AND 1=1";
				if(tf[0].getText().equals("")&&tf[1].getText().equals("")&&tf[2].getText().equals("")&&tf[3].getText().equals("")&&tf[4].getText().equals("")&&tf[5].getText().equals("")&&tf[6].getText().equals("")&&tf[7].getText().equals("")&&tf[8].getText().equals("")&&tf[9].getText().equals("")) {
					//无查询条件
				}
				else { //有查询条件
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
	
	public void allStay() { //该宿管管理的宿舍楼的所有住宿
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","入住时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_stayinfo视图信息
			String sql="SELECT * FROM student_stayinfo WHERE floor_num='"+floor_num+"'"; //SQL语句
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条住宿信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("添加",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
			if(i==2) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
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
											dorAdminJFrame.repaint();
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
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
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
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("您无管理的宿舍楼！"); //若宿管无管理的宿舍楼，则查询不到任何结果
				else
					tf[i].setText(floor_num); //宿管仅能查询其管理的宿舍楼
			}
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
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
	
	public void allInout() { //该宿管管理的宿舍楼的所有出入信息
		String[] columnNames={"学号","姓名","宿舍楼","类别","时间","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_inout视图信息
			String sql="SELECT * FROM student_inout WHERE floor_num='"+floor_num+"'"; //SQL语句
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
                do { //获取该宿管管理的宿舍楼的所有出入信息
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条出入信息！  ");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_add=new JButton("登记",new ImageIcon("image/add.png")),bt_export=new JButton("导出",new ImageIcon("image/导出.png"));
		bt_add.setBackground(Color.white.darker());
		bt_add.setFont(new Font("黑体",0,20));
		bt_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(floor_num.equals(""))
			bt_add.setEnabled(false);
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
			if(i==2) {
				tf[i].setText(floor_num);
				tf[i].setEditable(false);
			}
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
									dorAdminJFrame.repaint();
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
		dorAdminJFrame.repaint();
		pn_first.add(pn_first_2);
		dorAdminJFrame.validate();
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
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("您无管理的宿舍楼！"); //若宿管无管理的宿舍楼，则查询不到任何结果
				else
					tf[i].setText(floor_num); //宿管仅能查询其管理的宿舍楼
			}
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
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
	
	public void allRepair() { //该宿管管理的宿舍楼的所有宿舍报修
		String[] columnNames={"学号","姓名","楼号","楼层","宿舍号","报修详细","是否处理","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取student_repair视图信息
			String sql="SELECT * FROM student_repair WHERE floor_num='"+floor_num+"'"; //SQL语句
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
                do { //获取该宿管管理的宿舍楼的所有报修信息
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条宿舍报修信息！  ");
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
			if(i==2) {
				tf[i].setEditable(false);
				if(floor_num.equals(""))
					tf[i].setText("您无管理的宿舍楼！"); //若宿管无管理的宿舍楼，则查询不到任何结果
				else
					tf[i].setText(floor_num); //宿管仅能查询其管理的宿舍楼
			}
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
		dorAdminJFrame.repaint();
		pn_second.add(pn_second_1);
		dorAdminJFrame.validate();
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
	
	public void allAdvice() { //该宿管管理的宿舍楼所有学生的建议与反馈
		String[] columnNames={"学号","姓名","建议与反馈","操作"}; //表格列名
		String[][] rowData=null; //表格数据
		int count=0; //表的元组总数
		try { //获取adminview_advice视图信息
			String sql="SELECT * FROM adminview_advice WHERE admin_num='"+admin_num+"'"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            rs.last();
            count=rs.getRow(); //获取建议与反馈信息元组总数
            if(count==0) { //若adminview_advice视图无元组
            	rowData=new String[1][4];
            	for(int i=0;i<4;i++)
            		rowData[0][i]="无";
            }
            else { //若adminview_advice视图有元组
            	rowData=new String[count][4];
                rs.first();
                int i=0;
                do { //获取该宿管管理的宿舍楼的所有建议与反馈信息
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
		JLabel lb_num;
		if(floor_num.equals(""))
			lb_num=new JLabel("  您无管理的宿舍楼！  ");
		else
			lb_num=new JLabel("  "+floor_num+"宿舍楼共有"+count+"条建议与反馈！  ");
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
				FileDialog fd=new FileDialog(dorAdminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
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
			String sql="SELECT * FROM adminview_advice WHERE stu_num='"+stu_num+"' AND info='"+info+"'"; //SQL语句
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
		dorAdminJFrame.repaint();
		pn_function.add(pn_first_2);
		dorAdminJFrame.validate();
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
						String sql="SELECT password FROM admin WHERE admin_num="+admin_num; //SQL语句
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
							String sql="UPDATE admin set password=? WHERE admin_num=?"; //SQL语句
							PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
							ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
							ps.setString(1,String.valueOf(pf_new1.getPassword())); //SQL语句第一个?值
							ps.setString(2,admin_num); //SQL语句第二个?值
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
		JDialog tips=new JDialog(dorAdminJFrame,"  提示",true);
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
		JDialog choiceTips=new JDialog(dorAdminJFrame,"  提示",true);
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
		if(lb_tips.getText().equals("是否退出宿管模式？")) { //确定退出宿管模式，返回到欢迎界面
			try {
				HomePage.connection.close(); //关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dorAdminJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
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
	}
}