

//package SplashScreen;

import Player.Home;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
//import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.UIManager;
import javax.swing.UIManager;



public class splashscreen {

    splashscreen() {
        createImage();
      
    }

    void createImage() {
        String message;
        SplashScreen splashscreen = SplashScreen.getSplashScreen();
        if (splashscreen != null) {
            Graphics2D g = (Graphics2D) splashscreen.createGraphics();
            for (int i = 0; i < 20; i++) {
                if (i % 3 == 0) {
                    message = "Loading.";

                } else if (i % 3 == 1) {
                    message = "Loading..";

                } else {
                    message = "Loading...";
                }
                g.setComposite(AlphaComposite.Clear);
                g.fillRect(20, 545, 80, 10);
                g.setPaintMode();
                g.setColor(new Color(0, 0, 0));
                g.drawString(message, 100, 360);
                 g.setColor(new Color(0, 0, 0));
                g.fillRect(95, 365, i * 18, 13);

                splashscreen.update();
                try {
                    if (i == 19) {

                        Thread.sleep(500);
                    } else {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                }

            }

        }
        JFrame f = new Home();
        
        f.setBounds(140, 140, 800, 500);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String args[]) {
    	try {
    	UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
    	} catch (ClassNotFoundException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} catch (InstantiationException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} catch (IllegalAccessException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} catch (UnsupportedLookAndFeelException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	}
    	new splashscreen();



    	}
}

