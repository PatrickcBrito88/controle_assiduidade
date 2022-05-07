
package domain.Model.ControleTabelas;


public class MenuAdminPendenciasModel {
    private String nome;
    private String competencia;
    private int nEventos;
    private String dataElaboracao;
    private String setor;
    private int nEventosNaoAbonados;

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public int getnEventos() {
        return nEventos;
    }

    public void setnEventos(int nEventos) {
        this.nEventos = nEventos;
    }

    public String getDataElaboracao() {
        return dataElaboracao;
    }

    public void setDataElaboracao(String dataElaboracao) {
        this.dataElaboracao = dataElaboracao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getnEventosNaoAbonados() {
        return nEventosNaoAbonados;
    }

    public void setnEventosNaoAbonados(int nEventosNaoAbonados) {
        this.nEventosNaoAbonados = nEventosNaoAbonados;
    }
    
    
    
}
