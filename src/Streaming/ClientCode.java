
package Streaming;


import Player.Home;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.*;
import java.io.*;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ClientCode extends JFrame implements MouseListener, ActionListener {

    String message = "Enter Ip Address Of Server as: 127.0.0.1";

    public ClientCode() {
//        this.h=h;
//        s=null;
        setBounds(20, 20, 600, 180);
//        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        handler = new ButtonHandler();
        getcontainer();
        String result = JOptionPane.showInputDialog(message);
        try {

            socket = new Socket(result, 8765);
            socket1 = new Socket(result, 8766);
            getconnected();
        } catch (Exception e) {
        }
    }

    void getcontainer() {
        model = new DefaultListModel();
        con = getContentPane();
        setLayout(new BorderLayout());
        jpanel2 = new JPanel();
        jpanel2.setLayout(new BorderLayout());
        getattributes();//gets the arguments for transmitting
        jlist1 = new JList(model);
        jpanel1 = new JPanel();
        jpanel1.add(jlist1);
        jpanel1.setBackground(new Color(230,230,250));
        jpanel2.setBackground(new Color(230,230,250));
        con.setBackground(new Color(230,230,250));
        jlist1.setBackground(new Color(230,230,250));
        add(jpanel1, BorderLayout.NORTH);
        add(jpanel2, BorderLayout.CENTER);
        jlist1.addMouseListener(this);

    }

    void getattributes() {
        jpanel3 = new JPanel();
        jpanel4 = new JPanel();
        jpanel5 = new JPanel();
        jpanel3.setBackground(new Color(230,230,250));
        jpanel4.setBackground(new Color(230,230,250));
        jpanel5.setBackground(new Color(230,230,250));
        jpanel5.setLayout(new BorderLayout());
        jpanel3.setLayout(new GridLayout(1, 4, 1, 0));
        jpanel4.setLayout(new GridLayout(1, 2, 1, 0));
        jlabel1 = new JLabel("Session Address");
        jlabel2 = new JLabel("Port");
        jtext1 = new JTextField();
        jtext2 = new JTextField();
        jtext3 = new JTextField();
        jtext4 = new JTextField();
        jtext5 = new JTextField();
        jtext6 = new JTextField();
        b1 = new JRadioButton("Audio");
        b2 = new JRadioButton("Video");
        group = new ButtonGroup();

        group.add(b1);
        group.add(b2);
        group.setSelected(m, true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        jbutton1 = new JButton("Receive");
        jbutton1.addActionListener(handler);
        jpanel3.add(jlabel1);
        jpanel3.add(jtext1);
        jpanel3.add(jtext2);
        jpanel3.add(jtext3);
        jpanel3.add(jtext4);
        jpanel4.add(jlabel2);
        jpanel4.add(jtext5);
        jpanel4.add(jtext6);
        jpanel3.add(b1);
        jpanel3.add(b2);
        jpanel5.add(jpanel3, BorderLayout.NORTH);
        jpanel5.add(jpanel4, BorderLayout.CENTER);
        jpanel5.add(jbutton1, BorderLayout.SOUTH);
        jpanel2.add(jpanel5, BorderLayout.SOUTH);

    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbutton1) {
                if (audio == 0) {
                    s[0] = jtext1.getText() + "." + jtext2.getText() + "." + jtext3.getText() + "." + jtext4.getText() + "/" + jtext5.getText();
                    s[1] = jtext1.getText() + "." + jtext2.getText() + "." + jtext3.getText() + "." + jtext4.getText() + "/" + jtext6.getText();
                    receive = new Receive(s);
                } else {
                    s1[0] = jtext1.getText() + "." + jtext2.getText() + "." + jtext3.getText() + "." + jtext4.getText() + "/" + jtext5.getText();
                    receive = new Receive(s1);
                }
                if (!receive.initialize()) {
                    System.err.println("Failed to initialize the sessions.");
                    System.exit(-1);
                }
                // Check to see if AVReceive2 is done.
                try {
                    while (!receive.isDone()) {
                        Thread.sleep(1000);
                    }
                } catch (Exception e3) {
                }

                System.err.println("Exiting AVReceive2");
            }
        }
    }

    void getconnected() throws Exception {

        int character, i = 0;
        out1 = socket1.getOutputStream();
        in = socket.getInputStream();
        out = socket.getOutputStream();

        while ((character = in.read()) != -1) {
            if ((char) character == '\n') {
                str[i] = s2;
                s2 = "";
                i++;
            } else {
                s2 = s2 + (char) character;
            }
        }
        for (int j = 0; j < i; j++) {
            model.addElement(str[j]);
        }
        jlist1.updateUI();
        socket.close();
    }

    public void mouseClicked(MouseEvent e) {
        JList jlist = (JList) e.getSource();
        int j = jlist.locationToIndex(e.getPoint());
        s2 = "";
        s2 = s2 + j + "\n";
        try {

            byte buffer1[] = s2.getBytes();
            out1.write(buffer1);
            socket1.close();
        } catch (Exception e1) {
            System.out.println("Exception is" + e1);
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            audio = 1;
        } else {
            audio = 0;
        }
    }
    private String str[] = new String[10];
    private DefaultListModel model;
    private Container con;
    private JPanel jpanel1,  jpanel2,  jpanel3,  jpanel4,  jpanel5;
    private JList jlist1;
    private String s2 = "";
    private Socket socket,  socket1;
    private InputStream in;
    private OutputStream out,  out1;
    private String string;
    private String s[] = new String[2];
    private String s1[] = new String[1];
    private byte buffer1[] = null;
    private JLabel jlabel1,  jlabel2;
    private JTextField jtext1,  jtext2,  jtext3,  jtext4,  jtext5,  jtext6;
    private JButton jbutton1;
    private ButtonHandler handler;
    private Receive receive;
    private JRadioButton b1,  b2;
    private ButtonGroup group;
    private int audio = 1;
    private ButtonModel m;
    private Home h = null;
}



