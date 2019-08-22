import java.util.*; 
import java.io.*;
public class Admin extends User implements AdminInterface,java.io.Serializable{
	private static final long serialVersionUID = 1L;
	transient Scanner in=new Scanner(System.in);
	public String username = "Admin";
	public String password = "Admin001";
	public Admin() {
	}
	public Admin(String fName, String lName, String username, String password) {
		super(fName, lName, username, password); 
		 
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Course createCourse(ArrayList<Course> courses) { //asks for info to create a course object
		System.out.println("Please enter the course name: ");
		String name = in.nextLine();
		System.out.println("Please enter the Course ID: ");
		String id = in.nextLine();
		System.out.println("Please enter the maximum number of students: ");
		int maxStuds = Integer.parseInt(in.nextLine());
		System.out.println("Please enter the name of the Course Instructor: ");
		String instructor = in.nextLine();
		System.out.println("Please enter the Course Section Number: ");
		int secNum = Integer.parseInt(in.nextLine());
		System.out.println ("Please enter the course location: ");
		String location = in.nextLine();
		Course c= new Course(name, id, maxStuds, 0, new ArrayList<Student>(), instructor, secNum, location); //current num students=0
		//String name, String id, int maxStuds, int currentNumStuds, ArrayList<Student> studentList, String instructor, String secNum, String location)
		return c; 
	}
	
	public int searchCourse(ArrayList<Course> courses, String name, int secNum){
		
		for(int i=0; i<courses.size(); i++) {
			//System.out.println(courses.get(i).getName() +  " | " + name);
			 if(courses.get(i).getName().equalsIgnoreCase(name)&&courses.get(i).getSecNum()==secNum) {
				 return i;
			 }
		}
		System.out.println("Course not found");
		return -1;
	}//returns course in arraylist courses
	
	public int deleteCourse(ArrayList<Course> courses) {
		System.out.println("What is the name of the course you want to search for: ");
		String cName=in.nextLine();
		System.out.println("What is the section number of the course you want to search for: ");
		int secNum=Integer.parseInt(in.nextLine());
		int c=searchCourse(courses,cName,secNum);
		
		if(c==-1) {
			System.out.println("Course not found. Please try again! "); 
			return -1;
		}
		else{
			System.out.println("able to be removed");
			return c;
		}
	} //delete course by name
	//get course
	public Course returnCourse(String name, int secNum, ArrayList<Course> courses) { //returns course object according to given parameters
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getName().equals(name) && courses.get(i).getSecNum()==secNum) {
				Course c= courses.get(i);
				return c;
			}
		}
		return null;	//if dont find course
	}	
	public void editCourse(ArrayList<Course> courses, ArrayList<Student> students) {
		System.out.println("Enter the name of the course you want to search for: ");
		String name=in.nextLine(); 
		System.out.println("What is the section number you want to search for?");
		int secNum=Integer.parseInt(in.nextLine());
		Course returned=returnCourse(name,secNum,courses);
		int option=0;
		if(returned!=null) {
			do {
				option=chooseOptionToEdit(); 
				if(option==1) {
					System.out.println("What is the new maximum number of students you want per class?");
					int newMax=Integer.parseInt(in.nextLine());
					//courses.get(courseIndex).setMaxStuds(newMax);
					returned.setMaxStuds(newMax);
				}
				else if(option==2) {
					System.out.println("What is the new name of the course instructor? ");
					String instructorName=in.nextLine();
					returned.setInstructor(instructorName);
				}
				else if(option==3) {
					System.out.println("What is the new section number?");
					int sectionNum=Integer.parseInt(in.nextLine());
					returned.setSecNum(sectionNum);
				}	
				else if(option==4) {
					System.out.println("What is the new location? ");
					String newLoc=in.nextLine();
					returned.setLocation(newLoc);
				}
			} while(option!=5); //end of do
		System.out.println("Goodbye");
		return;
		}
		else {
			System.out.println("Course not found ");
		}
	}
	
	public int chooseOptionToEdit() {
		System.out.println("Which option do you want to edit? ");
		System.out.println("1. Max # of Students ");
		System.out.println("2. Instructor ");
		System.out.println("3. Section Number ");
		System.out.println("4. Location ");
		System.out.println("5. Done ");
		int choice=Integer.parseInt(in.nextLine());
		return choice;
	}
	public void displayCourseInfo(ArrayList<Course> courses, String name, int secNum) {
		
		for(int a=0; a<courses.size(); a++) {
			if(courses.get(a).getName().equalsIgnoreCase(name)&& courses.get(a).getSecNum()==secNum) {
				courses.get(a).printCourseAll();
//				System.out.println("Information about course: "+id);
//				System.out.println("Name: "+ courses.get(a).getName()); //null pointer
//				System.out.println("Maximum # of Students: "+courses.get(a).getMaxStuds());
//				System.out.println("Current Number of Students: "+courses.get(a).getCurrentNumStuds());
//				System.out.println("Location: "+courses.get(a).getLocation());
//				System.out.println("Section Number: "+courses.get(a).getSecNum());
//				System.out.println("Instructor: "+courses.get(a).getInstructor());
				
			}
		}
	}
	//Register a student (this option will allow the admin to add a student 
	public Student addStudent(ArrayList<Student> students) {
		System.out.println("Enter a student first name: ");
		String sfirst=in.nextLine();
		System.out.println("Enter a student last name: ");
		String slast=in.nextLine(); 
		System.out.println("Enter a student's username: ");
		String user=in.nextLine(); 
		System.out.println("Enter a student's password: ");
		String pass=in.nextLine();
		Student s=new Student(sfirst,slast,user,pass); 
//		students.add(s);
		return s;
	}
	//report functions 
	//View all courses (for every course the admin should be able to see the list of enrolled
	//student’s names, enrolled student’s ids, number of students registered, and the maximum
	//number of students allowed to be registered)

	
	//get list of full courses
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses) {
		ArrayList<Course> fullCourses=new ArrayList<Course>(); 
		boolean isChanged=false;
		for(int i=0; i<courses.size(); i++) {
			if(courses.get(i).isFull(courses.get(i))) {
				fullCourses.add(courses.get(i));
			}	
		}
		if(isChanged) {
			return fullCourses;
		}
		else {
			return null; //check this
		}
	}
	
	public void viewAllFull(ArrayList <Course> courses) {
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCurrentNumStuds() >= courses.get(i).getMaxStuds()) {
				System.out.println(courses.get(i));
			}
		}
	}
	
	public void writeFullCourses(ArrayList<Course> courses) {
		ArrayList<Course> fullCourses=new ArrayList<Course>(); 
		for(int i=0;i<courses.size();i++) {//iterate through all courses 
			if(courses.get(i).isFull(courses.get(i))) { //check if course is full
				fullCourses.add(courses.get(i)); //add full courses to list 
			}
		}//close for loop
		String fileName = "fullCourses.txt";
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i=0; i<fullCourses.size();i++) {
				String courseToWrite = "Name: "+fullCourses.get(i).getName();
				bufferedWriter.write(courseToWrite); //write name to file
				bufferedWriter.newLine();
				//Always close writer
				bufferedWriter.close();
			}
		}
			//Always close files
		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
	//viewAllFull array 
	public void sortCourses(ArrayList<Course> courses) { //bubble sort based on number of students enrolled
		
		for(int i=0; i<courses.size(); i++) {
			for(int j=0; j<courses.size()-i-1;j++) {
				if(courses.get(j).getCurrentNumStuds()<courses.get(j+1).getCurrentNumStuds()) {
					Course temp= courses.get(j);
					courses.set(j, courses.get(j+1));
					courses.set(j+1, temp);
				}
			}
		}
	}
	
	
	public void printNamesinCourse(Course c) {
		int areNames=0;
		for (int i=0;i<c.getStudentList().size();i++) {
			System.out.println("First name: " +c.getStudentList().get(i).getfName()+ " Last name: "+c.getStudentList().get(i).getlName());
			areNames=1;
		}
		if(areNames==0) {
			System.out.println("Sorry no students in that course ");
		}
	}
	//View the list of courses that a given student is registered in (given a student first name
	//and last name the system shall display all the courses that student is registered in
	public void viewCoursesforStudent(Student s, ArrayList<Course> courses) {
		int numCourses=0;
		for(int i=0; i<courses.size();i++) {
			ArrayList<Student> studentsInCourse=courses.get(i).getStudentList(); //get student list for course at index i
			if(studentsInCourse.contains(s)) {
				System.out.println(courses.get(i).getName());
				numCourses=1;
			}
		}
		if(numCourses==0) { //if student isnt in any courses 
			System.out.println("Student is not registered in any courses ");
		}
	}
	public Student studentSearch(ArrayList<Student> students) {
		System.out.println("What is the first name of the student you want to search for? ");
		String firstName=in.nextLine();
		System.out.println("What is the last name of the student you want to search for? ");
		String lastName=in.nextLine();
		for(int i=0; i<students.size();i++) {
			if(students.get(i).getfName().equalsIgnoreCase(firstName) && students.get(i).getlName().equalsIgnoreCase(lastName)) {
				return students.get(i);
				//works here 
			}
		}
		return null;
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
	//view the names of the students that are registered in a specific course
//	public void viewNames(Course c) {
//		ArrayList<Student> studsInCourse=c.getStudentList();
//		if(studsInCourse.size()>0) {
//			for(int i=0;i<studsInCourse.size();i++) {
//				studsInCourse.get(i).printStudName();
//			}
//		}
//	}
}
