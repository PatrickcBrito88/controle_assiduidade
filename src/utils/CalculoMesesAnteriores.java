/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;

public class CalculoMesesAnteriores {
    
   public String getMes (){
        ConversaoMeses conversaoMeses = new ConversaoMeses();
        LocalDate now = LocalDate.now(); // 2015-11-24
        LocalDate earlier = now.minusMonths(0); // 2015-10-24
        int ano=earlier.getYear();

      
        earlier.getMonth(); // Java.time.Month = OCTOBER
        int mes=earlier.getMonth().getValue(); // 10
        
        String mesAtual=conversaoMeses.getMes(mes);

        return mesAtual+": "+ano;
    }
   
    public String getMes_1 (){
        ConversaoMeses conversaoMeses = new ConversaoMeses();
        LocalDate now = LocalDate.now(); // 2015-11-24
        LocalDate earlier = now.minusMonths(1); // 2015-10-24
        int ano=earlier.getYear();

      
        earlier.getMonth(); // Java.time.Month = OCTOBER
        int mes=earlier.getMonth().getValue(); // 10
        
        String mesAnterior=conversaoMeses.getMes(mes);

        return mesAnterior+": "+ano;
    }
    
    public String getMes_2 (){
        ConversaoMeses conversaoMeses = new ConversaoMeses();
        LocalDate now = LocalDate.now(); // 2015-11-24
        LocalDate earlier = now.minusMonths(2); // 2015-10-24
        int ano=earlier.getYear();

      
        earlier.getMonth(); // Java.time.Month = OCTOBER
        int mes=earlier.getMonth().getValue(); // 10
        
        String mesAnterior=conversaoMeses.getMes(mes);

        return mesAnterior+": "+ano;
    }
    
    public String ConverteData (int dia, String mesEAno){
        
        String mes="";
        
        if (mesEAno.contains("JANEIRO")){
            mes="01";
        }
        if (mesEAno.contains("FEVEREIRO")){
            mes="02";
        }
        if (mesEAno.contains("MARÇO")){
            mes="03";
        }
        if (mesEAno.contains("ABRIL")){
            mes="04";
        }
        if (mesEAno.contains("MAIO")){
            mes="05";
        }
        if (mesEAno.contains("JUNHO")){
            mes="06";
        }
        if (mesEAno.contains("JULHO")){
            mes="07";
        }
        if (mesEAno.contains("AGOSTO")){
            mes="08";
        }
        if (mesEAno.contains("SETEMBRO")){
            mes="09";
        }
        if (mesEAno.contains("OUTUBRO")){
            mes="10";
        }
        if (mesEAno.contains("NOVEMBRO")){
            mes="11";
        }
        if (mesEAno.contains("DEZEMBRO")){
            mes="12";
        }
        
        String ano=mesEAno.replaceAll("[^0-9]", "");
        
        String data=dia+"/"+mes+"/"+ano;
        return data;
    }
}
