import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentLogin {
    JFrame frame8 = new JFrame();
    public StudentLogin(){

        JPanel panel7 = new JPanel();
        panel7.setBackground(new Color(108, 122, 137));
        panel7.setLayout(null);

        frame8.setSize(1000, 500);
        frame8.setResizable(false);
        frame8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame8.add(panel7);

        JLabel label1 = new JLabel("Student");
        label1.setFont(new Font("Forte", Font.PLAIN, 35));
        label1.setBounds(440, 20,430,100);
        panel7.add(label1);

        JLabel copyright1 = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright1.setBounds(405,420, 250,50);
        panel7.add(copyright1);

        JLabel username = new JLabel("Username");
        username.setBounds(360,150,100,30);
        panel7.add(username);

        JTextField txt20 = new JTextField(20);
        txt20.setBounds(440,150,135,30);
        panel7.add(txt20);

        JLabel password = new JLabel("Password");
        password.setBounds(360,190,100,30);
        panel7.add(password);

        JPasswordField txt21 = new JPasswordField(15);
        txt21.setBounds(440,190,135,30);
        panel7.add(txt21);

        JButton login = new JButton("Login");
        login.setBorderPainted(false);
        login.setBackground(new Color(210, 215, 211));
        login.setFocusPainted(false);
        login.setBounds(445, 240,120,20);
        panel7.add(login);

        JButton home3 = new JButton("Home Page");
        home3.setBorderPainted(false);
        home3.setBackground(new Color(210, 215, 211));
        home3.setFocusPainted(false);
        home3.setBounds(443, 410,120,20);
        panel7.add(home3);

        home3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == home3){
                    frame8.dispose();
                    new WelcomePage();
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == login){
                    PreparedStatement ps9;
                    ResultSet rs9;
                    String ChildFullName = txt20.getText();
                    String ChildAdmNo = txt21.getText();

                    String query9 = "SELECT * FROM `tbl_parent` WHERE `ChildFullName`=? AND `ChildAdmNo`=?";
                    try {
                        ps9 = DatabaseConnection.getConnection().prepareStatement(query9);
                        ps9.setString(1, ChildFullName);
                        ps9.setString(2, ChildAdmNo);
                        rs9 = ps9.executeQuery();

                        if (rs9.next()) {
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            frame8.dispose();
                            new SportsClub();
                        } else {
                            JOptionPane.showMessageDialog(null, "Login Denied! Enter correct username or password");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txt20.setText("");
                    txt21.setText("");
                }
            }
        });
        frame8.setVisible(true);
    }
}