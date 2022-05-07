
package domain.Modelo;


public class DiretoriaModel {
    private int idDir;
    private String nome;
    private String sigla;
    private int presidencia;
    private String email;

   
    public int getIdDir() {
        return idDir;
    }

    
    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

   
    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
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
