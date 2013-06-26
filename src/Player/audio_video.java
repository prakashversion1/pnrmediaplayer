
package Player;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

import javax.swing.JPanel;
//f2 for full screen
//
public class audio_video extends JFrame implements MouseListener, MouseMotionListener {

    audio_video() {
        f3 = new JFrame();
        f2 = new JFrame();
        jpan2 = new JPanel();
        jpan2.setLayout(new BorderLayout());
        f2.setBounds(0, 0, 1026, 770);
        f3.setBounds(850, 610, 150, 130);
        f2.setUndecorated(true);
        f3.setUndecorated(true);
        f2.setVisible(false);
        f3.setVisible(false);
        con2 = f2.getContentPane();
        con2.setLayout(new BorderLayout());
        con1 = f3.getContentPane();
        con1.setLayout(new BorderLayout());
        con1.setBackground(new Color(0, 0, 0));


    }

    public void reset_fullscreen_flag() {
        reset_controls();
        flag_2 = false;
        flag_1 = true;

    }

    public void get_Home_Object(Home h) {

        this.h = h;
    }

    public void set_flags() {
        flag_1 = true;
        flag_2 = false;
        video_flag = false;
        flag_tray = false;
    }

    public void reset_flags() {
        if (flag_1 == true) {
            flag_1 = false;
        } else {

            flag_1 = true;
        }
        if (flag_2 == false) {
            h.setVisible(false);
            flag_2 = true;
        } else {

            flag_2 = false;
        }

        video_flag = false;
    }

    public void reset_video() {
        if (flag_1 == false) {       //in main tabbed
            if (video_flag == true) {
                jtabbedpane.setupdatetab();        // here is the code to remove video
            } else {
                jtabbedpane.setupdatetab();       //here is the code to remove visualization
            }
        } else {                    //in full screen
            if (video_flag == true) {
                con2.remove(video);         // here is the code to remove video
            } else {
                if (jpan1 != null) {
                    con2.remove(jpan1);         //here is the code to remove visualization}
                }
            }
        }
        if (flag_tray == true) {
            if (video_flag == true) {
                con1.remove(jpan2);
                jpan2.remove(video);
            } else {
                if (jpan1 != null) {
                    jpan2.remove(jpan1);
                    con1.remove(jpan2);
                }

            }

        }
    }

    public void reset_controls() {
        if (flag_1 == false) {
            allpanel.remove(controls);
        } else {
            con2.remove(controls);
        }

    }

    public void get_video(Component video) {
        this.video = video;
        reset_video();
        video_flag = true;
        video.addMouseListener(this);
        video.addMouseMotionListener(this);
    }

    public void get_visual(JPanel jpan1) {

        this.jpan1 = jpan1;
        jpan1.setBounds(0, 0, 1026, 770);
        reset_video();
        video_flag = false;
        jpan1.addMouseListener(this);
        jpan1.addMouseMotionListener(this);

    }

    public void get_control(Component controls, JPanel allpanel) {
        this.controls = controls;
        this.allpanel = allpanel;
        check_state();
    }

    public void video_on_main_screen(PlayingTab jtabbedpane) {
        this.jtabbedpane = jtabbedpane;
    }

    public void check_state() {
        if (flag_tray == true) {
            if (video_flag == true) {
                jpan2.add(video, BorderLayout.CENTER);
            } else {
                if (jpan1 != null) {
                    r = jpan1.getBounds();
                    jpan1.setBounds(850, 610, 150, 130);
                    jpan2.add(jpan1, BorderLayout.CENTER);
                }
            }
            con1.add(jpan2);
            jpan2.updateUI();
        } else {
            con1.remove(jpan2);
            add_video();
            repaint();
        }

    }

    public void setviewing() {
        f3.setVisible(true);
    }

    public void resetviewing() {
        f3.setVisible(false);
    }

    public void add_video() {
        if (flag_1 == true) {// this is the code for adding video in the main tabbed panel
            f2.setVisible(false);
            if (video_flag == true) {
                con2.remove(video);         // here is the code to add/remove video
                jtabbedpane.updatetab(video);

            } else if (video_flag == false) {
                con2.remove(jpan1);         //here is the code to add/remove visualization
                jtabbedpane.updatetab(jpan1);
            }
            flag_2 = true;
            allpanel.add(controls, BorderLayout.CENTER);
            allpanel.updateUI();

            flag_1 = false;
            h.setVisible(true);
        } else if (flag_2 == true) {    // this is the code to add in for full screen
            h.setVisible(false);
            if (video_flag == true) {
                jtabbedpane.setupdatetab();         // here is the code to add/remove video
                con2.add(video, BorderLayout.CENTER);
            } else {
                jtabbedpane.setupdatetab();         //here is the code to add/remove visualization
                con2.add(jpan1, BorderLayout.CENTER);
            }
            flag_1 = true;
            con2.add(controls, BorderLayout.SOUTH);
            f2.setVisible(true);
            flag_2 = false;

        }

    }

    public void set_tray() {
        flag_tray = true;
        if ((video != null) || (jpan1 != null)) {
            reset_video();
            check_state();

        }

    }

    public void reset_tray() {

        flag_tray = false;
        flag_1 = true;
        if ((video != null) || (jpan1 != null)) {
            check_state();
        }
     
    }

    public void mouseClicked(MouseEvent e) {
        if (flag_tray == false) {       // checks whether it is in tray or not
            add_video();
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

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        if (flag_2 == false) {
            if (video != null) {
                //this flag is set for fullscreen but we have set it false after we are in fullscreen so used false here
                if (e.getY() > 750) {
                    video.setBounds(0, 0, 1026, 750);
                    con2.add(controls, BorderLayout.SOUTH);

                } else {
                    video.setBounds(0, 0, 1026, 770);
                    con2.remove(controls);
                }
//        }
            } else {
                if (e.getY() > 750) {
                    jpan1.setBounds(0, 0, 1026, 750);
                    con2.add(controls, BorderLayout.SOUTH);
                } else {
                    jpan1.setBounds(0, 0, 1026, 770);
                    con2.remove(controls);
                }
            }
        }
    }
    private JFrame f2,  f3;
    private JPanel jpan1,  allpanel,  jpan2;
    private Container con1,  con2;
    private boolean flag_1 = true,  flag_2 = false,  video_flag = false,  flag_tray = false;
    private Component video,  controls;
    private PlayingTab jtabbedpane;
    private Home h = null;
    private Rectangle r;
}

