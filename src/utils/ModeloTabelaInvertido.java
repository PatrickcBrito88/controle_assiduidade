
package utils;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabelaInvertido extends AbstractTableModel{
    
    private ArrayList colunas=null;
    private String [] linhas=null;
    
    public ModeloTabelaInvertido(ArrayList col, String[] lin){
        setColunas(col);
        setLinhas(lin);
    }
    
    public ArrayList getColunas(){
        return colunas;
    }
    
    public void setColunas(ArrayList dados){
        colunas = dados;
    }
    
    public String[] getLinhas(){
        return linhas;
    }

    public void setLinhas(String[] nomes){
        linhas=nomes;
    }
    
    public int getRowCount(){
        return linhas.length; //conta a quantidade de caracteres no array da coluna
    }
    
    public int getColumnCount(){
        return colunas.size(); //conta o tamanho da array
    }
    
    public String getRowName(int numCol){
        return linhas[numCol];
    }
    
    public Object getValueAt(int numCol, int numLin){
        Object[] coluna = (Object[])getColunas().get(numCol);
        return coluna [numLin];
    }
     
    
}
