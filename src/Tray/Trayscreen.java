
package Tray;


import Player.Home;
import Player.Playingmedia;
import Player.audio_video;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trayscreen {

    public Trayscreen() {

        constructGUI();

    }

    void constructGUI() {
        if (!SystemTray.isSupported()) {
            System.out.println("error");
            return;
        }
        systray = SystemTray.getSystemTray();
        Toolkit systoolkit = Toolkit.getDefaultToolkit();
        Image image = systoolkit.getImage("image//tray.png");
        PopupMenu popmenu = new PopupMenu();
        MenuItem msgitem = new MenuItem("Restore");
        msgitem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                hide();
                visual.reset_tray();
                remove();                       //remove tray
                h.display();                      // display previous wiindow
            }
        });
        popmenu.add(msgitem);
        MenuItem menuitem = new MenuItem("Exit");
        menuitem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                remove();
                System.exit(0);
            }
        });
        popmenu.add(menuitem);

        MenuItem menuitem1 = new MenuItem("View Video");
        menuitem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                show();

            }
        });
        popmenu.add(menuitem1);

        MenuItem menuitem2 = new MenuItem("Close Video");
        menuitem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                hide();
            // playingmedia.resetvideoflag();
            }
        });
        popmenu.add(menuitem2);


        tryicon = new TrayIcon(image, "Java Media Player", popmenu);
        tryicon.setImageAutoSize(true);


    }

    void hide() {
//        playingmedia.setviewingfalse();
        visual.resetviewing();

    }

    void show() {
//        playingmedia.setviewingtrue();
        visual.setviewing();
    }

    void remove() {
        systray.remove(tryicon);
    }

    public void add(Home h) {
        playingmedia = h.getvideoplaying();
        visual = playingmedia.getVisual();
        visual.set_tray();
        hide();
//        playingmedia.makesmall();
        try {
            systray.add(tryicon);
        } catch (AWTException e) {
            System.out.println("cannot be added");
        }

        //   container.add(video);
        this.h = h;
    }
//    public static void main(String args[]){
//
//        new Trayscreen();
//
//    }
    private SystemTray systray;
    private TrayIcon tryicon;
    private Home h = null;
    private Playingmedia playingmedia = null;
    private audio_video visual = null;
}

