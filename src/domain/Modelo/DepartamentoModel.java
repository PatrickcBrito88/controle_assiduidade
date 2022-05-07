
package domain.Modelo;


public class DepartamentoModel {
    private int idDep;
    private String nome;
    private int idDir;
    private String sigla;
    private int presidencia;
    private String email;

  
    public int getIdDep() {
        return idDep;
    }

   
    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

   
    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdDir() {
        return idDir;
    }

    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getPresidencia() {
        return presidencia;
    }

    public void setPresidencia(int presidencia) {
        this.presidencia = presidencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
