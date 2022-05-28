package sis;

import javax.swing.*;
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

public class AdminController implements ActionListener {
    private Admin admin;
    private AdminView adminView;

    public AdminController(){}
    public AdminController(Admin ad, AdminView adView) {
        this.admin = ad;
        this.adminView= adView;
    }
    public void updateView() {
        this.adminView.setAdmin(this.admin);
    }
    public AdminView getAdminView() {
        return this.adminView;
    }
    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }
    public Admin getAdmin() {
        return this.admin;
    }
    public void setStudent(Admin admin) {
        this.admin= admin;
    }

    public String getAdminName() {
        return admin.getName();
    }
    public void setAdminName(String name) {
        admin.setName(name);
    }
    public String getAdminId() {
        return admin.getId();
    }
    public void setAdminId(String id) {
        admin.setId(id);
    }
    public String getAdminUsername() {
        return admin.getUsername();
    }
    public void setAdminUsername(String username) {
        admin.setUsername(username);
    }
    public String getAdminPassword() {
        return admin.getPassword();
    }
    public void setAdminPassword(String password) {
        admin.setPassword(password);
    }


    public ArrayList<Course> getAdminCourses() {
        return admin.getCrs();
    }
    public void setAdminCourses(ArrayList<Course> courses) {
        admin.setCrs(courses);
    }
    public String getAdminTerm() {
        return admin.getTerm();
    }
    public void setAdminTerm(String trm) {
        admin.setTerm(trm);
    }
    public ArrayList<ArrayList<Student>> getAdminAllStds(){return this.admin.getAllStds();}
    public void setAdminAllStds(ArrayList<ArrayList<Student>> stds) { this.admin.setAllStds(stds);}
    private void addCourse(Course course){
        admin.getCrs().add(course);
        admin.getAllStds().add(new ArrayList<Student>());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminView.getMenuItem(0, 0)) {
            admin.setName(JOptionPane.showInputDialog(adminView, "New Name:", admin.getName()));
            adminView.getCoursesTable().setValueAt(admin.getName(), 0, 1);
            for (int i = 0; i < adminView.getStdsTable().size(); ++i) {
                adminView.getStdsTable().get(i).setValueAt(admin.getName(), 0, 1);
            }
        }
        else if (e.getSource() == adminView.getMenuItem(0, 1)) {
            admin.setPassword(JOptionPane.showInputDialog(adminView, "New Password:", admin.getPassword()));
        }
        else if (e.getSource() == adminView.getMenuItem(1, 0)) {
            //		Course course = new Course("GUI", "CMP256", "Spring2022", 3, grade);
            if (admin.getCrs().size()>3) {
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
            int option = JOptionPane.showConfirmDialog(adminView, myPanel, "Add Course",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                Course course = new Course(field1.getText(), field2.getText(),
                        Integer.parseInt(field4.getText()), new ArrayList<Grade>());
                addCourse(course);

                //table
                adminView.addTable(new JTable());
                //scrollpane
                adminView.addScrollPane(new JScrollPane());
                //model
                adminView.addModel(new DefaultTableModel(new String[]{"", "", "", "", "", ""},
                        4 + admin.getAllStds().get(admin.getCrs().size()-1).size()));
                adminView.updateStdsData(admin.getCrs().size()-1);
            }
            else if (option==JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(adminView, "Canceled");
            }
        }
        else if (e.getSource() == adminView.getMenuItem(1, 1)) {
            JFileChooser fileChooser = new JFileChooser("");
            int choice = fileChooser.showSaveDialog(adminView.getCoursesTable());
            if (choice == JFileChooser.APPROVE_OPTION) {
                File selectedFile = null;
                if (!fileChooser.getSelectedFile().toString().endsWith(".txt")) {
                    selectedFile = new File(fileChooser.getSelectedFile()+".txt");
                }
                else selectedFile = fileChooser.getSelectedFile();
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(selectedFile));
                    int i = 0;
                    while (i < admin.getCrs().size()) {
                        writer.write( admin.getCrs().get(i).getName()+";"
                                + admin.getCrs().get(i).getCourseCode() + ";"
                                + admin.getCrs().get(i).getCredits() + ";\n");
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
        else if (e.getSource() == adminView.getCourseChooser()) {
            CardLayout cl = (CardLayout) (adminView.getTopPanel().getLayout());
            cl.show(adminView.getTopPanel(), (String)adminView.getCourseChooser().getSelectedItem());
        }
    }

}
