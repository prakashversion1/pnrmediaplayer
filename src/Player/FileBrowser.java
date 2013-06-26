
package Player;


import java.io.File;
//import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileBrowser {

    public FileBrowser() {

        chooser = new JFileChooser();
    //  filters=filter;
    //   filters = new ExtensionFileFilter();

    }

    public void selectfile() {
        filter = new ExtensionFileFilter();
        filter.addExtension("mp3");//add the extension for the file types 
        filter.addExtension("wav");
        filter.addExtension("mpg");
        filter.addExtension("mpeg");
        filter.addExtension("vob");
        filter.addExtension("dat");
        filter.addExtension("aiff");
        filter.addExtension("gsm");
        filter.setDescription("Media files(*.mp3,*.wav,*.mpg,*.mpeg,*.vob,*.dat,*.aiff,*.gsm)");

//        filter.addExtension("jmp");

        chooser.setFileFilter(filter);
//        chooser.setFileFilter(filter);
        // chooser.setAccessory(new ImagePreviewer(chooser));
        //chooser.setFileView(new FileIconView(filter,new ImageIcon("test.png")));

        chooser.setMultiSelectionEnabled(true);
        result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFiles();
            select_file = true;
        } else {
            select_file = false;
            //JOptionPane.showMessageDialog(null, "Choose some media files");
        }

    }
public boolean test_file_select(){
    return select_file;
}
    public File[] getfiles() {
        if (file == null) {
            return null;
        }
        return file;
    }
    private File[] file = null;
    private File[] file2;
    private JFileChooser chooser;
    private ExtensionFileFilter filter;
    private int result = 0;
    private int i = 0;
    private boolean select_file = false;
    // private FileFilter filters;
}
    
 
