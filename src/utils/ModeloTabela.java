
package utils;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabela extends AbstractTableModel{
    
    private ArrayList linhas=null;
    private String [] colunas=null;
    
    public ModeloTabela(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);
    }
    
    public ArrayList getLinhas(){
        return linhas;
    }
    
    public void setLinhas(ArrayList dados){
        linhas = dados;
    }
    
    public String[] getColunas(){
        return colunas;
    }

    public void setColunas(String[] nomes){
        colunas=nomes;
    }
    
    public int getColumnCount(){
        return colunas.length; //conta a quantidade de caracteres no array da coluna
    }
    
    public int getRowCount(){
        return linhas.size(); //conta o tamanho da array
    }
    
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    
    public Object getValueAt(int numLin, int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha [numCol];
    }
     
    
}
