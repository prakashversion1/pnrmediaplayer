
package Playlist;


import javax.swing.JPanel;
public class HidePlaylist
{
    JPanel jpanel2;
    
    public HidePlaylist(JPanel jpanel1)
    {
        jpanel2=jpanel1;
        
    }
    
    public void hide()
    {
        jpanel2.setVisible(false);
    }
}

