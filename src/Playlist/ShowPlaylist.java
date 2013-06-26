
package Playlist;


import javax.swing.JPanel;
public class ShowPlaylist
{
    JPanel jpanel2;
    
    public ShowPlaylist(JPanel jpanel1)
    {
        jpanel2=jpanel1;
        
    }
    
    public void show()
    {
        jpanel2.setVisible(true);
    }
}

