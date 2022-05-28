package sis;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class InstructorController implements ActionListener {
	private Instructor inst;
	private InstructorView instView;

	public InstructorController(){}
	public InstructorController(Instructor inst, InstructorView instView) {
		this.inst = inst;
		this.instView = instView;
	}
	public void updateView() {
		this.instView.setInstructor(this.inst);
	}
	public InstructorView getInstructorView() {
		return this.instView;
	}
	public void setInstructorView(InstructorView instView) {
		this.instView = instView;
	}
	public Instructor getInstructor() {
		return this.inst;
	}
	public void setStudent(Instructor inst) {
		this.inst= inst;
	}
	
	public String getInstructorName() {
		return inst.getName();
	}
	public void setInstructorName(String name) {
		inst.setName(name);
	}
	public String getInstructorId() {
		return inst.getId();
	}
	public void setInstructorId(String id) {
		inst.setId(id);
	}


	public String getInstructorUsername() {
		return inst.getUsername();
	}
	public void setInstructorUsername(String username) {
		inst.setUsername(username);
	}
	public String getInstructorPassword() {
		return inst.getPassword();
	}
	public void setInstructorPassword(String password) {
		inst.setPassword(password);
	}


	public ArrayList<Course> getInstructorCourses() {
		return inst.getCrs();
	}
	public void setInstructorCourses(ArrayList<Course> courses) {
		inst.setCrs(courses);
	}
	public String getInstructorDepartment() {
		return inst.getDepartment();
	}
	public void setInstructorDepartment(String dept) {
		inst.setDepartment(dept);
	}
	public String getInstructorTerm() {
		return inst.getTerm();
	}
	public void setInstructorTerm(String trm) {
		inst.setTerm(trm);
	}
	public ArrayList<ArrayList<Student>> getInstrAllStds(){return this.inst.getAllStds();}
	public void setInstrAllStds(ArrayList<ArrayList<Student>> stds) { this.inst.setAllStds(stds);}
	private void addCourse(Course course){
		inst.getCrs().add(course);
		inst.getAllStds().add(new ArrayList<Student>());
	}
	//:) professor, for the next semesters pls go easy on them... we want to unalive ourselves
	public void changeGrade(int r, int c, int newGrade) { // r is for the class the student is in, c is for the student's index
		this.inst.getCrs().get(r).getStdGrades().get(c).setGrade(newGrade);
		this.instView.getStdsTable(r).setValueAt(newGrade, c+4, 2);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == instView.getMenuItem(0, 0)) {
			inst.setName(JOptionPane.showInputDialog(instView, "New Name:", inst.getName()));
			instView.getCoursesTable().setValueAt(inst.getName(), 0, 1);
			for (int i = 0; i < instView.getStdsTable().size(); ++i) {
				instView.getStdsTable().get(i).setValueAt(inst.getName(), 0, 1);
			}
		}
		else if (e.getSource() == instView.getMenuItem(0, 1)) {
			inst.setPassword(JOptionPane.showInputDialog(instView, "New Password:", inst.getPassword()));
		}
		else if (e.getSource() == instView.getMenuItem(1, 0)) {
			//		Course course = new Course("GUI", "CMP256", "Spring2022", 3, grade);
			if (inst.getCrs().size()>3) {
				JOptionPane.showMessageDialog(null, "Can't add more than 3 courses",
						"Error404", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JPanel myPanel = new JPanel();

			myPanel.setLayout(new GridLayout(2, 4, 5, 5));

			JTextField field1 = new JTextField(10);
			JTextField field2 = new JTextField(10);
			JTextField field4 = new JTextField(10);
			JLabel label1 = new JLabel("Name:");
			JLabel label2 = new JLabel("Code:");
			JLabel label4 = new JLabel("Credits:");
			myPanel.add(label1);
			myPanel.add(label2);
			myPanel.add(label4);
			myPanel.add(field1);
			myPanel.add(field2);
			myPanel.add(field4);
			//JOptionPane.showMessageDialog(null, myPanel);
			int option = JOptionPane.showConfirmDialog(instView, myPanel, "Add Course",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				Course course = new Course(field1.getText(), field2.getText(),
						Integer.parseInt(field4.getText()), new ArrayList<Grade>());
				addCourse(course);

				//table
				instView.addTable(new JTable());
				//scrollpane
				instView.addScrollPane(new JScrollPane());
				//model
				instView.addModel(new DefaultTableModel(new String[]{"", "", "", "", "", ""},
						4 + inst.getAllStds().get(inst.getCrs().size()-1).size()));
				instView.updateStdsData(inst.getCrs().size()-1);
			}
			else if (option==JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(instView, "Canceled");
			}
		}
		else if (e.getSource() == instView.getChangeGrades()){

			Vector<Vector<String>> stds = new Vector<Vector<String>>();
			Vector<String> crs = new Vector<String>();
			for (int i = 0; i < inst.getCrs().size(); ++i) {
				crs.add(inst.getCrs().get(i).getName());
			}
			for (int i = 0; i < inst.getAllStds().size(); ++i) {
				Vector<String> std = new Vector<String>();
				for (int j = 0; j < inst.getAllStds().get(i).size(); ++j) {
					std.add(inst.getAllStds().get(i).get(j).getName());
				}
				stds.add(std);
			}

			JFrame changeGradesFrame = new JFrame("Change Grade");
			changeGradesFrame.setBounds(100, 100, 250,150);
			changeGradesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			changeGradesFrame.setVisible(true);
			JPanel mainPanel = new JPanel();
			changeGradesFrame.setContentPane(mainPanel);
			mainPanel.setLayout(new BorderLayout());

			JPanel cardsPanel = new JPanel();
			cardsPanel.setLayout(new CardLayout());

			JPanel functionPanel = new JPanel();
			functionPanel.setLayout(new FlowLayout());

			ArrayList<JComboBox> comboBox2 = new ArrayList<JComboBox>();
			for (int i = 0; i < stds.size(); ++i) {
				comboBox2.add(new JComboBox(stds.get(i)));
			}
			JComboBox comboBox1 = new JComboBox(crs);
			comboBox1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout) (cardsPanel.getLayout());
					cl.show(cardsPanel, (String)comboBox1.getSelectedItem());
				}
			});
			JTextField newGrade = new JTextField("New Grade");
			JButton changeGrade = new JButton("Apply");
			changeGrade.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = crs.indexOf(comboBox1.getSelectedItem());
					changeGrade(comboBox1.getSelectedIndex(), comboBox2.get(index).getSelectedIndex(),
							Integer.parseInt(newGrade.getText()));
					changeGradesFrame.dispose();
				}
			});
			for (int i = 0; i < stds.size(); ++i) {
				JPanel newPanel = new JPanel();
				newPanel.setLayout(new BorderLayout());
				newPanel.add(comboBox2.get(i), BorderLayout.CENTER);
				cardsPanel.add(newPanel, crs.get(i));
			}
			functionPanel.add(comboBox1);
			functionPanel.add(newGrade);
			functionPanel.add(changeGrade);
			mainPanel.add(cardsPanel, BorderLayout.SOUTH);
			mainPanel.add(functionPanel, BorderLayout.CENTER);
		}
		else if (e.getSource() == instView.getCourseChooser()) {
			CardLayout cl = (CardLayout) (instView.getTopPanel().getLayout());
			cl.show(instView.getTopPanel(), (String)instView.getCourseChooser().getSelectedItem());
		}
	}
}
