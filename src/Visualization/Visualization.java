
package Visualization;


import Player.Playingmedia;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Visualization extends JPanel implements Runnable {

    public Visualization(Playingmedia test) {
        this.test = test;
        t1 = new Thread(this, "Creatin Thread");

        repaint();
    }

    public void reset() {
        flag_test = true;
    }

    public void make() {
        t1.start();
        resume();
    }
    public void re_make(){
       
       re_make=false;
    }
    public void resume() {
        hold_flag = false;
    }

    public void hold() {
        hold_flag = true;

    }

    public void generate() {
        int i_1, j_1;
        for (int i = 0; i < 8; i++) {
            a_x[i] = (int) (Math.random() * 1000);
            a_y[i] = (int) (Math.random() * 1000);
            a_x_1[i] = (int) (Math.random() * 1000);
            a_y_1[i] = (int) (Math.random() * 1000);
            a_x_2[i] = (int) (Math.random() * 1000);
            a_y_3[i] = (int) (Math.random() * 1000);
            a_x_3[i] = (int) (Math.random() * 1000);
            a_y_3[i] = (int) (Math.random() * 1000);
        }

    }

    public void run() {

        for (int j = 10; j < 70; j = j + 10) {
            try {
                if (flag_test == true) {
                    if (flag_2 == true) {
                        flag_2 = false;

                    } else {
                        flag_2 = true;

                    }
                    if (j > 50) {
                        j = 10;
                    }
                    while (hold_flag == true) {
                        Thread.sleep(200);
                    }
                }
                generate();
                Thread.sleep(100);

            } catch (InterruptedException ex) {

            }


        }
        end_flag = true;
        repaint();

    }

    public void stop() {
//        flag_test = false;
     re_make=true;
        repaint();
    }

    public void setBackgroundColor(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.drawRect(0, 0, 1200, 1200);
        g.fillRect(0, 0, 1200, 1200);
    }

    public void setBackgroundColor_1(Graphics g) {
        g.setColor(new Color(230, 230, 250));
        g.drawRect(0, 0, 1200, 1200);
        g.fillRect(0, 0, 1200, 1200);
    }

   /* public void draw_visualization_2(Graphics g) {
        g.setColor(new Color(230, 140, 170));
        if (flag_2 == true) {

            g.setColor(new Color(250, 20, 5));
        } else {

            g.setColor(new Color(5, 250, 20));
        }
        g.drawArc(120, 140, 40, 40, 0, 360);
        g.drawLine(140, 180, 140, 230);
        g.drawLine(140, 230, 100, 260);
        g.drawLine(140, 230, 180, 260);
        g.drawLine(140, 180, 100, 200);
        g.drawLine(140, 180, 180, 200);



    }
*/
    public void draw_a(Graphics g) {
        int p = -35, r = -50;


        g.translate(p, r);
        p = p + 10;
        r = r + 12;
        g.setColor(new Color(250, 0, 0));
        g.drawPolyline(a_x, a_y, 6);
        g.setColor(new Color(0, 250, 0));
        g.drawPolyline(a_x_1, a_y_1, 6);
        g.setColor(new Color(0, 0, 250));
        g.drawPolyline(a_x_2, a_y_2, 6);
        g.setColor(new Color(74, 124, 197));
        g.drawPolyline(a_x_3, a_y_3, 6);
        g.translate(p, r);
        p = p + 10;
        r = r + 12;

        g.setColor(new Color(250, 0, 0));
        g.drawArc(a_x[5] - 20, a_y[5] - 20, 20, 20, 0, 360);
        g.fillArc(a_x[5] - 20, a_y[5] - 20, 20, 20, 0, 360);
        g.setColor(new Color(0, 250, 0));
        g.drawArc(a_x_1[5] - 20, a_y_1[5] - 20, 20, 20, 0, 360);
        g.fillArc(a_x_1[5] - 20, a_y_1[5] - 20, 20, 20, 0, 360);
        g.setColor(new Color(0, 0, 250));
        g.drawArc(a_x_2[5] - 20, a_y_2[5] - 20, 20, 20, 0, 360);
        g.fillArc(a_x_2[5] - 20, a_y_2[5] - 20, 20, 20, 0, 360);
        g.setColor(new Color(74, 124, 197));
        g.drawArc(a_x_3[5] - 20, a_y_3[5] - 20, 20, 20, 0, 360);
        g.fillArc(a_x_3[5] - 20, a_y_3[5] - 20, 20, 20, 0, 360);



        g.translate(p, r);
        p = p + 10;
        r = r + 12;
    //drawetc(g);

    }

    public void drawetc(Graphics g) {
        g.setColor(new Color(250, 0, 0));
        g.drawPolyline(a_x, a_y, 6);
        g.setColor(new Color(0, 250, 0));
        g.drawPolyline(a_x_1, a_y_1, 6);

    }

    public void paint(Graphics g) {
        repaint();
//        test.show_again();
//        g.translate(250, 250);
        if(re_make){
            setBackgroundColor(g);
        }else{
        if (end_flag == false) {
            setBackgroundColor(g);
            draw_a(g);
           // draw_visualization_2(g);
        } else {
            setBackgroundColor_1(g);
        }
        repaint();
        }
    }
    private Thread t1;
    private int s1 = 0;
    private Playingmedia test;
    private boolean flag_2 = true,  flag_test = true,  end_flag = false,  hold_flag = false,re_make=false;
    private int a_x[] = new int[28];
    private int a_x_1[] = new int[28];
    private int a_y[] = new int[28];
    private int a_y_1[] = new int[28];
    private int a_x_2[] = new int[28];
    private int a_y_2[] = new int[28];
    private int a_x_3[] = new int[28];
    private int a_y_3[] = new int[28];
    }


 

