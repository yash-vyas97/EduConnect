import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TeacherLogin {
    JFrame frame1 = new JFrame();
    public TeacherLogin() {

        frame1.setSize(1000, 500);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(108, 122, 137));
        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel label1 = new JLabel("Teacher");
        label1.setFont(new Font("Forte", Font.PLAIN, 35));
        label1.setBounds(440,20,430,100);
        panel1.add(label1);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(405,420, 250,50);
        panel1.add(copyright);

        JLabel username = new JLabel("Username");
        username.setBounds(360,150,100,30);
        panel1.add(username);

        JTextField txt1 = new JTextField(20);
        txt1.setBounds(440,150,135,30);
        panel1.add(txt1);

        JLabel password = new JLabel("Password");
        password.setBounds(360,190,100,30);
        panel1.add(password);

        JPasswordField txt2 = new JPasswordField(15);
        txt2.setBounds(440,190,135,30);
        panel1.add(txt2);

        JButton login = new JButton("Login");
        login.setBorderPainted(false);
        login.setBackground(new Color(210, 215, 211));
        login.setFocusPainted(false);
        login.setBounds(445, 240,120,20);
        panel1.add(login);

        JLabel my_label1 = new JLabel("New to Fuzu Academy? Click to register");
        my_label1.setBounds(385,280,240,30);
        panel1.add(my_label1);

        JButton register_form = new JButton("Register Form");
        register_form.setBorderPainted(false);
        register_form.setBackground(new Color(210, 215, 211));
        register_form.setFocusPainted(false);
        register_form.setBounds(430, 320,150,20);
        panel1.add(register_form);

        JButton home = new JButton("Home Page");
        home.setBorderPainted(false);
        home.setBackground(new Color(210, 215, 211));
        home.setFocusPainted(false);
        home.setBounds(443, 410,120,20);
        panel1.add(home);

        register_form.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                if(e.getSource() == register_form) {
                    new TeacherRegistration();
                }
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                if(e.getSource() == home) {
                    new WelcomePage();
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == login) {
                    PreparedStatement ps2;
                    ResultSet rs1;
                    String username = txt1.getText();
                    String password = txt2.getText();

                    String query1 = "SELECT * FROM `tbl_teacher` WHERE `Username` =? AND `Password` =?";
                    try {
                        ps2 = DatabaseConnection.getConnection().prepareStatement(query1);
                        ps2.setString(1, username);
                        ps2.setString(2, password);
                        rs1 = ps2.executeQuery();

                        if (rs1.next()) {
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            frame1.dispose();
                            new MarksModifier();
                        } else {
                            JOptionPane.showMessageDialog(null, "Login Denied! Enter correct username or password");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TeacherLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txt1.setText("");
                    txt2.setText("");


                }
            }
        });

        frame1.setVisible(true);
    }
}