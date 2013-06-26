
package Playlist;


import Player.AllPanels;
import Player.FileBrowser;
import Player.Playingmedia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.media.GainControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlaylistPanel implements ChangeListener, MouseListener {

    public PlaylistPanel() {
        handler = new Buttonhandler();
        playlist = new Playlist();
        jslider1 = new JSlider();

    }

    public void initialize() {
        flag_slider = true;
    }

    public void getcontainer(Container container) {
        this.container = container;
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel3 = new JPanel();
        jpanel4 = new JPanel();
        jbutton2 = new JButton("add");
        openplaylist = new JButton("Open Playlist");
        saveplaylist = new JButton("SavePlaylist");
        //playlist=new Playlist();
        jbutton1 = new JButton(new ImageIcon("image//arr.gif"));
        jlabel1 = new JLabel("        PLAYLIST       ");
        jlabel2 = new JLabel(new ImageIcon("image//mute.png"));


        jlabel1.setForeground(Color.PINK);
        jscrollpane = new JScrollPane();
        jbutton2.setSize(10, 10);
        displaypanel();
    }

    public void getPlayingMedia(Playingmedia playmedia) {
        this.playmedia = playmedia;
    }

    void get_allpanels(AllPanels allpanels) {
        this.allpanels = allpanels;

    }

    void displaypanel() {

        jpanel3.setLayout(new BorderLayout());
        jpanel4.setBackground(new Color(70, 70, 70));
        jpanel4.setLayout(new BorderLayout());
        jpanel2.setLayout(new BorderLayout());
        jpanel1.setLayout(new BorderLayout());
        jpanel1.setBackground(new Color(70, 70, 70));
        jpanel1.add(jbutton1, BorderLayout.EAST);
        // jpanel1.add(jlabel1,BorderLayout.CENTER);
        jpanel1.add(openplaylist, BorderLayout.CENTER);
        jpanel1.add(saveplaylist, BorderLayout.WEST);

        jscrollpane.setBackground(new Color(70, 70, 70));
        jscrollpane.setForeground(new Color(70, 70, 70));
        jpanel2.add(jpanel1, BorderLayout.NORTH);
        jpanel4.add(jlabel2, BorderLayout.EAST);
        jpanel4.add(jslider1, BorderLayout.CENTER);
        jpanel3.add(jbutton2, BorderLayout.NORTH);
        jpanel3.add(jpanel4, BorderLayout.CENTER);
        jlabel2.addMouseListener(this);
        jslider1.addChangeListener(this);
        jpanel2.add(jpanel3, BorderLayout.SOUTH);
        jbutton2.addActionListener(handler);
        jbutton1.addActionListener(handler);
        openplaylist.addActionListener(handler);
        saveplaylist.addActionListener(handler);
        jpanel2.setBackground(new Color(70, 70, 70));
        showplaylist = new ShowPlaylist(jpanel2);
        hideplaylist = new HidePlaylist(jpanel2);
        //jslider1.setValue(20);
        addtocontainer();
    }

    void addtocontainer() {
        jpanel1.setSize(20, 20);
        jpanel3.setSize(20, 20);
        jpanel2.setSize(20, 20);
        container.add(jpanel2, BorderLayout.EAST);

    }

    public void getvolumecontrol(GainControl gaincontrol) {
        this.gaincontrol = gaincontrol;
        gaincontrol.setLevel(volume);

    }

    private class Buttonhandler implements ActionListener {

        FileBrowser f = new FileBrowser();

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbutton2) {        //for add button
                if (playmedia.set_button_enable()) {


                    f.selectfile();
                    if (f.test_file_select()) {
                        file = f.getfiles();
                        if (file != null) {
                            playlist.takefiles(file);//we have to write code for coping files from one object to another
                            playmedia.addfiles(file);
                        }
                    }
                }
            }
            if (e.getSource() == jbutton1) {
                hideplaylist.hide();
            }
            if (e.getSource() == openplaylist) {

                playmedia.callForOpen();

            }
            if (e.getSource() == saveplaylist) {
                //JOptionPane.showMessageDialog(null,"hello this is the testing saving");
                playmedia.forwardFiles();
            }
        }
        }

    public void displayplaylist() {
        showplaylist.show();
    }

    public Playlist getplaylist() {

        playlist.getcontainer(container, jpanel2);
        return playlist;
    }

    public float get_Volume() {
        return (float) jslider1.getValue() / 100;
    }

    public void stateChanged(ChangeEvent e) {
        if (flag_slider == true) {
            int vol = jslider1.getValue();
            volume =
                    (float) vol / 100;
            gaincontrol.setLevel(volume);

            volume =
                    gaincontrol.getLevel();
        }

    }

    public void mouseClicked(MouseEvent e) {
        if (flag_slider == true) {
            if (flag == 0) {
                gaincontrol.setMute(true);
                flag =
                        1;
            } else {
                gaincontrol.setMute(false);
                flag =
                        0;
            }

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
    private Container container;
    private JPanel jpanel1,  jpanel2,  jpanel3,  jpanel4;
    private JButton jbutton1;
    private JScrollPane jscrollpane;
    private JLabel jlabel1,  jlabel2;
    private Playlist playlist = null;
    private JButton jbutton2;
    private Buttonhandler handler;
    private File[] file;
    private Playingmedia playmedia;
    private JSlider jslider1;
    private GainControl gaincontrol;
    private int flag = 0;
    private float volume = 0.2f;
    private ShowPlaylist showplaylist;
    private HidePlaylist hideplaylist;
    private JButton openplaylist,  saveplaylist;
    private AllPanels allpanels;
    private boolean flag_slider = false;
}
