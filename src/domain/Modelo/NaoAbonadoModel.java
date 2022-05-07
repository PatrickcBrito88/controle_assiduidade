
package domain.Modelo;

public class NaoAbonadoModel {
    private int idRepositorio;
    private String motivoNaoAbono;
    private String setorNaoAbonado;

    public int getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(int idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public String getMotivoNaoAbono() {
        return motivoNaoAbono;
    }

    public void setMotivoNaoAbono(String motivoNaoAbono) {
        this.motivoNaoAbono = motivoNaoAbono;
    }

    public String getSetorNaoAbonado() {
        return setorNaoAbonado;
    }

    public void setSetorNaoAbonado(String setorNaoAbonado) {
        this.setorNaoAbonado = setorNaoAbonado;
    }
    
}
