/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import utils.ConectaBanco;
import domain.Modelo.DadosEmailModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author patrick
 */
public class DadosEmailControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public DadosEmailModel getDados (){
        conecta.conexao();
        DadosEmailModel dados = new DadosEmailModel();
        conecta.executaPesquisaSQL("Select * from dadosemail where id=1");
        try {
            conecta.rs.first();
            dados.setLogin(conecta.rs.getString("login_email"));
            dados.setSenha(conecta.rs.getString("senha_email"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar e-mail gerador.");
        } finally {
            conecta.desconecta();
        }
        return dados;        
    }
    
}
