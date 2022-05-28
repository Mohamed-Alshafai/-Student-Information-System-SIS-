package sis;

import java.util.ArrayList;

public class Student extends User {
	private String major;
	private int numCourses;
	private ArrayList<Course> courses;
	private int gpa;
	public Student(){
		super("","","","","");
	}
	public Student(String name, String id, String username, String password,
			String major, String term, ArrayList<Course> courses) {
		super(name, id, username, password, term);
		this.major = major;
		
		
		if (courses != null) {
			this.courses = courses;
			this.numCourses = this.courses.size();
			int weightedSum = 0; int totCredits = 0;
			for (int i = 0; i < this.numCourses; ++i) {
				// calculate gpa
				if (this.courses.get(i).getStdGrades().get(0).getGrade() != -1) {
					totCredits += this.courses.get(i).getCredits();
					weightedSum += (this.courses.get(i).getCredits()*(int)this.courses.get(i).getStdGrades().get(0).getGrade());
				}

			}
			if (totCredits != 0) this.gpa = weightedSum/totCredits;
			else this.gpa = 0;
		}
		else {
			this.courses = new ArrayList<Course>();
			this.numCourses = 0;
		}
	}

	public Student(String name, String id, String username, String password,
			String major, String term, Course courses) {
		super(name, id, username, password, term);
		this.major = major;
		
		this.courses = new ArrayList<Course>();
		this.numCourses = 1;
		this.courses.add(courses);
		this.gpa = courses.getStdGrades().get(0).getGrade();
	}
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getNumCourses() {
		return numCourses;
	}
	public void setNumCourses(int numCourses) {
		this.numCourses = numCourses;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	public int getGpa() {
		return gpa;
	}
	public void setGpa(int gpa) {
		this.gpa = gpa;
	}
	public void updateGpa() {
		int weightedSum = 0; int totCredits = 0;
		for (int i = 0; i < this.numCourses; ++i) {
			// calculate gpa
			if (this.courses.get(i).getStdGrades().get(0).getGrade() != -1) {
				totCredits += this.courses.get(i).getCredits();
				weightedSum += (this.courses.get(i).getCredits()*(int)this.courses.get(i).getStdGrades().get(0).getGrade());
			}
		}
		if (totCredits != 0) this.gpa = weightedSum/totCredits;
		else this.gpa = 0;
	}

	public void addCourse(Course course) {
		if (course != null) {
			this.courses.add(course);
			this.numCourses++;

		}
	}
}
