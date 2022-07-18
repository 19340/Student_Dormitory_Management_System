package sdms;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login { //登录类
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	
	public void adminLogin(String account,String password) { //管理员登录（包括系统管理员和宿管）
		boolean flag=false; //是否查找到相应账号密码
		if(account.equals("admin") && password.equals("admin")) { //检验是否为系统管理员账号密码，若是，则进入系统管理员功能界面
			lb_tips.setText("登录成功！");
			loginTips();
			new SysAdminFunction();
			HomePage.mainJFrame.dispose();
		}
		else { //若不是，则进行宿管账号密码检测
			String admin_num=null, pass; //宿管账号密码
			try {
				String sql="SELECT admin_num, password FROM admin"; //SQL语句
				PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
				ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
	            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
	            while(rs.next()){ //遍历结果集
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
			if(flag) { //若宿管账号密码正确，则进入宿管功能界面
				lb_tips.setText("登录成功！");
				loginTips();
				new DorAdminFunction(admin_num);
				HomePage.mainJFrame.dispose();
			}
			else { //若账号密码错误，则进行提示
				lb_tips.setText("账号或密码错误！");
				loginTips();
				try {
					HomePage.connection.close(); //关闭数据库连接
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void studentLogin(String account,String password) { //学生登录
		boolean flag=false; //是否查找到相应账号密码
		String stu_num=null, pass; //学生账号密码
		try {
			String sql="SELECT stu_num, password FROM student"; //SQL语句
			PreparedStatement ps; //创建PreparedStatement类对象ps，用来执行SQL语句
			ps=HomePage.connection.prepareStatement(sql); //把操作数据库返回的结果保存到ps中
            ResultSet rs=ps.executeQuery(sql); //ResultSet类，用来存放获取的结果集
            while(rs.next()) { //遍历结果集
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
		if(flag) { //若学生账号密码正确，则进入学生功能界面
			lb_tips.setText("登录成功！");
			loginTips();
			new StudentFunction(stu_num);
			HomePage.mainJFrame.dispose();
		}
		else { //若账号密码错误，则进行提示
			lb_tips.setText("账号或密码错误！");
			loginTips();
			try {
				HomePage.connection.close(); //关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loginTips() { //登录时弹出的提示信息窗口
		JDialog tips=new JDialog(HomePage.mainJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("确 定");
		tips.setSize(450,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,450,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_tips.setFont(new Font("黑体",0,20));
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