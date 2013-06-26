
package Streaming;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.media.MediaLocator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ServerCode extends JFrame{
    public ServerCode(){
        
        Server s=new Server();
        File []file=null;
        file=s.getfileschoosen();
        int k=0;
        handler=new ButtonHandler();
        con=getContentPane();
        setLayout(new BorderLayout());
        jpanel2=new JPanel();
        try{
            ServerSocket socket = new ServerSocket(8765);
            ServerSocket socket1 = new ServerSocket(8766);
            Socket insocket = socket.accept( );
            Socket insocket1 = socket1.accept( );
            
            BufferedReader in = new BufferedReader(new InputStreamReader(insocket.getInputStream()));//this is use to take text from client
            PrintWriter out = new PrintWriter(insocket.getOutputStream(), true);//this i suse to send text to client
            BufferedReader in1 = new BufferedReader(new InputStreamReader(insocket1.getInputStream()));
            for(int i=0;i<file.length;i++){
                out.println(file[i].getName());
            }
            insocket.close();
            
            String instring = in1.readLine();//info from client is receive here
            insocket1.close();
            setBounds(20,20,600,180);
//            setSize(300,300);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            int m=Integer.parseInt(instring);//infor is changed to int
            
            //System.out.println(file[m].getAbsolutePath());
            s1="file:/"+file[m].getAbsolutePath();
            getcontainer();
        } catch (Exception e) {}
        
    }
    void getcontainer(){
        jpanel2.setBackground(new Color(74,124,197));
        jpanel3=new JPanel();
        jpanel4=new JPanel();
        jpanel5=new JPanel();
        jpanel3.setBackground(new Color(74,124,197));
        jpanel4.setBackground(new Color(74,124,197));
        jpanel5.setBackground(new Color(74,124,197));
        jpanel5.setLayout(new BorderLayout());
        jpanel3.setLayout(new GridLayout(1,4,1,0));
        jpanel4.setLayout(new GridLayout(1,2,1,0));
        jlabel1=new JLabel("Session Address");
        jlabel2=new JLabel("Port");
        jtext1=new JTextField();
        jtext2=new JTextField();
        jtext3=new JTextField();
        jtext4=new JTextField();
        jtext5=new JTextField();
        jbutton1=new JButton("Transmit");
        jbutton1.addActionListener(handler);
        jpanel3.add(jlabel1);
        jpanel3.add(jtext1);
        jpanel3.add(jtext2);
        jpanel3.add(jtext3);
        jpanel3.add(jtext4);
        jpanel4.add(jlabel2);
        jpanel4.add(jtext5);
        jpanel2.add(jpanel3,BorderLayout.NORTH);
        jpanel2.add(jpanel4,BorderLayout.CENTER);
        jpanel2.add(jbutton1,BorderLayout.SOUTH);
        con.add(jpanel2);
    }
    private  class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jbutton1){
                s2=jtext1.getText()+"."+jtext2.getText()+"."+jtext3.getText()+"."+jtext4.getText();
                s3=jtext5.getText();
                
                transmit=new Transmit(new MediaLocator(s1),s2,s3,null);
                String result =  transmit.start();
                
                // result will be non-null if there was an error. The return
                // value is a String describing the possible error. Print it.
                if (result != null) {
                    System.err.println("Error : " + result);
                    System.exit(0);
                }
                // jlabel3=new JLabel("Start transmission for 60 seconds...");
                //jpanel2.add(jlabel3,BorderLayout.NORTH);
                System.err.println("Start transmission for 60 seconds...");
                
                
                try {
                    Thread.currentThread().sleep(60000);
                } catch (InterruptedException ie) {
                }
                
                // Stop the transmission
                transmit.stop();
                
                System.err.println("...transmission ended.");
                
            }
           
        }}
    
    private Container con;
    private JPanel jpanel2,jpanel3,jpanel4,jpanel5;
    private JLabel jlabel1,jlabel2,jlabel3;
    private JTextField jtext1,jtext2,jtext3,jtext4,jtext5;
    private JButton jbutton1;
    private ButtonHandler handler ;
    private String s2,s3,s1=null;
    private Transmit transmit;
    
}