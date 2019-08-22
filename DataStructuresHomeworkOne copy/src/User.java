
import java.io.*;
import java.util.ArrayList;
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String username, password, fName, lName;

	public User() {
		username=""; 
		password=""; 
		fName="";
		lName="";
	
		
	}
	public User(String fName, String lName, String username, String password){
		this.fName=fName;
		this.lName=lName;
		this.password=password; //change password to what you pass in 
		this.username=username;

		
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
		this.password=password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}

	public String toString() {
		return "Username: "+this.username+" Password: "+this.password;
	}
	public void viewAll(ArrayList<Course> courses) {
		for(int i= 0; i < courses.size(); i++) {
			courses.get(i).printCourseAll(); //print each course
		}     
		return;
	}
}
