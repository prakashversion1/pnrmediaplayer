
package Player;


import Playlist.Playlist;
import Playlist.PlaylistPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AllPanels extends JFrame {

    private JPanel bottombuttonpanel;
    private JPanel sidepanel;
    private JButton bottombuttons[];
    private int count = 0;
    private Container container;
    private Home h = null;

    AllPanels() {

        files = new File[500];
        f = new FileBrowser();
    }

    public void get_home_object(Home h) {
        this.h = h;
    }

    void getcontainer(Container container, PlaylistPanel listpanel, PlayingTab jtabbedpane) {
        this.container = container;
        this.jtabbedpane = jtabbedpane;
        this.listpanel = listpanel;
        playlist = new Playlist();
        playingmedia = new Playingmedia();
        listpanel.getPlayingMedia(playingmedia);
        playingmedia.getallpanels(this);
        makeButtonPanel();

    }

    public boolean set_button_disable() {
        return set_button_enable;
    }

    public void set_button_enable() {
        set_button_enable = true;
    }

    public void makeButtonPanel() {
        totalpanel = new JPanel();
        totalpanel.setLayout(new BorderLayout());
        bottombuttonpanel = new JPanel();
        bottombuttonpanel.setLayout(new GridLayout(1, 6, 0, 0));
       // bottombuttonpanel.setBackground(new Color(74, 124, 197));
        bottombuttons = new JButton[6];
        bottombuttonpanel.setLayout(new GridLayout(1, bottombuttons.length, 0, 0));//set the layout of the topobuttonpanel
        bottombuttons[0] = new JButton(new ImageIcon("image//open.jpg"));
        bottombuttons[2] = new JButton(new ImageIcon("image//play.jpg"));
       // bottombuttons[2].setBackground(new Color(74, 124, 197));
        // bottombuttons[2]=new JButton(new ImageIcon("pause.gif"));
        bottombuttons[3] = new JButton(new ImageIcon("image//next.jpg"));
        bottombuttons[1] = new JButton(new ImageIcon("image//previous.jpg"));
        bottombuttons[4] = new JButton(new ImageIcon("image//pause.jpg"));
        bottombuttons[5] = new JButton(new ImageIcon("image//advanced.jpg"));
        bottombuttons[0].setToolTipText("Open");
        bottombuttons[1].setToolTipText("Previous");
        bottombuttons[2].setToolTipText("Play");
        bottombuttons[4].setToolTipText("Pause");
          bottombuttons[3].setToolTipText("Next");
        bottombuttons[5].setToolTipText("Stop");
        //   bottombuttons[7]=new JButton(new ImageIcon("options.gif"));
        for (count = 0; count < bottombuttons.length; count++) {
            bottombuttonpanel.add(bottombuttons[count]);


        }
        bottombuttons[0].setMnemonic('O');
        bottombuttons[1].setMnemonic('P');
        bottombuttons[2].setMnemonic('N');
        bottombuttons[3].setMnemonic('P');
        bottombuttons[4].setMnemonic(' ');
        bottombuttons[5].setMnemonic('Y');

        addBottomPanel();
        handleButton();
//        set_button_disable();
    }

    void playingfiles() {
        handler.openfiles();
    }

    public void handleButton() {
        handler = new ButtonHandler(container, totalpanel, jtabbedpane);

        for (count = 0; count < bottombuttons.length; count++) {

            bottombuttons[count].addActionListener(handler);
        }

    }

    public void set_container_for_playlist(File[] playlist_file) {
        handler.set_files_for_playlist(playlist_file);
    }

    private class ButtonHandler implements ActionListener {

        ButtonHandler(Container container, JPanel allpanel, PlayingTab playingtab) {
            this.container = container;
            this.allpanel = allpanel;
            this.playingtab = playingtab;
            playingtab.setBackground(Color.BLACK);
            playingtab.repaint();
        }

        public void set_files_for_playlist(File[] playlist_file) {
            playingmedia.getcontainer(playlist_file, listpanel, playingtab, allpanel);
            playingmedia.get_Home_Object(h);
            allpanel.updateUI();
        }

        public void openfiles() {
            f.selectfile();
            if (f.test_file_select()) {
                playingmedia.set_files_null();

                file = f.getfiles();

                if (file != null) {

                    for (int a = 0; a < file.length; a++) {
                        files[a] = file[a];
                    }
                    playingmedia.getcontainer(files, listpanel, playingtab, allpanel);
                    if (!set_button_enable) {
                        playingmedia.get_Home_Object(h);
                    } else {
                        playingmedia.changetourl();
                    }
                    playingmedia.set_playlist_flag();
//                    playingmedia.getlength();
                }
                allpanel.updateUI();
                set_button_enable();
            }
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == bottombuttons[0]) {        //for open button

                openfiles();
            }
            if (event.getSource() == bottombuttons[2]) {        //for play and pause
                if (set_button_enable) {
                    playingmedia.play();
                }
            }
            if (event.getSource() == bottombuttons[3]) {
                if (set_button_enable) {
                    playingmedia.next();// for next
                }
            }
            if (event.getSource() == bottombuttons[1]) {
                if (set_button_enable) {
                    playingmedia.previous();// for previous
                }
            }
            if (event.getSource() == bottombuttons[4]) {
                if (set_button_enable) {
                    playingmedia.pause();//for stop
                }
            }
            if (event.getSource() == bottombuttons[5]) {
                  if (set_button_enable) {
                    playingmedia.stop();//for stop
                }
            }



        }
        private Container container;
        private JPanel allpanel;
        private PlayingTab playingtab;
    }

    File[] getfiles() {
        return file;
    }

    Playingmedia playingvideo() {
        return playingmedia;
    }

    public void addBottomPanel() {

        totalpanel.add(bottombuttonpanel, BorderLayout.SOUTH);
        container.add(totalpanel, BorderLayout.SOUTH);
    }
    private ButtonHandler handler;
    private File[] file;
    private File[] files;
    private PlaylistPanel listpanel;
    private Playlist playlist;
    private Playingmedia playingmedia;
    private PlayingTab jtabbedpane;
    private JPanel totalpanel;
    private FileBrowser f;
    private boolean set_button_enable = false;
}


