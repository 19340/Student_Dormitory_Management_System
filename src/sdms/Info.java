package sdms;

import java.util.*;
import java.text.*;

//������Ϣ��
public class Info {
	
}

class Floor { //����¥
	String floor_num; //¥��
	int layer_amount; //����
	int room_amount; //������
	String category; //���
	String sex; //��סʱ��
	String admin_num; //�޹ܱ��
	public Floor(String floor_num, String layer_amount, String room_amount, String category, String sex, String admin_num) {
		this.floor_num=floor_num;
		this.layer_amount=Integer.parseInt(layer_amount);
		this.room_amount=Integer.parseInt(room_amount);
		this.category=category;
		this.sex=sex;
		this.admin_num=admin_num;
	}
}

class Dormitory { //����
	String floor_num; //¥��
	int layer; //¥��
	int room_num; //�����
	int bed_total; //�ܴ�λ��
	int bed_surplus; //ʣ�ലλ��
	int price; //����
	public Dormitory(String floor_num, String layer, String room_num, String bed_total, String bed_surplus, String price) {
		this.floor_num=floor_num;
		this.layer=Integer.parseInt(layer);
		this.room_num=Integer.parseInt(room_num);
		this.bed_total=Integer.parseInt(bed_total);
		this.bed_surplus=Integer.parseInt(bed_surplus);
		this.price=Integer.parseInt(price);
	}
}

class Admin { //�޹�
	String admin_num; //���
	String password; //����
	String name; //����
	String sex; //�Ա�
	String phone; //�ֻ�
	public Admin(String admin_num, String password, String name, String sex, String phone) {
		this.admin_num=admin_num;
		this.password=password;
		this.name=name;
		this.sex=sex;
		this.phone=phone;
	}
}

class Student { //ѧ��
	String stu_num; //ѧ��
	String password; //����
	String name; //����
	String sex; //�Ա�
	int birth; //��������
	int grade; //�꼶
	String faculty; //Ժϵ
	String clas; //�༶
	String phone; //�ֻ���
	String yes_no; //�Ƿ���ס
	public Student(String stu_num, String password, String name, String sex, String birth, String grade, String faculty, String clas, String phone, String yes_no) {
		this.stu_num=stu_num;
		this.password=password;
		this.name=name;
		this.sex=sex;
		this.birth=Integer.parseInt(birth);
		this.grade=Integer.parseInt(grade);
		this.faculty=faculty;
		this.clas=clas;
		this.phone=phone;
		this.yes_no=yes_no;
	}
}

class Stay { //ס��
	String stu_num; //ѧ��
	String name; //����
	String floor_num; //¥��
	int layer; //¥��
	int room_num; //�����
	Date time; //��סʱ��
	public Stay(String stu_num, String name, String floor_num, String layer, String room_num, String time) {
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.stu_num=stu_num;
		this.name=name;
		this.floor_num=floor_num;
		this.layer=Integer.parseInt(layer);
		this.room_num=Integer.parseInt(room_num);
		try {
			this.time=timeFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class InOut { //����
	String stu_num; //ѧ��
	String name; //����
	String floor_num; //¥��
	String category; //���
	Date time; //ʱ��
	public InOut(String stu_num, String name, String floor_num, String category, String time) {
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.stu_num=stu_num;
		this.name=name;
		this.floor_num=floor_num;
		this.category=category;
		try {
			this.time=timeFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class Repair { //����
	String stu_num; //ѧ��
	String name; //����
	String floor_num; //¥��
	int layer; //¥��
	int room_num; //�����
	String info; //������ϸ
	String yes_no; //�Ƿ���
	public Repair(String stu_num, String name, String floor_num, String layer, String room_num, String info, String yes_no) {
		this.stu_num=stu_num;
		this.name=name;
		this.floor_num=floor_num;
		this.layer=Integer.parseInt(layer);
		this.room_num=Integer.parseInt(room_num);
		this.info=info;
		this.yes_no=yes_no;
	}
}

class Advice { //�����뷴��
	String stu_num; //ѧ��
	String name; //����
	String info; //��ϸ��Ϣ
	public Advice(String stu_num, String name, String info) {
		this.stu_num=stu_num;
		this.name=name;
		this.info=info;
	}
}

class Stu { //ѧ�������ڷ����������Ϣ
	String stu_num; //ѧ��
	int grade; //�꼶
	String faculty; //Ժϵ
	String clas; //�༶
	public Stu(String stu_num, String grade, String faculty, String clas) {
		this.stu_num=stu_num;
		this.grade=Integer.parseInt(grade);
		this.faculty=faculty;
		this.clas=clas;
	}
}

class Dor { //���ᣬ���ڷ����������Ϣ
	String floor_num; //¥��
	int layer; //¥��
	int room_num; //�����
	int bed_surplus; //ʣ�ലλ��
	public Dor(String floor_num, String layer, String room_num, String bed_surplus) {
		this.floor_num=floor_num;
		this.layer=Integer.parseInt(layer);
		this.room_num=Integer.parseInt(room_num);
		this.bed_surplus=Integer.parseInt(bed_surplus);
	}
}