
package Streaming;

import java.awt.Container;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Server {

    Server() {
        jfilechooser1 = new JFileChooser();

    //filenames=getnames();
    }

    File[] getfileschoosen() {

        jfilechooser1.setMultiSelectionEnabled(true);
        int result = jfilechooser1.showOpenDialog(null);
        File file1[] = jfilechooser1.getSelectedFiles();
        if (result == JFileChooser.APPROVE_OPTION) {
            return file1;
        } else {
            return null;
        }
    }

    String[] getnames() {
        // String arr[]=null;
        for (int i = 0; i < file.length; i++) {
            //arr[i]=file[i].getName();
            System.out.println(file[i].getName());
        }
        return null;
    // return arr;
    }
    private Container con;
    private JPanel jpanel1;
    private JFileChooser jfilechooser1;
    private File[] file = null;
    private String[] filenames;
}
