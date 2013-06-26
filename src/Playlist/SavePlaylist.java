
package Playlist;


import java.io.File;
import java.io.Serializable;

public class SavePlaylist implements Serializable {

    private File[] file;

    public SavePlaylist() {
        file = null;
    }

    public void getFiles(File[] files) {

        file = files;
//        String hello=file[1].toString();
//        JOptionPane.showMessageDialog(null,hello);
    }

    public File[] returnFile() {

        return file;
    }
   
}
