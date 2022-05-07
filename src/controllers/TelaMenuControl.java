package controllers;

import utils.ConectaBanco;
import domain.Modelo.TelaMenuModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaMenuControl {

    ConectaBanco conecta = new ConectaBanco();

    public TelaMenuModel preencheMenu(String local, String sigla) {
        conecta.conexao();
        TelaMenuModel t = new TelaMenuModel();
        AlteracaoFrequenciaControl aF = new AlteracaoFrequenciaControl();
        
        if (local.equals("Gerência")) {//Quem está logando no sistema
            GerenciaControl g = new GerenciaControl();
            int idGerencia = g.getIdGerencia(sigla);
           
            //Saber quantas estão para avaliar
            int numeroAvaliar=aF.QtdeAvaliarGerencia(idGerencia);
            t.setAlteracoesAvaliar(numeroAvaliar);
            
            //Saber quantas foram avaliadas
            int numeroAvaliadas=aF.QtdeAvaliadosGerencia(idGerencia);
            t.setAlteracoesAvaliadas(numeroAvaliadas);
            
            //Saber quantidade de funcionários
          int numeroFuncionarios= g.getNumeroFuncionariosGerencia(idGerencia);
          t.setTotalFuncionarios(numeroFuncionarios);
          
          //setor
          String nome=g.getNome(idGerencia);
          t.setSetor(nome);
          
        }//fim gerência
        
         if (local.equals("Departamento")) {//Quem está logando no sistema
            DepartamentoControl dE = new DepartamentoControl();
            int idDepartamento = dE.getIdDepartamento(sigla);
           
            //Saber quantas estão para avaliar
            int numeroAvaliar=aF.QtdeAvaliarDepartamento(idDepartamento);
            t.setAlteracoesAvaliar(numeroAvaliar);
            
            //Saber quantas foram avaliadas
            int numeroAvaliadas=aF.QtdeAvaliadosDepartamento(idDepartamento);
            t.setAlteracoesAvaliadas(numeroAvaliadas);
            
            //Saber quantidade de funcionários
          int numeroFuncionarios= dE.getNumeroFuncionariosDepartamento(idDepartamento);
          t.setTotalFuncionarios(numeroFuncionarios);
          
          //setor
          String nome=dE.getNome(idDepartamento);
          t.setSetor(nome);
          
        }//fim departamento
         
          if (local.equals("Diretoria")) {//Quem está logando no sistema
            DiretoriaControl dI = new DiretoriaControl();
            int idDiretoria = dI.getIdDiretoria(sigla);
           
            //Saber quantas estão para avaliar
            int numeroAvaliar=aF.QtdeAvaliarDiretoria(idDiretoria);
            t.setAlteracoesAvaliar(numeroAvaliar);
            
            //Saber quantas foram avaliadas
            int numeroAvaliadas=aF.QtdeAvaliadosDiretoria(idDiretoria);
            t.setAlteracoesAvaliadas(numeroAvaliadas);
            
            //Saber quantidade de funcionários
          int numeroFuncionarios= dI.getNumeroFuncionariosDiretoria(idDiretoria);
          t.setTotalFuncionarios(numeroFuncionarios);
          
          //setor
          String nome=dI.getNome(idDiretoria);
          t.setSetor(nome);
          
        }//fim diretoria
          
          if (local.equals("Presidência")) {//Quem está logando no sistema
            PresidenciaControl p = new PresidenciaControl();
         
           
            //Saber quantas estão para avaliar
            int numeroAvaliar=aF.QtdeAvaliarPresidencia();
            t.setAlteracoesAvaliar(numeroAvaliar);
            
            //Saber quantas foram avaliadas
            int numeroAvaliadas=aF.QtdeAvaliadosPresidencia();
            t.setAlteracoesAvaliadas(numeroAvaliadas);
            
            //Saber quantidade de funcionários
          int numeroFuncionarios= p.getNumeroFuncionariosPresidencia();
          t.setTotalFuncionarios(numeroFuncionarios);
          
          //setor
         t.setSetor("Presidência");
          
        }//fim presidência

        return t;
    }

}
