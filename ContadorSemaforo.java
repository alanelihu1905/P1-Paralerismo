package src;
import java.util.concurrent.Semaphore;

public class ContadorSemaforo {
    private int valor = 0;
    private final Semaphore mutex = new Semaphore(1, true); // binario y justo

    public void incrementar() {
        mutex.acquireUninterruptibly();
        try {
            valor++;
        } finally {
            mutex.release();
        }
    }

    public int obtener() {
        return valor;
    }
}
