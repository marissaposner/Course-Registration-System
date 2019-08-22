# Course-Registration-System

Course Registration System with multi-level user access (Admin & Student)
At the start of the program, the user is asked to check if they are a student or an admin then if the user is admin, she/he will be asked to enter the username and password. Same applies for student. The admin first has to create the student's login for this specific version of the registration system.

**Admin multiview:** \
*First view -* Course Management: 
1. Create a new course
2. Delete a course
3. Edit a course
4. Display information for a given course
5. Register a student
6. Exit

*Second view -* Reports: 
1. View all courses (for every course the admin should be able to see the list of enrolled student’s names, enrolled student’s ids , number of students registered, and the maximum number of students allowed to be registered)
2. View all courses that are FULL 
3. Write to a file the list of course that are full
4. View the names of the students that are registered in a specific course
5. View the list of courses that a given student is registered in (given a students first name
and last name the system shall display all the courses that student is registered in)
6. Sort courses based on number of students registered
7. Exit

**Student Single view:**
*Course Management:* 
1. View all courses
2. View all courses that are not full
3. Register in a course (in this case the student must enter the course name, section, and student full name, the name will be added to the appropriate course)
4. Withdraw from a course - in this case the student will be asked to enter her/his name and the course name, then the name of the student will be taken off from the given course’s list)
5. View all courses that the current student is registered in
6. Exit

*Notes: *
- Both Admin and Student inherit from a class User
- At the beginning of launching the program, the program reads all the courses information from the comma delimited file MyUniversityCourses.csv into an ArrayList of Course Objects
- The username and password for the admin is: Admin and Admin001

Serialization: At the beginning of the program, the Students.ser and Courses.ser files are deserialized to read the files where the objects are stored (ArrayLists of students objects and ArrayList of courses objects). When the program exits, the latest copy of the ArrayLists/objects are written into the serialized binary file. 
