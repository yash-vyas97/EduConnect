import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherRegistration {
    JFrame frame3 = new JFrame();
    public TeacherRegistration(){

        frame3.setSize(1000, 500);
        frame3.setResizable(false);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(108, 122, 137));
        panel3.setLayout(null);
        frame3.add(panel3);

        JLabel label3 = new JLabel("Teacher Registration");
        label3.setFont(new Font("Forte", Font.PLAIN, 35));
        label3.setBounds(340,20,430,100);
        panel3.add(label3);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(400,420, 250,50);
        panel3.add(copyright);

        JLabel fullname = new JLabel("Full Name");
        fullname.setBounds(360,110,100,30);
        panel3.add(fullname);

        JTextField txt5 = new JTextField(20);
        txt5.setBounds(440,110,135,30);
        panel3.add(txt5);

        JLabel username = new JLabel("Username");
        username.setBounds(360,150,100,30);
        panel3.add(username);

        JTextField txt6 = new JTextField(20);
        txt6.setBounds(440,150,135,30);
        panel3.add(txt6);

        JLabel password = new JLabel("Password");
        password.setBounds(360,190,100,30);
        panel3.add(password);

        JPasswordField txt7 = new JPasswordField(15);
        txt7.setBounds(440,190,135,30);
        panel3.add(txt7);

        JButton registration = new JButton("Register");
        registration.setBorderPainted(false);
        registration.setBackground(new Color(210, 215, 211));
        registration.setFocusPainted(false);
        registration.setBounds(430, 250,150,20);
        panel3.add(registration);

        JLabel return1 = new JLabel("Click below to login");
        return1.setBounds(450,310, 150,30);
        panel3.add(return1);

        JButton logBack = new JButton("Login Page");
        logBack.setBorderPainted(false);
        logBack.setBackground(new Color(210, 215, 211));
        logBack.setFocusPainted(false);
        logBack.setBounds(443, 340,130,20);
        panel3.add(logBack);

        logBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                if(e.getSource() == logBack) {
                    new TeacherLogin();
                }
            }
        });

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == registration) {
                    String fullname = txt5.getText();
                    String username = txt6.getText();
                    String password = txt7.getText();

                    if (fullname.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add fullname", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (username.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add username", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please set a password", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else {

                        PreparedStatement ps;
                        String query = "INSERT INTO `tbl_teacher`(`fullname`, `Username`, `Password`) VALUES (?,?,?)";

                        try {
                            ps = DatabaseConnection.getConnection().prepareStatement(query);
                            ps.setString(1, fullname);
                            ps.setString(2, username);
                            ps.setString(3, password);

                            if (ps.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "New Teacher Added", "Registration Successful", JOptionPane.PLAIN_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(TeacherRegistration.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        txt5.setText("");
                        txt6.setText("");
                        txt7.setText("");
                    }
                }
            }
        });

        frame3.setVisible(true);
    }
}