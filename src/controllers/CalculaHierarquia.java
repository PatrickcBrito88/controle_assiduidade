
package controllers;

import utils.ConectaBanco;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.LocalIdModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CalculaHierarquia {
    
    ConectaBanco conecta = new ConectaBanco();
    HierarquiaModel hierarquiaModel = new HierarquiaModel();
    
    public HierarquiaModel getHierarquiaFuncionario (int id){
        //criar um model para preencher o iddiretoria, iddepartamento e idgerencia de cada funcionário
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+id);
        try {
            conecta.rs.first();
            hierarquiaModel.setIdDepartamento(conecta.rs.getInt("departamento_iddepartamento_f"));
            hierarquiaModel.setIdDiretoria(conecta.rs.getInt("diretoria_iddiretoria_f"));
            hierarquiaModel.setIdGerencia(conecta.rs.getInt("gerencia_idgerencia_f"));
            hierarquiaModel.setIdPresidencia(conecta.rs.getInt("Presidencia_idPresidencia_F"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CalculaHierarquia/PreenchendoHierarquia.\n"+ex);
        } finally {
            conecta.desconecta();
        }
                
        return hierarquiaModel;
    }
    
    public HierarquiaModel getHierarquiaFuncionarioAvaliacao (int id){
        //criar um model para preencher o iddiretoria, iddepartamento e idgerencia de cada funcionário
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+id);
        try {
            conecta.rs.first();
            hierarquiaModel.setIdDepartamento(conecta.rs.getInt("departamento_iddepartamento_f"));
            hierarquiaModel.setIdDiretoria(conecta.rs.getInt("diretoria_iddiretoria_f"));
            hierarquiaModel.setIdGerencia(conecta.rs.getInt("gerencia_idgerencia_f"));
            int idPresidencia=conecta.rs.getInt("presidencia_idpresidencia_f");
            if (idPresidencia==1){
            hierarquiaModel.setIdPresidencia(idPresidencia);
            } else {
                hierarquiaModel.setIdPresidencia(0);//Deixei 0 porque estava com zero para todos e funcionando. O erro era apenas na Diretoria. Testando
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CalculaHierarquia/PreenchendoHierarquia.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
        if ((hierarquiaModel.getIdGerencia()!=999)&&(hierarquiaModel.getIdDepartamento()!=999)){
             DepartamentoControl dE = new DepartamentoControl();
            hierarquiaModel.setIdDiretoria(dE.getDiretoria(hierarquiaModel.getIdDepartamento()));
        }
                
        return hierarquiaModel;
    }
 
     public HierarquiaModel getHierarquiaLocal (String local, int idLocal){
        //criar um model para preencher o iddiretoria, iddepartamento e idgerencia de cada funcionário
        conecta.conexao();
        
        
        //Gerencia
        if (local.equals("Gerência")){
            hierarquiaModel.setIdGerencia(idLocal);
        }
        
        if (local.equals("Departamento")){
            hierarquiaModel.setIdDepartamento(idLocal);
        }
        
        if (local.equals("Diretoria")){
            hierarquiaModel.setIdDiretoria(idLocal);
        }             
        return hierarquiaModel;
    }
     
     public LocalIdModel getIdLocalOriginal (int mat){
         conecta.conexao();
         HierarquiaModel h = new HierarquiaModel();
         h=getHierarquiaFuncionario(mat);
         LocalIdModel l = new LocalIdModel();
         
         if (h.getIdGerencia()!=999){//Se é diferente de 999 é porque é de gerência
             l.setLocal("Gerência");
             l.setId(h.getIdGerencia());
         }
         
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()!=999)){ //Se gerencia é 999 e departamento é diferente, significa que é departamento
         l.setLocal("Departamento");
         l.setId(h.getIdDepartamento());
         }
         
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()==999)&&(h.getIdDiretoria()!=999)){
             l.setLocal("Diretoria");
             l.setId(h.getIdDiretoria());
         }
         return l;
     }
    
    
    
}
