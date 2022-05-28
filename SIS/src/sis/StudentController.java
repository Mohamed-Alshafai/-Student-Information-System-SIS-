package sis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentController implements ActionListener {
	private Student student;
	private StudentView studentView;

	public StudentController(){}
	public StudentController(Student student, StudentView studentView) {
		this.student = student;
		this.studentView = studentView;
	}
	
	public void updateView() {
		this.studentView.setStudent(this.student);
	}
	public StudentView getStudentView() {
		return this.studentView;
	}
	public void setStudentView(StudentView studentView) {
		this.studentView = studentView;
	}
	public Student getStudent() {
		return this.student;
	}
	public void setStudent(Student std) {
		this.student = std;
	}
	
	public String getStudentName() {
		return student.getName();
	}
	public void setStudentName(String name) {
		student.setName(name);
	}
	public String getStudentId() {
		return student.getId();
	}
	public void setStudentId(String id) {
		student.setId(id);
	}
	public String getStudentUsername() {
		return student.getUsername();
	}
	public void setStudentUsername(String username) {
		student.setUsername(username);
	}
	public String getStudentPassword() {
		return student.getPassword();
	}
	public void setStudentPassword(String password) {
		student.setPassword(password);
	}
	public String getStudentMajor() {
		return student.getMajor();
	}
	public void setStudentMajor(String major) {
		student.setMajor(major);
	}
	public int getStudentNumCourses() {
		return student.getNumCourses();
	}
	public void setStudentNumCourses(int numCourses) {
		student.setNumCourses(numCourses);
	}
	public ArrayList<Course> getStudentCourses() {
		return student.getCourses();
	}
	public void setStudentCourses(ArrayList<Course> courses) {
		student.setCourses(courses);
	}
	public int getStudentGpa() {
		return student.getGpa();
	}
	public void setStudentGpa(int gpa) {
		student.setGpa(gpa);
	}
	public String getStudentTerm() {
		return student.getTerm();
	}
	public void setStudentTerm(String term) {student.setTerm(term);}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == studentView.getMenubar().getMenu(0).getItem(0) || e.getSource() == studentView.getLoadCoursesB()) {
			JFileChooser fileChooser = new JFileChooser(".");
			int choice = fileChooser.showSaveDialog(studentView.getTable());
			if (choice == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Scanner scanner;
				try {
					String num, name;
					int creds;
					scanner = new Scanner(selectedFile);
					StringTokenizer s;
					while (scanner.hasNextLine()) {
						if (student.getNumCourses() == 5) {
							JOptionPane.showMessageDialog(studentView.getTable(), "Can't add more than 5 courses",
									"Error 256", JOptionPane.ERROR_MESSAGE);
							updateView();
							return;
						}
						s = new StringTokenizer(scanner.nextLine(), ";");
						Course course = new Course(s.nextToken(), s.nextToken(), Integer.parseInt(s.nextToken()), new Grade());
						student.getCourses().add(course);
						student.setNumCourses(student.getNumCourses()+1);
					}
					updateView();
				} catch (FileNotFoundException ex) {
					throw new RuntimeException(ex);
				}
			}
		}

		if (e.getSource() == studentView.getSaveCoursesM()) {
			JFileChooser fileChooser = new JFileChooser("");
			int choice = fileChooser.showSaveDialog(studentView.getTable());
			if (choice == JFileChooser.APPROVE_OPTION) {
				File selectedFile = null;
				if (!fileChooser.getSelectedFile().toString().endsWith(".txt")) {
					selectedFile = new File(fileChooser.getSelectedFile()+".txt");
				}
				else selectedFile = fileChooser.getSelectedFile();
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(selectedFile));
					int i = 3;
					while (i < studentView.getTable().getRowCount() - 1) {
						writer.write(String.valueOf(studentView.getTable().getValueAt(i, 1))
								+ ";" + String.valueOf(studentView.getTable().getValueAt(i, 2))
								+ ";" + String.valueOf(studentView.getTable().getValueAt(i, 3))
								+ ";\n");
						++i;
					}
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
					}
				}
			}
		}
	}
}

