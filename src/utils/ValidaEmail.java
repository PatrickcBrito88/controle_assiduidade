
package utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

public class ValidaEmail {
    
    public boolean validaEmail (String email){
        boolean sinal=true;
        
        try {
            InternetAddress emailAddr=new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            sinal=false;
            JOptionPane.showMessageDialog(null,"Digite um e-mail válido.");
        }
        return sinal;
    }
}
