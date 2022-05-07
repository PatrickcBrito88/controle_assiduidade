/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utils.ConectaBanco;
import domain.Modelo.FuncionarioModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ValidaLogin {
    
    FuncionarioModel funcionario = new FuncionarioModel();
    ConectaBanco conecta = new ConectaBanco();
    
    public boolean logarDefinitiva (int mat, String senha){
       conecta.conexao();
       conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+mat);
       String senhaBanco="";
       boolean sinal=false;
        try {
            conecta.rs.first();
            senhaBanco=conecta.rs.getString("senha_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ValidaLogin/Logar.\n"+ex);       
        } finally {
            conecta.desconecta();
        }
        
        if (senha.equals(senhaBanco)){
            sinal= true;
        }
        return sinal;
    }
    
    public boolean logarProvisoria (int mat, String senha){
       conecta.conexao();
       conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+mat);
       String senhaBanco="";
       boolean sinal=false;
        try {
            conecta.rs.first();
            senhaBanco=conecta.rs.getString("senha_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ValidaLogin/LogarProvisoria.\n"+ex);       
        } finally {
            conecta.desconecta();
        }
        
        if (senha.equals(senhaBanco)){
            sinal= true;
        }
        return sinal;
    }
    
    public boolean matriculaExiste (int mat){
        conecta.conexao();
        boolean sinal=false;
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+mat);
        try {
            if (conecta.rs.first()){
                sinal=true;
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao validar matrícula.");
        } finally {
            conecta.desconecta();
        }
        return sinal;        
    }
    
    
    
}
