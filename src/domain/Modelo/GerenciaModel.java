
package domain.Modelo;


public class GerenciaModel {
    private int idGerencia;
    private String nome;
    private int departamento;
    private int diretoria;
    private String sigla;
    private int presidencia;
    private String email;

   
    public int getIdGerencia() {
        return idGerencia;
    }

   
    public void setIdGerencia(int idGerencia) {
        this.idGerencia = idGerencia;
    }

   
    public String getNome() {
        return nome;
    }

   
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getDiretoria() {
        return diretoria;
    }

    public void setDiretoria(int diretoria) {
        this.diretoria = diretoria;
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
