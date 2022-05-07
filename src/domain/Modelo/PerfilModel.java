
package domain.Modelo;


public class PerfilModel {
    private boolean funcionarioGerencia;
    private boolean gerente ;
    private boolean chefe;
    private boolean funcionarioDpto;
    private boolean diretor;
    private boolean funcionarioDiretoria;
    private boolean funcionarioGercom;
    private boolean GerenteGercom;
    private boolean funcionarioDpci;
    private boolean chefeDepci;
    private boolean funcionarioGerjur;
    private boolean gerenteGerjur;

    
    public PerfilModel(){
        this.GerenteGercom=false;
        this.chefe=false;
        this.chefeDepci=false;
        this.diretor=false;
        this.funcionarioDiretoria=false;
        this.funcionarioDpci=false;
        this.funcionarioDpto=false;
        this.funcionarioGercom=false;
        this.funcionarioGerencia=false;
        this.gerente=false;        
    }
    
    
    public boolean isFuncionarioGerencia() {
        return funcionarioGerencia;
    }

    public void setFuncionarioGerencia(boolean funcionarioGerencia) {
        this.funcionarioGerencia = funcionarioGerencia;
    }

    public boolean isGerente() {
        return gerente;
    }

    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }

    public boolean isChefe() {
        return chefe;
    }

    public void setChefe(boolean chefe) {
        this.chefe = chefe;
    }

    public boolean isFuncionarioDpto() {
        return funcionarioDpto;
    }

    public void setFuncionarioDpto(boolean funcionarioDpto) {
        this.funcionarioDpto = funcionarioDpto;
    }

    public boolean isDiretor() {
        return diretor;
    }

    public void setDiretor(boolean diretor) {
        this.diretor = diretor;
    }

    public boolean isFuncionarioDiretoria() {
        return funcionarioDiretoria;
    }

    public void setFuncionarioDiretoria(boolean funcionarioDiretoria) {
        this.funcionarioDiretoria = funcionarioDiretoria;
    }

    public boolean isFuncionarioGercom() {
        return funcionarioGercom;
    }

    public void setFuncionarioGercom(boolean funcionarioGercom) {
        this.funcionarioGercom = funcionarioGercom;
    }

    public boolean isGerenteGercom() {
        return GerenteGercom;
    }

    public void setGerenteGercom(boolean GerenteGercom) {
        this.GerenteGercom = GerenteGercom;
    }

    public boolean isFuncionarioDpci() {
        return funcionarioDpci;
    }

    public void setFuncionarioDpci(boolean funcionarioDpci) {
        this.funcionarioDpci = funcionarioDpci;
    }

    public boolean isChefeDepci() {
        return chefeDepci;
    }

    public void setChefeDepci(boolean chefeDepci) {
        this.chefeDepci = chefeDepci;
    }

    public boolean isFuncionarioGerjur() {
        return funcionarioGerjur;
    }

    public void setFuncionarioGerjur(boolean funcionarioGerjur) {
        this.funcionarioGerjur = funcionarioGerjur;
    }

    public boolean isGerenteGerjur() {
        return gerenteGerjur;
    }

    public void setGerenteGerjur(boolean gerenteGerjur) {
        this.gerenteGerjur = gerenteGerjur;
    }
    
    
}
