package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBancoXAMPP {
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho="jdbc:mysql://localhost:3306/sistema_thais";
    private String usuario="root";
    private String senha="";
    public Connection conn;
    
    public void conexao(){
        
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConectaBancoXAMPP.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.setProperty("jdbc.Drivers", driver);
            conn=DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!!");
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
