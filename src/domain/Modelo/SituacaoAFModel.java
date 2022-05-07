/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Modelo;

import domain_enums.EnumSituacao;


public class SituacaoAFModel {
    private String referencia;
    private EnumSituacao situacaoGerencia;
    private EnumSituacao situacaoDepartamento;
    private EnumSituacao situacaoDiretoria;
    private EnumSituacao situacaoRH;
    private EnumSituacao situacaoPresidencia;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public EnumSituacao getSituacaoGerencia() {
        return situacaoGerencia;
    }

    public void setSituacaoGerencia(EnumSituacao situacaoGerencia) {
        this.situacaoGerencia = situacaoGerencia;
    }

    public EnumSituacao getSituacaoDepartamento() {
        return situacaoDepartamento;
    }

    public void setSituacaoDepartamento(EnumSituacao situacaoDepartamento) {
        this.situacaoDepartamento = situacaoDepartamento;
    }

    public EnumSituacao getSituacaoDiretoria() {
        return situacaoDiretoria;
    }

    public void setSituacaoDiretoria(EnumSituacao situacaoDiretoria) {
        this.situacaoDiretoria = situacaoDiretoria;
    }

    public EnumSituacao getSituacaoRH() {
        return situacaoRH;
    }

    public void setSituacaoRH(EnumSituacao situacaoRH) {
        this.situacaoRH = situacaoRH;
    }

    public EnumSituacao getSituacaoPresidencia() {
        return situacaoPresidencia;
    }

    public void setSituacaoPresidencia(EnumSituacao situacaoPresidencia) {
        this.situacaoPresidencia = situacaoPresidencia;
    }

    
    
    
}
