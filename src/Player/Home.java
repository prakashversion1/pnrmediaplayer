
package Player;


import Playlist.PlaylistPanel;
import Streaming.ClientCode;
import Streaming.ServerCode;
import Tray.Trayscreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Home extends JFrame implements ActionListener {

    public Home() {
        super(" PNR JAVA MEDIA PLAYER");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("image//icon.png");
        setIconImage(img);
        container = getContentPane();
        song = new song_name();
        t = new Trayscreen();
        file = new JMenu("File");
        opentray = new JMenuItem("go to tray");
        view = new JMenu("Play");
        edit = new JMenu("Option");
        visual = new JMenu("Visualization");
        stream = new JMenu("Rtp Streaming");
        bar = new JMenuBar();
        makeItem();
        container.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();
        label3 = new JLabel();
        set_label();
       // label3.setIcon("Java Media Player",new ImageIcon("image//banner.jpg"));
        panel1.setBackground(new Color(0, 0, 0));
        panel1.setBounds(0, 0, 300, 700);
        panel1.add(label3);
        add(panel1, BorderLayout.NORTH);
        repaint();
        allpanels = new AllPanels();
        playtab = new PlayingTab();
        listpanel = new PlaylistPanel();
        allpanels.getcontainer(container, listpanel, playtab);
        playtab.getcontainer(container);
        listpanel.getcontainer(container);
        allpanels.get_home_object(this);
        bar.setBackground(Color.WHITE);
        bar.addNotify();



    }
    public void set_label(){
        label3.setIcon(new ImageIcon("image//banner.jpg"));
        label3.setText("PNR Java Media Player");
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    public void add_song(File f1) {
    
        song.set_flag();
        song.get_file(f1, label3);
        song.make();

    }

    public void reset() {

    }

    public void name_song() {
        song.stop_it();
    }

    public void makeItem() {
        open = new JMenuItem("Open  File ");
        //  open.setMnemonic('O');
        close = new JMenuItem("Close ");
        close.setMnemonic('C');
        exit = new JMenuItem("Exit ");
        showplaylist = new JMenuItem("Show Playlist", new ImageIcon("image//openlist.png"));
        repeatall = new JMenuItem("Repeat All", new ImageIcon("image//repeatall.png"));
        repeatthis = new JMenuItem("Repeat This", new ImageIcon("image//repeatthis.png"));
        random = new JMenuItem("Random");
        transmit = new JMenuItem("Rtp Transmit");
        receive = new JMenuItem("Rtp Receive");

        addFileItem();
    }

    public void addFileItem() {
        stream.add(transmit);
        transmit.addActionListener(this);
        stream.add(receive);
        receive.addActionListener(this);
        file.add(open);
        open.addActionListener(this);
        file.add(close);
        close.addActionListener(this);
        file.add(exit);
        exit.addActionListener(this);
        edit.add(showplaylist);
        showplaylist.addActionListener(this);
        view.add(repeatthis);
        repeatthis.addActionListener(this);
        view.add(repeatall);
        repeatall.addActionListener(this);
        edit.add(random);
        random.addActionListener(this);
        edit.add(opentray);

        opentray.addActionListener(this);
        addmenu();
    }

    public void addmenu() {
        bar.add(file);
        bar.add(view);
        bar.add(edit);
        bar.add(stream);
        setJMenuBar(bar);
    }

    public Playingmedia getvideoplaying() {
        playingmedia = allpanels.playingvideo();
        return playingmedia;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == transmit) {
           JFrame f = new ServerCode();
        }
        if (e.getSource() == receive) {
            JFrame f = new ClientCode();
        }
        if (e.getSource() == opentray) {
            setVisible(false);
            t.add(this);
        }
        if (e.getSource() == random) {
            playingmedia = allpanels.playingvideo();
            playingmedia.setsuffle();
        }
        if (e.getSource() == showplaylist) {
            listpanel.displayplaylist();
        }
        if (e.getSource() == repeatall) {
            playingmedia = allpanels.playingvideo();
            playingmedia.setrepeatall();
        }
        if (e.getSource() == repeatthis) {
            playingmedia = allpanels.playingvideo();
            playingmedia.setrepeatthis();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == open) {
            allpanels.playingfiles();
        }
        if (e.getSource() == close) {
            //JOptionPane.showMessageDialog(null, "hi to every one in this world from the team of the java media player");

        }
    }

    public void display() {
        setVisible(true);
    }
    private JMenu file,  view,  edit,  visual,  stream;
    private JMenuItem transmit,  opentray,  receive,  open,  close,  exit,  saveas,  showplaylist,  repeatall,  random,  repeatthis,  merge,  split,  first,  second,  third;
    private JMenuBar bar;
    private Container container = null;
    private JPanel panel1 = null,  panel2 = null;
    private AllPanels allpanels;
    private PlayingTab playtab;
    private PlaylistPanel listpanel;
    private JLabel label1,  label3;
    private Trayscreen t = null;
    private Component video = null;
    private Playingmedia playingmedia = null;
    private song_name song = null;
}

