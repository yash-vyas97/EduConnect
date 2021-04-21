import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class WelcomePage {
    JFrame frame =new JFrame();

    public WelcomePage(){

        JPanel panel = new JPanel();
        panel.setBackground(new Color(108, 122, 137));

        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("Welcome to Fuzu Academy");
        label.setFont(new Font("Forte", Font.PLAIN, 35));
        label.setBounds(300,50,430,100);
        panel.add(label);

        JLabel logo = new JLabel("Fuzu Academy");
        logo.setFont(new Font("STENCIL", Font.BOLD, 50));
        logo.setForeground(new Color(191, 191, 191));
        logo.setBounds(315,0,430,100);
        panel.add(logo);

        JLabel option = new JLabel("Please select one");
        option.setFont(new Font("Forte", Font.PLAIN, 35));
        option.setBounds(365,160, 300,50);
        panel.add(option);

        JLabel copyright = new JLabel("Copyright © 2021 Fuzu Academy");
        copyright.setBounds(400,420, 250,50);
        panel.add(copyright);

        JButton teacher = new JButton("Teacher");
        teacher.setBorderPainted(false);
        teacher.setBackground(new Color(210, 215, 211));
        teacher.setFocusPainted(false);
        teacher.setBounds(440,220,100,30);
        panel.add(teacher);

        JButton parent = new JButton("Parent");
        parent.setBorderPainted(false);
        parent.setBackground(new Color(210, 215, 211));
        parent.setFocusPainted(false);
        parent.setBounds(440,260,100,30);
        panel.add(parent);

        JButton student = new JButton("Student");
        student.setBorderPainted(false);
        student.setBackground(new Color(210, 215, 211));
        student.setFocusPainted(false);
        student.setBounds(440,300,100,30);
        panel.add(student);

        teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == teacher){
                    frame.dispose();
                    new TeacherLogin();
                }
            }
        });

        parent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == parent){
                    frame.dispose();
                    new ParentLogin();
                }
            }
        });

        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == student) {
                    frame.dispose();
                    new StudentLogin();
                }
            }
        });

        frame.setVisible(true);

    }
}