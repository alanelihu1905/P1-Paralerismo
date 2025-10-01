package src;
public class MainSemaforo {
    private static final int HILOS = 8;
    private static final int INCREMENTOS_POR_HILO = 100_000;

    public static void main(String[] args) throws InterruptedException {
        ContadorSemaforo contador = new ContadorSemaforo();
        Thread[] workers = new Thread[HILOS];

        long inicio = System.currentTimeMillis();

        for (int t = 0; t < HILOS; t++) {
            workers[t] = new Thread(() -> {
                for (int i = 0; i < INCREMENTOS_POR_HILO; i++) {
                    contador.incrementar();
                }
            }, "HiloSem-" + t);
            workers[t].start();
        }

        for (Thread w : workers) w.join();

        long fin = System.currentTimeMillis();
        int esperado = HILOS * INCREMENTOS_POR_HILO;
        int obtenido = contador.obtener();

        System.out.println("=== Ejecución con Semáforo ===");
        System.out.println("Esperado = " + esperado);
        System.out.println("Obtenido = " + obtenido);
        System.out.println("Tiempo   = " + (fin - inicio) + " ms");
    }
}
