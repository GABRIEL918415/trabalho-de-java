package corrida;

import java.util.ArrayList;
import java.util.List;

public class Corrida {

    private static final double DISTANCIA_TOTAL = 500.0;
    private static final int NUMERO_DE_CARROS = 5;

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   LARGADA SIMULADOR DE CORRIDA DE CARROS ");
        System.out.println("==========================================");

        Podio podio = new Podio();
        List<Thread> threadsCorrida = new ArrayList<>();

        for (int i = 1; i <= NUMERO_DE_CARROS; i++) {
            Carro carro = new Carro("Carro " + i, DISTANCIA_TOTAL, podio);
            Thread threadCarro = new Thread(carro);
            threadsCorrida.add(threadCarro);
        }

        for (Thread thread : threadsCorrida) {
            thread.start();
        }

        for (Thread thread : threadsCorrida) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        podio.exibirPodio();
    }
}
