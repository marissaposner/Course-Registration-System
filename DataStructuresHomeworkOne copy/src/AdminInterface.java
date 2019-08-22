import java.util.ArrayList;

public interface AdminInterface {
	public Course createCourse(ArrayList<Course> courses);
	public int searchCourse(ArrayList<Course> courses, String name, int secNum);
	public int deleteCourse(ArrayList<Course> courses) ;
	public void editCourse(ArrayList<Course> courses,ArrayList<Student> students);
	public int chooseOptionToEdit();
	public void displayCourseInfo(ArrayList<Course> courses, String name, int secNum);
	public Student addStudent(ArrayList<Student> students);
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses);
	public void viewAllFull(ArrayList<Course> courses);
	public void writeFullCourses(ArrayList<Course> courses);
	public void sortCourses(ArrayList<Course> courses);
	public void printNamesinCourse(Course c);
	public void viewCoursesforStudent(Student s, ArrayList<Course> courses);
	public Student studentSearch(ArrayList<Student> students);
	public Course courseSearch(ArrayList<Course> courses);
}
