package com.xialong.x008;
import java.util.Scanner;

public class Tms {
	public void menu(){
		System.out.println("****老师信息管理系统****");
		System.out.println("*  1.查看所有老师信息  *");
		System.out.println("*  2.添加老师          *");
		System.out.println("*  3.删除老师信息      *");
		System.out.println("*  4.查询老师信息      *");
		System.out.println("*  5.修改老师信息      *");
		System.out.println("*  help.帮助           *");
		System.out.println("*  exit.退出           *");
		System.out.println("************************");
	}
	//创建老师数组，存放老师信息
	private Teacher[] teas=new Teacher[3];
    //记录数组索引
	private int index=0;
	//添加老师
	public void tianjia(Teacher tea){  
		if(index>=teas.length){
			Teacher[] demo = new Teacher[teas.length+3];
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;
	}
	//查看所有老师信息
	public Teacher[] chakan(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	//通过id查找该学生所在的位置
	private int chaxunIndexById(long id){
		int teaIndex= -1;
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				teaIndex = i;
				break;
			}
		}
		return teaIndex;
	}
	//通过id查询老师信息
	public Teacher chaxunById(long id){
		int teaIndex = chaxunIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}
	//通过id删除老师信息
	public void shanchuById(long id){
		int teaIndex = chaxunIndexById(id);
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	//修改老师信息
	public void xiugai(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}
	public static void main(String[] args){
		Tms tms=new Tms();
		tms.menu();
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			String option=scanner.nextLine();
			switch(option){
				case "1"://查看所有
					System.out.println("以下是所有老师的信息：");
				    System.out.println("职工号\t姓名\t年龄");
					Teacher[] teas=tms.chakan();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("共计"+teas.length+"人");
					break;
				case "2"://添加
					while(true){
						System.out.println("请输入老师信息【id,name,age】或者输入break回到上一级目录");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split(",");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//封装对象
						Teacher tea = new Teacher(id,name,age);
						tms.tianjia(tea);
						System.out.println("添加成功！");
					}
					break;
				case "3"://删除
					while(true){
						System.out.print("请输入您要删除老师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
					    tms.shanchuById(Long.parseLong(id));
						System.out.println("删除成功！老师个数为："+tms.index);
					}
					break;
				case "4"://查询
					while(true){
						System.out.print("请输入您要查询老师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.chaxunById(Long.parseLong(id));
						System.out.println("以下是您要查找的老师的信息：");
						System.out.println("职工号\t姓名\t年龄");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
                case "5"://修改
					while(true){
						System.out.print("请输入您要修改老师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.chaxunById(Long.parseLong(id));
						if(tea == null){
							System.out.println("该老师不存在！");
							continue;
						}
						System.out.println("原信息为：");
						System.out.println("职工号\t姓名\t年龄");
						System.out.println(tea);
						System.out.println("请输入您要修改的信息【name,age】");
						String teaStr = scanner.nextLine();
						String[] teaArr = teaStr.split(",");
						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);

						Teacher newtea = new Teacher(Long.parseLong(id),name,age);

						tms.xiugai(newtea);
						System.out.println("修改成功！");
					}
					break; 
                case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
			}
		}

	}
		
}