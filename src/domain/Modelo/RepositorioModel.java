/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Modelo;

import domain_enums.EnumSituacao;
import java.io.FileInputStream;
import java.sql.Date;

/**
 *
 * @author patri
 */
public class RepositorioModel {
    private int idRepositorio;
    private int idTipo;
    private int matFunc;
    private String referencia;    
    private EnumSituacao aprovacaoGerencia;
    private EnumSituacao aprovacaoDepartamento;
    private EnumSituacao aprovacaoDiretoria;
    private EnumSituacao recebimentoRH;
    private FileInputStream anexo;
    private String descricao;
    private Date dataEvento;
    private int idGerencia;
    private int idDepartamento;
    private int idDiretoria;

    
    public int getIdRepositorio() {
        return idRepositorio;
    }
    
    public void setIdRepositorio(int idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

   public int getMatFunc() {
        return matFunc;
    }

    public void setMatFunc(int matFunc) {
        this.matFunc = matFunc;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public FileInputStream getAnexo() {
        return anexo;
    }

    public void setAnexo(FileInputStream anexo) {
        this.anexo = anexo;
    }

    public EnumSituacao getAprovacaoGerencia() {
        return aprovacaoGerencia;
    }

    public void setAprovacaoGerencia(EnumSituacao aprovacaoGerencia) {
        this.aprovacaoGerencia = aprovacaoGerencia;
    }

    public EnumSituacao getAprovacaoDepartamento() {
        return aprovacaoDepartamento;
    }

    public void setAprovacaoDepartamento(EnumSituacao aprovacaoDepartamento) {
        this.aprovacaoDepartamento = aprovacaoDepartamento;
    }

    public EnumSituacao getAprovacaoDiretoria() {
        return aprovacaoDiretoria;
    }

    public void setAprovacaoDiretoria(EnumSituacao aprovacaoDiretoria) {
        this.aprovacaoDiretoria = aprovacaoDiretoria;
    }

    public EnumSituacao getRecebimentoRH() {
        return recebimentoRH;
    }

    public void setRecebimentoRH(EnumSituacao recebimentoRH) {
        this.recebimentoRH = recebimentoRH;
    }

    public int getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(int idGerencia) {
        this.idGerencia = idGerencia;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdDiretoria() {
        return idDiretoria;
    }

    public void setIdDiretoria(int idDiretoria) {
        this.idDiretoria = idDiretoria;
    }
}
