
package domain.Model.ControleTabelas;

import domain.Modelo.RepositorioModel;
import java.sql.Date;
import javax.swing.JLabel;


public class TabelaResumoAlteracaoModel{
    
    private String dataEvento;
    private String tipo;
    private boolean descricaoBool;
    private boolean anexo;
    private JLabel aprovacao;
    private int idRepositorio;
    private String descricao;
    private String referencia;
    private boolean trava;

    public String getDataEvento() {
        return dataEvento;
        
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDescricao() {
        return descricaoBool;
    }

    public void setDescricao(boolean descricao) {
        this.descricaoBool = descricao;
    }

    public boolean isAnexo() {
        return anexo;
    }

    public void setAnexo(boolean anexo) {
        this.anexo = anexo;
    }

    public JLabel getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(JLabel aprovacao) {
        this.aprovacao = aprovacao;
    }

    public int getId() {
        return idRepositorio;
    }

    public void setId(int id) {
        this.idRepositorio = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isTrava() {
        return trava;
    }

    public void setTrava(boolean trava) {
        this.trava = trava;
    }
    
}
