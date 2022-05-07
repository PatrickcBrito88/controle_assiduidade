package controllers;

import utils.ConectaBanco;
import domain.Modelo.AnalisaSituacaoModel;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.PerfilModel;

public class GetPerfil {

    ConectaBanco conecta = new ConectaBanco();
    
    
    public PerfilModel preencherPerfil(int mat) {
        HierarquiaModel h = new HierarquiaModel();
        PerfilModel p = new PerfilModel();
        CalculaHierarquia c = new CalculaHierarquia();
        FuncionarioControl f = new FuncionarioControl();
        h=c.getHierarquiaFuncionario(mat);
        String cargo=f.getCargo(mat);
        
         //##Verificação de Perfil
        //Funcionário Gerência
        if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()==999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioGerencia(true);
        }
        
//         //Funcionário Gercom
//         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
//                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()!=999)&&(cargo.equals("FUNCIONÁRIO"))){
//            p.setFuncionarioGercom(true);
//        }
        
        //Gerente Normal
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()==999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setGerente(true);
        }
         
           //Chefe Normal
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()!=999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setChefe(true);
        }
         
          //Gerente Gercom
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()!=999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setGerenteGercom(true);
        }
         
         //Funcionário Gercom
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()!=999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioGercom(true);
        }
         
          //Gerente Gercom
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(h.getIdPresidencia()!=999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setGerenteGercom(true);
        }
         
         //Funcionário Gerjur
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()==999)
                &&(h.getIdDiretoria()!=999)&&(h.getIdPresidencia()!=999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioGerjur(true);
        }
         
          //Gerente Gerjur
         if ((h.getIdGerencia()!=999)&&(h.getIdDepartamento()==999)
                &&(h.getIdDiretoria()!=999)&&(h.getIdPresidencia()!=999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setGerenteGerjur(true);
        }
         
           //Funcionário Departamento
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()!=999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioDpto(true);
        }
         
          //Funcionário DEPCI
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioDpci(true);
        }
         
          //Chefe DEPCI
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()!=999)
                &&(h.getIdDiretoria()==999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setChefeDepci(true);
        }
         
          //Funcionário Diretoria
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()==999)
                &&(h.getIdDiretoria()!=999)&&(cargo.equals("FUNCIONÁRIO"))){
            p.setFuncionarioDiretoria(true);
        }
         
           //Diretor
         if ((h.getIdGerencia()==999)&&(h.getIdDepartamento()==999)
                &&(h.getIdDiretoria()!=999)&&(!cargo.equals("FUNCIONÁRIO"))){
            p.setDiretor(true);
        }
        
        return p;
    }
    


}
