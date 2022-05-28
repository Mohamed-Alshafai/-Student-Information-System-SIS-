# -Student-Information-System-SIS-
Project for my CMP256 course

The proposed SIS consists of three main modules:
1. The Graphical User Interface (GUI)
2. The Information Repository (IR)
3. The Functionality Engine (FE)

Upon starting the system, the user is met with a login window, where he/she enters the 
username and the password. The SIS has to authenticate the user by checking for the username 
and password. If the username or password are not correct, the system shall prompt the user 
with an error message and ask them to reenter their data again. (Additional feature: you can 
make the SIS block the user out on the third wrong attempt to login)
The GUI of the SIS shall allow three types of users to access the SIS:
A. Student user
B. Instructor 
C. Administrator

Roles of the users in the system:

A. The Student user can access his/her information which includes:
1. Name
2. ID
3. Username
4. Password
5. Major
6. Courses taken in the current term:
a. Name of course
b. Number of course
c. Grade of student in the course
7. GPA of the student
The student shall be able to add courses in a specific term by loading course information from 
a text file that is formatted as follows:
Course Number; Course Name; Number of Credits
Adding courses shall be doable using a button and through a menu. 
A student can take up to 5 courses per term. 
The student shall be able to display his/her data in the form of a table
GPA is computed on the 100 scale as a weighted average of the grades of the courses taken 
in the semester.
The student shall be able to save the course data in a file using a button and through a menu.

B. The Instructor user can access instructor information which includes:
1. Name
2. ID
3. Username and Password
4. Department
5. Courses taught in the current semester
6. Grades of students in a course
An instructor shall be able to change:
1. His/her name
2. Grade of a student in a course
3. His/her Password
The instructor shall be able to display his/her data in a table format
The Instructor shall be able to add courses to his/her schedule by entering it through an 
interface that uses text-fields, one course at a time. An instructor can add up to 3 courses per 
term.
A course can accommodate up to 20 students. Grades are on a 100 scale.
The SIS shall display the ID and name of students who already added themselves to the 
course. An instructor can only change the grades of the students by using a popup window 
accessible by a button (Change Grades), one student at a time.
An Instructor can choose to save the data of a course into a file that can be read later by the 
Instructor and by the Administrator.

C. The Administrator user can access and display all information in the SIS in the same way 
students and instructors do.
The administrator shall be able to change all data in the system in similar ways to students 
and instructors. The administrator cannot change the grades of students. 
General Features

1. The implementation of the SIS shall make use of the MVC design pattern with a clear 
separation of the three components in the pattern.
Additional features (Optional)

1. The SIS shall allow more than one user to access their data concurrently through the 
utilization of threads (A thread for each user accessing the system). Proper 
synchronization shall be used to guarantee consistency of data in the SIS.

2. Use threads to keep the GUI as responsive as possible when multiple users are accessing 
the system.
