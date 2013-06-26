
package Player;


import Capture.Record;
import Convert.Converter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class PlayingTab extends JFrame {

    private Container container;
    private JTabbedPane jtabbedpane;

    PlayingTab() {


    }

    void getcontainer(Container container) {
        this.container = container;
        
        maketab();
    }

    public void maketab() {
        jtabbedpane = new JTabbedPane(SwingConstants.BOTTOM);
        jtabbedpane.setTabPlacement(JTabbedPane.TOP);
        jlabel = new JLabel("This is testing label");
        jlabel2 = new JLabel("Here to convert");
        jpanel2 = new Converter();
        jpanel = new JPanel();
        jpanel1 = new Record();// return the object of the file which holds GUI using JPanel
        jpanel3 = new JPanel();
        jlabel3 = new JLabel(new ImageIcon("image//black.jpg"));
        jpanel.setLayout(new BorderLayout());
        jpanel.setBackground(new Color(240, 248, 255));
        jtabbedpane.addTab("Playing", jlabel3);
        if (video != null) {
            updatetab(video);
        }
//        jtabbedpane.addTab("Playing",video);
        // jtabbedpane.addTab("Playlist",null,jpanel,null);
        jtabbedpane.addTab("Wav-Mp3 Converter", jpanel2);
        jtabbedpane.addTab("Audio Recording", jpanel1);
        //jtabbedpane.addTab("Record Video",jpanel4);
        
        addtabpane();
    }

    public void addtabpane() {
        container.add(jtabbedpane, BorderLayout.CENTER);
    }

    void setupdatetab() {
        int i = jtabbedpane.indexOfTab("Playing");
        jtabbedpane.setComponentAt(i, null);

    }

    void updatetab(Component video) {
        int i = jtabbedpane.indexOfTab("Playing");
        jtabbedpane.setComponentAt(i, video);

    }

    void addtab(Component video) {
        int i = jtabbedpane.indexOfTab("Playing");
        jtabbedpane.setComponentAt(i, video);

    }

    int getindextab(Component video) {
        int i = 0;
        i = jtabbedpane.indexOfComponent(video);
        return i;
    }
    private Component video = null;
    private Component video3 = null;
    private JPanel jpanel,  jpanel1 = null,  jpanel3,  jpanel2,  jpanel4;
    private JLabel jlabel,  jlabel2,  jlabel3;
    private int m = 0;
    private JFrame f3 = null;
    private Container con = null;
    private boolean flag = false;
}


