import java.util.ArrayList;

public interface StudentInterface {

	int StudentCourseManagement();

	String getFullName();

	void setFullName(String fullName);

	String toString();

	void viewAll(ArrayList<Course> courses);

	void viewAllCoursesNotFull(ArrayList<Course> courses);
	//Withdraw from a course (in this case the student will be asked to enter her/his name and
	//the course name, then the name of the student will be taken off from the given course’s list)
	public void withdraw(Course c, String fName, String lName, ArrayList<Student> students);
	//View all courses that the current student is registered in
	void viewAllMyCourses(ArrayList<Course> courses, Student s);
	public Course courseSearch(ArrayList<Course> course);
	public boolean registerCourse(Student s,Course c);
	
}