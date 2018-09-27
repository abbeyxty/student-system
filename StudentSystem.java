package myproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {

	public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
		   
		    String fileName = "student.txt";
		
			System.out.println("-----welcome to the student system-----");
			System.out.println("------1.find all of students info------");
			System.out.println("-----------2.add student info----------");
			System.out.println("---------3.delete student info---------");
			System.out.println("---------4.update student info---------");
			System.out.println("-----------------5.exit----------------");
	//		ArrayList<Student> studentlist = new ArrayList<Student>();
			
			while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("please enter your choice: ");
			String choice = sc.nextLine();
			
//			ArrayList<Student> studentlist = new ArrayList<Student>();
			switch(choice){
				case "1":
					//find
					findAllStudent(fileName);
					break;
				case "2":
					//add
					addAllStudent(fileName);
					break;
				case "3":
					//delete
					removeStudent(fileName);
					break;
				case "4":
					//update
					updateStudent(fileName);
					break;
				case "5":
					System.out.println("exit the system successfully!");
				default:
					System.exit(0);
					break;		
			}
	
		}
	}
	
	public static void findAllStudent(String fileName) throws IOException{
		ArrayList<Student> studentlist = new ArrayList<Student>();
		readFile(fileName, studentlist);
		
		if(studentlist.size() == 0){
			System.out.println("this is no record in the system.");
			return;
		}else{
			System.out.println("id---name---age---address");
			for(int i = 0; i < studentlist.size(); i++){
				Student stu = studentlist.get(i);
				System.out.println(stu.getId()+ "---" + stu.getName() + "---"+stu.getAge()+"---"+ stu.getAddress());
			}
		}	
	}
	
	public static void addAllStudent(String fileName) throws IOException{
		    ArrayList<Student> studentlist = new ArrayList<Student>();
		    readFile(fileName,studentlist);
		    
			Scanner sc= new Scanner(System.in);
			//System.out.println("please enter the student id: ");
			String id;
			
			while(true){
				
		    System.out.println("please enter the student id: ");
		    id = sc.nextLine();
		    
			boolean flag = false;
			for(int i = 0; i < studentlist.size(); i++){
				Student stu2 = studentlist.get(i);
				if(id.equals(stu2.getId())){
					//System.out.println("this id already exist, please enter again");
					flag = true;
					break;
				}
			}
			
			if(flag){
				System.out.println("this id already exist, please enter again");
		}else{
			break;
		}
			}
			System.out.println("please enter the student name: ");
			String name = sc.nextLine();
			System.out.println("please enter the student age: ");
			String age = sc.nextLine();
			System.out.println("please enter the student address: ");
			String address = sc.nextLine();
			
			Student stu = new Student(id,name,age,address);
			studentlist.add(stu);	
			
			writeFile(fileName, studentlist);
			System.out.println("add student successfully!.");
	}
	
	public static void removeStudent(String fileName) throws IOException{
		ArrayList<Student> studentlist = new ArrayList<Student>();
	    readFile(fileName,studentlist);
	    
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the student id you want to delete:");
		String id = sc.nextLine();
		
		int index = -1;
		for(int i = 0; i < studentlist.size();i++){
			Student stu = studentlist.get(i);
			if(id.equals(stu.getId())){
		//		studentlist.remove(i);
				index = i;
				break;
			}
		}
		
		if(index == -1){
			System.out.println("we can not find the student id.");
		}else{
			studentlist.remove(index);
			writeFile(fileName, studentlist);
		System.out.println("delete successfully!");
		}
	}
	
	public static void updateStudent(String fileName) throws IOException{
		ArrayList<Student> studentlist = new ArrayList<Student>();
	    readFile(fileName,studentlist);
	    
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the student id you want to update:");
		String id = sc.nextLine();
		
		int index = -1;
		for(int i = 0; i < studentlist.size();i++){
			Student stu = studentlist.get(i);
			if(id.equals(stu.getId())){
				index = i;
				break;
			}
		}
		
		if(index == -1){
			System.out.println("we can not find the student id.");
		}else{
			System.out.println("please enter the student name: ");
			String name = sc.nextLine();
			System.out.println("please enter the student age: ");
			String age = sc.nextLine();
			System.out.println("please enter the student address: ");
			String address = sc.nextLine();
			
			Student stu = new Student();
			stu.setId(id);
			stu.setName(name);
			stu.setAge(age);
			stu.setAddress(address);
			
			studentlist.set(index, stu);
			
			writeFile(fileName, studentlist);
			
		System.out.println("update successfully!");
		}
		
	}
	
	public static void readFile(String fileName, ArrayList<Student> array) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line;
		while((line = br.readLine()) != null){
			String[] datas = line.split(",");
			Student stu = new Student();
			stu.setId(datas[0]);
			stu.setName(datas[1]);
			stu.setAge(datas[2]);
			stu.setAddress(datas[3]);
			array.add(stu);
		}
		
		br.close();
	}
	
	public static void writeFile(String fileName, ArrayList<Student> array) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		
		for(int i = 0; i < array.size(); i++){
			Student stu = array.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append(stu.getId()).append(",").append(stu.getName()).append(",").append(stu.getAge()).append(",").append(stu.getAddress());
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
  
}
