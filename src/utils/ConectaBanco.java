package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBanco {
    
    public String caminhoDesenvolvimento="jdbc:mysql://localhost:3306/Servicos";
    public String userDesenvolvimento="root";
    public String passDesenvolvimento="";
    
    public String caminhoProd="jdbc:mysql://10.0.0.1:3306/Servicos";
    public String userProd="RecursosHumanos";
    public String passProd="123";        
    
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho=caminhoDesenvolvimento;
    private String usuario=userDesenvolvimento;
    private String senha=passDesenvolvimento;
    public Connection conn;
    
    //10.0.6.44:3306 //10.0.6.56 marco
    //sistemacompras senha ""
    //RecursosHumanos 123
    public void conexao(){
        
        try {
            try {
                try {
                    Class.forName(driver).newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Erro, motivo: \n"+ex);
            }
//            System.setProperty("jdbc.Drivers", driver);
            conn=DriverManager.getConnection(caminho, usuario, senha);
//            JOptionPane.showMessageDialog(null, "Conectado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexao/n"+ex);
        }
    }
    
    public void executaSQL(String sql){
        try {
            stm=conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
           int rs= stm.executeUpdate(sql);
//           JOptionPane.showMessageDialog(null, "ExcluÃ­do com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de ExecuÃ§Ã£o SQL! \n"+ex.getMessage());
        }
    }
    
    public void executaPesquisaSQL(String sql){
        try {
            stm=conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
           rs= stm.executeQuery(sql);
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de ExecuÃ§Ã£o SQL! \n"+ex.getMessage());
        }
    }
    public void desconecta(){
        try{
//            JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexao");            
        }
    }
}
