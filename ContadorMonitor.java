package src;
public class ContadorMonitor {
    private int valor = 0;

    public synchronized void incrementar() {
        valor++;
    }

    public synchronized int obtener() {
        return valor;
    }
}
