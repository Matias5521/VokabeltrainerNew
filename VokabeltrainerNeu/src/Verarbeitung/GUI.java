package Verarbeitung;

import javax.swing.*;
import java.awt.*;

public class GUI {
	
    private JFrame jf1;
    
    public GUI() {
    	
        this.jf1 = new JFrame();
        
        //Liest die Größe von Monitor aus und passt JFrame darauf an
        Toolkit tlk1 = Toolkit.getDefaultToolkit();
        Dimension screensize = tlk1.getScreenSize();
        jf1.setSize(screensize.width, screensize.height);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf1.setLocationRelativeTo(null);
        jf1.setTitle("Vokabeltrainer");
        jf1.setResizable(false);
        jf1.requestFocus();
        //Für Vollbild zustndig
        jf1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jf1.setUndecorated(true);
        jf1.setVisible(true);
        this.mainMenue();
        
    }
    
    public void mainMenue() {
    	
    }
    
    public void Anmeldung() {
    	
    }
    
    public void Regestrierung() {
    	
    }
}