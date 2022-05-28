package sis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SISView extends JFrame {
    private SIS sis;
    private JButton logInButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    SISView() {
        sis = new SIS();
        initGUI();
    }
    SISView(SIS sis) {
        this.sis = sis;
        initGUI();
    }

    private void initGUI() {
        setTitle("AUS Self-Service System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 660, 476);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // panel of options
        JPanel panel = new JPanel();
        panel.setBounds(10, 251, 626, 178);
        contentPane.add(panel);

        // first jpanel for logo
        JPanel logo = new JPanel();
        // set panel dimensions
        logo.setBounds(188, 33, 92, 69);
        // add panel to the pane
        contentPane.add(logo);
        // set the layout of the panel
        logo.setLayout(new BorderLayout(0, 0));
        // label of AUS
        JLabel AUS = new JLabel("AUS");
        // change foreground color to maroon
        AUS.setForeground(new Color(128, 0, 0));
        // change font
        AUS.setFont(new Font("Arial", Font.BOLD, 40));
        // add to panel
        logo.add(AUS);
        // label of banner, same operations as AUS label
        JLabel bannerExplain = new JLabel("Banner Self-Service System");
        bannerExplain.setFont(new Font("Arial", Font.BOLD, 13));
        bannerExplain.setBounds(280, 36, 187, 66);
        contentPane.add(bannerExplain);
        // 2nd panel for log in
        JPanel logInPanel = new JPanel();
        // set dimensions of panel
        logInPanel.setBounds(188, 112, 279, 129);
        // add panel to pane
        contentPane.add(logInPanel);
        // remove layout
        logInPanel.setLayout(null);
        // create and add password field to log in panel
        passwordField = new JPasswordField();
        passwordField.setBounds(85, 58, 184, 30);
        logInPanel.add(passwordField);
        // create and add user field to log in panel
        usernameField = new JTextField();
        usernameField.setBounds(85, 18, 184, 30);
        logInPanel.add(usernameField);
        usernameField.setColumns(10);
        // create and add label of username
        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setBounds(10, 21, 65, 22);
        logInPanel.add(lblNewLabel);
// create and add label of password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 66, 65, 22);
        logInPanel.add(lblPassword);
//create and add login button
        logInButton = new JButton("Log In");
        logInButton.setBounds(20, 98, 85, 21);
        logInPanel.add(logInButton);
    }
    public void registerListener(SISController listener) {
        logInButton.addActionListener(listener);
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }
    public SIS getSis() {
        return sis;
    }
}