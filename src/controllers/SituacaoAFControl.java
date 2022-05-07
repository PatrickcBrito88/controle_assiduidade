
package controllers;

import utils.ConectaBanco;
import domain_enums.EnumSituacao;
import domain.Modelo.SituacaoAFModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SituacaoAFControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<SituacaoAFModel> getSituacao (int mat){
        conecta.conexao();
        ArrayList<SituacaoAFModel> lista = new ArrayList();
        
        conecta.executaPesquisaSQL("Select * from AlteracaoFrequencia where funcionario_matfunc_af="+mat);
        try {
            conecta.rs.first();
            do{
             SituacaoAFModel situacao = new SituacaoAFModel();   
             situacao.setReferencia(conecta.rs.getString("Referencia_AF"));
             
             situacao.setSituacaoDepartamento(EnumSituacao.valueOf(conecta.rs.getString("SituacaoDepartamento_AF")));
             situacao.setSituacaoDiretoria(EnumSituacao.valueOf(conecta.rs.getString("SituacaoDiretoria_AF")));
             situacao.setSituacaoGerencia(EnumSituacao.valueOf(conecta.rs.getString("SituacaoGerencia_AF")));
             situacao.setSituacaoRH(EnumSituacao.valueOf(conecta.rs.getString("SituacaoRH_AF")));
             situacao.setSituacaoPresidencia(EnumSituacao.valueOf(conecta.rs.getString("SituacaoPresidencia_AF")));
             lista.add(situacao);                
            }while (conecta.rs.next());
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null,"SituacçãoAFControl/getSituação.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
}
