import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParentRegistration {
    JFrame frame4 = new JFrame();
    public ParentRegistration(){

        frame4.setSize(1000, 500);
        frame4.setResizable(false);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel4 = new JPanel();
        panel4.setBackground(new Color(108, 122, 137));
        panel4.setLayout(null);
        frame4.add(panel4);

        JLabel label4 = new JLabel("Parent - Child Registration");
        label4.setFont(new Font("Forte", Font.PLAIN, 35));
        label4.setBounds(315,20,430,100);
        panel4.add(label4);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(405,420, 250,50);
        panel4.add(copyright);

        JLabel fullname = new JLabel("Full Name");
        fullname.setBounds(230,110,100,30);
        panel4.add(fullname);

        JTextField txt8 = new JTextField(20);
        txt8.setBounds(310,110,135,30);
        panel4.add(txt8);

        JLabel username = new JLabel("Username");
        username.setBounds(230,150,100,30);
        panel4.add(username);

        JTextField txt9 = new JTextField(20);
        txt9.setBounds(310,150,135,30);
        panel4.add(txt9);

        JLabel parentid = new JLabel("Parent ID");
        parentid.setBounds(230,190,100,30);
        panel4.add(parentid);

        JTextField txt10 = new JTextField(20);
        txt10.setBounds(310,190,135,30);
        panel4.add(txt10);

        JLabel childname = new JLabel("Child FullName");
        childname.setBounds(490,110,100,30);
        panel4.add(childname);

        JTextField txt11 = new JTextField(20);
        txt11.setBounds(590,110,135,30);
        panel4.add(txt11);

        JLabel childAdm = new JLabel("Child AdmNo");
        childAdm.setBounds(490,150,100,30);
        panel4.add(childAdm);

        JTextField txt12 = new JTextField(20);
        txt12.setBounds(590,150,135,30);
        panel4.add(txt12);

        JLabel childclass = new JLabel("Child Class");
        childclass.setBounds(490,190,100,30);
        panel4.add(childclass);

        JTextField txt13 = new JTextField(20);
        txt13.setBounds(590,190,135,30);
        panel4.add(txt13);

        JLabel password = new JLabel("Password");
        password.setBounds(360,250,100,30);
        panel4.add(password);

        JPasswordField txt14 = new JPasswordField(15);
        txt14.setBounds(440,250,135,30);
        panel4.add(txt14);

        JButton registration2 = new JButton("Register");
        registration2.setBorderPainted(false);
        registration2.setBackground(new Color(210, 215, 211));
        registration2.setFocusPainted(false);
        registration2.setBounds(433, 300,150,20);
        panel4.add(registration2);

        JLabel return2 = new JLabel("Click below to login");
        return2.setBounds(450,360, 150,30);
        panel4.add(return2);

        JButton logBack2 = new JButton("Login Page");
        logBack2.setBorderPainted(false);
        logBack2.setBackground(new Color(210, 215, 211));
        logBack2.setFocusPainted(false);
        logBack2.setBounds(443, 390,130,20);
        panel4.add(logBack2);

        logBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.dispose();
                if(e.getSource() == logBack2){
                    new ParentLogin();

                }
            }
        });

        registration2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == registration2) {
                    String parentid = txt10.getText();
                    String fullname = txt8.getText();
                    String username = txt9.getText();
                    String password = txt14.getText();
                    String childname = txt11.getText();
                    String childno = txt12.getText();
                    String childclass = txt13.getText();


                    if (fullname.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add fullname", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (username.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add username", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (parentid.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add valid ID", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (childname.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Child Name", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }else if (childno.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Child Admission No", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }else if (childclass.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Child Class", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }else if (password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please set a password", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else {

                        PreparedStatement ps3;
                        String query3 = "INSERT INTO `tbl_parent`(`ParentID`, `FullName`, `Username`, `Password`, `ChildFullName`, `ChildAdmNo`, `ChildClass`) VALUES (?,?,?,?,?,?,?)";

                        try {
                            ps3 = DatabaseConnection.getConnection().prepareStatement(query3);
                            ps3.setString(1, parentid);
                            ps3.setString(2, fullname);
                            ps3.setString(3, username);
                            ps3.setString(4, password);
                            ps3.setString(5, childname);
                            ps3.setString(6, childno);
                            ps3.setString(7, childclass);

                            if (ps3.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "Parent - Child Registration Success", "Registration Successful", JOptionPane.PLAIN_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(ParentRegistration.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        txt8.setText("");
                        txt9.setText("");
                        txt10.setText("");
                        txt11.setText("");
                        txt12.setText("");
                        txt13.setText("");
                        txt14.setText("");
                    }
                }
            }
        });

        frame4.setVisible(true);
    }
}