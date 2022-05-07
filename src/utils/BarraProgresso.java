
package utils;

import vision_menus.MenuAdmin;
import com.lowagie.text.Font;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;


public class BarraProgresso extends Thread {
    
    private JProgressBar barra;
    
    public BarraProgresso (JProgressBar barra){
        this.barra=barra;
        this.barra.setStringPainted(true);
        
    }
    
    public void load(){
      start();
    }
    
    @Override
    public void run(){
    barra.setMaximum(30);

            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i <= 100; i++) {
                        barra.setValue(i);
                        
                        try {
                            sleep(20);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    barra.setString("Concluído");
                }
            }.start();
            
    }
}
