
package Playlist;


import Player.AllPanels;
import Player.ExtensionFileFilter;
import Player.Playingmedia;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OpenFile {

    private File[] file = null;
    private ObjectInputStream input = null;  //create a outputstream to write the file
    private JFileChooser filechooser;
    private File filename;
    private File[] files;
    private Playingmedia playingmedia = null;
    private ExtensionFileFilter filter;

    public OpenFile() {

        filechooser = new JFileChooser();
    }

    public void openTheFile(AllPanels allpanel) {
        String str = null;

        filter = new ExtensionFileFilter();
        filter.addExtension("txt");//only choose the files with the jmp file extensions 
        filter.setDescription("Playlist(*.txt)");
        filechooser.setFileFilter(filter);
        int result = filechooser.showOpenDialog(null);
        filechooser.setMultiSelectionEnabled(false);
        if (result == filechooser.APPROVE_OPTION) {
            filename = filechooser.getSelectedFile();
            str = filename.toString();
            if (filename == null || filename.getName().equals("")) {
                JOptionPane.showMessageDialog(null, "Choose From Playlist");
            } else if (!str.endsWith("txt")) {
                JOptionPane.showMessageDialog(null, "Choose txt files Playlist");
            } else {
                if (allpanel != null) {
                    allpanel.set_button_enable();
                }
                try {
                    if (input != null) {
                        input = null;
                    }
                    input = new ObjectInputStream(
                            new FileInputStream(filename));

                    readFiles();
                    open_playlist_flag = true;
                } catch (Exception e) {
                    System.out.print("the exception is here" + e);
                    e.printStackTrace();
                }
            }
        } else {
            //JOptionPane.showMessageDialog(null, "You Choose not to open playlist");
        }



    }

    public void set_open_flag() {
        open_playlist_flag = true;
    }

    public void getPlayingMedia(Playingmedia media) {
        playingmedia = media;
    }

    public void readFiles() {
        if (open_playlist_flag == true) {
            playingmedia.set_flags_forvideo();
        }
        SavePlaylist savelist;
        int i = 0;
        try {

            savelist = (SavePlaylist) input.readObject();
            if (savelist != null) {
                files = savelist.returnFile();//return the array of the files to the files from the files
                closefile();
            }
        } catch (Exception e) {

            System.out.print("the exception is here 1111\t" + e);
            e.printStackTrace();
        }

        play_list = playingmedia.get_playlist_panel();
        if (files != null) {
            play_list.make_playlist(files);
            playingmedia.getplaylist_files(files);
        }

    }

    public void closefile() throws IOException {
        input.close();
    }
    private Playlist play_list = null;
    private boolean open_playlist_flag = false;
}

