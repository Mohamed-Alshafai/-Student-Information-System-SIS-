package sis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminView extends JFrame {
    Admin admin;
    AdminController listener;
    final String[] headers = {"","","","","",""};
    private JScrollPane coursesScrollPane;
    private JTable coursesTable;
    private DefaultTableModel coursesModel;

    private ArrayList<JScrollPane> stdsScrollPane;
    private ArrayList<JTable> stdsTable;
    private ArrayList<DefaultTableModel> stdsModel;
    private JMenuBar mainMenu;

    private JComboBox courseChooser;
    private JPanel topPanel;
    private Vector<String> courseNames;
    /**
     * Create the panel.
     */
    public AdminView(){}
    public AdminView(Admin admin) {
        this.admin = admin;
        initGui();
    }

    public void initGui() {
        setBounds(100, 100, 600, 500);
        setTitle("Admin System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        courseNames = new Vector<String>();
        topPanel = new JPanel();
        topPanel.setLayout(new CardLayout());

        coursesTable = new JTable();
        coursesScrollPane = new JScrollPane();

        stdsModel = new ArrayList<DefaultTableModel>();
        stdsTable = new ArrayList<JTable>();
        stdsScrollPane = new ArrayList<JScrollPane>();

        for (int i = 0; i < admin.getCrs().size(); ++i) {
            stdsTable.add(new JTable());
            stdsScrollPane.add(new JScrollPane());
        }

        courseChooser = new JComboBox();

        updateData();


        add(topPanel, BorderLayout.CENTER);
        ///////////////

        ///////////////////////
        mainMenu = new JMenuBar();

        JMenu instToolsMenu = new JMenu("Instructor Tools");
        JMenuItem changeName = new JMenuItem("Change Name");
        JMenuItem changePass = new JMenuItem("Change Password");

        instToolsMenu.add(changeName);
        instToolsMenu.add(changePass);

        JMenu courseToolsMenu = new JMenu("Course Tools");
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem saveCourses = new JMenuItem("Save Courses");
        courseToolsMenu.add(addCourse);
        courseToolsMenu.add(saveCourses);
        mainMenu.add(instToolsMenu);
        mainMenu.add(courseToolsMenu);




        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        bottomPanel.add(courseChooser);

        add(mainMenu, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateData(){
        updateAdminData();
        for (int i = 0; i < admin.getCrs().size(); ++i) {
            updateStdsData(i);
        }
    }
    public void updateAdminData() {
        if (coursesModel==null) {
            coursesModel = new DefaultTableModel(headers, 4+admin.getCrs().size());
        }
        else coursesModel.insertRow(coursesModel.getRowCount(), new Object[]{});
        coursesTable.setModel(coursesModel);
        // first row values
        coursesTable.setValueAt("Name", 0, 0);
        coursesTable.setValueAt(admin.getName(), 0, 1);
        coursesTable.setValueAt("ID", 0, 2);
        coursesTable.setValueAt(admin.getId(), 0, 3);
        coursesTable.setValueAt("Department", 0, 4);
        coursesTable.setValueAt("admin", 0, 5);
        // second row values
        coursesTable.setValueAt("Term", 1, 0);
        coursesTable.setValueAt(admin.getTerm(), 1, 1);
        // third row values
        coursesTable.setValueAt("Courses", 2, 0);
        coursesTable.setValueAt("Name", 3, 0);
        coursesTable.setValueAt("Number", 3, 1);
        // filling courses info starting 4th row
        int i = 4;
        int crsIndex = 0;
        while (i < admin.getCrs().size()+4) {
            coursesTable.setValueAt(admin.getCrs().get(crsIndex).getName(), i, 0);
            coursesTable.setValueAt(admin.getCrs().get(crsIndex).getCourseCode(), i, 1);
            ++crsIndex;
            ++i;
        }

        this.coursesScrollPane.setViewportView(coursesTable);
        topPanel.add(coursesScrollPane, "admin");
        courseChooser.addItem("admin");
    }

    public void updateStdsData(int tIndex) {
        if (stdsModel.size() == tIndex) {
            stdsModel.add(new DefaultTableModel(headers, 4 + admin.getAllStds().get(tIndex).size()));
        }
        stdsTable.get(tIndex).setModel(stdsModel.get(tIndex));
        // first row values
        stdsTable.get(tIndex).setValueAt("Name", 0, 0);
        stdsTable.get(tIndex).setValueAt(admin.getName(), 0, 1);
        stdsTable.get(tIndex).setValueAt("ID", 0, 2);
        stdsTable.get(tIndex).setValueAt(admin.getId(), 0, 3);
        stdsTable.get(tIndex).setValueAt("Department", 0, 4);
        stdsTable.get(tIndex).setValueAt("admin", 0, 5);
        // second row values
        stdsTable.get(tIndex).setValueAt("Term", 1, 0);
        stdsTable.get(tIndex).setValueAt(admin.getTerm(), 1, 1);
        // third row values
        int i = 2;

        int stdsIndex = 0;
        stdsTable.get(tIndex).setValueAt("Course", i, 0);
        stdsTable.get(tIndex).setValueAt(admin.getCrs().get(tIndex).getCourseCode(), i, 1);
        stdsTable.get(tIndex).setValueAt(admin.getCrs().get(tIndex).getName(), i, 2);
        ++i;
        stdsTable.get(tIndex).setValueAt("ID", i, 0);
        stdsTable.get(tIndex).setValueAt("Name", i, 1);
        stdsTable.get(tIndex).setValueAt("Grade", i, 2);
        ++i;
        while (stdsIndex < admin.getAllStds().get(tIndex).size()) {
            stdsTable.get(tIndex).setValueAt(admin.getAllStds().get(tIndex).get(stdsIndex).getId(), i, 0);
            stdsTable.get(tIndex).setValueAt(admin.getAllStds().get(tIndex).get(stdsIndex).getName(), i, 1);
            stdsTable.get(tIndex).setValueAt(admin.getCrs().get(tIndex).getStdGrades().get(stdsIndex).getGrade(),
                    i, 2);
            ++stdsIndex;
            ++i;
        }


        this.stdsScrollPane.get(tIndex).setViewportView(stdsTable.get(tIndex));
        topPanel.add(stdsScrollPane.get(tIndex), admin.getCrs().get(tIndex).getName());
        courseNames.add(admin.getCrs().get(tIndex).getName());
        courseChooser.addItem(admin.getCrs().get(tIndex).getName());
    }

    public void registerListener(AdminController listener) {
        mainMenu.getMenu(0).getItem(0).addActionListener(listener);
        mainMenu.getMenu(0).getItem(1).addActionListener(listener);
        mainMenu.getMenu(1).getItem(0).addActionListener(listener);
        mainMenu.getMenu(1).getItem(1).addActionListener(listener);

        courseChooser.addActionListener(listener);
    }
    public JMenuItem getMenuItem(int menuIndex, int itemIndex) {
        return mainMenu.getMenu(menuIndex).getItem(itemIndex);
    }

    public JTable getCoursesTable() {
        return coursesTable;
    }

    public JTable getStdsTable(int index) {
        if (index < getStdsTable().size())
            return stdsTable.get(index);
        return null;
    }
    public ArrayList<JTable> getStdsTable() { return stdsTable; }
    public DefaultTableModel getCoursesModel() {return coursesModel;}
    public ArrayList<DefaultTableModel> getStdsModel() {
        return stdsModel;
    }
    public DefaultTableModel getStdsModel(int index) {
        if (index < stdsModel.size())
            return stdsModel.get(index);
        return null;
    }
    public JPanel getTopPanel() {
        return topPanel;
    }

    public JComboBox getCourseChooser() {
        return courseChooser;
    }

    public void addTable(JTable table){stdsTable.add(table);}
    public void addScrollPane(JScrollPane scrollPane){stdsScrollPane.add(scrollPane);}
    public void addModel(DefaultTableModel model){stdsModel.add(model);}
    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    @Override
    public void dispose(){
        File file = new File("textFiles/Users/"+admin.getUsername()+".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(admin.getName()+";"+admin.getId()+";"+admin.getUsername()+";"
                    +admin.getPassword()+";"+admin.getTerm()+";"
                    +admin.getCrs().size()+";");
            for (int i = 0; i < admin.getCrs().size(); ++i) {
                writer.write(admin.getCrs().get(i).getName() + ";" + admin.getCrs().get(i).getCourseCode() + ";"
                        + admin.getCrs().get(i).getCredits() + ";");
                writer.write(admin.getCrs().get(i).getStdGrades().size() + ";");
                for (int j = 0; j < admin.getCrs().get(i).getStdGrades().size(); ++j) {
                    writer.write(admin.getCrs().get(i).getStdGrades().get(j).getGrade()+";");
                }
                writer.write(admin.getAllStds().get(i).size() + ";");
                for (int j = 0; j < admin.getAllStds().get(i).size(); ++j) {
                    writer.write(admin.getAllStds().get(i).get(j).getName()+";"+admin.getAllStds().get(i).get(j).getId()+";");
                }
            }
            writer.close();
            super.dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
