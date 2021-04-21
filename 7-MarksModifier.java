import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarksModifier {
    JFrame frame5 = new JFrame();
    public MarksModifier(){
        frame5.setSize(1000, 500);
        frame5.setResizable(false);
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel5 = new JPanel();
        panel5.setBackground(new Color(108, 122, 137));
        panel5.setLayout(null);
        frame5.add(panel5);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(405,420, 250,50);
        panel5.add(copyright);

        JLabel label5 = new JLabel("Welcome Teacher");
        label5.setFont(new Font("Forte", Font.PLAIN, 35));
        label5.setBounds(370,20,430,100);
        panel5.add(label5);

        JLabel label6 = new JLabel("You can modify marks here");
        label6.setFont(new Font("Forte", Font.PLAIN, 20));
        label6.setBounds(380,70,430,100);
        panel5.add(label6);

        JLabel label7 = new JLabel("Child AdmNo");
        label7.setBounds(380,150,100,30);
        panel5.add(label7);

        JTextField txt15 = new JTextField(10);
        txt15.setBounds(470,150, 135,30);
        panel5.add(txt15);

        JLabel label8 = new JLabel("Enter respective marks");
        label8.setFont(new Font("Forte", Font.PLAIN, 20));
        label8.setBounds(400,160,430,100);
        panel5.add(label8);

        JLabel label9 = new JLabel("Maths");
        label9.setBounds(380,190,430,100);
        panel5.add(label9);

        JTextField txt16 = new JTextField(5);
        txt16.setBounds(470,225,135,30);
        panel5.add(txt16);

        JLabel label10 = new JLabel("English");
        label10.setBounds(380,230,430,100);
        panel5.add(label10);

        JTextField txt17 = new JTextField(5);
        txt17.setBounds(470,265,135,30);
        panel5.add(txt17);

        JLabel label11 = new JLabel("Kiswahili");
        label11.setBounds(380,270,430,100);
        panel5.add(label11);

        JTextField txt18 = new JTextField(5);
        txt18.setBounds(470,305,135,30);
        panel5.add(txt18);

        JButton upload = new JButton("Upload");
        upload.setBorderPainted(false);
        upload.setBackground(new Color(210, 215, 211));
        upload.setFocusPainted(false);
        upload.setBounds(657, 170,100,20);
        panel5.add(upload);

        JButton update = new JButton("Update");
        update.setBorderPainted(false);
        update.setBackground(new Color(210, 215, 211));
        update.setFocusPainted(false);
        update.setBounds(657, 210,100,20);
        panel5.add(update);

        JButton delete = new JButton("Delete");
        delete.setBorderPainted(false);
        delete.setBackground(new Color(210, 215, 211));
        delete.setFocusPainted(false);
        delete.setBounds(657, 250,100,20);
        panel5.add(delete);

        JButton viewer = new JButton("View Marks");
        viewer.setBorderPainted(false);
        viewer.setBackground(new Color(210, 215, 211));
        viewer.setFocusPainted(false);
        viewer.setBounds(650, 290,120,20);
        panel5.add(viewer);

        JLabel returner = new JLabel("Click below to return to login page");
        returner.setBounds(400,350,460,70);
        panel5.add(returner);

        JButton loginpg = new JButton("Login Page");
        loginpg.setBorderPainted(false);
        loginpg.setBackground(new Color(210, 215, 211));
        loginpg.setFocusPainted(false);
        loginpg.setBounds(450, 400,100,20);
        panel5.add(loginpg);

        viewer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == viewer){
                    frame5.dispose();
                    new ViewMarks();
                }
            }
        });

        loginpg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == loginpg){
                    frame5.dispose();
                    new TeacherLogin();
                }
            }
        });

        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == upload) {
                    String childAdmNo = txt15.getText();
                    String math = txt16.getText();
                    String eng = txt17.getText();
                    String kiswahili = txt18.getText();

                    if (childAdmNo.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add admission number", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (math.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Math marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (eng.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add English marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (kiswahili.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Kiswahili marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }else {

                        PreparedStatement ps5;
                        String query5 = "INSERT INTO `tbl_marks`(`ChildAdmNo`, `Maths`, `English`, `Kiswahili`) VALUES (?,?,?,?)";

                        try {
                                ps5 = DatabaseConnection.getConnection().prepareStatement(query5);
                                ps5.setString(1, childAdmNo);
                                ps5.setString(2, math);
                                ps5.setString(3, eng);
                                ps5.setString(4, kiswahili);

                                if (ps5.executeUpdate() > 0) {
                                    JOptionPane.showMessageDialog(null, "Marks Uploaded", "Upload Successful", JOptionPane.PLAIN_MESSAGE);
                                }

                        } catch (SQLException ex) {
                            Logger.getLogger(MarksModifier.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        txt15.setText("");
                        txt16.setText("");
                        txt17.setText("");
                        txt18.setText("");
                    }
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == update) {
                    String childAdmNo = txt15.getText();
                    String math = txt16.getText();
                    String eng = txt17.getText();
                    String kiswahili = txt18.getText();

                    if (childAdmNo.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add admission number", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (math.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Math marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (eng.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add English marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    } else if (kiswahili.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add Kiswahili marks", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }else {

                        PreparedStatement ps6;
                        String query6 = "UPDATE `tbl_marks` SET `ChildAdmNo`=?,`Maths`=?,`English`=?,`Kiswahili`=?";

                        try {
                            ps6 = DatabaseConnection.getConnection().prepareStatement(query6);
                            ps6.setString(1, childAdmNo);
                            ps6.setString(2, math);
                            ps6.setString(3, eng);
                            ps6.setString(4, kiswahili);

                            if (ps6.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "Marks Updated", "Update Successful", JOptionPane.PLAIN_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(MarksModifier.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        txt15.setText("");
                        txt16.setText("");
                        txt17.setText("");
                        txt18.setText("");
                    }
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == delete) {
                    String childAdmNo = txt15.getText();

                    if (childAdmNo.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please add admission number", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                    }
                    else {

                        PreparedStatement ps7;
                        String query7 = "DELETE FROM `tbl_marks` WHERE `ChildAdmNo`=?";

                        try {
                            ps7 = DatabaseConnection.getConnection().prepareStatement(query7);
                            ps7.setString(1, childAdmNo);
                            ps7.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Marks Deleted", "Delete Successful", JOptionPane.PLAIN_MESSAGE);

                        } catch (SQLException ex) {
                            Logger.getLogger(MarksModifier.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        txt15.setText("");
                        txt16.setText("");
                        txt17.setText("");
                        txt18.setText("");
                    }
                }
            }
        });
        frame5.setVisible(true);
    }
}