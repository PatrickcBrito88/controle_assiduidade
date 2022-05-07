
package tables;

import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import infrastructure_dto.VisaoTabelaDto;
import domain.Model.ControleTabelas.MenuAdminPendenciasModel;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;


public class TabelaMenuAdminPendencia extends AbstractTableModel {

    private final String[] nomescolunas = {
        
        "Nome",
        "Competência",
        "Setor",
        "Eventos Pendentes",
        "Data Elaboração"
            
            
    };
    
    private final List<MenuAdminPendenciasModel> mList;
    
    public TabelaMenuAdminPendencia (List<MenuAdminPendenciasModel> pList){
        mList=pList;
        
    }

    public TabelaMenuAdminPendencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public int getRowCount() {
        if (mList==null){
            return 0;
        } else {
            return mList.size();
        }
    }

    @Override
    public int getColumnCount() {
        return nomescolunas.length;
    }
    
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna){
            
            case 0:
                return mList.get(linha).getNome();
            case 1:
                return mList.get(linha).getCompetencia(); 
                case 2:
                return mList.get(linha).getSetor();                
            case 3:
                return mList.get(linha).getnEventos();
             case 4:
              return mList.get(linha).getDataElaboracao();
        }   
         return 0;
    }
    
    @Override
    public String getColumnName (int indice){
        return nomescolunas[indice];
    }
    
    public Class getColClass (int coluna){
        switch (coluna){
            
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
        }
            return null;
    }
    

    }


    

