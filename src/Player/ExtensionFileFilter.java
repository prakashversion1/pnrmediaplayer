
package Player;


import java.io.File;
//import java.io.FileFilter;
import javax.swing.filechooser.FileFilter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;


public class ExtensionFileFilter extends FileFilter {

    public void addExtension(String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
        }
        extensions.add(extension.toLowerCase());
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public String getDescription() {
        return description;
    }

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String name = f.getName().toLowerCase();
        //check if the file name ends with the any of the extensions

        for (String extension : extensions) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
    private String description = ".";
    private ArrayList<String> extensions = new ArrayList<String>();
}
