package sis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SISController implements ActionListener {
    private SIS sis;
    private SISView sisView;

    public SISController() {
        this.sis = new SIS();
        this.sisView = new SISView(sis);
    }
    public SISController(SIS sis, SISView sisView) {
        if (sis != null && sisView != null) {
            this.sis = sis;
            this.sisView = sisView;
        }
        else {
            this.sis = new SIS();
            this.sisView = new SISView(sis);
        }
    }

    public synchronized int authenticateInfo(String name, String password) {
        int x;
        int y;
        int userType = -1;
        for (int i = 0; i < 3; ++i) {
            x = sis.getNames(i).indexOf(name);
            y = sis.getPasswords(i).indexOf(password);
            if (x != -1 && y != -1) {
                userType = i;
                break;
            }
        }
        return userType;
    }
    public synchronized int getUserIndex(String name, int accountType) {
        return sis.getNames(accountType).indexOf(name);
    }

    public synchronized void retrieveUser(StudentController studentController, SISThreads sisThread) {
        int userIndex = getUserIndex(sisThread.getUsername(), sisThread.getAccountType());
        File file = new File("textFiles/Users/"+sisThread.getUsername()+".txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            StringTokenizer st = new StringTokenizer(scanner.nextLine(), ";");
            ArrayList<Course> courses = new ArrayList<Course>();
            String name, id, user, password, major, term;
            name = st.nextToken();
            id = st.nextToken();
            user = st.nextToken();
            password = st.nextToken();
            major = st.nextToken();
            term = st.nextToken();

            int numCourses = Integer.parseInt(st.nextToken());
            while(numCourses>0) {
                courses.add(new Course(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), new Grade()));
                --numCourses;
            }
            
            studentController.setStudentId(id);
            studentController.setStudentName(name);
            studentController.setStudentUsername(user);
            studentController.setStudentPassword(password);
            studentController.setStudentMajor(major);
            studentController.setStudentTerm(term);
            studentController.setStudentCourses(courses);
            studentController.setStudentNumCourses(courses.size());
            studentController.getStudentView().setStudent(studentController.getStudent());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (scanner!=null) {
                scanner.close();
            }
        }
    }
    public synchronized void retrieveUser(InstructorController instructorController, SISThreads sisThread) {
        int userIndex = getUserIndex(sisThread.getUsername(), sisThread.getAccountType());
        File file = new File("textFiles/Users/" + sisThread.getUsername() + ".txt");
        Scanner scanner = null;
        StringTokenizer st = null;
        try {
            scanner = new Scanner(file);
            st = new StringTokenizer(scanner.nextLine(), ";");
            ArrayList<Course> courses = new ArrayList<Course>();
            ArrayList<ArrayList<Student>> allStudents = new ArrayList<ArrayList<Student>>();

            String name, id, user, password, department, term;
            name = st.nextToken();
            id = st.nextToken();
            user = st.nextToken();
            password = st.nextToken();
            department = st.nextToken();
            term = st.nextToken();
            int numCourses = Integer.parseInt(st.nextToken());
            while (numCourses > 0) {
                ArrayList<Student> students = new ArrayList<Student>();
                ArrayList<Grade> grades = new ArrayList<Grade>();
                String courseName, code;
                int credits;

                courseName = st.nextToken();
                code = st.nextToken();
                credits =  Integer.parseInt(st.nextToken());
                int numStudents = Integer.parseInt(st.nextToken());
                while (numStudents > 0) {
                    grades.add(new Grade(Integer.parseInt(st.nextToken())));
                    --numStudents;
                }
                courses.add(new Course(courseName, code, credits, grades));
                numStudents = Integer.parseInt(st.nextToken());
                int num = numStudents;
                while (numStudents > 0) {
                    students.add(new Student(st.nextToken(), st.nextToken(), "", "",
                            "", "", new Course(courseName, code, credits, grades.get(num - numStudents))));
                    --numStudents;
                }
                allStudents.add(students);
                --numCourses;
            }
            instructorController.setInstructorId(id);
            instructorController.setInstructorDepartment(department);
            instructorController.setInstructorTerm(term);
            instructorController.setInstructorName(name);
            instructorController.setInstructorPassword(password);
            instructorController.setInstructorUsername(user);
            instructorController.setInstrAllStds(allStudents);
            instructorController.setInstructorCourses(courses);
            instructorController.getInstructorView().setInstructor(instructorController.getInstructor());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
        public synchronized void retrieveUser(AdminController adminController, SISThreads sisThread) {
            int userIndex = getUserIndex(sisThread.getUsername(), sisThread.getAccountType());
            File file = new File("textFiles/Users/" + sisThread.getUsername() + ".txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                StringTokenizer st = new StringTokenizer(scanner.nextLine(), ";");

                ArrayList<Course> courses = new ArrayList<Course>();
                ArrayList<ArrayList<Student>> allStudents = new ArrayList<ArrayList<Student>>();

                String name, id, user, password, term;
                name = st.nextToken();
                id = st.nextToken();
                user = st.nextToken();
                password = st.nextToken();
                term = st.nextToken();
                int numCourses = Integer.parseInt(st.nextToken());
                while (numCourses > 0) {
                    ArrayList<Student> students = new ArrayList<Student>();
                    ArrayList<Grade> grades = new ArrayList<Grade>();
                    String courseName, code;
                    int credits;

                    courseName = st.nextToken();
                    code = st.nextToken();
                    credits =  Integer.parseInt(st.nextToken());

                    int numStudents = Integer.parseInt(st.nextToken());
                    int num = numStudents;
                    while (numStudents > 0) {
                        grades.add(new Grade(Integer.parseInt(st.nextToken())));
                        --numStudents;
                    }
                    courses.add(new Course(courseName, code, credits, grades));
                    numStudents = Integer.parseInt(st.nextToken());
                    while (numStudents > 0) {
                        students.add(new Student(st.nextToken(), st.nextToken(), "", "",
                                "", "", new Course(courseName, code, credits,
                                grades.get(num-numStudents))));
                        --numStudents;
                    }
                    allStudents.add(students);
                    --numCourses;
                }
                adminController.setAdminId(id);
                adminController.setAdminName(name);
                adminController.setAdminPassword(password);
                adminController.setAdminUsername(user);
                adminController.setAdminTerm(term);
                adminController.setAdminCourses(courses);
                adminController.setAdminAllStds(allStudents);
                adminController.getAdminView().setAdmin(adminController.getAdmin());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                scanner.close();
            }
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() == sisView.getLogInButton()) {
            String name = sisView.getUsernameField().getText();
            String password = String.valueOf(sisView.getPasswordField().getPassword());
            int confirm = authenticateInfo(name, password);
            if (confirm != -1) {
                JOptionPane.showMessageDialog(sisView, "Logged In", "Login Confirmation", JOptionPane.INFORMATION_MESSAGE);
                Thread thread = new Thread(new SISThreads(this, confirm, name));
                thread.start();
            }
            else JOptionPane.showMessageDialog(sisView, "Invalid Login Info", "Login Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
