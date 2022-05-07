package infrastructure_dto;


import domain.Model.ControleTabelas.TabelaVisaoAlteracaoModel;
import java.util.Date;
import javax.swing.JLabel;


public class VisaoTabelaDto {
    private String Referencia;
    private JLabel aprovacaoGerencia;
    private JLabel aprovacaoDepartamento;
    private JLabel aprovacaoDiretoria;
    private JLabel recebimentoRH;
    private JLabel aprovacaoPresidencia;
    private JLabel aprovacaoGenerica;
   
    
    public JLabel getAprovacaoGerencia() {
        return aprovacaoGerencia;
    }

    public void setAprovacaoGerencia(JLabel aprovacaoGerencia) {
        this.aprovacaoGerencia = aprovacaoGerencia;
    }

    public JLabel getAprovacaoDepartamento() {
        return aprovacaoDepartamento;
    }

    public void setAprovacaoDepartamento(JLabel aprovacaoDepartamento) {
        this.aprovacaoDepartamento = aprovacaoDepartamento;
    }

    public JLabel getAprovacaoDiretoria() {
        return aprovacaoDiretoria;
    }

    public void setAprovacaoDiretoria(JLabel aprovacaoDiretoria) {
        this.aprovacaoDiretoria = aprovacaoDiretoria;
    }

    public JLabel getRecebimentoRH() {
        return recebimentoRH;
    }

    public void setRecebimentoRH(JLabel recebimentoRH) {
        this.recebimentoRH = recebimentoRH;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public JLabel getAprovacaoPresidencia() {
        return aprovacaoPresidencia;
    }

    public void setAprovacaoPresidencia(JLabel aprovacaoPresidencia) {
        this.aprovacaoPresidencia = aprovacaoPresidencia;
    }

    public JLabel getAprovacaoGenerica() {
        return aprovacaoGenerica;
    }

    public void setAprovacaoGenerica(JLabel aprovacaoGenerica) {
        this.aprovacaoGenerica = aprovacaoGenerica;
    }
    
    
    
    
}
