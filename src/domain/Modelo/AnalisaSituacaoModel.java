
package domain.Modelo;

import domain_enums.EnumSituacao;


public class AnalisaSituacaoModel {
    private int mat;
    private int idRepositorio;
    private EnumSituacao situacao;
    private String local;
    private String setor;
    private String motivoNaoAbonado;

    public int getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(int idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public EnumSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacao situacao) {
        this.situacao = situacao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getMotivoNaoAbonado() {
        return motivoNaoAbonado;
    }

    public void setMotivoNaoAbonado(String motivoNaoAbonado) {
        this.motivoNaoAbonado = motivoNaoAbonado;
    }
    
}
