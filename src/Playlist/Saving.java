
package Playlist;

import Player.Playingmedia;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Saving {

    private SavePlaylist savelist;
    private ObjectOutputStream output;  //create a outputstream to write the file
    private JFileChooser filechooser;
    private File filename;
    private File[] file;
    private OpenFile openfile;
    private Playingmedia playingmedia;

    public void getSavePlaylist(SavePlaylist saveplaylist) {
        savelist = saveplaylist;
        openFile();
    //readFiles();

    }

    public void getPlayingMedia(Playingmedia media) {
        playingmedia = media;
    }

    public Saving() {
        filechooser = new JFileChooser();
        openfile = new OpenFile();
    }

    public void openTheFile() {
        readFiles();
    }

    public void openFile() {


        int result = filechooser.showSaveDialog(null);

        filechooser.setMultiSelectionEnabled(false);
        filename = filechooser.getSelectedFile();
        if (result == filechooser.APPROVE_OPTION) {
            String hello = filename.toString();
            hello = hello + ".txt";

//       JOptionPane.showMessageDialog(null,hello);

            if (filename == null || filename.getName().equals("")) {
                JOptionPane.showMessageDialog(null, "invalid file name");
            } else {
                try {
                    output = new ObjectOutputStream(
                            new FileOutputStream(hello));
                    output.writeObject(savelist); //save the playlist to the desired location as given by the file
                    output.flush();
                    closeFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            // JOptionPane.showMessageDialog(null, "You choose not to save the files");
        }

    }

    public void closeFile() {
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void save() {
        try {
            output.writeObject(savelist);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void readFiles() {

        openfile.openTheFile(null);
        openfile.getPlayingMedia(playingmedia);
    //  openfile.readFiles();
    }
    //for the purpose of testing reading of the files i.e playlist
}

