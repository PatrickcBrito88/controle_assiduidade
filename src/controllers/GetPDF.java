/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import utils.BarraProgresso;
import utils.ConectaBanco;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class GetPDF {

    ConectaBanco conecta = new ConectaBanco();

    public boolean getArquivoPDF(int idRepositorio, JProgressBar barra) {
        boolean sinal = false;
        conecta.conexao();
        byte[] fileBytes;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra=" + idRepositorio);

        try {
            conecta.rs.first();
            
            TiposControle tipoControle = new TiposControle();
            fileBytes = conecta.rs.getBytes("Anexo_RA");
            String teste = conecta.rs.getString("Anexo_RA");
            String matricula = String.valueOf(conecta.rs.getInt("AlteracaoFrequencia_MatFunc_RA"));
            String tipos = tipoControle.getNomeTipo(conecta.rs.getInt("idTipo_RA"));
            String data = conecta.rs.getString("DataEvento_RA");
            String nomeArquivo = matricula + "_" + tipos + "_" + data + ".pdf";

            OutputStream targetFile;

            String caminho = System.getProperty("user.home") + "//downloads//" + nomeArquivo;

            try {
                targetFile = new FileOutputStream(caminho);

//                        "c://Teste//jdbc//newtest.pdf");
                try {
                    if (!teste.equals("")){
                    targetFile.write(fileBytes);
                    sinal = true;
                  
                    
                        BarraProgresso barraProgresso = new BarraProgresso(barra);
                        barraProgresso.load();
                        String caminhoArquivo = System.getProperty("user.home") + "\\downloads\\" + nomeArquivo;
                        File pdf = new File(caminhoArquivo);

                        Desktop.getDesktop().open(pdf);// função que abre o arquivo com o visualizador de anexo padrão
                    } else {
                        JOptionPane.showMessageDialog(null,"Este Evento não possui anexo!!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Este evento não possui anexo!");
                }

                try {
                    targetFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(GetPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(GetPDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Este evento não possui anexo!");
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

}
