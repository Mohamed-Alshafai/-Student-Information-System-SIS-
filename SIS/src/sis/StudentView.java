package sis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentView extends JFrame {
	final String[] headers = {"","","","","",""};
	private Student student;
	private JTable table;
	private DefaultTableModel model;
	private JButton loadCoursesB;
	private JMenuBar menuBar;
	private JMenu coursesMenu;
	private JMenuItem loadCoursesM;
	private JMenuItem saveCoursesM;
	private JPanel bottomPanel;
	/**
	 * Create the panel.
	 */
	public StudentView(){}
	public StudentView(Student student) {
		this.student = student;
		initGui();
	}
	
	public void initGui() {
		setBounds(100, 100, 600, 500);
		setTitle("Student System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		/// Information Table
		table = new JTable();

		model = new DefaultTableModel(headers, 4+student.getNumCourses());
		table.setModel(model);
		// first row values
		table.setValueAt("Name", 0, 0);
		table.setValueAt(student.getName(), 0, 1);
		table.setValueAt("ID", 0, 2);
		table.setValueAt(student.getId(), 0, 3);
		table.setValueAt("Major", 0, 4);
		table.setValueAt(student.getMajor(), 0, 5);
		// second row values
		table.setValueAt("Semester", 1, 0);
		table.setValueAt(student.getTerm(), 1, 1);
		// third row values
		table.setValueAt("Courses", 2, 0);
		table.setValueAt("Name", 2, 1);
		table.setValueAt("Number", 2, 2);
		table.setValueAt("Credits", 2, 3);
		table.setValueAt("Grade", 2, 4);
		// filling courses info starting 4th row
		int i = 3;
		int numCourse = 0;
		while (i < student.getNumCourses()+3) {
			table.setValueAt(i-2, i, 0);
			table.setValueAt(student.getCourses().get(numCourse).getName(), i, 1);
			table.setValueAt(student.getCourses().get(numCourse).getCourseCode(), i, 2);
			table.setValueAt(student.getCourses().get(numCourse).getCredits(), i, 3);
			table.setValueAt(student.getCourses().get(numCourse).getStdGrades().get(0).getGrade(), i, 4);
			++i;
			++numCourse;
		}
		//filling last row
		table.setValueAt("GPA", i, 3);
		table.setValueAt(student.getGpa(), i, 4);
		JScrollPane studentInfoPane = new JScrollPane(table);
		add(studentInfoPane, BorderLayout.CENTER);
		///// Menu Bar
		menuBar = new JMenuBar();
		coursesMenu = new JMenu("Course Tools");

		loadCoursesM = new JMenuItem("Load Courses");
		saveCoursesM = new JMenuItem("Save Courses");
		coursesMenu.add(loadCoursesM);
		coursesMenu.addSeparator();
		coursesMenu.add(saveCoursesM);
		//coursesMenu.addSeparator();
		menuBar.add(coursesMenu);
		add(menuBar, BorderLayout.NORTH);
		///// Button
		loadCoursesB = new JButton("Load Courses");
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(loadCoursesB);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void updateData() {
		student.updateGpa();
		int size = student.getCourses().size()-(model.getRowCount()-4);
		for (int i = 0; i < size; ++i) {
			model.insertRow(model.getRowCount(), new Object[]{});
		}
		// first row values
		table.setValueAt("Name", 0, 0);
		table.setValueAt(student.getName(), 0, 1);
		table.setValueAt("ID", 0, 2);
		table.setValueAt(student.getId(), 0, 3);
		table.setValueAt("Major", 0, 4);
		table.setValueAt(student.getMajor(), 0, 5);
		// second row values
		table.setValueAt("Semester", 1, 0);
		table.setValueAt(student.getTerm(), 1, 1);
		// third row values
		table.setValueAt("Courses", 2, 0);
		table.setValueAt("Name", 2, 1);
		table.setValueAt("Number", 2, 2);
		table.setValueAt("Credits", 2, 3);
		table.setValueAt("Grade", 2, 4);
		// filling courses info starting 4th row
		int i = 3;
		int courseIndex = 0;
		while (i < student.getNumCourses()+3) {
			table.setValueAt(courseIndex+1, i, 0);
			table.setValueAt(student.getCourses().get(courseIndex).getName(), i, 1);
			table.setValueAt(student.getCourses().get(courseIndex).getCourseCode(), i, 2);
			table.setValueAt(student.getCourses().get(courseIndex).getCredits(), i, 3);
			table.setValueAt(student.getCourses().get(courseIndex).getStdGrades().get(0).getGrade(), i, 4);
			++i;
			++courseIndex;
		}
		//filling last row
		table.setValueAt("GPA", i, 3);
		table.setValueAt(student.getGpa(), i, 4);
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return this.student;
	}
	public void registerListener(StudentController listener) {
		this.loadCoursesB.addActionListener(listener);
		this.loadCoursesM.addActionListener(listener);
		this.saveCoursesM.addActionListener(listener);
	}
	public JTable getTable() { return table; }

	public JMenuBar getMenubar() {
		return menuBar;
	}

	public JButton getLoadCoursesB() {
		return loadCoursesB;
	}
	public JMenuItem getSaveCoursesM() {
		return saveCoursesM;
	}
	@Override
	public void dispose(){
		File file = new File("textFiles/Users/"+student.getUsername()+".txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(student.getName()+";"+student.getId()+";"+student.getUsername()+";"
					+student.getPassword()+";"+student.getMajor()+";"+student.getTerm()+";"
					+student.getNumCourses()+";");
			for (int i = 0; i < student.getNumCourses(); ++i) {
				writer.write(student.getCourses().get(i).getName() + ";" + student.getCourses().get(i).getCourseCode() + ";"
						+ student.getCourses().get(i).getCredits() + ";");
			}
			writer.close();
			super.dispose();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
