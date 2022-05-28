package sis;

import javax.swing.*;

public class SISThreads implements Runnable {
    private int accountType;
    private String username;
    private SISController sisController;
    SISThreads(SISController sisController, int accountType, String username) {
        this.username = username;
        this.accountType = accountType;
        if (sisController!=null) {
            this.sisController = sisController;
        }
        else System.exit(-1);
    }

    public String getUsername() {
        return username;
    }

    public int getAccountType() {
        return accountType;
    }

    @SuppressWarnings("")
    @Override
    public void run() {
        switch (accountType) {
            case 0: {
                Admin admin = new Admin();
                AdminView adminView = new AdminView();
                AdminController adminController = new AdminController(admin, adminView);
                sisController.retrieveUser(adminController, this);
                adminView.initGui();
                adminView.registerListener(adminController);
                adminView.setVisible(true);
                break;
            }
            case 1: {
                Instructor instructor = new Instructor();
                InstructorView instructorView = new InstructorView();
                InstructorController instructorController = new InstructorController(instructor, instructorView);
                sisController.retrieveUser(instructorController, this);
                instructorView.initGui();
                instructorView.registerListener(instructorController);
                instructorView.setVisible(true);
                break;
            }
            case 2: {
                Student student = new Student();
                StudentView studentView = new StudentView();
                StudentController studentController = new StudentController(student, studentView);
                sisController.retrieveUser(studentController, this);
                studentView.initGui();
                studentView.registerListener(studentController);
                studentView.setVisible(true);
                break;
            }

        }
    }
}
