import java.io.*;
import java.util.*;
public class Student extends User implements StudentInterface,java.io.Serializable{
	private static final long serialVersionUID = 1L;
	transient Scanner in=new Scanner(System.in);
	String fullName;
	
	ArrayList<Course> enrolled=new ArrayList<Course>();
	public Student() {
		
	}
	public Student(String fName, String lName, String username, String password) {
		super(fName, lName, username, password); 
		fullName=fName+" "+lName;

	}
	public Student(String fName, String lName) {
		fName=this.fName;
		lName=this.lName;
	}
	public Student(String fName, String lName, String username, String password, ArrayList<Course> enrolled) {
		super(fName, lName, username, password); 
		fullName=fName+" "+lName;
		this.enrolled=enrolled;
	}
	public int StudentCourseManagement() {
		System.out.println("1. View All Courses");
		System.out.println("2. View All Courses that are NOT FULL");
		System.out.println("3. Register for a course ");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View all courses that the current student is being registered in");
		System.out.println("6. Exit");
		int choice=Integer.parseInt(in.nextLine()); 
		return choice;
	}
	public String getFullName() {
		return this.fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public ArrayList<Course> getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(ArrayList<Course> enrolled) {
		this.enrolled = enrolled;
	}
	public void printStudName() {
		System.out.println("Name: "+this.getFullName());
	}
	public String toString() {
		return fullName;
	}
	public void viewAll(ArrayList<Course> courses) {
		for(int i=0; i<courses.size(); i++) {
			courses.get(i).printCourseStudent(courses.get(i));
		}     
	}
	public void viewAllCoursesNotFull(ArrayList <Course> courses) {
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCurrentNumStuds() <courses.get(i).getMaxStuds()) {
				courses.get(i).printCourseStudent(courses.get(i));
			}
		}
	}
	
	//Register in a course (in this case the student must enter the course name, section, and
	//student full name, the name will be added to the appropriate course) 
	public boolean registerCourse(Student s, Course c) { //using
//		for (int i = 0; i < cList.size(); i++) {
//			if (cList.get(i).equals(c)) {
			if(c!=null) {
				if(c.isFull(c)) { //check if course is full
					System.out.println("Course is full ");
					return false;
				}
				else if(c.studentInCourse(s,c)==1) {//check if already registered
					System.out.println("Already registered ");
					return false;
				}
				else {
					int current=c.getCurrentNumStuds();
					c.getStudentList().add(s);
//					cList.get(i).studentList.add(s);
					s.getEnrolled().add(c);
					current+=1; //update current number 
					c.setCurrentNumStuds(current); //check this LOGIC ???
					return true;
				}
			}//end of if not null
//			}
//		}
		System.out.println("Course not found ");
		return false;
	}
	public Course courseSearch(ArrayList<Course> courses) { //method loops through course list and finds relevant course
		System.out.println("What is the name of the course you want to search for? ");
		String n=in.nextLine();
		System.out.println("What is the section number? ");
		int secNum=Integer.parseInt(in.nextLine());
		for(int i=0; i<courses.size();i++) {
			if(courses.get(i).getName().equals(n) && courses.get(i).getSecNum()==secNum) {
				return courses.get(i);
			}
		}
		return null;
	}
	//Withdraw from a course (in this case the student will be asked to enter her/his name and the course name, then the name of the student will be taken off from the given course’s list)
	public void withdraw(Course c, String fName, String lName, ArrayList<Student> students) {
		boolean isFound=false;
		for(int i=0; i<students.size();i++) {
			if(students.get(i).getfName().equals(fName) && students.get(i).getlName().equals(lName)) {
//				System.out.println(c.getStudentList().remove(students.get(i))); //remove student
				for(int j=0; j<c.getStudentList().size();j++) {
					if(c.getStudentList().get(j).getfName().equals(fName)&&c.getStudentList().get(j).getlName().equals(lName)) {
						c.getStudentList().remove(j);
						students.get(i).getEnrolled().remove(c);
						System.out.println("Withdrew " +students.get(i).getfName() +" from "+c.getName());
						isFound=true;
					}
				}
			}
		}
		if(isFound==true) {
			c.setCurrentNumStuds((c.getCurrentNumStuds()-1));
		}
		else {
			System.out.println("Student not in course ");
		}
	}


	public int searchCourseName(Course c, ArrayList<Course> courses)
	{
		for(int a = 0;a < courses.size();a ++) {
			
			if(courses.get(a).equals(c)){
				return a; //return index of course
			}
		}
		return 1 + courses.size();
	}
	public int validateCourse(ArrayList<Course> courseList) {
		
		System.out.println("Enter the course name: ");
		String courseName = in.nextLine();
		System.out.println("Enter section number: ");
		int sectionNumber = Integer.parseInt(in.nextLine());
		int found = -1;
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getName().equalsIgnoreCase(courseName)) {
				if (courseList.get(i).getSecNum()==sectionNumber) {
					System.out.println("Course Found");
					found = i;
					return found;
				}
			}
		}
		return found;
	}
	//view courses student s is registered in
	public void viewAllMyCourses(ArrayList<Course> courses, Student s) {
		int isChanged=0;
		for (int i = 0; i < courses.size(); i++) {
			courses.get(i).printCourse(courses.get(i));
			isChanged=1;
		}
		if(isChanged==0) {
			System.out.println("This student is not enrolled in any courses");
		}
	}
	//authenticate student
	public static Student authenticate(String username, String password, ArrayList<Student> students){ //authentication method for student
		for(int i=0; i<students.size();i++) {
			if(students.get(i).getUsername().equals(username)&& students.get(i).getPassword().equals(password)) { //check student login credentials
				return students.get(i);
			}
		}
		System.out.println("User not found");
		return null;
	}
	
}
