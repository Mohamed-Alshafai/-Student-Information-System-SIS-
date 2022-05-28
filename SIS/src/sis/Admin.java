package sis;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Course> crs = new ArrayList<Course>();
    private ArrayList<ArrayList<Student>> stds = new ArrayList<ArrayList<Student>>();
    public Admin(){
        super("","","","", "");
    }
    public Admin(String name, String id, String username, String password, String term, ArrayList<Course> crs, ArrayList<ArrayList<Student>> stds) {
       super(name, id, username, password, term);
        if (crs != null){
            this.crs = crs;
        }
        if (stds!=null){
            this.stds = stds;
        }
    }

    public Admin(String name, String id, String username, String password, String term, Course course, ArrayList<Student> stds) {
       super(name, id, username, password, term);
        if (course!=null) {
            this.crs.add(course);
        }

        if (stds!=null){
            this.stds.add(stds);
        }
    }

    public ArrayList<ArrayList<Student>> getAllStds(){return this.stds;}
    public void setAllStds(ArrayList<ArrayList<Student>> stds) { this.stds = stds;}
    public ArrayList<Course> getCrs() {return crs;}
    public void setCrs(ArrayList<Course> crs) {this.crs = crs;}
}
