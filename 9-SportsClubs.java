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

public class SportsClub {
    JFrame frame9 = new JFrame();
    public SportsClub(){
        JPanel panel8 = new JPanel();
        panel8.setBackground(new Color(108, 122, 137));
        panel8.setLayout(null);

        frame9.setSize(1000, 500);
        frame9.setResizable(false);
        frame9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame9.add(panel8);

        JLabel welcome = new JLabel("Welcome");
        welcome.setFont(new Font("Forte", Font.PLAIN, 35));
        welcome.setBounds(440, 0, 430, 100);
        panel8.add(welcome);

        JLabel copyright1 = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright1.setBounds(405,420, 250,50);
        panel8.add(copyright1);

        JLabel message4 = new JLabel("You can choose a sport or club");
        message4.setFont(new Font("Forte", Font.PLAIN, 35));
        message4.setBounds(280, 40, 520, 100);
        panel8.add(message4);

        JLabel message5 = new JLabel("Enter Admission Number");
        message5.setBounds(423, 100, 520, 100);
        panel8.add(message5);

        JTextField txt22 = new JTextField(10);
        txt22.setBounds(428, 170, 135, 30);
        panel8.add(txt22);

        JLabel message6 = new JLabel("Select your sport or club");
        message6.setBounds(423, 180, 520, 100);
        panel8.add(message6);

        JLabel message7 = new JLabel("Click below to view all students in a table");
        message7.setBounds(375, 310, 520, 100);
        panel8.add(message7);

        JButton view = new JButton("View");
        view.setBorderPainted(false);
        view.setBackground(new Color(210, 215, 211));
        view.setFocusPainted(false);
        view.setBounds(437, 380,120,20);
        panel8.add(view);

        JFrame frame10 = new JFrame("Results");
        frame10.setSize(1000, 500);
        frame10.setResizable(false);
        frame10.setBackground(new Color(108, 122, 137));
        frame10.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] options = {"Football","Tennis","Volleyball","Cricket","Badminton","Swimming","EMUN","Acting","AISEC","Spanish","French","German"};

         final JComboBox<String> cb = new JComboBox<String>(options);
         cb.setBounds(430,250,135,20);
         panel8.add(cb);

         JButton save = new JButton("Save");
        save.setBorderPainted(false);
        save.setBackground(new Color(210, 215, 211));
        save.setFocusPainted(false);
        save.setBounds(437, 290,120,20);
        panel8.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == save){

                    String admno1 = txt22.getText();
                    String value = cb.getSelectedItem().toString();

                        if (admno1.equals("")) {
                            JOptionPane.showMessageDialog(null, "Please add admission number", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                        } else if (cb.getSelectedItem().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please select a sport or club", "ERROR!", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            PreparedStatement ps12;
                            String query11 = "INSERT INTO `tbl_sportsorclubs`(`ChildAdmNo`, `SportsorClub`) VALUES (?,?)";
                            try {
                                ps12 = DatabaseConnection.getConnection().prepareStatement(query11);
                                ps12.setString(1, admno1);
                                ps12.setString(2, value);

                                if (ps12.executeUpdate() > 0) {
                                    JOptionPane.showMessageDialog(null, "Save Successfull", "Success!", JOptionPane.PLAIN_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SportsClub.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view){
                    frame9.dispose();
                    DefaultTableModel model2 = new DefaultTableModel();
                    model2.addColumn("ChildAdmNo");
                    model2.addColumn("Sports/Club");

                    JScrollPane scrollPane2 = new JScrollPane();
                    JTable table1 = new JTable(model2);
                    scrollPane2.setViewportView(table1);
                    table1.setSize(800, 500);
                    frame10.add(scrollPane2);

                    PreparedStatement ps13;
                    ResultSet rs13;
                    String query12 = "SELECT * FROM tbl_sportsorclubs";

                    try{
                        ps13 = DatabaseConnection.getConnection().prepareStatement(query12);
                        rs13 = ps13.executeQuery();

                        while(rs13.next()){
                            String id = rs13.getString("ChildAdmNo");
                            String a = rs13.getString("SportsorClub");
                            model2.addRow(new Object[]{id,a});
                            frame10.setVisible(true);
                        }
                    }catch (SQLException ex) {
                        Logger.getLogger(SportsClub.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        frame9.setVisible(true);
    }
}