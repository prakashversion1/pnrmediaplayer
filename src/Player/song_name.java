package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JLabel;

public class song_name extends JLabel implements Runnable {

    public song_name() {
           
        t1 = new Thread(this, "Creatin Thread");

    }

    public void get_file(File f1, JLabel label3) {
        if (s1 != null) {
            s1 = null;
        }
        s1 = f1.getName();
        this.label3 = label3;
        label3.setForeground(new Color(120,80,80));
        label3.setText(s1);
    }

    public void set_flag() {
        flag_end = true;
    }

    public void stop_it() {
        flag_end = false;
    }

    public void make() {
        if (!start) {
            t1.start();
        }

    }

    public void run() {
               boolean check_color=false;
        start = true;
        do {
            try {
                if(check_color){
                     label3.setForeground(new Color(250,250,250));
                     check_color=false;
                }else{
                    label3.setForeground(new Color(53, 104, 195));
                    check_color=true;
                }
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }

//            label3.setHorizontalAlignment(x);




        } while (flag_end);
    }

    public void paint(Graphics g) {
    
    }
    private String s1 = null;
    private int x = 50;
    private boolean flag_end = true,  start = false,  y = true;
    private Thread t1 = null;
    private JLabel label3 = null;
}
