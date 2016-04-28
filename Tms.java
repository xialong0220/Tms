package com.xialong.x008;
import java.util.Scanner;

public class Tms {
	public void menu(){
		System.out.println("****��ʦ��Ϣ����ϵͳ****");
		System.out.println("*  1.�鿴������ʦ��Ϣ  *");
		System.out.println("*  2.�����ʦ          *");
		System.out.println("*  3.ɾ����ʦ��Ϣ      *");
		System.out.println("*  4.��ѯ��ʦ��Ϣ      *");
		System.out.println("*  5.�޸���ʦ��Ϣ      *");
		System.out.println("*  help.����           *");
		System.out.println("*  exit.�˳�           *");
		System.out.println("************************");
	}
	//������ʦ���飬�����ʦ��Ϣ
	private Teacher[] teas=new Teacher[3];
    //��¼��������
	private int index=0;
	//�����ʦ
	public void tianjia(Teacher tea){  
		if(index>=teas.length){
			Teacher[] demo = new Teacher[teas.length+3];
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;
	}
	//�鿴������ʦ��Ϣ
	public Teacher[] chakan(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	//ͨ��id���Ҹ�ѧ�����ڵ�λ��
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
	//ͨ��id��ѯ��ʦ��Ϣ
	public Teacher chaxunById(long id){
		int teaIndex = chaxunIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}
	//ͨ��idɾ����ʦ��Ϣ
	public void shanchuById(long id){
		int teaIndex = chaxunIndexById(id);
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	//�޸���ʦ��Ϣ
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
			System.out.print("�����빦�ܱ�ţ�");
			String option=scanner.nextLine();
			switch(option){
				case "1"://�鿴����
					System.out.println("������������ʦ����Ϣ��");
				    System.out.println("ְ����\t����\t����");
					Teacher[] teas=tms.chakan();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("����"+teas.length+"��");
					break;
				case "2"://���
					while(true){
						System.out.println("��������ʦ��Ϣ��id,name,age����������break�ص���һ��Ŀ¼");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split(",");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//��װ����
						Teacher tea = new Teacher(id,name,age);
						tms.tianjia(tea);
						System.out.println("��ӳɹ���");
					}
					break;
				case "3"://ɾ��
					while(true){
						System.out.print("��������Ҫɾ����ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
					    tms.shanchuById(Long.parseLong(id));
						System.out.println("ɾ���ɹ�����ʦ����Ϊ��"+tms.index);
					}
					break;
				case "4"://��ѯ
					while(true){
						System.out.print("��������Ҫ��ѯ��ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.chaxunById(Long.parseLong(id));
						System.out.println("��������Ҫ���ҵ���ʦ����Ϣ��");
						System.out.println("ְ����\t����\t����");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
                case "5"://�޸�
					while(true){
						System.out.print("��������Ҫ�޸���ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.chaxunById(Long.parseLong(id));
						if(tea == null){
							System.out.println("����ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��");
						System.out.println("ְ����\t����\t����");
						System.out.println(tea);
						System.out.println("��������Ҫ�޸ĵ���Ϣ��name,age��");
						String teaStr = scanner.nextLine();
						String[] teaArr = teaStr.split(",");
						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);

						Teacher newtea = new Teacher(Long.parseLong(id),name,age);

						tms.xiugai(newtea);
						System.out.println("�޸ĳɹ���");
					}
					break; 
                case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("����������������룡");
			}
		}

	}
		
}