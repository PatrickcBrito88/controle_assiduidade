package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



        public class ConnectionFactory {
   
            private String driver = "com.mysql.jdbc.Driver";
            private String caminho="jdbc:mysql://10.0.0.1:3306/Servicos";
            
            //public String caminhonotebook="jdbc:mysql://localhost:3306/afericao_desempenho";
//            public String usernotebook="root";
//            public String senhanotebook="";
//    
//            public String caminhoMeuPc="jdbc:mysql://localhost:3306/afericao_desempenho";
//            public String userMeuPC="sistemacompras";
//            public String senhaMeuPC="";
//
//            public String caminhoMarco="jdbc:mysql://10.0.6.56:3306/afericao_desempenho";
//            public String userMarco ="RecursosHumanos";
//            public String senhaMarco="";

            static{
                
                try{
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }catch (ClassNotFoundException exc){
                    exc.printStackTrace();
                }
            }
    
    public static Connection getConnection(
            String url,
            String usuario,
            String senha) throws SQLException{

            return DriverManager.getConnection(url, usuario, senha);
    }

    public static Connection getConnection()throws SQLException{
        return ConnectionFactory.getConnection(
    "jdbc:mysql://10.0.0.248:3306/Servicos",
    "root",
    "");
        
//         "jdbc:mysql://10.0.0.248:3306/afericao_desempenho",
//    "RecursosHumanos",
//    "123");
   
    }

}

