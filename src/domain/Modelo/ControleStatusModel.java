
package domain.Modelo;

public class ControleStatusModel {
    private boolean sinal;
    private int contNaoAbonada;
    private boolean todosNaoAbonados;

    public boolean isSinal() {
        return sinal;
    }

    public void setSinal(boolean sinal) {
        this.sinal = sinal;
    }

    public int getContNaoAbonada() {
        return contNaoAbonada;
    }

    public void setContNaoAbonada(int contNaoAbonada) {
        this.contNaoAbonada = contNaoAbonada;
    }

    public boolean isTodosNaoAbonados() {
        return todosNaoAbonados;
    }

    public void setTodosNaoAbonados(boolean todosNaoAbonados) {
        this.todosNaoAbonados = todosNaoAbonados;
    }
    
    
}
