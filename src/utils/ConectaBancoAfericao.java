package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBancoAfericao {
    
    public String caminhonotebook="jdbc:mysql://localhost:3306/Afericao_desempenho";
    public String usernotebook="root";
    public String senhanotebook="";
    
    public String caminhoMeuPc="jdbc:mysql://localhost:3306/Afericao_desempenho";
    public String userMeuPC="root";
    public String senhaMeuPC="";
    
    public String caminhoservidor="jdbc:mysql://10.0.0.248:3306/Afericao_desempenho";
    public String userservidor="RecursosHumanos";
    public String senhaservidor="123";        
    
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho=caminhonotebook;
    private String usuario=usernotebook;
    private String senha=senhanotebook;
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
                    Logger.getLogger(ConectaBancoAfericao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ConectaBancoAfericao.class.getName()).log(Level.SEVERE, null, ex);
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
