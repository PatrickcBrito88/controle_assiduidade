package report_code;

import utils.ConnectionFactory;
import utils.Relatorios;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

public class ImprimeRelatorio {

    public void ImprimeRelatorioFinal(int mat, String referencia, String lotacao) {

        InputStream inputStream = getClass().getResourceAsStream("/RelatorioPrincipal.jasper");
        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("Matricula", mat);
        parametros.put("Referencia", referencia);
        parametros.put("Lotação", lotacao);

        try {

            Relatorios relat4 = new Relatorios();
            relat4.openReport("RelatorioPrincipal", inputStream, parametros, ConnectionFactory.getConnection());
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        } catch (JRException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        }
    }
    
    public void ImprimeEspelho(int mat, String referencia, String lotacao) {

        InputStream inputStream = getClass().getResourceAsStream("/RelatorioPrincipal_Espelho.jasper");
        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("Matricula", mat);
        parametros.put("Referencia", referencia);
        parametros.put("Lotação", lotacao);

        try {

            Relatorios relat4 = new Relatorios();
            relat4.openReport("RelatorioPrincipal", inputStream, parametros, ConnectionFactory.getConnection());
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        } catch (JRException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        }
    }
    
    public void ImprimeEventosAlteracao (int mat, String referencia, String lotacao, int idNaoAbono) {

        InputStream inputStream = getClass().getResourceAsStream("/RelatorioUltimaAvaliacao.jasper");
        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("Matricula", mat);
        parametros.put("Referencia", referencia);
        parametros.put("Lotação", lotacao);
        parametros.put("idnaoabono", idNaoAbono);

        try {

            Relatorios relat4 = new Relatorios();
            relat4.openReport("RelatorioUltimaAvaliacao", inputStream, parametros, ConnectionFactory.getConnection());
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        } catch (JRException exc) {
            JOptionPane.showMessageDialog(null, "Erro relatorio, motivo: \n" + exc);
        }
    }
    
    

}
