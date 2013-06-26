
package Player;


import Playlist.OpenFile;
import Playlist.Playlist;
import Playlist.PlaylistPanel;
import Playlist.SavePlaylist;
import Playlist.Saving;
import Visualization.Visualization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.EndOfMediaEvent;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Playingmedia extends JFrame implements javax.media.ControllerListener {

    Playingmedia() {

        addpopupmenu();
        jpan = new JPanel();
        jpan2 = new JPanel();
        jpan2.setLayout(new BorderLayout());
        jpan.setLayout(new BorderLayout());
        //jpan.setBackground(new Color(0, 200, 0));
        use_video = new audio_video();
        repaint();
        files = null;
        open_file = new OpenFile();
        saveplaylist = new SavePlaylist();
        saving = new Saving();
        playlist = new Playlist();

    }

    void getallpanels(AllPanels allpanels) {
        this.allpanels = allpanels;
    }

    void getcontainer(File[] files, PlaylistPanel listpanel, PlayingTab jtabbedpane, JPanel allpanel) {

        this.files = files;
        this.listpanel = listpanel;
        this.allpanel = allpanel;
        this.jtabbedpane = jtabbedpane;
        test_playing_media();
        playlist = listpanel.getplaylist();
        playlist.takefile(files, this);
        getlength();
       


    }

    public void test_playing_media() {
        if (mediaplayer != null) {
            destroy();
            use_video.reset_controls();
            use_video.reset_video();
            use_video.reset_flags();
        // use_video=new audio_video();
        }
    }

    public void show_again() {
        repaint();
    }

    void set_files_null() {
        if (files != null) {
            int z = 0;
            do {
                files[z] = null;
                z++;
            } while (files[z] != null);
            listlength = 0;
        }
    }

    void addpopupmenu() {
        jpopupmenu = new JPopupMenu();
        jpopupmenu.add(new JMenuItem("open"));
        jpopupmenu.add(new JMenuItem("close"));
    }

    void getlength() {
        for (int a = 0; a < files.length; a++) {
            if (files[a] != null) {
                listlength++;
            }

        }
    }

    public void addfiles(File file[]) {
        for (int a = listlength; a < listlength + file.length; a++) {
            files[a] = file[a - listlength];
        }
        listlength = listlength + file.length;

    }

    public void callForOpen() {
        open_file.getPlayingMedia(this);
        open_file.openTheFile(allpanels);
    }

    public void getplaylist_files(File[] file) {
        if (mediaplayer != null) {
            getplaylist_files_1(file);
        } else {
            if (files == null) {
                files = new File[500];
            }
            for (int a = 0; a < file.length; a++) {
                if (file[a] != null) {
                    if (files == null) {
                        System.out.println("null pointer");
                    } else {
                        files[a] = file[a];
                    }

                    k++;
                }
            //            }
            }
            allpanels.set_container_for_playlist(files);
        }
    }

    public void get_Home_Object(Home h) {
        this.h = h;
        use_video.get_Home_Object(h);
        changetourl();
        listpanel.initialize();
    }

    public void getplaylist_files_1(File[] file) {
        if (mediaplayer != null) {
            destroy();
            files = null;
        }
        i = 0;
        int k = 0;
        if (files == null) {
            files = new File[500];
        }
        for (int a = 0; a < file.length; a++) {
            if (file[a] != null) {
                if (files == null) {
                    System.out.println("null pointer");
                } else {
                    files[a] = file[a];
                }

                k++;
            }
        //            }
        }
        listlength = k;
        play_list_flag = true;
        changetourl();

    }

    public void getFiles(File[] file) {
        files = file;
        getlength();
        addfiles(files);
        allpanel.repaint();
        jpanel1.repaint();
        //playlist.takefiles(files);
        playlist.takefile(files, this);
        String hello = files[0].toString();
        //JOptionPane.showMessageDialog(null, "hi to every one in this world from the team of the java media player" + hello);

    }

    void rtpreceive(Player p) {

    }

    public boolean set_button_enable() {
        return allpanels.set_button_disable();
    }

    public void forwardFiles()//for passing the fies to be written
    {
        if (allpanels.set_button_disable()) // String hello=files[0].toString();
        //JOptionPane.showMessageDialog(null,"i am really testing the file "+hello);
        {
            saveplaylist.getFiles(files);    //pass the updated files to the saveplaylist class for saving of the files
            saving.getSavePlaylist(saveplaylist);   //pass the saveplaylist to the saving class
        } else {
            JOptionPane.showMessageDialog(null, "No media Files to save");
        }

    }

    public void set_playlist_flag() {
        open_file.set_open_flag();
    }

    public void set_flags_forvideo() {
        use_video.reset_fullscreen_flag();
    }

    void changetourl() {
        if (gaincontrol != null) {
            volume = gaincontrol.getMute();
        }
        f1 = files[i];
        try {

            mediaurl = f1.toURL();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "test_2"+e.toString());

//            playlist.listgetunsupported(i);
            if (f1 != null) {
                next();
            }
        }
        if (mediaplayer != null) {
            mediaplayer.stop();
            mediaplayer = null;
        }
        if (mediaurl != null) {
            //setLayout( new BorderLayout() );
            Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
            try {
                  playlist.listsetforeground(i);
                mediaplayer = Manager.createRealizedPlayer(mediaurl);
                gaincontrol = mediaplayer.getGainControl();
                gaincontrol.setMute(volume);

                video = mediaplayer.getVisualComponent();
                controls = mediaplayer.getControlPanelComponent();
                use_video.video_on_main_screen(jtabbedpane);

                if (video != null) {
                    use_video.get_video(video);
                } else {
                    bars = new Visualization(this);
                    jpan1 = bars;
                    use_video.get_visual(jpan1);
                    bars.make();
                }
                if (controls != null) {
                    use_video.get_control(controls, allpanel);
                }
               
                listpanel.getvolumecontrol(gaincontrol);

                mediaplayer.start();
                h.add_song(f1);
               
                gaincontrol.setLevel(listpanel.get_Volume());
            } catch (NoPlayerException noPlayerException) {
//                playlist.listgetunsupported(i);
                next();
            } catch (CannotRealizeException cannotRealizeException) {
//                playlist.listgetunsupported(i);
                next();
            //here we should write code to remove unsupported formats
            } catch (IOException iOException) {
//                playlist.listgetunsupported(i);
                next();
            }
            mediaplayer.addControllerListener(this);
        }

    }

    public synchronized void controllerUpdate(ControllerEvent e) {
        if (e instanceof EndOfMediaEvent) {

//            bars.hold();
            if (((i < listlength - 1) && (repeatthis == false)) || (repeatall == true)) {

                next();
            } else if (repeatthis == true) {
                mediaplayer.setMediaTime(new Time(0));
                mediaplayer.start();
            } else {
//                mediaplayer.setMediaTime(new Time(0));

                bars.hold();
            }
        //mediaPlayer.start();
        }



    }

    void makeplayer() {

    }

    void play() {

        mediaplayer.start();
        if(pause){
        bars.resume();
        }else{
            bars.re_make();
        }
        flag = 0;

    }

    void pause() {
        if (flag == 0) {
            mediaplayer.stop();
            flag = 1;
            bars.hold();
            pause=true;
        }
    }

    int suffle() {
        j = playlist.getshuffle();
        k = ((int) (Math.random() * j));
        return k;
    }

    void setsuffle() {
        if (shuffleflag == 0) {
            shuffleflag = 1;
        } else {
            shuffleflag = 0;
        }
    }

    void next() {
        flag = 0;
        //   JOptionPane.showMessageDialog(null, "test_1");
        if (select_song == false) {
            if (shuffleflag == 0) {

                if (i == listlength - 1) {
                    i = 0;
                } else {
                    i++;
                }
            } else {
                i = suffle();
            }
        }
//        checkflag=0;
        if (mediaplayer != null) {


            if (video == null) {
                bars.stop();
            }


            use_video.reset_video();
            use_video.reset_controls();
            use_video.reset_flags();
            bars = null;

        }
        destroy();

        changetourl();
    }

    void setrepeatall() {
        repeatthis = false;
        if (repeatall == true) {
            repeatall = false;
        } else {
            repeatall = true;

        }

    }

    void setrepeatthis() {
        repeatall = false;
        if (repeatthis == true) {
            repeatthis = false;
        } else {

            repeatthis = true;
        }

    }

    void previous() {

        flag = 0;
        if (i == 0) {
            i = listlength - 1;
        } else {
            i--;
        }
//        checkflag=0;
        if (mediaplayer != null) {
            if (video == null) {
                bars.stop();
            }

            use_video.reset_video();
            use_video.reset_controls();
            use_video.reset_flags();
            bars = null;
        }
//        con.remove(video);
//        jtabbedpane.updatetab(video);
        destroy();
        changetourl();
    }

    void destroy() {
        if (mediaplayer != null) {
            mediaplayer.stop();
            mediaplayer.deallocate();
            mediaplayer.close();
            h.name_song();
        }
    }

    void stop() {
        mediaplayer.stop();
        mediaplayer.setMediaTime(new Time(0));
        bars.stop();
        pause=false;
        //System.exit(-1);
    }

    void forward() {
        System.exit(-1);
    }

    void rewind() {
        System.exit(-1);
    }

    public void select(int i) {
        this.i = i;
        select_song = true;
        next();
        select_song = false;

    }

    public Playlist get_playlist_panel() {
        return playlist;
    }

    public audio_video getVisual() {
        return use_video;
    }
    private File[] files;
    private PlaylistPanel listpanel;
    private Playlist playlist;
    private int i = 0,  listlength = 0,  flag = 0,  videoflag = 0,  checkflag = 1,  shuffleflag = 0;
    private int k = 0,  j;
    private boolean repeatall = false,  repeatthis = false;
    private File f1;
    private URL mediaurl;
    private Player mediaplayer;
    private PlayingTab jtabbedpane;
    private Component video = null,  video1 = null;
    private Component controls = null;
    private JPanel allpanel;
    private GainControl gaincontrol = null;
    private JFrame f2 = null;
    private Container container = null,  con = null;
    private JPanel jpan = null,  jpan2 = null,  jpanel1 = null,  jpan1 = null;
    boolean volume = false;
    private JPopupMenu jpopupmenu;
    private JFrame f3 = null;
    private boolean flag1 = false,  pause=false,flag2 = true,  flag3 = false,  flag4 = true,  play_list_flag = false,  select_song = false;
    private Home h = null;
    private SavePlaylist saveplaylist;
    private Saving saving;
    private OpenFile open_file;
    private AllPanels allpanels;
    private Visualization bars;
    private audio_video use_video;
}


