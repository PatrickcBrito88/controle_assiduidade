
package controllers;

import utils.ConectaBanco;
import java.util.Random;


public class ResetaSenhaControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public String geraSenha(){
           Random gerador = new Random();
            String senhaRandom="@@#";
            
            for (int i=0; i<6; i++){
                int n=gerador.nextInt(9);
                String n1=String.valueOf(n);
                senhaRandom+=n1;
            }
            return senhaRandom;
    }
    
    
}
