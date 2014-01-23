package logic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class windowlistener implements WindowListener {
 
    public void windowDeactivated(WindowEvent e) {
        
    }
    
    public void windowActivated(WindowEvent e) {
        
    }
    
    public void windowDeiconified(WindowEvent e) {
        
    }
    
    public void windowIconified(WindowEvent e) {
        
    }
    
    public void windowClosed(WindowEvent e) {
        
    }
    
    public void windowClosing(WindowEvent e) {
        int counter = 0;
        while (counter < Database.opendatabases.size()) {
            Packer.pack(Database.opendatabases.get(counter));
            counter++;
        }
        System.exit(0);        
    }
    
    public void windowOpened(WindowEvent e) {
        
    }
    
}