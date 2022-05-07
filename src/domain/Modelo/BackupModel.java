/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Modelo;

/**
 *
 * @author patri
 */
public class BackupModel {
    private String situacaoGerencia;
    private String situacaoDepartamento;
    private String situacaoDiretoria;
   private String situacaoPresidencia;
   private int mat;
   private String referencia;
   private String situacaoRH;

    public String getSituacaoGerencia() {
        return situacaoGerencia;
    }

    public void setSituacaoGerencia(String situacaoGerencia) {
        this.situacaoGerencia = situacaoGerencia;
    }

    public String getSituacaoDepartamento() {
        return situacaoDepartamento;
    }

    public void setSituacaoDepartamento(String situacaoDepartamento) {
        this.situacaoDepartamento = situacaoDepartamento;
    }

    public String getSituacaoDiretoria() {
        return situacaoDiretoria;
    }

    public void setSituacaoDiretoria(String situacaoDiretoria) {
        this.situacaoDiretoria = situacaoDiretoria;
    }

    public String getSituacaoPresidencia() {
        return situacaoPresidencia;
    }

    public void setSituacaoPresidencia(String situacaoPresidencia) {
        this.situacaoPresidencia = situacaoPresidencia;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSituacaoRH() {
        return situacaoRH;
    }

    public void setSituacaoRH(String situacaoRH) {
        this.situacaoRH = situacaoRH;
    }
   
   
}
