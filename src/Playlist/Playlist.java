
package Playlist;

import Player.Playingmedia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Playlist implements MouseListener {

    public Playlist() {
        listModel = new DefaultListModel();
    }

    void getcontainer(Container container, JPanel jpanel) {
        this.conatiner = container;
        this.jpanel = jpanel;

    }

    public void takefile(File[] file, Playingmedia playingmedia) {
        if (!listModel.isEmpty()) {

            listModel.removeAllElements();

        }
        this.playingmedia = playingmedia;
        for (i = 0; i < file.length; i++) {
            if (file[i] != null) {

                listModel.addElement(file[i].getName());
            }
        }
        jlist = new JList(listModel);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setFixedCellHeight(15);
        jlist.setFixedCellWidth(18);
        jlist.setBackground(new Color(70, 70, 70));
        jlist.setForeground(Color.WHITE);
        jscrollpane = new JScrollPane(jlist);
        jpanel.add(jscrollpane, BorderLayout.CENTER);
        jlist.addMouseListener(this);
        jpanel.repaint();

    }

    public void listsetforeground(int k) {
        jlist.setSelectedIndex(k);
        jlist.setSelectionForeground(new Color(53, 104, 195));
        jlist.setSelectionBackground(new Color(0,0,0));

    }

    public void listgetunsupported(int k) {
        jlist.setSelectedIndex(k);
        jlist.setSelectionForeground(new Color(250, 250, 0));
        jlist.setSelectionBackground(new Color(128, 128, 128));

    }

    public void takefiles(File files[]) {
        for (i = 0; i < files.length; i++) {
            if (files[i] != null) {
                listModel.addElement(files[i].getName());
            }
        }

    }

    public void make_playlist(File files[]) {
        if (listModel != null) {
            listModel.removeAllElements();
        }
        for (i = 0; i < files.length; i++) {
            if (files[i] != null) {
                listModel.addElement(files[i].getName());
            }
        }

    }

    public int getshuffle() {
        int i = listModel.getSize();
        return i;
    }

    public void mouseClicked(MouseEvent e) {

        JList jlist = (JList) e.getSource();
        int j = jlist.locationToIndex(e.getPoint());
        jlist.setSelectionForeground(new Color(20, 250, 0));
        playingmedia.select(j);
        jpanel.updateUI();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    private Container conatiner;
    private JScrollPane jscrollpane;
    private JPanel jpanel;
    private String[] arr = null;
    private int i = 0;
    private JList jlist = null;
    private Playingmedia playingmedia;
    private DefaultListModel listModel;
}


