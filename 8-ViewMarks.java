import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMarks {
    JFrame frame6 = new JFrame();

    public ViewMarks() {
        frame6.setSize(1000, 500);
        frame6.setResizable(false);
        frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel6 = new JPanel();
        panel6.setBackground(new Color(108, 122, 137));
        panel6.setLayout(null);
        frame6.add(panel6);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(405, 420, 250, 50);
        panel6.add(copyright);

        JLabel message = new JLabel("Welcome");
        message.setFont(new Font("Forte", Font.PLAIN, 35));
        message.setBounds(440, 0, 430, 100);
        panel6.add(message);

        JLabel message2 = new JLabel("You can view student marks here");
        message2.setFont(new Font("Forte", Font.PLAIN, 35));
        message2.setBounds(280, 40, 520, 100);
        panel6.add(message2);

        JLabel message3 = new JLabel("Enter Admission Number");
        message3.setBounds(423, 100, 520, 100);
        panel6.add(message3);

        JTextField txt19 = new JTextField(10);
        txt19.setBounds(428, 170, 135, 30);
        panel6.add(txt19);

        JButton search = new JButton("Search");
        search.setBorderPainted(false);
        search.setBackground(new Color(210, 215, 211));
        search.setFocusPainted(false);
        search.setBounds(435, 240, 120, 20);
        panel6.add(search);

        JLabel text = new JLabel("Click to go back to login page");
        text.setBounds(410,340,400,20);
        panel6.add(text);

        JButton btn1 = new JButton("Teacher");
        btn1.setBorderPainted(false);
        btn1.setBackground(new Color(210, 215, 211));
        btn1.setFocusPainted(false);
        btn1.setBounds(390, 370, 100, 20);
        panel6.add(btn1);

        JButton btn2 = new JButton("Parent");
        btn2.setBorderPainted(false);
        btn2.setBackground(new Color(210, 215, 211));
        btn2.setFocusPainted(false);
        btn2.setBounds(505, 370, 100, 20);
        panel6.add(btn2);

        JFrame frame7 = new JFrame("Marks");
        frame7.setSize(1000, 500);
        frame7.setResizable(false);
        frame7.setBackground(new Color(108, 122, 137));
        frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton back = new JButton("Back");
        back.setBorderPainted(false);
        back.setBackground(new Color(210, 215, 211));
        back.setFocusPainted(false);
        back.setBounds(435,420,120,20);
        frame7.add(back);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn1){
                    frame6.dispose();
                    new TeacherLogin();
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn2){
                    frame6.dispose();
                    new ParentLogin();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == back){
                    frame7.dispose();
                }
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == search) {

                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ChildAdmNo");
                    model.addColumn("Maths");
                    model.addColumn("English");
                    model.addColumn("Kiswahili");
                    JScrollPane scrollPane = new JScrollPane();
                    JTable table1 = new JTable(model);
                    scrollPane.setViewportView(table1);
                    table1.setSize(800, 500);
                    frame7.add(scrollPane);

                    String admno = txt19.getText();
                    PreparedStatement ps8;
                    ResultSet rs8;
                    String query8 = "SELECT * FROM `tbl_marks` WHERE `ChildAdmNo`=?";

                    try {
                        if (admno.equals("")) {
                            JOptionPane.showMessageDialog(null, "Please add admission number", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            ps8 = DatabaseConnection.getConnection().prepareStatement(query8);
                            ps8.setString(1, admno);
                            rs8 = ps8.executeQuery();

                                if (rs8.next()) {
                                    String id = rs8.getString("ChildAdmNo");
                                    String b = rs8.getString("Maths");
                                    String c = rs8.getString("English");
                                    String d = rs8.getString("Kiswahili");
                                    model.addRow(new Object[]{id, b, c, d});
                                    frame7.setVisible(true);
                                }
                                else  {
                                    JOptionPane.showMessageDialog(null,"Admission number is incorrect \n OR \n The marks haven't been uploaded","ERROR!",JOptionPane.PLAIN_MESSAGE);
                                }
                        }
                    }catch(SQLException ex){
                        Logger.getLogger(ViewMarks.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    txt19.setText("");
                }
            }
        });
        frame6.setVisible(true);
    }
}