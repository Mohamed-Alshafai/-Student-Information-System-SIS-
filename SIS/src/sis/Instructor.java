package sis;

import java.util.ArrayList;

public class Instructor extends User {
	private String department;
	private ArrayList<Course> crs = new ArrayList<Course>();
	private ArrayList<ArrayList<Student>> stds = new ArrayList<ArrayList<Student>>();
	public Instructor(){
		super("","","","","");
	}
	public Instructor(String name, String id, String username, String password, ArrayList<Course> crs,
					  String department, String term, ArrayList<ArrayList<Student>> stds) {
		super(name, id, username, password, term);
		this.department = department;
		if (crs != null){
			this.crs = crs;
		}
		if (stds!=null){
			this.stds = stds;
		}

	}
	public Instructor(String name, String id, String username, String password, Course course,
					  String department2, String term, ArrayList<Student> stds) {
		super(name, id, username, password, term);
		this.department = department2;
		this.crs.add(course);
		if (stds!=null){
			this.stds.add(stds);
		}
	}
	public ArrayList<ArrayList<Student>> getAllStds(){return this.stds;}
	public void setAllStds(ArrayList<ArrayList<Student>> stds) { this.stds = stds;}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ArrayList<Course> getCrs() {
		return crs;
	}

	public void setCrs(ArrayList<Course> crs) {
		this.crs = crs;
	}

}
