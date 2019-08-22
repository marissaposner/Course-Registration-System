import java.io.*;
import java.util.*;
public class CourseManagement implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	static transient Scanner in=new Scanner(System.in); //transient
	public static Admin admin = new Admin();
	public static Student student = new Student(); 
	public static ArrayList<Course> courses=new ArrayList<Course>();
	public static ArrayList<Student> students=new ArrayList<Student>(); 
	
	public static void main(String[] args) {
		
		//courses.add(new Course("Computer science", "CS102",25,10,"Anasse Bari",1,"CIWW 102"));
		//add new test courses
		try {
			//deserealize
			
			FileInputStream sfis = new FileInputStream("Students.ser");
			FileInputStream cfis = new FileInputStream("Courses.ser");
			ObjectInputStream sois = new ObjectInputStream(sfis);
			ObjectInputStream cois = new ObjectInputStream(cfis);
			
			students=(ArrayList<Student>) sois.readObject();
	
			courses=(ArrayList<Course>) cois.readObject();
			
			
			
			sfis.close();
			cfis.close();
			sois.close();
			cois.close();
			
			System.out.println("Deserialized!");
			
		}catch (IOException ioe) {
			
			System.out.println(".........");
			//read csv 
			String fileName = "MyUniversityCourses.csv";
			//split objects by line
			String line = null;
			try{
				//FileReader reads text files as characters as opposed to bytes (like FileInputStream)
				//First, we instantiate the file reader class
				FileReader fileReader = new FileReader(fileName);
				
				//The BufferedReader class can wrap around Readers, like FileReader, to buffer the input and improve efficiency.
				//ALWAYS wrap FileReader in BufferedReader
				
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				//readLine() reads a line of text.
				//A line is considered to be terminated by a new line ('\n'). So Buffered reader would literally read line by line.
				//While there are still lines to read, our program will print the file
				line=bufferedReader.readLine(); //0th line and then after 
				while((line = bufferedReader.readLine()) != null) { 
					StringTokenizer st = new StringTokenizer(line,",");
					while(st.hasMoreTokens()) {
							String name = st.nextToken();
							String id = st.nextToken();
							int maxStudents = Integer.parseInt(st.nextToken());
							int currentNumberStudents = Integer.parseInt(st.nextToken());
							
							//System.out.println("course name: " + name + " id: " + id + " maxStudents: " + maxStudents + " current num students: " + currentNumberStudents);
							ArrayList <Student> studentList= new ArrayList<>();
							st.nextToken();//this is just here so it doesnt do anything with the above line yet because 
							
							String instructor = st.nextToken();
							int sectionNumber = Integer.parseInt(st.nextToken());
							String location = st.nextToken();
								//create new CourseEntry and append to Courses ArrayList
							Course entry = new Course(name, id, maxStudents, currentNumberStudents, studentList, instructor, sectionNumber, location);
							System.out.println(entry.toString()); //prints all entries
							courses.add(entry);
				}
			}
				//Always close files
				bufferedReader.close();
			}
			//The catch block performs a specific action depending on the exception
			catch(FileNotFoundException ex){
				System.out.println( "Unable to open file '" + fileName + "'");
				//the printStackTrace method will print out an error output stream ("What went wrong?" report);
				ex.printStackTrace();
			}
			catch (IOException ex) {
				System.out.println( "Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}
		System.out.println("Enter username:");
		String username=in.nextLine(); 
		System.out.println("Enter password :");
		String password=in.nextLine();
		
		if(admin.getUsername().equals(username)&& admin.getPassword().equals(password)) {
			int adminOverallChoices=0;
			do {
				adminOverallChoices=AdminChoiceMenu();
				if(adminOverallChoices==1) {//course management options for admin
					int courseChoiceMenu=0;
					do {
						courseChoiceMenu=AdminCourseMenu(); 
						if(courseChoiceMenu==1) { //create new course						
							Course c=admin.createCourse(courses);
							courses.add(c);
							System.out.println("Added: "+c.toString());
						}
						else if(courseChoiceMenu==2){ //delete course
							int b=admin.deleteCourse(courses);
//							courses.remove(courses.get(b)); //get the index of the course and then remove it 
							ArrayList<Student> studsInCourse=courses.get(b).getStudentList();
							for(int i=0; i<studsInCourse.size();i++) {
								studsInCourse.get(i).getEnrolled().remove(courses.get(b));
								courses.remove(courses.get(b));
							}
						}
						else if(courseChoiceMenu==3) {//edit a course
							admin.editCourse(courses,students);
						}
						else if(courseChoiceMenu==4) {//display info for a Course
							System.out.println("What is the Name of the Course you want to see the information for? ");
							String name=in.nextLine();
							System.out.println("What is the section number of the Course you want to see the information for? ");
							int secNum=Integer.parseInt(in.nextLine());
							admin.displayCourseInfo(courses, name, secNum);
						}
						else if(courseChoiceMenu==5) {//register student 
							Student stud=admin.addStudent(students);
							students.add(stud);
						}
					}while(courseChoiceMenu!=6); //exit
					System.out.println("Back to Menu!"); //check this
				}// Course management options for Admin 
				else if(adminOverallChoices==2) {//Reports options for admin
					int reportsMenu=0;
					do {
						reportsMenu=AdminReportsMenu();
						if(reportsMenu==1) { //view all courses
							admin.viewAll(courses);
						}
						else if(reportsMenu==2) {//view all courses that are full 
							admin.viewAllFull(courses);
						}
						else if(reportsMenu==3) {//write to a file the list of courses that are full
							admin.writeFullCourses(courses);
						}
						else if(reportsMenu==4) {//view the names of the students that are registered in a specific course
							Course c=admin.courseSearch(courses); //get course to search for
							if(c!=null) {
								admin.printNamesinCourse(c); //print names for this course
							}
//							for(int i=0; i<courses.size();i++) {
//								if(courses.get(i).getName().equals(name)&& courses.get(i).getSecNum()==sNum) {
//									Course c=courses.get(i);
//									admin.printNamesinCourse(c,c.getStudentList()); //print the names
//								}
//							}
							else { //course=null
								System.out.println("Course not found! ");
							}
						}
						else if(reportsMenu==5) {//view the list of courses that a given student is registered in (give a student first name and last name)
							Student obj=admin.studentSearch(students); //return student from student Search method 
//							System.out.println("Full name: "+obj.getFullName()+" username "+obj.getUsername()+" "+obj.getPassword());
//							for(int p=0;p<obj.getEnrolled().size();p++) {
//								System.out.println("Course: "+obj.getEnrolled().get(p).getName());
//							}
							
							if(obj!=null) { //if student exists
								ArrayList<Course> studentClasses=obj.getEnrolled(); //not adding to enrolled?
								if(studentClasses.size()>0) {
									for(int m=0; m<studentClasses.size();m++) {
										studentClasses.get(m).printCourse(studentClasses.get(m));
									}
								}
								else {
									System.out.println("Student is not enrolled in any classes " );
								}
							}						
							else {
								System.out.println("Student does not exist ");
							}
						}//end of 5
						else if(reportsMenu==6) {//sort courses based on the number of students registered 
//							ArrayList<Course> test = new ArrayList<>();
//							int num = 0, i =0;
//							while (num < 7 && i < courses.size()) {
////								if (courses.get(i).getCurrentNumStuds() > 0) {
//									test.add(courses.get(i));
//									num++;
////								}
//								i++;
//							}
							admin.sortCourses(courses);
							
//							for (Course c: test) {
//								System.out.println(c.getName() + " " + c.getCurrentNumStuds());
//							}
//							admin.sortCourses(courses); //sort courses
							admin.viewAll(courses);
//							for(int i=0;i<sorted.size();i++) {
//								sorted.get(i).printCourseAll(); //print out sorted courses
//							}
						}
					}while(reportsMenu!=7);
					System.out.println("Back to Menu!");
				}//end reports options for admins 
			}while(adminOverallChoices!=3);
			//do serialization here
			try {
			// FileOutput Stream writes data to a file
				FileOutputStream fos = new FileOutputStream("Students.ser");

				// ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(students);
				
				FileOutputStream cor = new FileOutputStream("Courses.ser");

				// ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream coo = new ObjectOutputStream(cor);
				coo.writeObject(courses);
				
				// Close both streams
				oos.close();
				fos.close();
				cor.close();
				coo.close();
				
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("Admin Serialized!");
		}//end of admin
		else {
			//initialize Student object to the validated student who logged in
			Student st= studentLogInProcess(username, password, students);
			if(st!=null) {
//			if(student.authenticate(username,password,students)) { //check student login credentials
				System.out.println("Welcome to the Course management system! ");
				int studentMenu=0; //get option from student
				do {
					int cIndex;
					studentMenu=StudentCourseMenu(); //get option from student
					if(studentMenu==1) { // View all courses
						student.viewAll(courses);
					}
					else if(studentMenu==2) { //View all courses that are not full
						student.viewAllCoursesNotFull(courses);
					}
					else if(studentMenu==3) {//Register in a course
						Course crs=student.courseSearch(courses);
						if(crs!=null) {
							boolean registered = student.registerCourse(st, crs);
							if (registered) {
								System.out.println("Success! You were registered in " + crs.getName() + ".");
							}
						}
						else {
							System.out.println("Course not found");
						}
						
					}
					else if(studentMenu==4) { //withdraw from a course
						Course toWithdraw=student.courseSearch(courses); //get course to withdraw
						if(toWithdraw!=null) {
							student.withdraw(toWithdraw, st.getfName(), st.getlName(), students);
							
						}
					}
					else if(studentMenu==5) { //View all courses that the current student is registered in
						student.viewAllMyCourses(st.getEnrolled(),st); //display students enrolled courses 
					}
				}while(studentMenu!=6); //exit
				System.out.println("Goodbye! ");
			} //end of if login credentials
			
			//do serialization here
			try {
			// FileOutput Stream writes data to a file
				FileOutputStream fos = new FileOutputStream("Students.ser");

				// ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(students);
				
				FileOutputStream cor = new FileOutputStream("Courses.ser");

				// ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream coo = new ObjectOutputStream(cor);
				coo.writeObject(courses);
				
				// Close both streams
				oos.close();
				fos.close();
				cor.close();
				coo.close();
				
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("Serialized!");
		}//end of students 
	}//end of main
	public static int AdminChoiceMenu() {
		System.out.println("1. Course Management"); 
		System.out.println("2. Reports");  
		System.out.println("3. Exit"); 
		System.out.println("Enter your choice: ");
		int choice=Integer.parseInt(in.nextLine()); 
		return choice; 
		
	}
	public static int AdminCourseMenu() {
		System.out.println("1. Create a new course "); 
		System.out.println("2. Delete a course ");  
		System.out.println("3. Edit a course ");
		System.out.println("4. Display information for a given course ");  
		System.out.println("5. Register a student ");  
		System.out.println("6. Exit"); 
		System.out.println("Enter your choice: ");
		int choice=Integer.parseInt(in.nextLine()); 
		return choice; 
		
	}
	public static int AdminReportsMenu() {
		System.out.println("1. View all courses "); 
		System.out.println("2. View all courses that are FULL ");  
		System.out.println("3. Write to a file the list of courses that are full ");
		System.out.println("4. View the names of the students that are registered in a specfic course ");  
		System.out.println("5. View the list of courses that a given student is registered in ");  
		System.out.println("6. Sort the courses based on the current number of students registered "); 
		System.out.println("7. Exit "); 
		System.out.println("Enter your choice: ");
		int choice=Integer.parseInt(in.nextLine()); 
		return choice; 
	}
	
	public static int StudentCourseMenu() {
		System.out.println("1. View all courses "); 
		System.out.println("2. View all courses that are not full ");  
		System.out.println("3. Register to a course ");
		System.out.println("4. Withdraw from a course ");  
		System.out.println("5. View all courses that you are registred in "); 
		System.out.println("6. Exit "); 
		System.out.println("Enter your choice: ");
		int choice=Integer.parseInt(in.nextLine()); 
		return choice; 
	}
	public static Student studentLogInProcess(String username, String password, ArrayList<Student> students) {
		Student s = Student.authenticate(username, password, students);//pass to method authenticate in student
		if (!(s == null)) {
			System.out.println("\nLogin Successful! " + username + " was found in the system.\n");	
			return s;
		}
		return null;
	}
	
}
