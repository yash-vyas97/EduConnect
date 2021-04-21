import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParentLogin {
    JFrame frame2 = new JFrame();
    public ParentLogin(){
        frame2.setSize(1000, 500);
        frame2.setResizable(false);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(108, 122, 137));
        panel2.setLayout(null);
        frame2.add(panel2);

        JLabel label2 = new JLabel("Parent");
        label2.setFont(new Font("Forte", Font.PLAIN, 35));
        label2.setBounds(440,20,430,100);
        panel2.add(label2);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(405,420, 250,50);
        panel2.add(copyright);

        JLabel username = new JLabel("Username");
        username.setBounds(360,150,100,30);
        panel2.add(username);

        JTextField txt3 = new JTextField(20);
        txt3.setBounds(440,150,135,30);
        panel2.add(txt3);

        JLabel password2 = new JLabel("Password");
        password2.setBounds(360,190,100,30);
        panel2.add(password2);

        JPasswordField txt4 = new JPasswordField(15);
        txt4.setBounds(440,190,135,30);
        panel2.add(txt4);

        JButton login1 = new JButton("Login");
        login1.setBorderPainted(false);
        login1.setBackground(new Color(210, 215, 211));
        login1.setFocusPainted(false);
        login1.setBounds(445, 240,120,20);
        panel2.add(login1);

        JLabel my_label2 = new JLabel("New to Fuzu Academy? Click to register");
        my_label2.setBounds(385,280,240,30);
        panel2.add(my_label2);

        JButton register = new JButton("Register Form");
        register.setBorderPainted(false);
        register.setBackground(new Color(210, 215, 211));
        register.setFocusPainted(false);
        register.setBounds(430, 320,150,20);
        panel2.add(register);

        JButton home1 = new JButton("Home Page");
        home1.setBorderPainted(false);
        home1.setBackground(new Color(210, 215, 211));
        home1.setFocusPainted(false);
        home1.setBounds(445, 410,110,20);
        panel2.add(home1);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                if(e.getSource() == register) {
                    new ParentRegistration();
                }
            }
        });

        home1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                if(e.getSource() == home1) {
                    new WelcomePage();
                }
            }
        });

        login1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == login1) {
                    PreparedStatement ps4;
                    ResultSet rs2;
                    String username = txt3.getText();
                    String password = txt4.getText();

                    String query4 = "SELECT * FROM `tbl_parent` WHERE `Username` =? AND `Password` =?";
                    try {
                        ps4 = DatabaseConnection.getConnection().prepareStatement(query4);
                        ps4.setString(1, username);
                        ps4.setString(2, password);
                        rs2 = ps4.executeQuery();

                        if (rs2.next()) {
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            frame2.dispose();
                            new ViewMarks();
                        } else {
                            JOptionPane.showMessageDialog(null, "Login Denied! Enter correct username or password");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ParentLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txt3.setText("");
                    txt4.setText("");
                }
            }
        });

        frame2.setVisible(true);
    }
}
