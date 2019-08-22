import java.util.*;
import java.io.*;
public class Course implements java.io.Serializable, Comparable<Course>{
	private static final long serialVersionUID = 1L;
	public transient InputStreamReader isr = new InputStreamReader(System.in);
	public transient BufferedReader br = new BufferedReader(isr);	
	//variables that define a course
	public String name;
	public String id;
	public int maxStuds;
	public int currentNumStuds;
	public ArrayList <Student> studentList;//list of names for specific course
	public String instructor;
	public int secNum; //section number
	public String location; //location
	public Course() {
		this.name="";
		this.id=""; 
		this.maxStuds=100;
		this.currentNumStuds=0;
		this.studentList = new ArrayList <Student>();
		this.instructor="";
		this.secNum=0;
		this.location="";
	}//empty course default constructor 
//	public Course(String name) {
//		this.name=name;
//	}
//	public Course(String name, String id, int maxStuds, int currentNumStuds, String instructor, int secNum, String location) {
//		//default constructor
//		this.name = name;
//		this.id = id;
//		this.maxStuds = maxStuds;
//		this.currentNumStuds=currentNumStuds;
//		this.instructor = instructor;
//		this.secNum=secNum; 
//		this.location=location; 
//	}
	public Course(String name, String id, int maxStuds, int currentNumStuds, ArrayList<Student> studentList, String instructor, int secNum, String location) {
		this.name = name;
		this.id = id;
		this.maxStuds = maxStuds;
		this.currentNumStuds=studentList.size();
		this.studentList = studentList;
		this.instructor = instructor;
		this.secNum=secNum; 
		this.location=location; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMaxStuds() {
		return maxStuds;
	}
	public void setMaxStuds(int maxStuds) {
		this.maxStuds = maxStuds;
	}
	public int getCurrentNumStuds() {
		return currentNumStuds;
		
	}
	public void setCurrentNumStuds(int currentNumStuds) {
		this.currentNumStuds = currentNumStuds;
		//set it to the size of studentList
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList<Student> students) {
		this.studentList = students;
	}
	
	public boolean isFull(Course c) {
		if(c.getMaxStuds()==c.getCurrentNumStuds()) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	
	public int compareTo(Course o) {
		if(this.currentNumStuds > o.currentNumStuds)
			return 1;
		else if (this.currentNumStuds < o.currentNumStuds) 
			return -1;
		else
			return 0;
		
	}
	
	
	public boolean equals(Object o) {
		Course temp=(Course) o;
		if(temp==null) {
			return false;
		}
		if(this.name.equals(temp.name)){
			return true; 
		}
		else {
			return false; 
		}
	}
	public void printCourse(Course c) {
		String b="\t";
		System.out.println("Name: "+name +b+ "ID: "+id+"Instructor: "+ instructor+b+"Current # of Students: "+ studentList.size()+b+"Maximum Number of Students: "+ maxStuds+b+"Section Number: "+ secNum+b+"Location: "+ location);
		//without student list because just want to see which ones are full
	}
	public void printCourseStudent(Course c) {
		String b="\t";
		System.out.println("Name: "+name +b+ "ID: "+id+"Instructor: "+ instructor+b+"Current # of Students: "+ studentList.size()+b+"Maximum Number of Students: "+ maxStuds+b+"Section Number: "+ secNum+b+"Location: "+ location);
	}
	public void printCourseAll() { //print everything 
		String b="\t";
		System.out.println("Name: "+name +b+ "ID: "+id+"Instructor: "+ instructor+b+"Current # of Students: "+ studentList.size()+b+"Maximum Number of Students: "+ maxStuds+b+"Section Number: "+ secNum+b+"Location: "+ location);
		if(studentList.size()>0) {
			System.out.println("Students in the Class: ");
			for(int i=0; i<studentList.size();i++){//print student list
				System.out.println("Name: "+studentList.get(i).getFullName()); //print students name
				//on same line
			}
		return;
		}
		else {
			System.out.println("Students in the Class: 0");
		}
	}
	public void removeStudent(Course c, Student s) { //remove student
		c.getStudentList().remove(s);
		s.getEnrolled().remove(c);
		
		c.setCurrentNumStuds(currentNumStuds-1); //set current num students less 
		System.out.println("Student removed from course ");
	}
	
	public int studentInCourse(Student s, Course c) {
		for(int i=0; i<c.getStudentList().size();i++) {
			
			if(c.getStudentList().get(i).getfName().equals(s.getfName())&& c.getStudentList().get(i).getlName().equals(s.getlName())) {
				return 1;
			}
		}
		return 0;
	}
	//to String method
	@Override
	public String toString() {
		return "Name: "+this.name+" ID: "+this.id +"Max Students: "+this.maxStuds+"Current Number of Students: "+this.currentNumStuds+"Student List: "+this.studentList+"Instructor: "+this.instructor+"Section number: "+this.secNum+"Location: "+this.location;
		//	public Course(String name, String id, int maxStuds, int currentNumStuds, ArrayList<Student> studentList, String instructor, int secNum, String location) {
	}
	
}
