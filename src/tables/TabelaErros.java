
package tables;

import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import infrastructure_dto.VisaoTabelaDto;
import domain.Model.ControleTabelas.ErroModelTabela;
import domain.Modelo.ErroModel;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;


public class TabelaErros extends AbstractTableModel {

        private final String[] nomescolunas = {
        "Data de Registro",
        "Título",
        "Status"       
            
    };
    
    private final List<ErroModelTabela> mList;
    
    public TabelaErros (List<ErroModelTabela> pList){
        mList=pList;
        
    }

    public TabelaErros() {
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
                return mList.get(linha).getData();
            case 1:
                return mList.get(linha).getTitulo();       
             case 2: 
                return mList.get(linha).getStatus();
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
                return Date.class;
            case 1:
                return String.class;
            case 2:
                return JLabel.class;
          
        }
            return null;
    }
    
//    public VisaoTabelaAlteracaoModel getObjeto (int linha){
//        VisaoTabelaAlteracaoModel visaoLinha = new VisaoTabelaAlteracaoModel();
//        visaoLinha.setDataEvento(mList.get(linha).getDataEvento());
//        visaoLinha.setAprovacaoGerencia(mList.get(linha).());
//        visaoLinha.setAprovacaoDepartamento(mList.get(linha).isAprovacaoDepartamento());
//        visaoLinha.setAprovacaoDiretoria(mList.get(linha).isAprovacaoDiretoria());
//        visaoLinha.setRecebimentoRH(mList.get(linha).isRecebimentoRH());
//        visaoLinha.setAnexo(mList.get(linha).isAnexo());
//        
//        return visaoLinha;
//    }
    
    
    //FAZER METODOS PARA PERSONALIZAAR A TABLE
    }


    

